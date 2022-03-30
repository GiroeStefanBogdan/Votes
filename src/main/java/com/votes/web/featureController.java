package com.votes.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.votes.entity.Feature;
import com.votes.service.FeatureService;

@RequestMapping("/products/{productId}/features")
@Controller
public class featureController {

	Logger log = LoggerFactory.getLogger(featureController.class);
	@Autowired	
	private FeatureService featureService;
	
	@PostMapping("")
	public String createFeature(@PathVariable Long productId) {
		Feature feature = featureService.createFeature(productId);
		return "redirect:/products/" + productId + "/features/" + feature.getId();
	}

	@GetMapping("{featureId}")
	public String getFeature(ModelMap model, @PathVariable Long productId, @PathVariable Long featureId) {
		Optional<Feature> featureOpt = featureService.findById(featureId);
		if(featureOpt.isPresent()) {
			model.put("feature", featureOpt.get());
		}
		return "feature";
	}
	
	@PostMapping("{featureId}")
	public String updateFeature (Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
	feature = featureService.save(feature);
		
		String encodedProductName;
		try {
			encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.warn("Unable to encode the URL String: " + feature.getProduct().getName() + "Redirecting to dashboard insted of the intended product user view page.");
			return "redirect:/dashboard";
		}
		
		return "redirect:/p/"+encodedProductName;
	}
	
}
