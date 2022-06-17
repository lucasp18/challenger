package com.bookcomet.business;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bookcomet.entity.BookInventory;
import com.bookcomet.exception.BusinessException;

@Component
public class BookInventoryWithQuantitityPositiveImpl implements BookInventoryWithQuantitityPositive {

	@Override
	public void execute(BookInventory bookInventory) {
		
		if(bookInventory == null) {
			return;
		}
		
		if(bookInventory.getQuantity() < 0) {
			throw new BusinessException("quantity inventory need positive value", HttpStatus.FORBIDDEN);
			
		}		
		
	}
}
