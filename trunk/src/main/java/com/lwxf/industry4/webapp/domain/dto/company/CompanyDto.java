package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能：
 * @author：dongshibo(F_baisi)
 * @create：2018/12/8/008 11:27
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="公司详情",description="公司详情")
public class CompanyDto extends Company {
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	@ApiModelProperty(value = "联系人名称")
	private String leaderName;
	@ApiModelProperty(value = "客户统计")
	private String customerCount; //客户统计
	@ApiModelProperty(value = "订单统计")
	private String orderCount; //订单统计
	@ApiModelProperty(value = "账户余额(自由资金)")
	private String balance; //账户余额
	@ApiModelProperty(value = "地区名称")
    private String mergerName;
	@ApiModelProperty(value = "附件集合")
	private List<UploadFiles> uploadFilesList;
	@ApiModelProperty(value = "大区经理名称")
	private String businessManagerName;
	@ApiModelProperty(value = "保证金余额")
	private String depositBalance;
	@ApiModelProperty(value = "设计金余额")
	private String designBalance;
	@ApiModelProperty(value = "省ID")
	private String provinceId;
	@ApiModelProperty(value = "市ID")
	private String cityId;
	@ApiModelProperty(value = "级别转义")
	private String gradeName;
	@ApiModelProperty(value = "默认物流公司ID")
	private String logisticsCompanyId;
	@ApiModelProperty(value = "默认物流公司名称")
	private String logisticsCompanyName;
	@ApiModelProperty(value = "经销商账户列表")
	private List<DealerAccount> accountList;
	@ApiModelProperty(value = "经销商类型名称")
	private String typeName;
	@ApiModelProperty(value = "经销商状态名称")
	private String statusName;
	@ApiModelProperty(value = "经销商证件信息")
	private List<CompanyCertificatesDto> companyCertificates;
	@ApiModelProperty(value = "经销商物流公司")
	private List<WxDealerLogisticsDto> dealerLogisticsDtos;


	public List<DealerAccount> getAccountList() {return accountList;}

	public void setAccountList(List<DealerAccount> accountList) {this.accountList = accountList;}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public List<UploadFiles> getUploadFilesList() {
		return uploadFilesList;
	}

	public void setUploadFilesList(List<UploadFiles> uploadFilesList) {
		this.uploadFilesList = uploadFilesList;
	}
	public String getCustomerCount() {return customerCount;}

	public void setCustomerCount(String customerCount) {this.customerCount = customerCount;}

	public String getOrderCount() {return orderCount;}

	public void setOrderCount(String orderCount) {this.orderCount = orderCount;}

	public String getBalance() {return balance;}

	public void setBalance(String balance) {this.balance = balance;}

	public String getBusinessManagerName() {
		return businessManagerName;
	}

	public void setBusinessManagerName(String businessManagerName) {
		this.businessManagerName = businessManagerName;
	}

	public String getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(String depositBalance) {
		this.depositBalance = depositBalance;
	}

	public CompanyDto clone(Company company) {
		this.id = company.getId();
		this.name = company.getName();
		this.type = company.getType();
		this.cityAreaId = company.getCityAreaId();
		this.address = company.getAddress();
		this.lng = company.getLng();
		this.lat = company.getLat();
		this.created = company.getCreated();
		this.creator = company.getCreator();
		this.status = company.getStatus();
		this.followers = company.getFollowers();
		this.leader = company.getLeader();
		this.leaderTel = company.getLeaderTel();
		this.businessManager = company.getBusinessManager();
		this.depositBank = company.getDepositBank();
		this.bankAccount = company.getBankAccount();
		this.bankAccountHolder = company.getBankAccountHolder();
		this.shopArea = company.getShopArea();
		this.logo = company.getLogo();
		this.grade = company.getGrade();
		this.serviceTel = company.getServiceTel();
		this.serviceStaff = company.getServiceStaff();
		this.no = company.getNo();
		this.founderName = company.getFounderName();
		this.contractTime=company.getContractTime();
		this.note = company.getNote();
		this.contractExpiredDate=company.getContractExpiredDate();
		return this;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getDesignBalance() {
		return designBalance;
	}

	public void setDesignBalance(String designBalance) {
		this.designBalance = designBalance;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStatusName() {
		String statusName= CompanyStatus.getByValue(status).getName();
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<CompanyCertificatesDto> getCompanyCertificates() {
		return companyCertificates;
	}

	public void setCompanyCertificates(List<CompanyCertificatesDto> companyCertificates) {
		this.companyCertificates = companyCertificates;
	}

	public List<WxDealerLogisticsDto> getDealerLogisticsDtos() {
		return dealerLogisticsDtos;
	}

	public void setDealerLogisticsDtos(List<WxDealerLogisticsDto> dealerLogisticsDtos) {
		this.dealerLogisticsDtos = dealerLogisticsDtos;
	}
}
