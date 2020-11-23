package com.lwxf.industry4.webapp.domain.entity.company;
import java.math.BigDecimal;
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
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：company_share_member 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-05 11:05
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "company_share_member",displayName = "company_share_member")
public class CompanyShareMember extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_id",displayName = "公司id")
	private String companyId;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "user_id",displayName = "")
	private String userId;
	@Column(type = Types.TINYINT,nullable = false,updatable = false,name = "identity",displayName = "身份(0：家具顾问；1：设计师；2：安装工)")
	private Integer identity;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "加入时间")
	private Date created;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 待审批（申请状态）；1 - 审批未通过；2 - 已审批（可营业状态）；3 - 被禁用")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = Types.FLOAT,name = "score",displayName = "评分（0.0-10.0）")
	private Float score;
	@Column(type = Types.INTEGER,name = "numbers",displayName = "数量（设计的数量，安装的数量，顾问的数量)")
	private Integer numbers;
	@Column(type = Types.VARCHAR,length = 100,name = "coverage",displayName = "服务区域")
	private String coverage;
	@Column(type = Types.INTEGER,name = "reservations_count",displayName = "预约数量")
	private Integer reservationsCount;
	@Column(type = Types.DECIMAL, precision = 10, scale = 2,name = "min_quotations",displayName = "最低报价")
	private BigDecimal minQuotations;
	@Column(type = Types.DECIMAL, precision = 10, scale = 2,name = "max_quotations",displayName = "最高报价")
	private BigDecimal maxQuotations;
	@Column(type = Types.VARCHAR, length = 50, name = "name", displayName = "人员名称")
	@ApiModelProperty(value = "人员名称")
	private String name;
	@Column(type = Types.VARCHAR, length = 50, name = "phone", displayName = "联系电话")
	@ApiModelProperty(value = "联系电话")
	private String phone;
	public CompanyShareMember() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}

			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));

		}
		if (this.identity == null) {
			validResult.put("identity", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("status");

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
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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

	public void setIdentity(Integer identity){
		this.identity=identity;
	}

	public Integer getIdentity(){
		return identity;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public Integer getReservationsCount() {
		return reservationsCount;
	}

	public void setReservationsCount(Integer reservationsCount) {
		this.reservationsCount = reservationsCount;
	}

	public BigDecimal getMinQuotations() {
		return minQuotations;
	}

	public void setMinQuotations(BigDecimal minQuotations) {
		this.minQuotations = minQuotations;
	}

	public BigDecimal getMaxQuotations() {
		return maxQuotations;
	}

	public void setMaxQuotations(BigDecimal maxQuotations) {
		this.maxQuotations = maxQuotations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
