package com.bookcomet.usecase;

import com.bookcomet.entity.Book;

public interface UpdateBookUseCase {

	public Book execute(Long id, Book book);

	
}
