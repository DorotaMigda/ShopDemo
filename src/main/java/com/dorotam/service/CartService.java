package com.dorotam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dorotam.model.Cart;
import com.dorotam.model.Product;
import com.dorotam.model.User;
import com.dorotam.repository.CartRepository;
import com.dorotam.repository.ProductRepository;
import com.dorotam.repository.UserRepository;

@Service
public class CartService {
private final CartRepository cartRepo;
private final UserRepository userRepo;
private final ProductRepository productRepo;
	
	
	public CartService(CartRepository cartRepo, UserRepository userRepo, ProductRepository productRepo) {
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
		this.productRepo = productRepo;
	}
	
	
	
	
	public List<Cart> getAll(){
		return cartRepo.findAll();
	}
	
	
	public Cart getById(int id) throws Exception {
		Optional<Cart> cart = cartRepo.findById(id);
		
		if(cart.isEmpty()) {
			throw new Exception(cartWithIdNotFound(id));
		}
		
		return cart.get();
	}
	
	
	
	public Cart createCart(Cart cart) throws Exception {
		Optional<Cart> carty = cartRepo.findById(cart.getId());
		
		if(carty.isPresent()){
			throw new Exception("Cart with ID: "+cart.getId()+" already exists");
		}
		return cartRepo.save(cart);
	}
	
	public String cartWithIdNotFound(int id) {
		return "cart with id: "+id+" not found";
	}
	
	
	public void deleteCartById(int id) throws Exception {
		Optional<Cart> cart = cartRepo.findById(id);
		
		if(cart.isEmpty()) {
			throw new Exception(cartWithIdNotFound(id));
		}
		
		cartRepo.deleteById(id);
		
	}
	
	
	public Cart updateById(Cart cart, int id) throws Exception {
		Optional<Cart> carty = cartRepo.findById(id);
		
		if(carty.isEmpty()) {
			throw new Exception(cartWithIdNotFound(id));
		}
		
		cart.setId(id);
		
		return cartRepo.save(cart);
	}
	
	public Cart getOrCreateCart(User user) throws Exception {
		Cart cart = user.getCart();
		if (cart == null) {
		    cart = new Cart();
		    cart.setUser(user);
		    user.setCart(cart);
		    cart = cartRepo.save(cart);
		    userRepo.save(user);
		}
		return cart;
			
    }
	
	
	 public void addProducts(Product product, User user) {
		    Cart cart = user.getCart();
		    List<Product> products = cart.getProducts();
		    if (!products.contains(product)) {
		        products.add(product);
		        cart.setProducts(products);
		        product.addCart(cart); 
		        cartRepo.save(cart);
		        userRepo.save(user);
		    }
		}
	

}
