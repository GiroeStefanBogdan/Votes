package com.votes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votes.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
