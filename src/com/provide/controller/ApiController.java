package com.provide.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.function.service.ApiService;

/**
 * MemberAPI
 */
@Controller
public class ApiController extends _BaseController{
	
	@Autowired
	public ApiService apiService;
	
	@RequestMapping(value="domain")
	public @ResponseBody Map result(HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//接口请求，API_ID、版本号、方法名和方法参数校验
		Map checkResultMap = apiService.CheckRequest(request);
		String result = (String)checkResultMap.get("result");
		if(!"1".equals(result) ) //校验不通过
			return checkResultMap;
		
		return apiService.ExcuteRequest(request,response);
	}

}
