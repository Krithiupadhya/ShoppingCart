package com.mindtree.shoppingcart.entity.product;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.mindtree.shoppingcart.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="APPARRELS")
@PrimaryKeyJoinColumn(name="PRODUCT_ID")  
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Apparrel extends Product {

	private String type;
	private String brand;
	private String design;
	
}
