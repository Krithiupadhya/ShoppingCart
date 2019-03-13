/**
 * 
 */
package com.mindtree.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="USERS")
public class User {

	@Id
	private String username;
	private String password;
}
