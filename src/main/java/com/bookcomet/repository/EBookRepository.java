package com.bookcomet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookcomet.entity.EBook;

public interface EBookRepository extends JpaRepository<EBook, Long> {

}
