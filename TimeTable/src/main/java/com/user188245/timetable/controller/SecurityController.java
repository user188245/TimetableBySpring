package com.user188245.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("id", "username");
		model.addAttribute("password","password");
        return "login";
    }
	
	
	@GetMapping(value = "/signup")
	public String signup(Model model) {
		model.addAttribute("id", "username");
		model.addAttribute("password","password");
		model.addAttribute("passwordValidation","passwordValidation");
		model.addAttribute("email", "email");
		model.addAttribute("description","description");
        return "signup";
    }
	

}
