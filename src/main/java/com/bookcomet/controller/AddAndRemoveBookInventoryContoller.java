package com.bookcomet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bookcomet.entity.BookInventory;
import com.bookcomet.usecase.AddQuantityBookInventory;
import com.bookcomet.usecase.AddQuantityBookInventoryImpl;
import com.bookcomet.usecase.RemoveQuantityBookInventory;
import com.bookcomet.usecase.RemoveQuantityBookInventoryImpl;

@RestController
public class AddAndRemoveBookInventoryContoller {
	
	@Autowired
	private final AddQuantityBookInventory addQuantityBookInventory;	
	@Autowired
	private final RemoveQuantityBookInventory removeQuantityBookInventory;
	
	public AddAndRemoveBookInventoryContoller(AddQuantityBookInventoryImpl a, RemoveQuantityBookInventoryImpl r) {
		this.addQuantityBookInventory = a;
		this.removeQuantityBookInventory = r;
	}
		
	@GetMapping("/addQuantity/{id}/{newQuantity}")
	public BookInventory add(@PathVariable Long id, @PathVariable Integer newQuantity) {
		return this.addQuantityBookInventory.addInventory(id, newQuantity);
	}
		
	@GetMapping("/removeQuantity/{id}/{newQuantity}")
	public BookInventory remove(@PathVariable Long id, @PathVariable Integer newQuantity) {
		return this.removeQuantityBookInventory.removeInventory(id, newQuantity);
	}
}
