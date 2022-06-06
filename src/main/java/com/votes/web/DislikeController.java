package com.votes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.votes.entity.Product;
import com.votes.entity.User;
import com.votes.service.DislikeService;
import com.votes.service.LikeService;

@Controller
public class DislikeController {
	@Autowired
	private DislikeService dislikeService;
	@GetMapping("/products/{product}/dislike")
	public String likeProduct(@AuthenticationPrincipal User user, @PathVariable Product product) {
		dislikeService.toggleDislike(product, user);
		return "redirect:/ViewAllProducts";
	}

}
