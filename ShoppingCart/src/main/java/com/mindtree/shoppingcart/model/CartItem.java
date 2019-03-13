package com.mindtree.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CART_ITEMS")
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cartItemId;
	
	@ManyToOne
	private Product product;
	private int quantity;
	
}
