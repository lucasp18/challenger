package com.bookcomet.usecase;

import com.bookcomet.entity.BookInventory;

public interface AddQuantityBookInventory {
		
	public BookInventory addInventory(Long id, Integer newQuantity);

}
