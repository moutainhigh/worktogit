package com.lwxf.industry4.webapp.controller.admin.factory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.industry4.webapp.common.constant.WebConstant;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/13/013 14:25
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class FactorySysController {

	@GetMapping("/sys/touch")
	public String touch() {
		/*String userId = WebUtils.getCurrUserId();
		if (userId != null) {
			String sessionId = WebUtils.getHttpSession().getId();
			String key = WebConstant.MQ_TOKEN_PREFIX.concat(sessionId);
		}*/
		return WebConstant.REQUEST_RESULT_AJAX_EMPTY_OBJ;
	}
}
