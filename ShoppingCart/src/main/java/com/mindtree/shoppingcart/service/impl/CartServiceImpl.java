package com.mindtree.shoppingcart.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.entity.Cart;
import com.mindtree.shoppingcart.entity.CartItem;
import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.DataNotFoundException;
import com.mindtree.shoppingcart.repository.CartRepository;
import com.mindtree.shoppingcart.service.CartService;
import com.mindtree.shoppingcart.util.ShoppingCartModelMappers;
import com.mindtree.shoppingcart.util.ShoppingCartUtil;

@Service
@Transactional(value=TxType.REQUIRED)
public class CartServiceImpl implements CartService {

	private static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	public CartRepository cartRepository;
	
	@Override
	public CartDTO getCartDetails(String cartId) throws ApplicationException {

		Cart cart = getCartById(cartId);
		
		return ShoppingCartModelMappers.getCartDTO(cart);
	}

	private Cart getCartById(String cartId) {
		long cartIdNo= ShoppingCartUtil.getLong(cartId);
		Optional<Cart> cart= cartRepository.findById(cartIdNo);

		if(!cart.isPresent()) {
			logger.info("Cart doesnt exist in the system");
			throw new DataNotFoundException("Invalid cart Id");
		}
		return cart.get();
	}

	@Override
	public CartDTO addCartDetails(CartDTO cartDTO) {
		
		cartDTO.setProducts(mergeProducts(cartDTO.getProducts()));
		Cart cart= cartRepository.save(ShoppingCartModelMappers.getCart(cartDTO));
		
		Optional<Cart> cartWithProductDetails=Optional.ofNullable(cart);
		if(cart.getCartId()!=0)
			cartWithProductDetails= cartRepository.findById(cart.getCartId());
		return ShoppingCartModelMappers.getCartDTO(cartWithProductDetails.get());
	}
	
	

	
	private Set<ProductDTO> mergeProducts(Set<ProductDTO> products) {

		Set<ProductDTO> productsMerged = null;
		
		
		if(products!=null)
			productsMerged= products.stream().map(t->t).collect(
		         Collectors.<ProductDTO, Integer, ProductDTO>toMap(
				     t->t.getProductId(), 
				     t-> t, 
				     (l,r)-> {l.setQuantity(l.getQuantity()+r.getQuantity()) ; return l;}
				    )
				 ).values().stream().collect(Collectors.toSet());
		 
		return productsMerged; 
	}
	
	
	@Override
	public CartDTO updateProductInCart(String cartItemId, ProductDTO productDTO) {
		
		Cart cart=getCartById(cartItemId);
		
		
		CartItem cartItem =cart.getCartItems().stream().filter(
				t->(t.getProduct().getProductId()==productDTO.getProductId())).collect(ShoppingCartUtil.toSingleton());
		
		if(cartItem!=null) {
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}else {
			cartItem = new CartItem();
			cartItem.setProduct(ShoppingCartModelMappers.getProduct(productDTO));
		}
		
		cartRepository.save(cart);
		return ShoppingCartModelMappers.getCartDTO(cart);
	}

	
	public boolean removeProductFromCart(String cartItemId, ProductDTO productDTO) {
		
		
		return false;
		
	}

}
