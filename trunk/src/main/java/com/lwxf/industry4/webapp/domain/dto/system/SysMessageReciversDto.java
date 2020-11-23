package com.lwxf.industry4.webapp.domain.dto.system;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessage;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessageRecivers;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;

/**
 * 功能：sys_message_recivers 实体类
 *
 * @author：zhangxiaolin(3965488qq.com)
 * @created：2019-12-09 04:42 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="消息接收者实体", description="消息接收者实体")
public class SysMessageReciversDto extends SysMessageRecivers {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "消息标题")
	private String title;
	@ApiModelProperty(value = "内容")
	private String content;

    public SysMessageReciversDto() {
     }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
