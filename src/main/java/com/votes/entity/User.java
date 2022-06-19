package com.votes.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.votes.security.Authority;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)//strategy=... is for autoincrement id
	private Long id;
	private String username;
	private String password;
	private String name;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="user")
	private Set<Authority> authorities = new HashSet<>();
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
	public Set<Product> products = new HashSet<>();
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
	private Set<Feature> features = new HashSet<>();
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
	public Set<Like> likes = new HashSet<>();
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
	public Set<Dislike> dislike = new HashSet<>();




	@ManyToOne
	@JoinColumn(name = "roles_id")
	private Role roles;

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}


	public Role getRole() {
		return roles;
	}

	public void setRole(Role role) {
		this.roles = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

public Set<Product> getProducts() {
	return products;
}
public void setProducts(Set<Product> products) {
	this.products = products;
}


public Set<Like> getLikes() {
	return likes;
}
public void setLikes(Set<Like> likes) {
	this.likes = likes;
}
	
	

	public Set<Authority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", authorities=" + authorities + "]";

	
	
	
	}

	public Set<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	public Set<Dislike> getDislike() {
		return dislike;
	}
	public void setDislike(Set<Dislike> dislike) {
		this.dislike = dislike;
	}

}
