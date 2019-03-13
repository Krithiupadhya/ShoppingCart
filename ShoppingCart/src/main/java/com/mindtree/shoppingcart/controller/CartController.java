package com.mindtree.shoppingcart.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.InvalidInputException;
import com.mindtree.shoppingcart.service.CartService;

@RestController
@RequestMapping("/rest")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/cart/{cartItem}")
	public ResponseEntity<CartDTO> getCartDetails(@PathParam("cartItem") String cartItemId ) throws ApplicationException{
		if(StringUtils.isEmpty(cartItemId))
			throw new InvalidInputException("Cart Id not sent in the input");
		
		CartDTO cartDTO = cartService.getCartDetails(cartItemId);
		
		return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.OK);
	}
}
