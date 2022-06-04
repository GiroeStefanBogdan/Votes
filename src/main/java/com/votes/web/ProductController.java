package com.votes.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.votes.entity.Product;
import com.votes.entity.User;
import com.votes.repositories.ProductRepository;
import com.votes.service.ProductService;

import javassist.NotFoundException;

@Controller
public class ProductController {
	private Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;	
	@Autowired
	private ProductRepository productRepo;

	
	
	
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable Long productId, ModelMap model, HttpServletResponse response) throws IOException {
	 Optional<Product> productOpt = productRepo.findByIdWithUser(productId);
	 
	 if(productOpt.isPresent()) {
		 Product product = productOpt.get();
		 model.put("product", product);
	 }else {	
		 response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + productId + " was not found");
		 return "product";
	 }
	 
		return "product";
	}
	
	@GetMapping("/p/{productName}")
	public String productUserView (@PathVariable String productName, ModelMap model) {
		if(productName != null) {
			try {
				String decodedProductName = URLDecoder.decode(productName, StandardCharsets.UTF_8.name());
				Optional<Product> productOpt = productRepo.findByName(decodedProductName);
				if(productOpt.isPresent()) {
					model.put("product", productOpt.get());
				}
			} catch (UnsupportedEncodingException e) {
				
				log.error("There was an error decoding a product URL", e);
			}
		}
		return "productUserView";
	}
	
	@PostMapping("/products/{productId}")
	public String saveProduct(@PathVariable Long productId, Product product) {
		product = productRepo.save(product);
		System.out.println(product);
		return "redirect:/products/" + product.getId();
	}
	
	@DeleteMapping("/products/{productId}/delete")
	public ResponseEntity<Long> deleteProduct(@PathVariable(value="Id") Long productId, Product product, @PathVariable Long userId, User user) {
		productRepo.deleteById(product.getId());
		return ResponseEntity.ok(productId);
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
