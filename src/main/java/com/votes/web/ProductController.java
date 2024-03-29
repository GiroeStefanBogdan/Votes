package com.votes.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.votes.entity.Role;
import com.votes.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
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
	
	@GetMapping("/ViewAllProducts")
		public String ViewAllProducts(ModelMap model, @AuthenticationPrincipal User user, @AuthenticationPrincipal Role role) {
		List<Product> products = productRepo.findAll();
		model.put("allProducts", products);
		model.put("user", user);
		//model.put("role", roleRepo.getById(user.getId()).getRole());

		model.put("role2", user.getId());

		return "ViewAllProducts";
		}
	
	
	@GetMapping("/p/{productName}")
	public String productUserView (@PathVariable String productName, ModelMap model,@AuthenticationPrincipal User user) {
		if(productName != null) {
			try {
				String decodedProductName = URLDecoder.decode(productName, StandardCharsets.UTF_8.name());
				Optional<Product> productOpt = productRepo.findByName(decodedProductName);
				if(productOpt.isPresent()) {
					model.put("product", productOpt.get());
					model.put("user", user);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				log.error("There was an error decoding a product URL", e);
			}
		}
		model.put("role", user.getId());
		return "productUserView";
	}
	
	@PostMapping("/products/{productId}")
	public String saveProduct(@PathVariable Long productId, Product product) {
		product = productRepo.save(product);
		System.out.println(product);
		return "redirect:/products/" + product.getId();
	}
	@GetMapping("/products/{productId}/delete")
		public String deleteAProduct(@PathVariable Product productId) {
			productRepo.deleteById(productId.getId());
			return "redirect:/dashboard";
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
