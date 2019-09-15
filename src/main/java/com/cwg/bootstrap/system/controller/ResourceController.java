package com.cwg.bootstrap.system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwg.bootstrap.system.model.Resource;
import com.cwg.bootstrap.system.service.IResourceService;
import com.cwg.bootstrap.web.BaseController;
import com.cwg.bootstrap.web.JsonResult;

@RestController
@RequestMapping("/sys/resource")
public class ResourceController extends BaseController {
	@Autowired
	IResourceService resourceService;
	
	@PostMapping("/")
	public JsonResult post(@Valid @RequestBody Resource resource, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return fail(bindingResult.getFieldError().getDefaultMessage());
		}
		int row = resourceService.add(resource);
		if(row > 0) {
			return success(row);
		}else {
			return fail();
		}
	}
	
	@PutMapping("/")
	public JsonResult put(@Valid @RequestBody Resource resource, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return fail(bindingResult.getFieldError().getDefaultMessage());
		}
		int row = resourceService.update(resource);
		if(row > 0) {
			return success(row);
		}else {
			return fail();
		}
	}
	
	@GetMapping("/tree")
	public JsonResult getTree() {
		List<Resource> resources = resourceService.getTreeList();
		return success(resources);
	}
}
