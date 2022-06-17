package com.bookcomet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookcomet.entity.BookInventory;

@Repository
public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {

}
