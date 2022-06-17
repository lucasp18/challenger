package com.bookcomet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookcomet.entity.Book;
import com.bookcomet.repository.BookRepository;
import com.bookcomet.usecase.CreateBookOrBookInventory;
import com.bookcomet.usecase.CreateBookOrBookInventoryImpl;
import com.bookcomet.usecase.DeleteBookUseCase;
import com.bookcomet.usecase.DeleteBookUseCaseImpl;
import com.bookcomet.usecase.FindAuthorAndPublisherUseCase;
import com.bookcomet.usecase.FindAuthorAndPublisherUseCaseImpl;
import com.bookcomet.usecase.UpdateBookUseCase;
import com.bookcomet.usecase.UpdateBookUseCaseImpl;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;
		
	@Autowired 
	private final CreateBookOrBookInventory create;
	
	@Autowired
	private final DeleteBookUseCase deleteBookUseCase;
	
	@Autowired
	private final FindAuthorAndPublisherUseCase findAuthorAndPublisherUseCase;
		
	@Autowired
	private final UpdateBookUseCase updateBookUseCase; 
	
	public BookController(DeleteBookUseCaseImpl d, FindAuthorAndPublisherUseCaseImpl f, UpdateBookUseCaseImpl u, CreateBookOrBookInventoryImpl c) {
		this.deleteBookUseCase = d;
		this.findAuthorAndPublisherUseCase = f;
		this.updateBookUseCase = u;
		this.create = c;
	}
	
	@GetMapping("/book")
	public List<Book> all() {		
		return bookRepository.findAll();
	}
	 	 
	@PostMapping("/book")
	public Book newBook(@RequestBody Book newBook) {		
		return create.create(newBook);		
	}
	 	 
	@PutMapping("/book/{id}")
	Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {	   
	   return this.updateBookUseCase.execute(id, newBook);
	}
	 	 
	@DeleteMapping("/book/{id}")
	void deleteBook(@PathVariable Long id) {
	   this.deleteBookUseCase.execute(id);
	}
		
	@GetMapping("/find/{author}/{publisher}")
	public List<Book> find(@PathVariable String author, @PathVariable String publisher ) {		
		return this.findAuthorAndPublisherUseCase.execute(author, publisher);				
	}

}
