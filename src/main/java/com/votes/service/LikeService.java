package com.votes.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votes.entity.Feature;
import com.votes.entity.Like;
import com.votes.entity.Product;
import com.votes.entity.User;
import com.votes.repositories.FeatureRepository;
import com.votes.repositories.LikeRepository;
import com.votes.repositories.ProductRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepo;
	
	public Like createLike(Product product, User user) {
		if(!likeExist(product, user)) {
			Like like = new Like();
			like.setProduct(product);
			like.setUser(user);
			likeRepo.save(like);
			return like;
		}else {
			return null;
		}
	
		
		
	}
	public int likeCount(Product product) {
		return product.getLikes().size();
	}
	
	private Boolean likeExist(Product product, User user) {
	Set<Like> productLikes = product.getLikes();
	Boolean found = false;
	for(Like like: productLikes) {
		if(like.getUser().getId()==user.getId()) {
			found = true;
		}
	};
		return found;
	}

	

	
}
