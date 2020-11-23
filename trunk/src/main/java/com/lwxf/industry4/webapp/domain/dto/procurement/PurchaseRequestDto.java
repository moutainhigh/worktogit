package com.lwxf.industry4.webapp.domain.dto.procurement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseRequest;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/17/017 11:38
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="采购申请单扩展信息",description="采购申请单扩展信息")
public class PurchaseRequestDto extends PurchaseRequest {
	@ApiModelProperty(value="审核人员名称，多个人名组合",name="auditName",example = "")
	private  List<String> auditName;//审核人员名称
	@ApiModelProperty(value="审核意见，多个意见组合",name="auditOpinion",example = "")
	private String auditOpinion;//审核意见
	@ApiModelProperty(value="抄送人",name="copyPerson",example = "")
	private List<String> copyPerson;//抄送人
	@ApiModelProperty(value="采购人名称",name="purchaserName",example = "")
	private String purchaserName;//采购人名称
	@ApiModelProperty(value="供应公司名称",name="supplierName",example = "")
	private String supplierName; //供应公司名称
	@ApiModelProperty(value="申请人名称",name="name",example = "")
	private String proposerName;//申请人名称
	@ApiModelProperty(value="采购申请单产品",name="purchaseProductDtoList",example = "")
	private List<PurchaseProductDto> purchaseProductDtoList;
	@ApiModelProperty(value="申请单状态名称",name="statusName",example = "")
	private String statusName;//申请单状态名称
	@ApiModelProperty(value="审核人id，字符串逗号分隔",name="auditIds",example = "")
	private List<String> auditIds;//审核人id，字符串逗号分隔
	@ApiModelProperty(value="抄送人，字符串逗号分隔",name="copyPersonIds",example = "")
	private List<String> copyPersonIds;//抄送人，字符串逗号分隔
	@ApiModelProperty(value="登录人在申请单中的分类",name="loginType",example = "")
	private List<Integer> loginType;//登录人在申请单中的分类 0-审核人 1-抄送人  2-申请人 3-采购人
	private Integer auditStatus;//审核人员对单子的审核状态 0-未审核 1-已审核 2-无审核操作按钮
	private Integer finance;//是否为财务人员
	private String requestType;//申请单类型
	private String financialAuditName;//财务审核人姓名

	public List<String> getAuditName() {
		return auditName;
	}

	public void setAuditName(List<String> auditName) {
		this.auditName = auditName;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public List<String> getCopyPerson() {
		return copyPerson;
	}

	public void setCopyPerson(List<String> copyPerson) {
		this.copyPerson = copyPerson;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public List<PurchaseProductDto> getPurchaseProductDtoList() {
		return purchaseProductDtoList;
	}

	public void setPurchaseProductDtoList(List<PurchaseProductDto> purchaseProductDtoList) {
		this.purchaseProductDtoList = purchaseProductDtoList;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<String> getAuditIds() {
		return auditIds;
	}

	public void setAuditIds(List<String> auditIds) {
		this.auditIds = auditIds;
	}

	public List<String> getCopyPersonIds() {
		return copyPersonIds;
	}

	public void setCopyPersonIds(List<String> copyPersonIds) {
		this.copyPersonIds = copyPersonIds;
	}

	public List<Integer> getLoginType() {
		return loginType;
	}

	public void setLoginType(List<Integer> loginType) {
		this.loginType = loginType;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getFinance() {
		return finance;
	}

	public void setFinance(Integer finance) {
		this.finance = finance;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getFinancialAuditName() {
		return financialAuditName;
	}

	public void setFinancialAuditName(String financialAuditName) {
		this.financialAuditName = financialAuditName;
	}
}
