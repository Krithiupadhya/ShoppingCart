package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;

public interface CartService {

	/**
	 * Create a cart for a user
	 * @param cartDTO
	 * @return
	 */
	public Long createCart(CartDTO cartDTO);
	
	/**
	 * Update the cart i.e update the details/products/product quantities of a cart
	 * @param cartDTO
	 * @return
	 */
	public void updateCart(Long cartId, List<ProductDTO> products);
	
	/**
	 * Remove product/items of a cart
	 * @param cartId
	 * @param productId
	 * @return
	 */
	public boolean removeCartItem(Long cartId, Long productId);
	
	/**
	 * Get the cart details based on the Id
	 * @param cartId
	 * @return
	 */
	public CartDTO getCartDetails(Long cartId);
	
	/**
	 * Add Product/Item to a cart
	 * @param cartId
	 * @param productId
	 * @return
	 */
	public CartDTO addItemToCart(Long cartId, Long productId);
	
}
