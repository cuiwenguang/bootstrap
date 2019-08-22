package com.cwg.bootstrap.system.controller;

import java.util.Random;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwg.bootstrap.system.model.User;
import com.cwg.bootstrap.system.service.IUserService;
import com.cwg.bootstrap.web.BaseController;
import com.cwg.bootstrap.web.JsonResult;

@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/")
	public JsonResult create(@Valid @RequestBody User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return fail(bindingResult.getFieldError().getDefaultMessage());
		}
		if(userService.create(user)) {
			return success("success");
		}else {
			return fail("fail");
		}
	}
	
	@GetMapping("/me")
	public JsonResult myInfo() {
		Subject subject = SecurityUtils.getSubject();
		return success(subject.getPrincipal());
	}
}
