package com.cwg.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwg.bootstrap.common.web.BaseController;
import com.cwg.bootstrap.common.web.JsonResult;
import com.cwg.bootstrap.domain.Role;
import com.cwg.bootstrap.service.IRoleService;

@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
	@Autowired
	IRoleService roleService;
	
	@PutMapping("/")
	public JsonResult put(@RequestBody Role role) {
		int row = roleService.update(role);
		if (row>0) {
			return success(row);
		}else {
			return fail("更新失败");
		}
	}
	
	@PostMapping("/")
	public JsonResult post(@RequestBody Role role) {
		int row = roleService.add(role);
		if (row>0) {
			return success(row);
		}else {
			return fail("添加失败");
		}
	}
	
	@DeleteMapping("/{id}")
	public JsonResult delete(@PathVariable("id")Integer id) {
		int row = roleService.remove(id);
		if (row>0) {
			return success(row);
		}else {
			return fail("删除失败");
		}
	}
	
	@GetMapping("/{id}")
	public JsonResult get(@PathVariable("id")Integer id) {
		Role role = roleService.getById(id);
		return success(role);
	}
}
