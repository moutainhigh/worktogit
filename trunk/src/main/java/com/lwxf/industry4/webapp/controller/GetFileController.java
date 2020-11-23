package com.lwxf.industry4.webapp.controller;


import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import org.apache.shiro.util.AntPathMatcher;

import com.lwxf.industry4.webapp.common.constant.WebConstant;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/11 0011 16:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "GetFileController",tags ={"图片显示接口"} )
@RestController(value = "GetFileController")
@RequestMapping(value = "/prod/**", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class GetFileController {
	@Value(value = "${upload.file.upload.component.rootPath}")
	private String hostpath;
	@GetMapping
	public void view(HttpServletRequest request, HttpServletResponse response) {
		String imgPath = extractPathFromPattern(request);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			imgPath = imgPath.replace("..", "");
			if (imgPath.endsWith(",")) {
				imgPath = imgPath.substring(0, imgPath.length() - 1);
			}
			response.setContentType("image/jpeg/doc/docx/txt;charset=utf-8");
			String imgurl =File.separator +  hostpath+ "/prod/"+  imgPath;
			File file=new File(imgurl);
			inputStream = new BufferedInputStream(new FileInputStream(file));
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, len);
			}
			response.flushBuffer();
		} catch (IOException e) {
			 e.printStackTrace();
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	private static String extractPathFromPattern(final HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}
}
