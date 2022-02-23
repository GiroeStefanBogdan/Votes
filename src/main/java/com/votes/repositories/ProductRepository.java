package com.votes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.votes.entity.Product;
import com.votes.entity.User;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p"
		+ " join fetch p.user" 
		+ " where p.id = :id")
	Optional<Product> findByIdWithUser(@Param("id") Long id);
	
	//select * from product where user = :user
	List<Product> findByUser (@Param("month") User user);
}
