package com.votes.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votes.entity.Dislike;
import com.votes.entity.Feature;
import com.votes.entity.Like;
import com.votes.entity.Product;
import com.votes.entity.User;
import com.votes.repositories.DislikeRepository;
import com.votes.repositories.FeatureRepository;
import com.votes.repositories.LikeRepository;
import com.votes.repositories.ProductRepository;

@Service
public class DislikeService {

	@Autowired
	private DislikeRepository DislikeRepo;
	@Autowired
	private LikeService likeService;
	
	public void toggleDislike(Product product, User user) {
		if(findDislike(product, user)!= null) {//daca i-a dat dislike
			deleteDislike(product, user);
		}else {
			createDislike(product, user);
			if(likeService.findLike(product, user)!=null) {
					likeService.deleteLike(product, user);	
			}
		}
	}
	
	private Dislike createDislike(Product product, User user) {
		
			Dislike dislike = new Dislike();
			dislike.setProduct(product);
			dislike.setUser(user);
			DislikeRepo.save(dislike);
			return dislike;	
	}
	
	public void deleteDislike(Product product, User user) {
		Dislike dislike = findDislike(product, user);
		DislikeRepo.deleteById(dislike.getId());
		
	}
	
	
	
	public Dislike findDislike(Product product, User user) {
	Set<Dislike> productDislikes = product.getDislikes();
	Dislike found = null;
	for(Dislike dislike: productDislikes) {
		if(dislike.getUser().getId()==user.getId()) {
			found = dislike;
		}
	};
		return found;
	}

	

	
}
