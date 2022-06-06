package com.votes.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PreRemove;


@Entity
public class VoteClass {

	
	private VoteId pk; //VoteId will be a composite key with user_id and feature_id
	private Boolean upvote;

	public Boolean getUpvote() {
		return upvote;
	}

	public void setUpvote(Boolean upvote) {
		this.upvote = upvote;
	}

	@EmbeddedId
	public VoteId getPk() {
		return pk;
	}

	public void setPk(VoteId pk) {
		this.pk = pk;
	}
	@PreRemove
	public void preRemove() {
		this.pk = null;
		
	}
}
