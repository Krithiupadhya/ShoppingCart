package com.mindtree.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingcart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
