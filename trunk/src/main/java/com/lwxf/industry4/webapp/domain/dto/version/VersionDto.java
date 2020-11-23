package com.lwxf.industry4.webapp.domain.dto.version;

import com.lwxf.industry4.webapp.domain.entity.version.UpdateVersion;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/5/6 0006 11:23
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class VersionDto extends UpdateVersion {
	private Integer isNew;

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
}
