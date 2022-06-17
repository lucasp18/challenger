package com.bookcomet.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Book {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(
			  name = "books_authors", 
			  joinColumns = @JoinColumn(name = "book_id"), 
			  inverseJoinColumns = @JoinColumn(name = "author_id")) 
	private List<Author> authors;
		
	private String publisher;
	
	private Integer yearOfPublication;

	private String sumary;
		
	@OneToOne
	@JoinColumn(name="id_ebook")
	private EBook ebook;
	
	@OneToOne
	@JoinColumn(name="id_bookInventory")
	private BookInventory bookInventory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(Integer yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getSumary() {
		return sumary;
	}

	public void setSumary(String sumary) {
		this.sumary = sumary;
	}

	public EBook getEbook() {
		return ebook;
	}

	public void setEbook(EBook ebook) {
		this.ebook = ebook;
	}

	public BookInventory getBookInventory() {
		return bookInventory;
	}

	public void setBookInventory(BookInventory bookInventory) {
		this.bookInventory = bookInventory;
	}

}
