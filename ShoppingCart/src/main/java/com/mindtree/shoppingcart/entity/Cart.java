package com.mindtree.shoppingcart.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="CARTS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cart {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long cartId;
	
	@OneToOne
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cart_id")
	private Set<CartItem> cartItems;
	
}
