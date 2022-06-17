package com.bookcomet.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bookcomet.entity.BookInventory;
import com.bookcomet.exception.BusinessException;
import com.bookcomet.repository.BookInventoryRepository;

@Component
public class AddQuantityBookInventoryImpl implements AddQuantityBookInventory{

	@Autowired
	private BookInventoryRepository bookInventoryRepository;
	
	
	public AddQuantityBookInventoryImpl() {
		
	}
	
	public BookInventory addInventory(Long id, Integer newQuantity) {
		BookInventory bookInventory = this.bookInventoryRepository.findById(id).orElse(null);
		
		if(bookInventory == null) {
			throw new BusinessException("The bookInventory not found ", HttpStatus.NOT_FOUND);
		}
		
		if(newQuantity < 0) {
			throw new BusinessException("The new quantity must positive value", HttpStatus.FORBIDDEN);
		}
		
		bookInventory.setQuantity(bookInventory.getQuantity() + newQuantity);
		
		return this.bookInventoryRepository.save(bookInventory);
	}
	
}
