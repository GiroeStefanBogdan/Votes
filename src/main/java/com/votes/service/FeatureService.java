package com.votes.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.stereotype.Service;

import com.votes.entity.Feature;
import com.votes.entity.Product;
import com.votes.repositories.FeatureRepository;
import com.votes.repositories.ProductRepository;

@Service
public class FeatureService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private FeatureRepository featureRepo;
	
	public Feature createFeature(Long productId) {
		Feature feature = new Feature();
		
		Optional<Product> productOpt = productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			feature.setProduct(product);
			product.getFeatures().add(feature);
			
			feature.setStatus("Pending Review");
			
			return featureRepo.save(feature); 
		}
		
		return feature;
		
		
	}
}
