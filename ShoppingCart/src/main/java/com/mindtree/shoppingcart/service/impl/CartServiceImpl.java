package com.mindtree.shoppingcart.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
import com.mindtree.shoppingcart.service.ProductService;
import com.mindtree.shoppingcart.util.ApplicationModelMappers;

@Service
@Transactional(value = TxType.REQUIRED)
class CartServiceImpl implements CartService {

	private static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductService productService;

	@Override
	public Long createCart(CartDTO cartDTO) {
		logger.debug("creating cart for user: {}", cartDTO.getUser());
		cartDTO.setProducts(mergeProducts(cartDTO.getProducts()));
		// Resetting the Id to not update an existing cart
		cartDTO.setCartId(0);
		Cart cart = cartRepository.save(ApplicationModelMappers.getCart(cartDTO));
		logger.debug("created cart for user: {}, cart id: {}", cartDTO.getUser(), cartDTO.getCartId());
		return cart.getCartId();
	}

	@Override
	public void updateCart(Long cartId, List<ProductDTO> products) {
		Cart cart = getCartById(cartId);
		Set<ProductDTO> productSet = mergeProducts(products);
		List<Product> allProducts = productService.getAllProducts();
		Set<CartItem> itemsToSave = productSet.stream()
				.filter(p -> allProducts.stream().anyMatch(allProd -> allProd.getProductId() == p.getProductId()))
				.map(p -> CartItem.builder()
						.product(Product.builder().productId(p.getProductId()).build())
						.quantity(p.getQuantity()).build())
				.collect(Collectors.toSet());
		cart.setCartItems(itemsToSave);
		cart = cartRepository.save(cart);
		logger.debug("cart updated successfully: {}", cart);
	}

	@Override
	public CartDTO getCartDetails(Long cartId) throws ApplicationException {
		logger.debug("Fetching cart details: {}", cartId);
		Cart cart = getCartById(cartId);
		return ApplicationModelMappers.getCartDTO(cart);
	}

	@Override
	public CartDTO addItemToCart(Long cartId, Long productId) {

		logger.debug("Adding product {} to cart: {}", productId, cartId);
		Cart cart = getCartById(cartId);
		Product product = productService.getProductDetails(productId);

		if (!Objects.isNull(cart.getCartItems())) {
			Optional<CartItem> cartItem = cart.getCartItems().stream()
					.filter(t -> (productId == t.getProduct().getProductId()))
					.findFirst();

			if (cartItem.isPresent()) {
				cartItem.get().setQuantity(cartItem.get().getQuantity() + 1);
			} else {
				cart.getCartItems().add(CartItem.builder().product(product).quantity(1).build());
			}
		} else {
			Set<CartItem> cartItems = new HashSet<>();
			cartItems.add(CartItem.builder().product(product).quantity(1).build());
			cart.setCartItems(cartItems);
		}

		cartRepository.save(cart);
		return ApplicationModelMappers.getCartDTO(cart);
	}

	@Override
	public boolean removeCartItem(Long cartId, Long productId) {
		boolean productAvailable = true;
		Cart cart = getCartById(cartId);
		logger.debug("cart: {}, Cart item details before removing: {}", cartId, cart.getCartItems());
		// Remove all the products when the product id is not sent
		if (Objects.isNull(productId)) {
			if (cart.getCartItems() != null && !cart.getCartItems().isEmpty())
				cart.setCartItems(null);
			else
				productAvailable = true;
		} else {
			Optional<CartItem> cartitem = cart.getCartItems().stream()
					.filter(cartItem -> productId == cartItem.getProduct().getProductId())
					.findFirst();
			if (cartitem.isPresent()) {
				cart.getCartItems().remove(cartitem.get());
			} else {
				productAvailable = false;
			}
		}
		cartRepository.save(cart);
		logger.debug("cart: {}, Cart item details after removing: {}", cartId, cart.getCartItems());
		return productAvailable;

	}

	/**
	 * Fetch the details of a cart
	 * 
	 * @param cartId
	 * @return
	 */
	private Cart getCartById(Long cartId) {
		Optional<Cart> cart = cartRepository.findById(cartId);

		return cart.orElseThrow(() -> {
			logger.info("Cart doesnt exist in the system");
			throw new DataNotFoundException("Invalid cart Id: " + cartId);
		});
	}

	/**
	 * Merge the same product and get a set of products. While merging the quantity
	 * of the product is increased
	 * 
	 * @param products
	 * @return
	 */
	private Set<ProductDTO> mergeProducts(Collection<ProductDTO> products) {

		Set<ProductDTO> productsMerged = null;

		if (products != null)
			productsMerged = products.stream()
					.collect(Collectors.<ProductDTO, Long, ProductDTO>toMap(t -> t.getProductId(), t -> t, (l, r) -> {
						l.setQuantity(l.getQuantity() + r.getQuantity());
						return l;
					})).values()
					.stream()
					.collect(Collectors.toSet());

		productsMerged.removeIf(t -> t.getQuantity() <= 0);
		return productsMerged;
	}
}
