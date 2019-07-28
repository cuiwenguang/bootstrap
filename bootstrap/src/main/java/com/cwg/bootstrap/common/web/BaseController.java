package com.cwg.bootstrap.common.web;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
	public JsonResult success(Object data) {
		JsonResult result = new JsonResult();
		result.setCode(200);
		result.setData(data);
		return result;
	}
	public JsonResult success(Object data,String msg) {
		JsonResult result = new JsonResult();
		result.setCode(200);
		result.setData(data);
		result.setMsg(msg);
		return result;
	}
	
	public JsonResult fail(String msg) {
		JsonResult result = new JsonResult();
		result.setCode(410);
		result.setMsg(msg);
		return result;
	}
}
