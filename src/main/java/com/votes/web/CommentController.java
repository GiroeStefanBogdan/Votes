package com.votes.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.votes.entity.Comment;
import com.votes.entity.Feature;
import com.votes.entity.User;
import com.votes.repositories.CommentRepository;
import com.votes.repositories.FeatureRepository;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments")
public class CommentController {
	@Autowired
	public FeatureRepository featureRepo;
	@Autowired
	public CommentRepository commentRepo;
	
	
	@GetMapping("") 
	@ResponseBody
	public List<Comment> getComments(@PathVariable Long featureId){
		List<Comment> findByFeatureId = commentRepo.findByFeatureId(featureId);
		return findByFeatureId;
	}
	
	@PostMapping("")
	public String postComment(@AuthenticationPrincipal User user, @PathVariable Long productId,
						@PathVariable Long featureId,Comment comment, @RequestParam(required=false) Long parentId,@RequestParam(required=false) String childCommentText) {
			Optional<Feature> featureOpt = featureRepo.findById(featureId);
			
			if(parentId!=null) {
				comment = new Comment();
				Optional<Comment> parentCommentOpt = commentRepo.findById(parentId);
				if(parentCommentOpt.isPresent()) {
					comment.setComment(parentCommentOpt.get());					
				}
				comment.setText(childCommentText);
				
			}
			
			
			
				
			if(featureOpt.isPresent()) {
				comment.setFeature(featureOpt.get());
				comment.setUser(user);
				comment.setCreatedDate(new Date());
				
				commentRepo.save(comment);
				
			}
		return "redirect:/products/" + productId + "/features/" + featureId;

	}
}
