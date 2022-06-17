package com.bookcomet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookcomet.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b WHERE b.name = ?1")
	public List<Book> duplicateBookAndAuthor(String name);
	
	@Query("SELECT b FROM Book b JOIN b.authors authors WHERE b.publisher = ?1 AND authors.name = ?2")
	public List<Book> findPublisherAuthor(String name, String author);
		
	@Query("SELECT DISTINCT b FROM Book b JOIN b.authors authors WHERE b.name = ?1 AND authors.name IN ?2")
	public List<Book> duplicateBookAndAuthor(String name, List<String> authors);
	
	
	@Query("SELECT DISTINCT b FROM Book b JOIN b.authors authors WHERE authors.name IN ?1")
	public List<Book> findBooks(List<String> authors);
	
}
