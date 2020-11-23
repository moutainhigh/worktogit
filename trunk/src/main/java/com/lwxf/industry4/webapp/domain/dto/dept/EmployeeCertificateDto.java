package com.lwxf.industry4.webapp.domain.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeCertificate;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/22/022 11:44
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "员工证书信息",discriminator = "员工证书信息")
public class EmployeeCertificateDto extends EmployeeCertificate {
	@ApiModelProperty(value = "资源文件")
	private List<UploadFiles> uploadFilesList;

	public List<UploadFiles> getUploadFilesList() {
		return uploadFilesList;
	}

	public void setUploadFilesList(List<UploadFiles> uploadFilesList) {
		this.uploadFilesList = uploadFilesList;
	}
}
