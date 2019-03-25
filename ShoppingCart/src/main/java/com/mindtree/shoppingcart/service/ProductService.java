package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.exception.ApplicationException;

public interface ProductService {

	public List<Product> getAllProducts() throws ApplicationException;
}
