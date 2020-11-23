package com.lwxf.industry4.webapp.domain.entity.jdproduct;


import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：jd_product_info 实体类
 *
 * @author：lyh
 * @created：2019-12-02 04:42 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="jd_product_info对象", description="jd_product_info")
@Table(name = "jd_product_info",displayName = "jd_product_info")
public class JdProductInfo extends IdEntity {
	private String id;
	@Column(type = TypesExtend.DATETIME,name = "fetching_Time",displayName = "抓取时间")
	@ApiModelProperty(value = "抓取时间")
	private Date fetchingTime;
	@Column(type = Types.VARCHAR,length = 255,name = "product_Picture",displayName = "商品图片")
	@ApiModelProperty(value = "商品图片")
	private String productPicture;
	@Column(type = Types.VARCHAR,length = 255,name = "produc_title",displayName = "商品描述")
	@ApiModelProperty(value = "商品描述")
	private String producTitle;
	@Column(type = Types.VARCHAR,length = 255,name = "product_href",displayName = "商品连接")
	@ApiModelProperty(value = "商品连接")
	private String productHref;
	@Column(type = Types.VARCHAR,length = 255,name = "product_price",displayName = "商品价格")
	@ApiModelProperty(value = "商品价格")
	private String productPrice;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve1",displayName = "备用字段1")
	@ApiModelProperty(value = "备用字段1")
	private String reserve1;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve2",displayName = "备用字段2")
	@ApiModelProperty(value = "备用字段2")
	private String reserve2;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve3",displayName = "备用字段3")
	@ApiModelProperty(value = "备用字段3")
	private String reserve3;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve4",displayName = "备用字段4")
	@ApiModelProperty(value = "备用字段4")
	private String reserve4;
	@ApiModelProperty(value = "排序由小到大")
	private int orderBy;

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public JdProductInfo() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.productPicture) > 255) {
			validResult.put("productPicture", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.producTitle) > 255) {
			validResult.put("producTitle", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.productHref) > 255) {
			validResult.put("productHref", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.productPrice) > 255) {
			validResult.put("productPrice", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve1) > 255) {
			validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve2) > 255) {
			validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve3) > 255) {
			validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve4) > 255) {
			validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","fetchingTime","productPicture","producTitle","productHref","productPrice","reserve1","reserve2","reserve3","reserve4");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if(map.size()==0){
			return ResultFactory.generateErrorResult("error",ErrorCodes.VALIDATE_NOTNULL);
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if(!flag){
			return ResultFactory.generateErrorResult("error",ErrorCodes.VALIDATE_ILLEGAL_ARGUMENT);
		}
		if(map.containsKey("fetchingTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("fetchingTime",String.class))) {
				validResult.put("fetchingTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("productPicture")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productPicture",String.class)) > 255) {
				validResult.put("productPicture", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("producTitle")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("producTitle",String.class)) > 255) {
				validResult.put("producTitle", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productHref")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productHref",String.class)) > 255) {
				validResult.put("productHref", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productPrice")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productPrice",String.class)) > 255) {
				validResult.put("productPrice", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve1")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve1",String.class)) > 255) {
				validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve2")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve2",String.class)) > 255) {
				validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve3")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve3",String.class)) > 255) {
				validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve4")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve4",String.class)) > 255) {
				validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setFetchingTime(Date fetchingTime){
		this.fetchingTime=fetchingTime;
	}

	public Date getFetchingTime(){
		return fetchingTime;
	}

	public void setProductPicture(String productPicture){
		this.productPicture=productPicture;
	}

	public String getProductPicture(){
		return productPicture;
	}

	public void setProducTitle(String producTitle){
		this.producTitle=producTitle;
	}

	public String getProducTitle(){
		return producTitle;
	}

	public void setProductHref(String productHref){
		this.productHref=productHref;
	}

	public String getProductHref(){
		return productHref;
	}

	public void setProductPrice(String productPrice){
		this.productPrice=productPrice;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public void setReserve1(String reserve1){
		this.reserve1=reserve1;
	}

	public String getReserve1(){
		return reserve1;
	}

	public void setReserve2(String reserve2){
		this.reserve2=reserve2;
	}

	public String getReserve2(){
		return reserve2;
	}

	public void setReserve3(String reserve3){
		this.reserve3=reserve3;
	}

	public String getReserve3(){
		return reserve3;
	}

	public void setReserve4(String reserve4){
		this.reserve4=reserve4;
	}

	public String getReserve4(){
		return reserve4;
	}
}
