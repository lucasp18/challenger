package com.bookcomet.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookcomet.business.BookInventoryWithQuantitityPositive;
import com.bookcomet.business.BookInventoryWithQuantitityPositiveImpl;
import com.bookcomet.business.UniqueNameBookAndAuthor;
import com.bookcomet.business.UniqueNameBookAndAuthorImpl;
import com.bookcomet.entity.Author;
import com.bookcomet.entity.Book;
import com.bookcomet.entity.BookInventory;
import com.bookcomet.repository.AuthorRepository;
import com.bookcomet.repository.BookInventoryRepository;
import com.bookcomet.repository.BookRepository;
import com.bookcomet.repository.EBookRepository;

@Component
public class CreateBookOrBookInventoryImpl implements CreateBookOrBookInventory {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookInventoryRepository bookInventoryRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private EBookRepository eBookRepository;
	
	private final BookInventoryWithQuantitityPositive bookInventoryWithQuantitityPositiveImpl; 
	
	private final UniqueNameBookAndAuthor uniqueNameBookAndAuthor;
	
	public CreateBookOrBookInventoryImpl(UniqueNameBookAndAuthorImpl u, BookInventoryWithQuantitityPositiveImpl b) {
		this.uniqueNameBookAndAuthor = u;
		this.bookInventoryWithQuantitityPositiveImpl = b;
	}

	public Book create(Book book) {
		book.setName(book.getName().toUpperCase());
		book.getAuthors().forEach(b -> {
			b.setName(b.getName().toUpperCase());
		});
		
		
		this.bookInventoryWithQuantitityPositiveImpl.execute(book.getBookInventory());
		this.uniqueNameBookAndAuthor.execute(book.getName(), book.getAuthors());
				
		List<String> names = book.getAuthors().stream().map(m -> m.getName()).collect(Collectors.toList());
		
		List<Author> authors = this.authorRepository.namesIn(names);
		
		if(authors.size() == 0) {
			authors = this.authorRepository.saveAll(book.getAuthors());
			if(book.getEbook() != null) {
				book.setEbook(this.eBookRepository.save(book.getEbook()));
			}
			
			if(book.getBookInventory()!= null) {
				BookInventory bookInventory = this.bookInventoryRepository.save(book.getBookInventory());
				book.setBookInventory(bookInventory);
			}
			
			book.setAuthors(authors);
			return this.bookRepository.save(book);
		}
		
		List<Author> restAuthor = new ArrayList<Author>(book.getAuthors());
		
		for (Author author : authors) {
			for (int i = 0; i < book.getAuthors().size()-1; i++) {
				if(book.getAuthors().get(i).getName().equals(author.getName())) {
					restAuthor.remove(i);
				}
			}
		}
		
		List<Author> savedResAuthors = this.authorRepository.saveAll(restAuthor);
		savedResAuthors.addAll(authors);
		
		book.setBookInventory(this.bookInventoryRepository.save(book.getBookInventory()));
		book.setAuthors(savedResAuthors);
		
		if(book.getEbook() != null) {
			book.setEbook(this.eBookRepository.save(book.getEbook()));
		}
		
		return this.bookRepository.save(book);
			
	}
		
	
}
