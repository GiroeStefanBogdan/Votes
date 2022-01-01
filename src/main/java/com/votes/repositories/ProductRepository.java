package com.votes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votes.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	
}
