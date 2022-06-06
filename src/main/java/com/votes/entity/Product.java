package com.votes.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
@Entity
public class Product {

	private Long Id;
	private String name;
	private User user;
	private Set<Feature> features = new HashSet<>();
	private Boolean published;
	public Set<Like> likes = new HashSet<>();
	public Set<Dislike> dislikes = new HashSet<>();
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")//cascad -> delete all the features if the product is deleted
	public Set<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")//cascad -> delete all the features if the product is deleted
	public Set<Like> getLikes() {
		return likes;
	}
	public void setLikes(Set<Like> likes) {
		this.likes = likes;
	}
	
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	@Override
	public String toString() {
		return "Product [Id=" + Id + ", name=" + name + ", user=" + user + ", features=" + features + ", published="
				+ published + "]";
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")//cascad -> delete all the features if the product is deleted
	public Set<Dislike> getDislikes() {
		return dislikes;
	}
	public void setDislikes(Set<Dislike> dislikes) {
		this.dislikes = dislikes;
	}
	
}
