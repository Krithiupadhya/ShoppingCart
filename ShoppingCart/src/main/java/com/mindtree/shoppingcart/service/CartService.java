package com.mindtree.shoppingcart.service;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;

public interface CartService {

	public CartDTO getCartDetails(String cartId);
	public CartDTO addCartDetails(CartDTO cartDTO);
	public CartDTO updateProductInCart(String cartItemId, ProductDTO productDTO);
	public boolean removeProductFromCart(String cartItemId, ProductDTO productDTO);
}
