package com.votes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votes.entity.Comment;
import com.votes.entity.Feature;
import com.votes.entity.Product;
import com.votes.entity.User;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p"
		+ " join fetch p.user" 
		+ " where p.id = :id")
	Optional<Product> findByIdWithUser(@Param("id") Long id);
	
	//select * from product where user = :user
	List<Product> findByUser (@Param("month") User user);
	
	//List<Feature> findById(Product productId);
	//void deleteById(Long id);
	Optional<Product> findById(Long Id);

	Optional<Product> findByName(String name);
	
	
}
