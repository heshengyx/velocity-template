package com.velocity.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myself.common.exception.ServiceException;
import com.myself.common.message.JsonMessage;
import com.velocity.template.data.ClazzAttribute;
import com.velocity.template.entity.Clazz;
import com.velocity.template.enums.AttributeTypeEnum;
import com.velocity.template.page.IPage;
import com.velocity.template.param.ClazzQueryParam;
import com.velocity.template.service.IClazzService;

@Controller
@RequestMapping("/manage/clazz")
public class ClazzManageController extends BaseController {

	private final static Logger logger = LoggerFactory
			.getLogger(ClazzManageController.class);
	
	@Autowired
	private IClazzService clazzService;
	
	@RequestMapping("")
	public String page() {
		return "clazz-list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		AttributeTypeEnum[] types = AttributeTypeEnum.values();
		model.addAttribute("types", types);
		return "clazz-add";
	}
	
	@RequestMapping("/edit")
	public String edit() {
		return "clazz-edit";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(ClazzQueryParam param, int page, int rows) {
		JsonMessage jMessage = new JsonMessage();
		try {
			IPage<Clazz> datas = clazzService.query(param, page, rows);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setData(datas);
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	
	@RequestMapping("/getData")
	@ResponseBody
	public Object getData(String id) {
		JsonMessage jMessage = new JsonMessage();
		Clazz data = null;
		try {
			data = clazzService.getDataById(id);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setData(data);
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Object save(ClazzAttribute param) {
		JsonMessage jMessage = new JsonMessage();
		try {
			clazzService.save(param);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setMessage("新增成功");
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(Clazz param) {
		JsonMessage jMessage = new JsonMessage();
		try {
			clazzService.update(param);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setMessage("更新成功");
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		JsonMessage jMessage = new JsonMessage();
		try {
			clazzService.deleteById(id);
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
}