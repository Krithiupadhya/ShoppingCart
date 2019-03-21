package com.mindtree.shoppingcart.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDTO {

	@NotNull(message="{product.id.required}")
	private int productId;
	private String name;
	private double price;
	@NotNull(message="{product.quantity.required}")
	private int quantity;
	
	public double getSubtotal() {
		return price * quantity;
	}
}
