package com.dorotam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dorotam.model.Product;
import com.dorotam.service.ProductService;


@Controller
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@RequestMapping("/")
	public String allProducts(Model model) {
		model.addAttribute("allProducts", productService.getAll());
		return "welcome";
	}

	@RequestMapping("addProduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product/add-product";
	}

	@PostMapping("addProductSubmit")
	public String addProductSubmit(@ModelAttribute Product product) throws Exception {
		productService.createProduct(product);
		return "redirect:/allProductsAdmin";
		
	}

	@GetMapping("editProduct")
	public String editProduct(@RequestParam int id, Model model) throws Exception {
		model.addAttribute("product", productService.getById(id));
		return "edit-product";
	}

	@PostMapping("editProductSubmit")
	public String editProductSubmit(@ModelAttribute Product product) throws Exception {
		productService.updateById(product, product.getId());
		return "forward:/";
	}

	@GetMapping("deleteProduct")
	public String deleteProduct(@RequestParam int id) throws Exception {
		productService.deleteProductById(id);
		return "forward:/";
	}

	@RequestMapping("infoProduct")
	public String infoProduct(@RequestParam int id, Model model) throws Exception {
		model.addAttribute("product", productService.getById(id));
		return "info-product";
	}

	@GetMapping("searchProductsByNameContaining") 
	public String searchProductsByNameContaining(Model model, @RequestParam String name) {
		model.addAttribute("allProducts", productService.findByNameContaining(name));
		return "welcome"; 
		
	}

	@GetMapping("searchUserProductsByNameContaining") 
	public String searchUserProductsByNameContaining(Model model, @RequestParam String name) {
		model.addAttribute("allProducts", productService.findByNameContaining(name));
		return "home"; 
		
	}
}