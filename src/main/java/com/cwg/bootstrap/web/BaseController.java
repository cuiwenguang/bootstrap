package com.cwg.bootstrap.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

public class BaseController {
	@Autowired
	private HttpServletRequest request;
	
	public JsonResult success() {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.SUCCESS);
		return result;
	}
	
	public JsonResult success(String msg) {
		JsonResult result = new JsonResult();
		result.setMsg(msg);
		return result;
	}
	
	public JsonResult success(Object data) {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.SUCCESS);
		result.setData(data);
		return result;
	}
	
	public JsonResult success(Object data,String msg) {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.SUCCESS);
		result.setData(data);
		result.setMsg(msg);
		return result;
	}
	
	public JsonResult fail() {
		JsonResult result = new JsonResult();
		result.setMsg("request failed");
		return result;
	}
	
	public JsonResult fail(String msg) {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.FAIL);
		result.setMsg(msg);
		return result;
	}
	
	public JsonResult error(String msg) {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.ERROR);
		result.setMsg(msg);
		return result;
	}
	
	public void pager() {
		int num = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		PageHelper.startPage(num, size);
	}
}
