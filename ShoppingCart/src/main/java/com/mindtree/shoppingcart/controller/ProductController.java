package com.mindtree.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.model.Product;
import com.mindtree.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/rest")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.GET, value="/products")
	public ResponseEntity<List<Product>> getProducts() throws ApplicationException{
		
		List<Product> products=  productService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
}
