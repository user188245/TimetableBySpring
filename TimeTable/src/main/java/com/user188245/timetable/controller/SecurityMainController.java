package com.user188245.timetable.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.user188245.timetable.Constant;
import com.user188245.timetable.model.dto.User;

@Controller
public class SecurityMainController {
	
	@Autowired
	OAuth2AuthorizedClientService authorizedClientService;
	
	@GetMapping(value = "/index")
    public String index(Principal principal, Model model) {
		if(principal instanceof OAuth2AuthenticationToken) {
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)principal;
			if(token.getPrincipal() instanceof User) {
				User user = (User)token.getPrincipal();
				if(user.checkRegistrationRequired()) {
					model.addAttribute("name", user.getEmail());
					return "oauth_registration";
				}
			}
			else
				return "index";
		}
		return "index";
    }

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
	
	@GetMapping(value ="/signup/social")
	public String socialSignup(Principal principal) {
		if(principal instanceof OAuth2AuthenticationToken) {
			System.out.println("good");
		}else
			System.out.println("bad");
        return "/";
    }

}
