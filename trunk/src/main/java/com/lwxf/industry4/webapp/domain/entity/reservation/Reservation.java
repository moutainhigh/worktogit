package com.lwxf.industry4.webapp.domain.entity.reservation;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
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
 * 功能：reservation 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 03:14
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "reservation",displayName = "reservation")
public class Reservation extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 50,nullable = false,updatable = false,name = "company_id",displayName = "公司id：app平台添加预约默认为制造商（红田），进入某门店添加预约则设为该门店")
	private String companyId;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "user_id",displayName = "用户ID")
	private String userId;
	@Column(type = Types.VARCHAR,length = 20,nullable = false,name = "phone",displayName = "预约时的电话")
	private String phone;
	@Column(type = Types.VARCHAR,length = 30,nullable = false,name = "name",displayName = "预约时填入的联系人名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "area",displayName = "住房面积")
	private String area;
	@Column(type = Types.TINYINT,defaultValue = "0",nullable = false,name = "status",displayName = "预约处理状态： 0:待接单（等待设计师接单）， 1:已接单(待测量)， 2:已测量（等待设计）， 3:设计中 4:等待设计确认（已上传设计图），未确认回到上一个状态从新设计 5:设计已确认（等待报价） 6:已报价 7:已付预付款（等待生产） 8:生产中 9:生产完成（等待发货） 10:已发货 11:已到货（等待安装） 12:安装中 13:安装完成 14:已支付尾款 15:完成 16:已取消")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 100,name = "descr",displayName = "管理员在后台给预约客户的备注")
	private String descr;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "所在城市id：精确到县（市）/区，用于匹配附近的经销商或加盟商")
	private String cityAreaId;
	@Column(type = Types.CHAR,length = 13,name = "designer",displayName = "设计师")
	private String designer;
	@Column(type = Types.CHAR,length = 13,name = "home_advisor",displayName = "家具顾问")
	private String homeAdvisor;
	@Column(type = Types.CHAR,length = 13,name = "erector",displayName = "安装工")
	private String erector;
	@Column(type = Types.INTEGER,name = "earnest",displayName = "定金（300顶600，则存的600）")
	private Integer earnest;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "total_price",displayName = "总价")
	private BigDecimal totalPrice;

	public Reservation() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 50) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.userId == null) {
			validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.phone == null) {
			validResult.put("phone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.phone) > 20) {
				validResult.put("phone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 30) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.area == null) {
			validResult.put("area", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.area) > 50) {
				validResult.put("area", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.descr) > 100) {
			validResult.put("descr", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.designer) > 13) {
			validResult.put("designer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.homeAdvisor) > 13) {
			validResult.put("homeAdvisor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.erector) > 13) {
			validResult.put("erector", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("phone","name","area","status","descr","cityAreaId","designer","homeAdvisor","erector","earnest","totalPrice");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("earnest")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("earnest",String.class))) {
				validResult.put("earnest", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("totalPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("totalPrice",String.class))) {
				validResult.put("totalPrice", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("phone")) {
			if (map.getTypedValue("phone",String.class)  == null) {
				validResult.put("phone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("phone",String.class)) > 20) {
					validResult.put("phone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 30) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("area")) {
			if (map.getTypedValue("area",String.class)  == null) {
				validResult.put("area", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("area",String.class)) > 50) {
					validResult.put("area", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("descr")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("descr",String.class)) > 100) {
				validResult.put("descr", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("designer")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("designer",String.class)) > 13) {
				validResult.put("designer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("homeAdvisor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("homeAdvisor",String.class)) > 13) {
				validResult.put("homeAdvisor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("erector")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("erector",String.class)) > 13) {
				validResult.put("erector", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setArea(String area){
		this.area=area;
	}

	public String getArea(){
		return area;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setDescr(String descr){
		this.descr=descr;
	}

	public String getDescr(){
		return descr;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setCityAreaId(String cityAreaId){
		this.cityAreaId=cityAreaId;
	}

	public String getCityAreaId(){
		return cityAreaId;
	}

	public void setDesigner(String designer){
		this.designer=designer;
	}

	public String getDesigner(){
		return designer;
	}

	public void setHomeAdvisor(String homeAdvisor){
		this.homeAdvisor=homeAdvisor;
	}

	public String getHomeAdvisor(){
		return homeAdvisor;
	}

	public void setErector(String erector){
		this.erector=erector;
	}

	public String getErector(){
		return erector;
	}

	public void setEarnest(Integer earnest){
		this.earnest=earnest;
	}

	public Integer getEarnest(){
		return earnest;
	}

	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice=totalPrice;
	}

	public BigDecimal getTotalPrice(){
		return totalPrice;
	}
}
