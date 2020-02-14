package com.mindtree.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="PRODUCTS")
@Inheritance(strategy=InheritanceType.JOINED)  
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Product{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productId;
	private String productName;
	private double price;
	
}
