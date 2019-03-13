package com.mindtree.shoppingcart.service;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.exception.ApplicationException;

public interface CartService {

	public CartDTO getCartDetails(String cartId) throws ApplicationException;
}
