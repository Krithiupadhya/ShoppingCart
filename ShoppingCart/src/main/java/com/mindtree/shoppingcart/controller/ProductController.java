package com.mindtree.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/ShoppingCart/rest")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/product")
	public ResponseEntity<List<Product>> getProducts() throws ApplicationException{
		
		List<Product> products=  productService.getAllProducts();
		logger.debug("Products fetched: {}", products.size());
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
}
