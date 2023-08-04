package com.dorotam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorotam.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
