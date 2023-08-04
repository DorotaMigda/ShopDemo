package com.dorotam.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dorotam.model.Admin;
import com.dorotam.model.Product;
import com.dorotam.model.User;
import com.dorotam.service.AdminService;
import com.dorotam.service.ProductService;
import com.dorotam.service.UserService;


@Controller
public class AdminController {
	
	private final AdminService adminService;
	private final UserService userService;
	private final ProductService productService;
	

	public AdminController(AdminService adminService, UserService userService, ProductService productService) {
		this.adminService = adminService;
		this.userService = userService;
		this.productService=productService;
	}
	
	@RequestMapping("adminLogin")
	public String toLogin() {
		return "admin/adminlogin";
	}
	
	@PostMapping("loginAdminSubmit")
	public String loginAdminSubmit(
		@RequestParam String email, 
		@RequestParam String password,
		Model model) {
		Optional<Admin> adminOpt = adminService.login(email, password);
		if(adminOpt.isPresent()) {
			model.addAttribute("adminId", adminOpt.get().getId());
			model.addAttribute("userFirstName", adminOpt.get().getFirstName());
			model.addAttribute("userLastName", adminOpt.get().getLastName());
			return "admin/adminpanel";
		}
		
		model.addAttribute("loginFailedMessage", "Email or Password incorrect, login failed.");
		return "forward:/adminLogin";
	}

	@GetMapping("adminRegister")
	public String toRegister() {
		return "admin/adminregister";
	}
	
	@PostMapping("registerAdminSubmit")
	public String registerSubmit(@RequestParam LocalDate dateOfBirth, @RequestParam String password, @RequestParam String email,
			@RequestParam String firstName, @RequestParam String lastName,@RequestParam String role) {
		adminService.saveAdmin(new Admin(firstName, lastName, dateOfBirth, email, password, role));
		return "admin/adminpanel";
	}
	
	
	@RequestMapping("allUsers")
	public String loginAdminSubmit(Model model) {
		model.addAttribute("allUsers", userService.findAll());
		return "allUsers";
	}
	
	@GetMapping("updateUser")
	public String updateUser(@RequestParam Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
	return "user/edit-user";
	}
	
	@PostMapping("updateUserSubmit")
	public String updateUserSubmit(@ModelAttribute User user) {
		userService.updateUser(user, user.getId());
		return "forward:/allUsers";
	}
	
	@GetMapping("deleteUser")
	public String deleteUser(@RequestParam Integer id) {
		userService.deleteUser(id);
		return "forward:/allUsers";
	}
	
	
	@RequestMapping("allProductsAdmin")
	public String loginAdminSubmit1(Model model) {
		model.addAttribute("allProductsAdmin", productService.getAll());
		return "product/allproducts";
	}
	
	@GetMapping("updateProductAdmin")
	public String updateProduct(@RequestParam Integer id, Model model) throws Exception {
		model.addAttribute("product", productService.getById(id));
	return "product/edit-product";
	}
	
	@PostMapping("updateProductSubmitAdmin")
	public String updateProductSubmit(@ModelAttribute Product product) throws Exception {
		productService.updateById(product, product.getId());
		return "forward:/allProductsAdmin";
	}
	
	@GetMapping("deleteProductAdmin")
	public String deleteProduct(@RequestParam Integer id) throws Exception {
		productService.deleteProductById(id);
		return "forward:/allProductsAdmin";
	}
	
	

}