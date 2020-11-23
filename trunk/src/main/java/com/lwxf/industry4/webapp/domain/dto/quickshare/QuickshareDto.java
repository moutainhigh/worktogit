package com.lwxf.industry4.webapp.domain.dto.quickshare;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Quickshare;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuickshareComment;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuicksharePraise;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/7 0007 14:37
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class QuickshareDto extends Quickshare{
	private List<QuickshareCommentDto> comments;
	private List<UploadFiles> files;
	private List<QuicksharePraiseDto> praises;
    private Integer commentSize;
    private Integer praiseSize;
	private boolean isPraise;
	private String creatorName;
	private String creatorAvatar;

	public List<QuickshareCommentDto> getComments() {
		return comments;
	}

	public void setComments(List<QuickshareCommentDto> comments) {
		this.comments = comments;
	}

	public List<UploadFiles> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFiles> files) {
		this.files = files;
	}

	public List<QuicksharePraiseDto> getPraises() {
		return praises;
	}

	public void setPraises(List<QuicksharePraiseDto> praises) {
		this.praises = praises;
	}
	public Integer getCommentSize() {
		return commentSize;
	}

	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}

	public Integer getPraiseSize() {
		return praiseSize;
	}

	public void setPraiseSize(Integer praiseSize) {
		this.praiseSize = praiseSize;
	}

	public boolean isPraise() {
		return isPraise;
	}

	public void setPraise(boolean praise) {
		isPraise = praise;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorAvatar() {
		return creatorAvatar;
	}

	public void setCreatorAvatar(String creatorAvatar) {
		this.creatorAvatar = creatorAvatar;
	}
}
