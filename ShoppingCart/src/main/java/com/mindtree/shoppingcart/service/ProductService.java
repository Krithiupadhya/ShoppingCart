package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.model.Product;

public interface ProductService {

	public List<Product> getAllProducts() throws ApplicationException;
}
