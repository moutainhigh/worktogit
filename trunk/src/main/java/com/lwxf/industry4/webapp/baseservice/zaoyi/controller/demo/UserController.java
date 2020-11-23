package com.lwxf.industry4.webapp.baseservice.zaoyi.controller.demo;

import javax.annotation.Resource;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.baseservice.zaoyi.facade.demo.UserFacade;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/3/1 0001 13:17
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController(value = "zyUserController")
@RequestMapping(value = "/api/zy/users",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class UserController {
	@Resource(name = "zyUserFacade")
	private UserFacade userFacade;
	JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
	@GetMapping
	public RequestResult findAll(@RequestParam(required = false) String params){
		if(null == params){
			return this.userFacade.findAll();
		}
		HashMap paramsMap = jsonMapper.fromJson(params,HashMap.class);
		return this.userFacade.findListByParams(paramsMap);
	}
}
