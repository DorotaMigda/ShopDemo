package com.dorotam.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodgen")
	@SequenceGenerator(name = "prodgen", sequenceName = "prod_id_seq", allocationSize = 1)
	private int id;
	
	@NotBlank (message = "Product name must not be null or blank.")
	private String name;
	
	@NotBlank (message = "Product description must not be null or blank.")
	private String description;
	
	@Min(value = 0, message = "Minimum price must be more or equal than 0")
	private double price;
	
	@Min(value = 0, message = "Minimum quantity must be more or equal than 0")
	private int quantityMax;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "CARTS_AND_PRODUCTS",
			joinColumns = @JoinColumn(name = "FK_PRODUCT_ID"),
			inverseJoinColumns = @JoinColumn(name = "Fk_CART_ID"))	
	
	private List<Cart> carts = new ArrayList<>();
	
	
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public Product() {
		super();
	}
	public Product(String name, String description, double price, int quantityMax) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantityMax = quantityMax;
	}
	public int getQuantityMax() {
		return quantityMax;
	}
	public void setQuantityMax(int quantityMax) {
		this.quantityMax = quantityMax;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public void addCart(Cart cart) {
	    this.carts.add(cart);
	}

}