package com.dorotam.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dorotam.model.Cart;
import com.dorotam.model.Product;
import com.dorotam.model.User;
import com.dorotam.service.CartService;
import com.dorotam.service.ProductService;
import com.dorotam.service.UserService;



@Controller
public class UserController {

	private final UserService userService;
	private final ProductService productService;
	private final CartService cartService;
	
	

	public UserController(UserService userService, ProductService productService, CartService cartService) {
		this.userService = userService;
		this.productService= productService;
		this.cartService= cartService;
	}
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "user/login";
	}
	
	@PostMapping("loginSubmit")
	public String loginSubmit(
			@RequestParam String email, 
			@RequestParam String password,
			Model model) {
		Optional<User> userOpt = userService.login(email, password);
		if(userOpt.isPresent()) {
			model.addAttribute("userId", userOpt.get().getId());
			model.addAttribute("userFirstName", userOpt.get().getFirstName());
			model.addAttribute("userLastName", userOpt.get().getLastName());
			model.addAttribute("allProducts", productService.getAll());
			return "home";
		}
		model.addAttribute("loginFailedMessage", "Email or Password incorrect, login failed.");
		return "forward:/toLogin";
	}

	@GetMapping("toRegister")
	public String toRegister() {
		return "user/register";
	}
	
	@PostMapping("registerSubmit")
	public String registerSubmit(@RequestParam LocalDate dateOfBirth, @RequestParam String password, @RequestParam String email,
			@RequestParam String firstName, @RequestParam String lastName, Model model) {
		userService.saveUser(new User(firstName, lastName, dateOfBirth, email, password));
		model.addAttribute("allProducts", productService.getAll());
		return "welcome";
	}
	
	
	
	
	
	@PostMapping("addToCart")
	public String addToCart(
	        @RequestParam Integer productId,
	        @RequestParam Integer userId,
	        @RequestParam(required = false) Integer cartId,
	        Model model
	) throws Exception {
	    Optional<User> userOpt = userService.findById(userId);
	    Optional<Product> productOpt = Optional.ofNullable(productService.getById(productId));

	    if (userOpt.isPresent() && productOpt.isPresent()) {
	        User user = userOpt.get();
	        Product product = productOpt.get();
	        Cart cart = cartService.getOrCreateCart(user);
	        cartService.addProducts(product, user);

	        model.addAttribute("user", userOpt);
	        model.addAttribute("userId", user.getId());
	        model.addAttribute("product", productOpt);
	        model.addAttribute("cart", cart);

	        return "cart";
	    }

	    return "forward:/toLogin";
	}

	
		
	
    
	
}