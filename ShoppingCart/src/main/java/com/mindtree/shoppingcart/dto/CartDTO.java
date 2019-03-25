package com.mindtree.shoppingcart.dto;

import java.util.Set;

import com.mindtree.shoppingcart.entity.User;
import com.mindtree.shoppingcart.validators.UserExists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartDTO {

	@UserExists
	private User user;
	private long cartId;
	private Set<ProductDTO> products;
	private double total;
}
