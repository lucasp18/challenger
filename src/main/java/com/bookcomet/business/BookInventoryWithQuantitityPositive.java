package com.bookcomet.business;

import org.springframework.stereotype.Component;

import com.bookcomet.entity.BookInventory;
@Component
public interface BookInventoryWithQuantitityPositive {

	
	public void execute(BookInventory bookInventory);
}
