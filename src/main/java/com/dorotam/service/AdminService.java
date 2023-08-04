package com.dorotam.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dorotam.model.Admin;
import com.dorotam.repository.AdminRepository;

@Service
public class AdminService {
	
private final AdminRepository adminRepo;
	

	public AdminService(AdminRepository adminRepo) {
		this.adminRepo = adminRepo;
	}


	public Optional<Admin> login(String email, String password) {
		return adminRepo.findFirstByEmailAndPassword(email, password);
	}
	
	public Admin saveAdmin(Admin admin) {
		return adminRepo.save(admin);
	}

}