package com.votes.service;

import com.votes.entity.Role;
import com.votes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.votes.entity.User;
import com.votes.repositories.UserRepository;
import com.votes.security.Authority;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User save(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user); 
		user.getAuthorities().add(authority);
		Role role = roleRepo.getById(2L);
		user.setRole(role);

		//System.out.println("UserService roleRepo." + roleRepo.getById(2L).getRole());
		return userRepo.save(user);
		
	}
}
