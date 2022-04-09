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
	
		boolean isPasswordMatches = encoder.matches(
		        "Gs199042",
		        "$2a$10$cAR7sSb.lFIFMdwtd/sGIuY6FLOsCZfz33HQIr/RI0IJcRjPWYIpm"
		);


		if (isPasswordMatches) { // correct password
		 System.out.println("Da, aia e parola");
		
		//System.out.println(encodedPassword);
		
		assertThat(rawPassword, not(encodedPassword));
		
		}else {
			System.out.println("Nu e buna parola");
		}

} 
}