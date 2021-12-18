package com.chat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@GetMapping("/listUsersByRole")
	public List<User> listUsersByRole(@RequestHeader String authorization,
			@RequestParam(value = "limit", defaultValue = "0") String limit,
			@RequestParam(value = "role", defaultValue = "") String role) {

		List<User> users = new ArrayList<User>();
		return users;
	}
}