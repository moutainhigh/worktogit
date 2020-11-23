package com.lwxf.industry4.webapp.domain.entity.company;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：dealer_shop 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-25 11:06 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "dealer_shop",displayName = "dealer_shop")
public class DealerShop extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "店铺名称")
	private String name;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "所属经销商（公司）ID")
	private String companyId;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "店铺类型（暂时跟着经销商公司类型）")
	private Integer type;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "区域ID")
	private String cityAreaId;
	@Column(type = Types.FLOAT,name = "lng",displayName = "经度")
	private Float lng;
	@Column(type = Types.FLOAT,name = "lat",displayName = "纬度")
	private Float lat;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = Types.INTEGER,nullable = false,name = "followers",displayName = "店铺粉丝数量（默认为0，每添加一个关注人，该字段增加1，相反则减1，减到0为止）")
	private Integer followers;
	@Column(type = Types.CHAR,length = 13,name = "leader",displayName = "负责人")
	private String leader;
	@Column(type = Types.CHAR,length = 13,name = "leader_tel",displayName = "负责人电话")
	private String leaderTel;
	@Column(type = Types.CHAR,length = 13,name = "business_manager",displayName = "业务经理")
	private String businessManager;
	@Column(type = Types.VARCHAR,length = 200,name = "address",displayName = "店铺详细地址")
	private String address;
	@Column(type = Types.VARCHAR,length = 20,name = "shop_area",displayName = "店铺面积")
	private String shopArea;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态（暂时未定）")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 100,name = "logo",displayName = "店铺logo")
	private String logo;
	@Column(type = Types.TINYINT,nullable = false,name = "grade",displayName = "等级0 - 初级；1 - 一级；级别定义暂时这样，后期根据需求再调整，默认0")
	private Integer grade;
	@Column(type = Types.VARCHAR,length = 13,name = "service_tel",displayName = "服务电话")
	private String serviceTel;
	@Column(type = Types.CHAR,length = 13,name = "service_staff",displayName = "服务人员")
	private String serviceStaff;
	@Column(type = Types.VARCHAR,length = 1000,name = "shop_info",displayName = "店铺描述")
	private String shopInfo;
	@Column(type = Types.CHAR,length = 13,name = "shop_cover_file",displayName = "店铺门头图片")
	private String shopCoverFile;

    public DealerShop() {  
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
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if (this.followers == null) {
			validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.leader) > 13) {
			validResult.put("leader", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.leaderTel) > 13) {
			validResult.put("leaderTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.businessManager) > 13) {
			validResult.put("businessManager", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.address) > 200) {
			validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.shopArea) > 20) {
			validResult.put("shopArea", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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
		if (LwxfStringUtils.getStringLength(this.shopInfo) > 1000) {
			validResult.put("shopInfo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.shopCoverFile) > 13) {
			validResult.put("shopCoverFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","companyId","type","cityAreaId","lng","lat","created","creator","followers","leader","leaderTel","businessManager","address","shopArea","status","logo","grade","serviceTel","serviceStaff","shopInfo","shopCoverFile");

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
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("followers")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("followers",String.class))) {
				validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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
		if(map.containsKey("companyId")) {
			if (map.getTypedValue("companyId",String.class)  == null) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
					validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
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
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 200) {
				validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("shopArea")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("shopArea",String.class)) > 20) {
				validResult.put("shopArea", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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
		if(map.containsKey("shopInfo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("shopInfo",String.class)) > 1000) {
				validResult.put("shopInfo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("shopCoverFile")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("shopCoverFile",String.class)) > 13) {
				validResult.put("shopCoverFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
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

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setShopArea(String shopArea){
		this.shopArea=shopArea;
	}

	public String getShopArea(){
		return shopArea;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
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

	public void setShopInfo(String shopInfo){
		this.shopInfo=shopInfo;
	}

	public String getShopInfo(){
		return shopInfo;
	}

	public void setShopCoverFile(String shopCoverFile){
		this.shopCoverFile=shopCoverFile;
	}

	public String getShopCoverFile(){
		return shopCoverFile;
	}
}
