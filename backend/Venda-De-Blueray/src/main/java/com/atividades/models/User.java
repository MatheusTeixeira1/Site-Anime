package com.atividades.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome é obrigatório")
	@NotNull(message = "Nome não deve ser nulo")
	private String username;
	@NotBlank(message = "Email é obrigatório")
	@NotNull(message = "Email não deve ser nulo")
	private String email;
	@NotBlank(message = "Senha é obrigatório")
	@NotNull(message = "Senha não deve ser nulo")
	private String userPassword;
	private UserRole roles;
	private String image;
	
	public User() {
		super();
	}
	
	public User(String username, String password, UserRole role) {
		super();
		this.username = username;
		this.userPassword = password;
		this.roles = role;
	}
	
	public User(String nome, String email, String senha, UserRole role, String image) {
		super();
		this.username = nome;
		this.email = email;
		this.userPassword = senha;
		this.roles = role;
		this.image = image;
	}
	public User(Long id, String nome, String email, String senha, UserRole role, String image) {
		super();
		this.id = id;
		this.username = nome;
		this.email = email;
		this.userPassword = senha;
		this.roles = role;
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return userPassword;
	}
	public void setPassword(String password) {
		this.userPassword = password;
	}
	public UserRole getRole() {
		return roles;
	}
	public void setRole(UserRole role) {
		this.roles = role;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(this.roles == UserRole.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));			
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	
	
}
