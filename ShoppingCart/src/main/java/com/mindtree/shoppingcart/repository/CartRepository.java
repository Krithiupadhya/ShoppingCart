package com.mindtree.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingcart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
