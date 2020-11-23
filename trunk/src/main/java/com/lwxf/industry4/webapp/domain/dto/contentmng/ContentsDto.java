package com.lwxf.industry4.webapp.domain.dto.contentmng;

import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import io.swagger.annotations.ApiModel;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/27 0027 13:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value=" 内容管理模型",description="内容管理模型")
public class ContentsDto extends Contents {
	private String typeName;
	private String fullPath;
	private String content;
	private String publisherName;
	private String coverId;
	private String[] contentdImgsId;
	private String creatorName;

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getCoverId() {
		return coverId;
	}

	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}

	public String[] getContentdImgsId() {
		return contentdImgsId;
	}

	public void setContentdImgsId(String[] contentdImgsId) {
		this.contentdImgsId = contentdImgsId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getContent() {return content;}

	public void setContent(String content) {this.content = content;}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
