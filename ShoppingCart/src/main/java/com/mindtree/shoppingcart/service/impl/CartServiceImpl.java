package com.mindtree.shoppingcart.service.impl;

import java.util.List;
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
import com.mindtree.shoppingcart.exception.ApplicationException;
import com.mindtree.shoppingcart.exception.DataNotFoundException;
import com.mindtree.shoppingcart.exception.InvalidInputException;
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
	public CartDTO createCart(CartDTO cartDTO) {
		
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
	public CartDTO updateProduct(String cartItemId, ProductDTO productDTO) {
		
		Cart cart=getCartById(cartItemId);
		
		
		Optional<CartItem> cartItem =cart.getCartItems().stream().filter(
				t->(t.getProduct().getProductId()==productDTO.getProductId())).findFirst();
		
		
		if(cartItem.isPresent()) {
			if(productDTO.getQuantity()==0) {
				cart.getCartItems().remove(cartItem.get());
			}else {
				cartItem.get().setQuantity(productDTO.getQuantity());
			}
		}else {
			logger.info("Product cannot be updated!! Not available in the cart. Product:"+productDTO);
			throw new InvalidInputException("Product cannot be updated!! Not available in the cart." );
		}
		
		cartRepository.save(cart);
		return ShoppingCartModelMappers.getCartDTO(cart);
	}

	
	public boolean removeProduct(String cartItemId, List<ProductDTO> productDTOs) {
		
		Cart cart=getCartById(cartItemId);
		
		Set<CartItem> cartItemsToRetain= cart.getCartItems().stream().filter(t-> !productDTOs.stream().anyMatch(f-> f.getProductId() == t.getProduct().getProductId())).collect(Collectors.toSet());
		if(cartItemsToRetain.size()!=0) {
			cart.setCartItems(cartItemsToRetain);
		}else {
			cart.setCartItems(null);
		}
		
		cart.setCartItems(cartItemsToRetain);
		return false;
		
	}

}
