package com.bookcomet.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookcomet.entity.Author;
import com.bookcomet.entity.Book;
import com.bookcomet.exception.BusinessException;
import com.bookcomet.repository.BookRepository;

@Service
public class UniqueNameBookAndAuthorImpl implements UniqueNameBookAndAuthor {
	
	@Autowired
	private BookRepository  bookRepository;
	
	@Override
	public void execute(String name, List<Author> authores) {
	
	    final Set<String> set1 = new HashSet<>();
	         
	    for (Author author : authores) {
	    	if (!set1.add(author.getName())) {
	    		throw new BusinessException("duplicate author and name book", HttpStatus.FORBIDDEN);
	        }
	    }
	    
	    List<Book> listBooks = this.bookRepository.duplicateBookAndAuthor(name);
	    
	    List<Author> authorsOfBook = new ArrayList<Author>();
	    List<Author> authorsTest =  new ArrayList<Author>();
	    for (Book book : listBooks) {
			
	    	authorsOfBook.clear();
	    	authorsOfBook.addAll(book.getAuthors());
	    	
	    	authorsTest.clear();
	    	authorsTest.addAll(authorsOfBook);
	    	if(authorsOfBook.size() == authores.size()) {
	    		for (Author author : authores) {
					for (Author author2 : authorsOfBook) {
						if(author.getName().equals(author2.getName())) {
							authorsTest.remove(0);
						}
					}	    			
				}
	    		
	    		if(authorsTest.size() == 0) {
	    			throw new BusinessException("already the book with same name and authors ", HttpStatus.FORBIDDEN);
	    		}
	    	}
	    	
		}
		
	}
	
}
