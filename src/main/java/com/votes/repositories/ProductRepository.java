package com.votes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votes.entity.Product;
import com.votes.entity.User;

public interface ProductRepository extends JpaRepository<Product, Long>{

	//select * from product where user = :user
	List<Product> findByUser(User user);
}
