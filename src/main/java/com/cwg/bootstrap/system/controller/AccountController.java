package com.cwg.bootstrap.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwg.bootstrap.system.auth.JwtUtil;
import com.cwg.bootstrap.system.model.User;

@RestController
@RequestMapping("/")
public class AccountController {
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
			return JwtUtil.sign(user.getUserName(), user.getPassword());
		}catch (Exception e) {
			return "";
		}
	}
	
	@GetMapping("/logout")
	public void logout() {
	}
}
