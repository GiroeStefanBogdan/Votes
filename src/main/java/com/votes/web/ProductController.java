package com.votes.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.votes.entity.Product;
import com.votes.entity.User;
import com.votes.repositories.ProductRepository;

import javassist.NotFoundException;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/products")
	public String getProducts(ModelMap model) {
		return "product";	
	}
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable Long productId, ModelMap model, HttpServletResponse response) throws IOException {
	 Optional<Product> productOpt = productRepo.findById(productId);
	 
	 if(productOpt.isPresent()) {
		 Product product = productOpt.get();
		 model.put("product", product);
	 }else {	
		 response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + productId + " was not found");
		 return "product";
	 }
	 
		return "product";
	}
	
	@PostMapping("/products/{productId}")
	public String saveProduct(@PathVariable Long productId, Product product) {
		product = productRepo.save(product);
		
		return "redirect:/products/" + product.getId();
	}
	
	@PostMapping("/products")
	public String createProducts(@AuthenticationPrincipal User user) {
		Product product = new Product();
		
		product.setPublished(false);
		product.setUser(user);
		
		
		product = productRepo.save(product);
		
		return "redirect:/products/" + product.getId();
	}
	
	
}
