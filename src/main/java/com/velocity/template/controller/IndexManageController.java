package com.velocity.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class IndexManageController extends BaseController {

	@RequestMapping("")
	public String page() {
		return "index";
	}
}
