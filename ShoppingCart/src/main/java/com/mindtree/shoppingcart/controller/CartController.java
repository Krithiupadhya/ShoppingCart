package com.mindtree.shoppingcart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.service.CartService;

@RestController
@RequestMapping("/ShoppingCart/rest")
public class CartController {

	private static Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@PostMapping(value = "/cart")
	public ResponseEntity<Map<String, Long>> createCart(@Valid @RequestBody CartDTO cartDTO) {
		logger.trace("Creating a new cart");
		Long cartId = cartService.createCart(cartDTO);
		logger.debug("Cart created. Cart Details:" + cartDTO);
		Map<String, Long> response = new HashMap<>();
		response.put("cartId", cartId);
		return new ResponseEntity<Map<String, Long>>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/cart/{cartId}")
	public HttpStatus updateCart(@PathVariable("cartId") @NotNull Long cartId,
			@Valid @RequestBody List<ProductDTO> products) {
		logger.trace("Updating cart: {}", cartId);
		cartService.updateCart(cartId, products);
		return HttpStatus.OK;
	}

	@GetMapping(value = "/cart/{cartId}")
	public ResponseEntity<CartDTO> viewCartDetails(@PathVariable("cartId") @NotNull Long cartId) {
		logger.trace("Viewing cart details: {}" , cartId);

		CartDTO cartDTO = cartService.getCartDetails(cartId);
		logger.debug("Cart Details:" + cartDTO);
		return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/cart/{cartId}/product/{productId}")
	public HttpStatus addItemToCart(@PathVariable("cartId") @NotNull Long cartId,
			@PathVariable("productId") @NotNull Long productId) {
		logger.trace("Adding product to cart: {}", cartId);
		cartService.addItemToCart(cartId, productId);

		return HttpStatus.OK;
	}

	@DeleteMapping(value = "/cart/{cartId}/product/{productId}")
	public HttpStatus removeItemsFromCart(@PathVariable("cartId") @NotNull Long cartId,
			@PathVariable("productId") Long productId) {

		logger.debug("Removing product: {} from cart :{}:", productId, cartId);
		boolean productDeleted = cartService.removeCartItem(cartId, productId);
		if (!productDeleted) {
			return HttpStatus.NOT_FOUND;
		}

		return HttpStatus.NO_CONTENT;
	}

}
