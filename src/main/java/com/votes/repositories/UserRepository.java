package com.votes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votes.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);//Object that make CRUD operation  //Select ..

	 

}
