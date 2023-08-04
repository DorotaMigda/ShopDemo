package com.dorotam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorotam.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findFirstByEmailAndPassword(String email, String password);
	
  
	
	
}