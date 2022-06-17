package com.bookcomet.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookcomet.entity.Book;
import com.bookcomet.repository.BookRepository;

@Component
public class FindAuthorAndPublisherUseCaseImpl implements FindAuthorAndPublisherUseCase {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public List<Book> execute(String author, String publisher){
		return this.bookRepository.findPublisherAuthor(publisher, author);
		
		
	}

}
