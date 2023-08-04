package com.dorotam.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartgen")
	@SequenceGenerator(name = "cartgen", sequenceName = "cart_id_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToMany(mappedBy = "carts")
	private List<Product> products = new ArrayList<>();
	
	@OneToOne(mappedBy="cart")
	User user;
	
	public Cart() {}
	
	
	

	public Cart(List<Product> products, User user) {
		this.products = products;
		this.user = user;
	}


public void addProduct (Product product){
	this.products.add(product);
	
	
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void clear() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	 
	
	
	 
	
	
}
