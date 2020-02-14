package com.mindtree.shoppingcart.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductDTO {

	@NotNull(message="{product.id.required}")
	private Long productId;
	private String name;
	private double price;
	private int quantity;
	private String additionalDetail;
	
	public double getSubtotal() {
		return price * quantity;
	}
}
