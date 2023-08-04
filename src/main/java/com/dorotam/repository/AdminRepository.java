package com.dorotam.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorotam.model.Admin;



public interface AdminRepository extends JpaRepository <Admin, Integer>{

	public Optional<Admin> findFirstByEmailAndPassword(String email, String password);
	
}
