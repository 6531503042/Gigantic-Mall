package com.gigantic.admin.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/")
	public MyResponseObject viewHomePage() {
		MyResponseObject responseObject = new MyResponseObject();
		responseObject.setMessage("Management-API Correct");
		return responseObject;
	}

	private static class MyResponseObject {
		private String message;

		// Getter and setter for 'message'
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
