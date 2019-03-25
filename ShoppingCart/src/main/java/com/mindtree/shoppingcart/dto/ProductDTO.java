package com.mindtree.shoppingcart.dto;

import javax.validation.constraints.Min;
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
	private Integer productId;
	private String name;
	private double price;
	@Min(value = 0L, message = "{product.quantity.positive}")
	private int quantity;
	
	public double getSubtotal() {
		return price * quantity;
	}
}
