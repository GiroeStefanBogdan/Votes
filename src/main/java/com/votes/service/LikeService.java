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
public class LikeService {

	@Autowired
	private LikeRepository likeRepo;
	@Autowired DislikeService dislikeService;
	
	public void toggleLike(Product product, User user) {
		if(findLike(product, user)!= null) {//daca exista like
			deleteLike(product, user);
		}else {
			createLike(product, user);
			if(dislikeService.findDislike(product, user)!=null) {
				dislikeService.deleteDislike(product, user);
			}
			}
		}
	
	
	private Like createLike(Product product, User user) {
		
			Like like = new Like();
			like.setProduct(product);
			like.setUser(user);
			likeRepo.save(like);
			return like;	
	}
	
	public void deleteLike(Product product, User user) {
		Like like = findLike(product, user);
		likeRepo.deleteById(like.getId());
		System.out.println(like);
	}
	
	
	public Like findLike(Product product, User user) {
	Set<Like> productLikes = product.getLikes();
	Like found = null;
	for(Like like: productLikes) {
		if(like.getUser().getId()==user.getId()) {
			found = like;
		}
	};
		return found;
	}

	

	
}
