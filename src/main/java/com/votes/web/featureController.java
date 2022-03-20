package com.votes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.votes.entity.Feature;
import com.votes.service.FeatureService;

//@RequestMapping("/products/productId/features")
@Controller
public class featureController {
	
	@Autowired
	private FeatureService featureService;
	
	@PostMapping("/products/productId/features")
	public String createFeature(@PathVariable Long productId) {
		Feature feature = featureService.createFeature(productId);
		return "redirect:/products/" + productId + "/features/" + feature.getId();
	}

	@GetMapping("/products/productId/features/{featureId}")
	public String getFeature(@PathVariable Long productId, @PathVariable Long featureId) {
		return "feature";
	}
}
