package com.user188245.timetable.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = "/index")
    public String index(Model model) {
        return "index";
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
