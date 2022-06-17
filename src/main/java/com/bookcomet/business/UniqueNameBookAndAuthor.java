package com.bookcomet.business;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bookcomet.entity.Author;

@Component
public interface UniqueNameBookAndAuthor {
	
	public void execute(String name, List<Author> autores);

}
