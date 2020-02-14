package com.mindtree.shoppingcart.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.DataNotFoundException;
import com.mindtree.shoppingcart.repository.ProductRepository;
import com.mindtree.shoppingcart.service.ProductService;

@Service
@Transactional(value=TxType.SUPPORTS)
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() throws ApplicationException {
		List<Product> products= productRepository.findAll();
		if(Objects.isNull(products) || products.isEmpty()) {
			logger.error("No products available in system!!");
			throw new DataNotFoundException("No products available");
		}
		return products;
	}

	@Override
	public Product getProductDetails(Long productId) throws ApplicationException {
		Optional<Product> product = productRepository.findById(productId);

		return product.orElseThrow(() -> {
			logger.info("product doesnt exist in the system");
			throw new DataNotFoundException("Invalid product Id: "+productId);
		});
	
	}

}
