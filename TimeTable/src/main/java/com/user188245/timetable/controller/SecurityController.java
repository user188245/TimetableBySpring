package com.user188245.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.user188245.timetable.Constant;
import com.user188245.timetable.model.dto.User;

@Controller
public class SecurityController {

	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("id", Constant.ID);
		model.addAttribute("password",Constant.PASSWORD);
        return "login";
    }
	
	
	@GetMapping(value = "/signup")
	public String signup(Model model) {
		model.addAttribute("id", Constant.ID);
		model.addAttribute("password",Constant.PASSWORD);
		model.addAttribute("passwordValidation",Constant.PASSWORD_CHECK);
		model.addAttribute("email", Constant.EMAIL);
		model.addAttribute("description",Constant.DESCRIPTION);
        return "signup";
    }
	

}
