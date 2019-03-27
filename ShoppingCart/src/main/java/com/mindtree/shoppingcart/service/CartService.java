package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;

public interface CartService {

	public CartDTO getCartDetails(String cartId);
	public CartDTO createCart(CartDTO cartDTO);
	public CartDTO updateProduct(String cartItemId, ProductDTO productDTO);
	public boolean removeProduct(String cartItemId, List<ProductDTO> productDTO);
}
