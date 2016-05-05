package com.xlinyu.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private static  final Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping({"/", "/index"})
	public String index(){
		logger.info(".......enter index page.....");
		return "index";
	}
	
}
