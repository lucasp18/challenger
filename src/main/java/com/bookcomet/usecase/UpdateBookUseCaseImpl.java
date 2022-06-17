package com.bookcomet.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bookcomet.business.BookInventoryWithQuantitityPositive;
import com.bookcomet.business.BookInventoryWithQuantitityPositiveImpl;
import com.bookcomet.entity.Author;
import com.bookcomet.entity.Book;
import com.bookcomet.exception.BusinessException;
import com.bookcomet.repository.AuthorRepository;
import com.bookcomet.repository.BookInventoryRepository;
import com.bookcomet.repository.BookRepository;
import com.bookcomet.repository.EBookRepository;

@Component
public class UpdateBookUseCaseImpl implements UpdateBookUseCase{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookInventoryRepository bookInventoryRepository;
	
	@Autowired
	private EBookRepository eBookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookInventoryWithQuantitityPositive bookInventoryWithQuantitityPositive;
	
	
	public UpdateBookUseCaseImpl(BookInventoryWithQuantitityPositiveImpl b) {
		this.bookInventoryWithQuantitityPositive = b;
	}
		
	@Override
	public Book execute(Long id, Book book) {
		
		Book bookFinded = this.bookRepository.findById(id).orElse(null);
		
		if(bookFinded == null) {
			throw new BusinessException("The book not found", HttpStatus.NOT_FOUND);
		}
		
		if(book.getBookInventory() !=  null) {
			this.bookInventoryWithQuantitityPositive.execute(book.getBookInventory());
			book.setBookInventory(this.bookInventoryRepository.save(book.getBookInventory()));	
		}else {			
			book.setBookInventory(null);
		}
		
		List<String> names = book.getAuthors().stream().map(m -> m.getName().toUpperCase()).collect(Collectors.toList());
		
		List<Author> authors = this.authorRepository.namesIn(names);
		List<Author> newAuthors = new ArrayList<Author>();
		//save when the authores exists in database
		if(authors.size() == names.size()) {
			
			List<String> names1 = authors.stream().map(m -> m.getName().toUpperCase()).collect(Collectors.toList());
			
			List<Book> books = this.bookRepository.findBooks(names1);
			for (Book book2 : books) {
				if(book2.getName().equals(book.getName())) {
					List<String> names2 = book2.getAuthors().stream().map(m -> m.getName().toUpperCase()).collect(Collectors.toList());
					if(names2.size() == names1.size()) {
						names2.removeAll(names1);
						if(names2.size() == 0 && book2.getId() != id) {
							throw new BusinessException("The book already exists with same authors", HttpStatus.FORBIDDEN);
						}
					}					
					
				}
			}
			
			book.setAuthors(authors);
		}else if(authors.size() ==0 ) {						
			book.setAuthors(this.authorRepository.saveAll(book.getAuthors()));
		}else {
			List<String> namesRe = authors.stream().map(m -> m.getName().toUpperCase()).collect(Collectors.toList());
			names.removeAll(namesRe);
			for (String name : names) {
				Author au = new Author();
				au.setName(name);
				newAuthors.add(au);
			}			
			
			newAuthors = this.authorRepository.saveAll(newAuthors);
			newAuthors.addAll(authors);
			book.setAuthors(newAuthors);
		}
		
		
		book.setBookInventory(this.bookInventoryRepository.save(book.getBookInventory()));
		book.setEbook(this.eBookRepository.save(book.getEbook()));
		book.setId(id);
		
		return this.bookRepository.save(book);
	}	

}
