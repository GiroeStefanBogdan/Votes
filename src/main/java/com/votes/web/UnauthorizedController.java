package com.votes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnauthorizedController {
	@GetMapping("/Unauthorized")
	public String Unauthorized() {
		return "Unauthorized";
	}

}
