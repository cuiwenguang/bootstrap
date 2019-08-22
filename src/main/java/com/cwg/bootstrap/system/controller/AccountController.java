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
import com.cwg.bootstrap.system.utils.ShiroUtils;
import com.cwg.bootstrap.web.BaseController;
import com.cwg.bootstrap.web.JsonResult;

@RestController
@RequestMapping("/")
public class AccountController extends BaseController {
	
	@PostMapping("/login")
	public JsonResult login(@RequestBody User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
			String tokenString = JwtUtil.sign(user.getUserName(), ShiroUtils.getSalt());
			return success(tokenString);
		}catch (Exception e) {
			return fail("验证失败");
		}
	}
	
	@GetMapping("/logout")
	public JsonResult logout() {
		SecurityUtils.getSubject().logout();
		return success("注销完成");
	}
}
