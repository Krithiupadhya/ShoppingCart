package com.mindtree.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="CART_ITEMS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cartItemId;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	private int quantity;
	
}
