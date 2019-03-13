package com.mindtree.shoppingcart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.DataNotFoundException;
import com.mindtree.shoppingcart.model.Cart;
import com.mindtree.shoppingcart.repository.CartRepository;
import com.mindtree.shoppingcart.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	public CartRepository cartRepository;
	
	@Override
	public CartDTO getCartDetails(String cartId) throws ApplicationException {

		long cartIdNo= Long.getLong(cartId);
		Cart cart= cartRepository.getOne(cartIdNo);

		if(cart==null) {
			logger.info("Cart doesnt exist in the system");
			throw new DataNotFoundException("Invalid cart Id");
		}
		
		//DTO object created from the model object
		CartDTO cartDTO = null;
		
		if(cart.getCartItems()!=null && cart.getCartItems().size()!=0) {
			
			cartDTO=new CartDTO();
			cartDTO.setCartId(cart.getCartId());
			List<ProductDTO> products= cart.getCartItems().stream().map(
					t-> new ProductDTO(t.getProduct().getProductId(), t.getProduct().getProdName(),
							t.getProduct().getPrice(), t.getQuantity())).collect(Collectors.toList());
			cartDTO.setProducts(products);
			cartDTO.setTotal(products.stream().mapToDouble(t->t.getSubtotal()).sum());
		}
		
		return cartDTO;
	}

}
