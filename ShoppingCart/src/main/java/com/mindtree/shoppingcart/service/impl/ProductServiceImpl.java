package com.mindtree.shoppingcart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.DataNotFoundException;
import com.mindtree.shoppingcart.model.Product;
import com.mindtree.shoppingcart.repository.ProductRepository;
import com.mindtree.shoppingcart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() throws ApplicationException {
		List<Product> products= productRepository.findAll();
		if(products==null || products.size()==0) {
			logger.info("No products available in system!!");
			throw new DataNotFoundException("No products available");
		}
		return products;
	}

}
