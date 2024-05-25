package com.gigantic.admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MainController {

	@GetMapping("/")
	public String viewHomePage() {
		return "Management-API Correct";
	}
}

