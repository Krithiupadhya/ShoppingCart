package com.mindtree.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="PRODUCTS")
public class Product{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	private String prodName;
	private float price;
}
