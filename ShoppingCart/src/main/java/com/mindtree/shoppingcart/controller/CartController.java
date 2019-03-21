package com.mindtree.shoppingcart.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.exception.InvalidInputException;
import com.mindtree.shoppingcart.service.CartService;
import com.mindtree.shoppingcart.util.ShoppingCartUtil;

@RestController
@RequestMapping("/ShoppingCart/rest")
public class CartController {

	private static Logger logger= LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/cart/{cartItem}", method= RequestMethod.GET)
	public ResponseEntity<CartDTO> getCartDetails(@PathVariable("cartItem") String cartItemId ) {
		if(StringUtils.isEmpty(cartItemId) || !ShoppingCartUtil.isLong(cartItemId)) {
			logger.error("Cart item is invalid: "+cartItemId);
			throw new InvalidInputException("Cart item is invalid!!");
		}
		
		CartDTO cartDTO = cartService.getCartDetails(cartItemId);
		
		return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/cart", method= RequestMethod.POST)
	public ResponseEntity<CartDTO> createCart(@Valid @RequestBody CartDTO cartDTO) {
		/*if(cart==null || cart.getCartItems()==null || cart.getCartItems().isEmpty()) {
			logger.error("Cart details is invalid: "+cart);
			throw new InvalidInputException("Cart details is invalid!!");
		}*/
		
		cartDTO = cartService.addCartDetails(cartDTO);
		
		return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/cart/{cartItem}/product", method= RequestMethod.POST)
	public ResponseEntity<CartDTO> addProductToCart(@PathParam("cartItem") String cartItemId,
			@Valid @RequestBody ProductDTO productDTO ) {
		
		CartDTO cartDTO = cartService.getCartDetails(cartItemId);
		
		return new ResponseEntity<CartDTO>(cartDTO,HttpStatus.OK);
	}
	
		
}
