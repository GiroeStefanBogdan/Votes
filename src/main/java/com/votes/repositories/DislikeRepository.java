package com.votes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votes.entity.Dislike;
import com.votes.entity.Like;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {

	

}
