package com.dorotam.model;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="USERS")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usergen")
	@SequenceGenerator(name = "usergen", sequenceName = "user_id_seq", allocationSize = 1)
	private Integer id;
	private String firstName;
	private String lastName;
	
	//dodać kaskadę na usuwanie usera
	@OneToOne
	@JoinColumn(name = "FK_CART_ID")
	private Cart cart;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String email;
	private String password;
	
	

	public User () {}
	
	
	
	public User(String firstName, String lastName, LocalDate dateOfBirth, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
	}




	public Cart getCart() {
		return cart;
	}

	public Cart getorCreateCart() {
		if (this.getCart()!= null) {
		return cart;
	}
		else return new Cart();}

	public void setCart(Cart cart) {
		this.cart = cart;
	}



	public Integer getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	
	
	

}