package com.mindtree.shoppingcart.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {

	private long cartId;
	private List<ProductDTO> products;
	private double total;
}
