package com.dorotam.repository;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorotam.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public ArrayList<Product> findByNameContaining(String name);
}