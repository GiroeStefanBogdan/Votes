package com.votes.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.votes.security.Authority;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User{
	
	private Long id;
	private String username;
	private String password;
	private String name;
	private Set<Authority> authorities = new HashSet<>();
	public Set<Product> products = new HashSet<>();
	private Set<Feature> features = new HashSet<>();
	public Set<Like> likes = new HashSet<>();
	public Set<Dislike> dislike = new HashSet<>();
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)//strategy=... is for autoincrement id	
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
	
@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
public Set<Product> getProducts() {
	return products;
}
public void setProducts(Set<Product> products) {
	this.products = products;
}

@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
public Set<Like> getLikes() {
	return likes;
}
public void setLikes(Set<Like> likes) {
	this.likes = likes;
}
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="user")
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
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
	public Set<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
	public Set<Dislike> getDislike() {
		return dislike;
	}
	public void setDislike(Set<Dislike> dislike) {
		this.dislike = dislike;
	}
	@PreRemove
	public void preRemove() {
		this.likes = null;
		this.products = null;
		this.features = null;
		this.authorities = null;
		this.dislike = null;
		
	}
}
