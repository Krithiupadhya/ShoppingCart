package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.exception.ApplicationException;

public interface ProductService {

	/**
	 * Fetches all the products
	 * @return
	 * @throws ApplicationException
	 */
	public List<Product> getAllProducts() throws ApplicationException;
	/**
	 * Fetches details of a product
	 * @param productId
	 * @return
	 * @throws ApplicationException
	 */
	public Product getProductDetails(Long productId) throws ApplicationException;
}
