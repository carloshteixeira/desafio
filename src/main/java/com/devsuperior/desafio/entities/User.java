package com.devsuperior.desafio.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;


	@Entity
	@Table(name = "tb_user")
	public class User {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String name;
		private String email;
		private String phone;
		
		@OneToMany(mappedBy = "client")
		private Set<Order> orders = new HashSet<>();
		
		@ManyToMany
		@JoinTable(
		    name = "tb_user_role",
		    joinColumns = @JoinColumn(name = "user_id"),
		    inverseJoinColumns = @JoinColumn(name = "role_id")
		)
		private Set<Role> roles = new HashSet<>();
		
		public Set<Role> getRoles() {
		    return roles;
		}
		
		public User() {
		}
		
		public User(Long id, String name, String email, String phone) {
		    this.id = id;
		    this.name = name;
		    this.email = email;
		    this.phone = phone;
		}		
		public Long getId() {
		    return id;
		}
		public String getName() {
		    return name;
		}

		public void setName(String name) {
		    this.name = name;
		}
		public String getEmail() {
		    return email;
		}

		public void setEmail(String email) {
		    this.email = email;
		}
		public String getPhone() {
		    return phone;
		}

		public void setPhone(String phone) {
		    this.phone = phone;
		}
		public Set<Order> getOrders() {
		    return orders;
		}
	}
	