package com.mindtree.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingcart.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
