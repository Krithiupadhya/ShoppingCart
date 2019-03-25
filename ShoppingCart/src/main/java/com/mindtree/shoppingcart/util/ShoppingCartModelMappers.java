package com.mindtree.shoppingcart.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.mindtree.shoppingcart.dto.CartDTO;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.entity.Cart;
import com.mindtree.shoppingcart.entity.CartItem;
import com.mindtree.shoppingcart.entity.Product;

public class ShoppingCartModelMappers {

	
	public static CartDTO getCartDTO(Cart cart) {
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
	
	public static Cart getCart(CartDTO cartDTO) {
		Cart cart= new Cart();
		cart.setCartId(cartDTO.getCartId());
		cart.setUser(cartDTO.getUser());
		if (cartDTO.getProducts()!=null && !cartDTO.getProducts().isEmpty()) {
			 Set<CartItem> cartItems= cartDTO.getProducts().stream().
					 filter(t->t.getQuantity()>0).
					 map(
					 t-> new CartItem(null, new Product(t.getProductId(),null,0), t.getQuantity()
					 )).collect(Collectors.toSet());
			 cart.setCartItems(cartItems);
		}
		return cart;
	}
	
	public static Product getProduct(ProductDTO productDTO) {
		
		Product product= new Product(productDTO.getProductId(),
				productDTO.getName(), productDTO.getPrice()
				);
		
		return product;
		
	}
}
