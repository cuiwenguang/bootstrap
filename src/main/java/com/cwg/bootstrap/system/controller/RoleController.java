package com.cwg.bootstrap.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwg.bootstrap.system.model.Role;
import com.cwg.bootstrap.system.service.IRoleService;
import com.cwg.bootstrap.web.BaseController;
import com.cwg.bootstrap.web.JsonResult;

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
			return fail();
		}
	}
	
	@PostMapping("/")
	public JsonResult post(@RequestBody Role role) {
		int row = roleService.add(role);
		if (row>0) {
			return success(row);
		}else {
			return fail();
		}
	}
	
	@DeleteMapping("/{id}")
	public JsonResult delete(@PathVariable("id")Integer id) {
		int row = roleService.remove(id);
		if (row>0) {
			return success(row);
		}else {
			return fail();
		}
	}
	
	@GetMapping("/{id}")
	public JsonResult get(@PathVariable("id")Integer id) {
		Role role = roleService.getById(id);
		return success(role);
	}
	
	@GetMapping("/")
	public JsonResult getList() {
		List<Role> roles = roleService.getList(new Role());
		return success(roles);
	}
	
	@PostMapping("/assign/{id}")
	public JsonResult assignResource(@PathVariable("id")Integer id, 
			         				 @RequestBody Map<String, List<Integer>> map) {
		int rows = roleService.assign(id, map.get("resourceIds"));
		if(rows > 0) {
			return success();
		}else {
			return fail();
		}
	}
	
}
