package com.votes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votes.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{	

	List<Comment> findByFeatureId(Long featureId);
}

        
