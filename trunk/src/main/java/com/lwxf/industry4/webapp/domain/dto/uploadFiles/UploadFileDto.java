package com.lwxf.industry4.webapp.domain.dto.uploadFiles;

import com.lwxf.industry4.webapp.common.utils.WebUtils;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/14 0014 11:38
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class UploadFileDto {
	private String realPath;

	public String getRealPath() {
		return  WebUtils.getDomainUrl()+this.realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
}
