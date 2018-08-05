package com.user188245.timetable.controller;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = "/")
    public String root(Principal principal) {
		return "redirect:/index";
    }
	
	@GetMapping(value = "/timetable")
	public String timetable(Model model) {
        return "timetable";
    }
	
	@GetMapping(value = "/lecture")
	public String lecture(Model model) {
        return "lecture";
    }
	
	@GetMapping(value = "/calender")
	public String calender(Model model) {
        return "calender";
    }
	
	
	
}
