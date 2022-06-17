package com.bookcomet.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookcomet.entity.Book;
import com.bookcomet.exception.BusinessException;
import com.bookcomet.repository.BookRepository;

@Service
public class DeleteBookUseCaseImpl implements DeleteBookUseCase{

	@Autowired
	private BookRepository bookRepository;
				
	public void execute(long id) {
		Book book = this.bookRepository.findById(id).orElse(null);

		if(book == null) {
			throw new BusinessException("the book not found", HttpStatus.NOT_FOUND);
		}
		
		if(book.getBookInventory() != null && book.getBookInventory().getQuantity() > 0) {
			throw new BusinessException("the book have no quantity 0 in some book inventory", HttpStatus.FORBIDDEN);
		}		
	
		this.bookRepository.deleteById(id);
	
	}
}
