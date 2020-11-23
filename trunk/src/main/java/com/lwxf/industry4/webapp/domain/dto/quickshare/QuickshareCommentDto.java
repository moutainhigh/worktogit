package com.lwxf.industry4.webapp.domain.dto.quickshare;

import com.lwxf.industry4.webapp.domain.entity.quickshare.QuickshareComment;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/8 0008 8:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class QuickshareCommentDto extends QuickshareComment {
	private String commentName;
	private String reCommentName;

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getReCommentName() {
		return reCommentName;
	}

	public void setReCommentName(String reCommentName) {
		this.reCommentName = reCommentName;
	}
}
