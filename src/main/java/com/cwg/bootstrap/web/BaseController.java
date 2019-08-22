package com.cwg.bootstrap.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

public class BaseController {
	@Autowired
	private HttpServletRequest request;
	
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
	
	public JsonResult fail(String msg) {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.FAIL);
		result.setMsg(msg);
		return result;
	}
	
	public JsonResult erro(String msg) {
		JsonResult result = new JsonResult();
		result.setCode(HttpStatus.ERROR);
		result.setMsg(msg);
		return result;
	}
	
	public void pager() {
		int num = Integer.parseInt(request.getParameter("pageNum"));
		int size = Integer.parseInt(request.getParameter("pageSize"));
		PageHelper.startPage(num, size);
	}
}
