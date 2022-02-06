package com.votes.service;


import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




class UserDetailsServiceTest {

	@Test
	public void generate_encrypted_password() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "asdfasdf";
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println(encodedPassword);
		
		assertThat(rawPassword, not(encodedPassword));
		
		
	}

} 
