package com.lwxf.industry4.webapp.domain.entity.company;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

/**
 * 功能：company 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-05 11:05
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "company",displayName = "company")
@ApiModel(value="公司详情",description="公司详情")
public class Company extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="经销商名称",name="name",required=true,example = "测试公司")
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "公司名称")
	protected String name;
	@ApiModelProperty(value="类型值",name="type",required=true,example = "1")
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "0：生产商/制造商/总店；1：直营店；2：店中店；3：专卖店；4：加盟店；5：散单;6:供应商;")
	protected Integer type;
	@ApiModelProperty(value="所在地(最终地址ID,如某区id)",name="cityAreaId",example = "110101")
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "所在城市id，精确到县(或市)/区，可用于定位")
	protected String cityAreaId;
	@ApiModelProperty(value="详细地址",name="address",example = "测试地址")
	@Column(type = Types.VARCHAR,length = 200,name = "address",displayName = "详细地址：用于店铺名片的显示地址，也可用于地图定位及导航")
	protected String address;
	@ApiModelProperty(value="经度",name="address",hidden = true)
	@Column(type = Types.FLOAT,name = "lng",displayName = "经度")
	protected Float lng;
	@ApiModelProperty(value="纬度",name="address",hidden = true)
	@Column(type = Types.FLOAT,name = "lat",displayName = "维度")
	protected Float lat;
	@ApiModelProperty(value="创建时间,",name="created",hidden = true)
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	protected Date created;
	@ApiModelProperty(value="创建人主键ID,",name="creator",hidden = true)
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人主键ID")
	protected String creator;
	@ApiModelProperty(value="状态:对于厂家来说，状态为1，经销商初始状态为0,",name="status",required=true,hidden = true,example = "1")
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 意向；1 - 已签约（正常）；2 - 已退网；3 - 已倒闭；4 - 已禁用;5 - 无价值;6 - 待财务审核;7 - 待启用。对于厂家来说，状态为1，经销商初始状态为0")
	protected Integer status;
	@ApiModelProperty(value="粉丝数",name="followers",hidden = true,required=true,example = "1")
	@Column(type = Types.INTEGER,nullable = false,name = "followers",displayName = "粉丝数量：默认为0，每添加一个关注人，该字段增加1，相反则减1，减到0为止")
	protected Integer followers;
	@ApiModelProperty(value="负责人ID",name="leader",example = "4guy63p3hlvk")
	@Column(type = Types.CHAR,length = 13,name = "leader",displayName = "负责人")
	protected String leader;
	@ApiModelProperty(value="负责人电话",name="leaderTel",example = "13523333333")
	@Column(type = Types.CHAR,length = 13,name = "leader_tel",displayName = "负责人电话（支持固话和手机），固话格式：029-33333333")
	protected String leaderTel;
	@ApiModelProperty(value="业务员/业务经理/大区经理",name="businessManager",example = "4guzmkrd2o74")
	@Column(type = Types.CHAR,length = 13,name = "business_manager",displayName = "业务经理")
	protected String businessManager;
	@ApiModelProperty(value="开户行",name="depositBank")
	@Column(type = Types.VARCHAR,length = 100,name = "deposit_bank",displayName = "开户行")
	protected String depositBank;
	@ApiModelProperty(value="银行账号",name="bankAccount")
	@Column(type = Types.VARCHAR,length = 50,name = "bank_account",displayName = "银行账号")
	protected String bankAccount;
	@ApiModelProperty(value="银行账号开户人",name="bankAccountHolder")
	@Column(type = Types.VARCHAR,length = 20,name = "bank_account_holder",displayName = "银行账号开户人")
	protected String bankAccountHolder;
	@ApiModelProperty(value="店铺面积（单位：㎡），只存数字",name="shopArea")
	@Column(type = Types.VARCHAR,length = 20,name = "shop_area",displayName = "店铺面积（单位：㎡），只存数字")
	protected String shopArea;
	@ApiModelProperty(value="店铺的logo",name="logo")
	@Column(type = Types.VARCHAR,length = 100,name = "logo",displayName = "店铺的logo")
	protected String logo;
	@ApiModelProperty(value="级别",name="grade",required=true,example = "0")
	@Column(type = Types.TINYINT,nullable = false,name = "grade",displayName = "级别，0 - 初级；1 - 一级；级别定义暂时这样，后期根据需求再调整，默认0")
	protected Integer grade;
	@ApiModelProperty(value="联系方式(支持固话和手机)",name="grade",example = "092-33333333")
	@Column(type = Types.VARCHAR,length = 13,name = "service_tel",displayName = "支持固话和手机），固话格式：029-33333333")
	protected String serviceTel;
	@Column(type = Types.CHAR,length = 13,name = "service_staff",displayName = "客服人员")
	protected String serviceStaff;
	@ApiModelProperty(value="编号(不可重复)",name="no",example = "32123321")
	@Column(type = Types.VARCHAR,length = 30,name = "no",displayName = "编号（不可重复）")
	protected String no;
	@ApiModelProperty(value="创始人名称",name="founderName")
	@Column(type = Types.VARCHAR,length = 30,name = "founderName",displayName = "创始人名称")
	protected String founderName;
	@ApiModelProperty(value="公司介绍",name="grade")
	@Column(type = Types.VARCHAR,length = 1000,name = "company_info",displayName = "公司介绍")
	protected String companyInfo;
	@ApiModelProperty(value="签约时间",name="contractTime",example = "2019-01-01")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = Types.DATE,name = "contract_time",displayName = "签约时间")
	protected Date contractTime;
	@ApiModelProperty(value="备注",name="founderName")
	@Column(type = Types.VARCHAR,length = 200,name = "note",displayName = "备注")
	protected String note;
	@ApiModelProperty(value="合同有效期",name="contractTime",example = "2019-01-01")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = Types.VARCHAR,name = "contract_expired_date",displayName = "合同有效期")
	protected String contractExpiredDate;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	protected String branchId;
	@Column(type = Types.VARCHAR,length = 200,name = "account_balances_remarks",displayName = "账户余额备注信息")
	private String accountBalancesRemarks;
	@Column(type = Types.VARCHAR,length = 200,name = "other_fund_remarks",displayName = "其他资金备注信息")
    private String otherFundRemarks;
	@Column(type = Types.DATE,name = "retire_net_time",displayName = "退网时间")
	private String retireNetTime;
	@Column(type = Types.VARCHAR,length = 200,name = "scope_business",displayName = "经营范围")
	private String scopeBusiness;
	@Column(type = Types.VARCHAR,length = 200,name = "standby_links",displayName = "备用联系方式")
	private String standbyLinks;
	@ApiModelProperty(value="发货确认 0-不需要 1-需要",name="deliverSure",example = "0")
	@Column(type = Types.TINYINT,nullable = false,name = "deliver_sure",displayName = "发货确认 0-不需要 1-需要")
	protected Integer deliverSure;
	@ApiModelProperty(value="经销商积分",name="integral",example = "0")
	@Column(type = Types.DECIMAL, precision = 10, scale = 2, nullable = false, name = "integral",displayName = "经销商积分")
	protected BigDecimal integral;
	@Column(type = Types.VARCHAR,length = 255,name = "shop_cover",displayName = "店铺封面图url")
	private String shopCover;

	public Company() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
			if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
				validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.address) > 200) {
			validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.followers == null) {
			validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.leader) > 13) {
			validResult.put("leader", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.leaderTel == null) {
			validResult.put("leaderTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else if (LwxfStringUtils.getStringLength(this.leaderTel) > 13) {
			validResult.put("leaderTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.businessManager) > 13) {
			validResult.put("businessManager", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.depositBank) > 100) {
			validResult.put("depositBank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.bankAccount) > 50) {
			validResult.put("bankAccount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.bankAccountHolder) > 20) {
			validResult.put("bankAccountHolder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.shopArea) > 20) {
			validResult.put("shopArea", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.logo) > 100) {
			validResult.put("logo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.grade == null) {
			validResult.put("grade", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.serviceTel) > 13) {
			validResult.put("serviceTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.serviceStaff) > 13) {
			validResult.put("serviceStaff", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.no) > 30) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.founderName == null) {
			validResult.put("founderName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else if (LwxfStringUtils.getStringLength(this.founderName) > 50) {
			validResult.put("founderName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.companyInfo) > 1000) {
			validResult.put("companyInfo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.accountBalancesRemarks) > 200) {
			validResult.put("accountBalancesRemarks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.otherFundRemarks) > 200) {
			validResult.put("otherFundRemarks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.scopeBusiness) > 200) {
			validResult.put("scopeBusiness", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.standbyLinks) > 200) {
			validResult.put("standbyLinks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","type","cityAreaId","address","lng","lat","status","followers","leader","leaderTel","businessManager","depositBank","bankAccount","bankAccountHolder","shopArea","logo","grade","serviceTel","serviceStaff","no","leaderName","founderName","companyInfo","contractTime","contractExpiredDate","otherFundRemarks","accountBalancesRemarks","scopeBusiness","standbyLinks","deliverSure","integral");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if(map.size()==0){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if(!flag){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("lng")) {
			if (!DataValidatorUtils.isFloat(map.getTypedValue("lng",String.class))) {
				validResult.put("lng", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("lat")) {
			if (!DataValidatorUtils.isFloat(map.getTypedValue("lat",String.class))) {
				validResult.put("lat", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
			if(!CompanyStatus.contains(map.getTypedValue("status",Integer.class))){
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("followers")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("followers",String.class))) {
				validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("grade")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("grade",String.class))) {
				validResult.put("grade", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 200) {
				validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("followers")) {
			if (map.get("followers")  == null) {
				validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("leader")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("leader",String.class)) > 13) {
				validResult.put("leader", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("leaderTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("leaderTel",String.class)) > 13) {
				validResult.put("leaderTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("businessManager")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("businessManager",String.class)) > 13) {
				validResult.put("businessManager", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("depositBank")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("depositBank",String.class)) > 100) {
				validResult.put("depositBank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("bankAccount")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bankAccount",String.class)) > 50) {
				validResult.put("bankAccount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("bankAccountHolder")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bankAccountHolder",String.class)) > 20) {
				validResult.put("bankAccountHolder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("shopArea")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("shopArea",String.class)) > 20) {
				validResult.put("shopArea", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("logo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("logo",String.class)) > 100) {
				validResult.put("logo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("grade")) {
			if (map.get("grade")  == null) {
				validResult.put("grade", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("serviceTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("serviceTel",String.class)) > 13) {
				validResult.put("serviceTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("serviceStaff")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("serviceStaff",String.class)) > 13) {
				validResult.put("serviceStaff", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("no")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 30) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("founderName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("founderName",String.class)) > 50) {
				validResult.put("founderName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("companyInfo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("companyInfo",String.class)) > 1000) {
				validResult.put("companyInfo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("note")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("note",String.class)) > 200) {
				validResult.put("note", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("scopeBusiness")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("scopeBusiness",String.class)) > 200) {
				validResult.put("scopeBusiness", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("standbyLinks")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("standbyLinks",String.class)) > 200) {
				validResult.put("standbyLinks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("contractTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("contractTime",String.class))) {
				validResult.put("contractTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
//		if(map.containsKey("contractExpiredDate")) {
//			if (!DataValidatorUtils.isDate(map.getTypedValue("contractExpiredDate",String.class))) {
//				validResult.put("contractExpiredDate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
//			}
//		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public String getBranchId() {return branchId;}

	public void setBranchId(String branchId) {this.branchId = branchId;}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setCityAreaId(String cityAreaId){
		this.cityAreaId=cityAreaId;
	}

	public String getCityAreaId(){
		return cityAreaId;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setLng(Float lng){
		this.lng=lng;
	}

	public Float getLng(){
		return lng;
	}

	public void setLat(Float lat){
		this.lat=lat;
	}

	public Float getLat(){
		return lat;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setFollowers(Integer followers){
		this.followers=followers;
	}

	public Integer getFollowers(){
		return followers;
	}

	public void setLeader(String leader){
		this.leader=leader;
	}

	public String getLeader(){
		return leader;
	}

	public void setLeaderTel(String leaderTel){
		this.leaderTel=leaderTel;
	}

	public String getLeaderTel(){
		return leaderTel;
	}

	public void setBusinessManager(String businessManager){
		this.businessManager=businessManager;
	}

	public String getBusinessManager(){
		return businessManager;
	}

	public void setDepositBank(String depositBank){
		this.depositBank=depositBank;
	}

	public String getDepositBank(){
		return depositBank;
	}

	public void setBankAccount(String bankAccount){
		this.bankAccount=bankAccount;
	}

	public String getBankAccount(){
		return bankAccount;
	}

	public void setBankAccountHolder(String bankAccountHolder){
		this.bankAccountHolder=bankAccountHolder;
	}

	public String getBankAccountHolder(){
		return bankAccountHolder;
	}

	public void setShopArea(String shopArea){
		this.shopArea=shopArea;
	}

	public String getShopArea(){
		return shopArea;
	}

	public void setLogo(String logo){
		this.logo=logo;
	}

	public String getLogo(){
		return logo;
	}

	public void setGrade(Integer grade){
		this.grade=grade;
	}

	public Integer getGrade(){
		return grade;
	}

	public void setServiceTel(String serviceTel){
		this.serviceTel=serviceTel;
	}

	public String getServiceTel(){
		return serviceTel;
	}

	public void setServiceStaff(String serviceStaff){
		this.serviceStaff=serviceStaff;
	}

	public String getServiceStaff(){
		return serviceStaff;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}


	public String getNote() {return note;}

	public void setNote(String note) {this.note = note;}


	public String getContractExpiredDate() {
		return contractExpiredDate;
	}

	public void setContractExpiredDate(String contractExpiredDate) {
		this.contractExpiredDate = contractExpiredDate;
	}

	public String getAccountBalancesRemarks() {
		return accountBalancesRemarks;
	}

	public void setAccountBalancesRemarks(String accountBalancesRemarks) {
		this.accountBalancesRemarks = accountBalancesRemarks;
	}

	public String getOtherFundRemarks() {
		return otherFundRemarks;
	}

	public void setOtherFundRemarks(String otherFundRemarks) {
		this.otherFundRemarks = otherFundRemarks;
	}

	public String getRetireNetTime() {
		return retireNetTime;
	}

	public void setRetireNetTime(String retireNetTime) {
		this.retireNetTime = retireNetTime;
	}

	public String getScopeBusiness() {
		return scopeBusiness;
	}

	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
	}

	public String getStandbyLinks() {
		return standbyLinks;
	}

	public void setStandbyLinks(String standbyLinks) {
		this.standbyLinks = standbyLinks;
	}

	public Integer getDeliverSure() {
		return deliverSure;
	}

	public void setDeliverSure(Integer deliverSure) {
		this.deliverSure = deliverSure;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public String getShopCover() {
		return shopCover;
	}

	public void setShopCover(String shopCover) {
		this.shopCover = shopCover;
	}
}
