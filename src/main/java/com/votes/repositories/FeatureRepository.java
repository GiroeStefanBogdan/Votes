package com.votes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votes.entity.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>{
	
}
