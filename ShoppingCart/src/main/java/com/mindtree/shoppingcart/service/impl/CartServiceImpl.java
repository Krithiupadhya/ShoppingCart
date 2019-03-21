package com.mindtree.shoppingcart.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.DataNotFoundException;
import com.mindtree.shoppingcart.model.Cart;
import com.mindtree.shoppingcart.model.CartItem;
import com.mindtree.shoppingcart.model.Product;
import com.mindtree.shoppingcart.repository.CartRepository;
import com.mindtree.shoppingcart.service.CartService;
import com.mindtree.shoppingcart.util.ShoppingCartUtil;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	private static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	public CartRepository cartRepository;
	
	@Override
	public CartDTO getCartDetails(String cartId) throws ApplicationException {

		long cartIdNo= ShoppingCartUtil.getLong(cartId);
		Optional<Cart> cart= cartRepository.findById(cartIdNo);

		if(!cart.isPresent()) {
			logger.info("Cart doesnt exist in the system");
			throw new DataNotFoundException("Invalid cart Id");
		}
		
		return getCartDTO(cart.get());
	}

	@Override
	public CartDTO addCartDetails(CartDTO cartDTO) {
		Cart cart= cartRepository.save(getCart(cartDTO));
		
		Optional<Cart> cartWithProductDetails=Optional.ofNullable(cart);
		if(cart.getCartId()!=0)
			cartWithProductDetails= cartRepository.findById(cart.getCartId());
		return getCartDTO(cartWithProductDetails.get());
	}
	
	private CartDTO getCartDTO(Cart cart) {
		//DTO object created from the model object
		CartDTO cartDTO = null;
		
		cartDTO=new CartDTO();
		cartDTO.setCartId(cart.getCartId());
		cartDTO.setUser(cart.getUser());
		if(cart.getCartItems()!=null && !cart.getCartItems().isEmpty()) {
			
			Set<ProductDTO> products= cart.getCartItems().stream().map(
					t-> new ProductDTO(t.getProduct().getProductId(), t.getProduct().getProductName(),
							t.getProduct().getPrice(), t.getQuantity())).collect(Collectors.toSet());
			cartDTO.setProducts(products);
			cartDTO.setTotal(products.stream().mapToDouble(t->t.getSubtotal()).sum());
		}else {
			cartDTO.setProducts(new HashSet<>());
		}
		
		return cartDTO;
	}
	
	private Cart getCart(CartDTO cartDTO) {
		Cart cart= new Cart();
		cart.setCartId(cartDTO.getCartId());
		cart.setUser(cartDTO.getUser());
		if (cartDTO.getProducts()!=null && !cartDTO.getProducts().isEmpty()) {
			 Set<CartItem> cartItems= cartDTO.getProducts().stream().map(
					 t-> new CartItem(null, new Product(t.getProductId(),null,0), t.getQuantity()
					 )).collect(Collectors.toSet());
			 cart.setCartItems(cartItems);
		}
		return cart;
	}

	

}
