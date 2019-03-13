package com.mindtree.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

	private int productId;
	private String name;
	private double price;
	private int quantity;
	
	public double getSubtotal() {
		return price * quantity;
	}
}
