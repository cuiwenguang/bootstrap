package com.cwg.bootstrap.system.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwg.bootstrap.system.model.User;

@RestController
@RequestMapping("/")
public class AuthController {
	@RequestMapping("/login")
	public String login(@RequestBody User user) {
		return "";
	}
}
