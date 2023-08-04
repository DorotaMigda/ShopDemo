package com.dorotam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dorotam.model.User;
import com.dorotam.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	public Optional<User> login(String email, String password) {
		return userRepo.findFirstByEmailAndPassword(email, password);
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	

	public List<User> findAll() {
		
		return userRepo.findAll();
		
	}

	
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
		
	}

   public void deleteUser(Integer id) {
	   userRepo.deleteById(id);
   }
	

   public User updateUser(User user, Integer id) {
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isEmpty()) {
			System.out.println("Exception");
		}
		user.setId(id);
		return userRepo.save(user);
	}

	
}