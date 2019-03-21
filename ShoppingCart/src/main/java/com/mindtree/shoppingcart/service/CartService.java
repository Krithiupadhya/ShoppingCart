package com.mindtree.shoppingcart.service;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.model.Cart;

public interface CartService {

	public CartDTO getCartDetails(String cartId);
	public CartDTO addCartDetails(CartDTO cartDTO);
}
