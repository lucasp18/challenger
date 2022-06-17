package com.bookcomet.usecase;

import java.util.List;

import com.bookcomet.entity.Book;

public interface FindAuthorAndPublisherUseCase {
	
	
	public List<Book> execute(String author, String publisher);

}
