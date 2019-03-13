package com.mindtree.shoppingcart.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="CARTS")
public class Cart {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long cartId;
	
	@OneToOne
	private User user;
	
	@OneToMany
	@JoinTable(name="CART_ITEMS")
	private List<CartItem> cartItems;
	
}
