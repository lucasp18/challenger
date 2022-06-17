package com.bookcomet.usecase;

import com.bookcomet.entity.BookInventory;

public interface RemoveQuantityBookInventory {
	
	
	public BookInventory removeInventory(Long id, Integer newQuantity);

}
