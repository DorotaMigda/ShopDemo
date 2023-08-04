package com.dorotam.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dorotam.model.Product;
import com.dorotam.repository.ProductRepository;


@Service
public class ProductService {
private final ProductRepository productRepo;
	
	
	public ProductService(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	
	
	
	  public String productWithIdNotFound(int id) { return
	  "product with id: "+id+" not found"; }
	 
	
	public List<Product> getAll(){
		return productRepo.findAll();
	}
	
	
	
	  public Product getById(int id) throws Exception { Optional<Product> prod =
	  productRepo.findById(id);
	  
	  if(prod.isEmpty()) { throw new Exception(productWithIdNotFound(id)); }
	  
	  return prod.get(); }
	 
	
	
	
	public Product createProduct(Product prod) throws Exception {
		Optional<Product> product = productRepo.findById(prod.getId());
		
		if(product.isPresent()){
			throw new Exception("Product with ID: "+prod.getId()+" already exists");
		}
		return productRepo.save(prod);
	}
	
	//public void deleteProductById(int id) throws Exception {

	/*
	 * Optional<Product> product = productRepo.findById(id);
	 * 
	 * if(product.isEmpty()) { throw new Exception(productWithIdNotFound(id)); }
	 * 
	 * productRepo.deleteById(id);
	 * 
	 * }
	 */
	public void deleteProductById(Integer id) {
		   productRepo.deleteById(id);
	   }
	
		
		  public Product updateById(Product prod, int id) throws Exception {
		  Optional<Product> product = productRepo.findById(id);
		  
		  if(product.isEmpty()) { throw new Exception(productWithIdNotFound(id)); }
		  
		  prod.setId(id);
		  
		  return productRepo.save(prod); }
		 
	
		
	public ArrayList<Product> findByNameContaining(String name){
		return productRepo.findByNameContaining(name);
	}
	
	

}