package com.lwxf.industry4.webapp.domain.dto.dispatchList;

import com.lwxf.industry4.webapp.domain.entity.assemble.ConstructionInspection;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/29 0029 14:54
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ConstructionInspectionDto extends ConstructionInspection {
	private String checkerName;
	private String reviewerName;

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
}
