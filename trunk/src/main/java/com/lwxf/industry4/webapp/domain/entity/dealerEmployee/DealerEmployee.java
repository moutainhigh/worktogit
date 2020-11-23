package  com.lwxf.industry4.webapp.domain.entity.dealerEmployee;
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
 * 功能：dealer_employee 实体类
 *
 * @author：lyh
 * @created：2019-12-20 04:34 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="dealer_employee对象", description="dealer_employee")
@Table(name = "dealer_employee",displayName = "dealer_employee")
public class DealerEmployee extends IdEntity {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 255,name = "name",displayName = "姓名")
	@ApiModelProperty(value = "姓名")
	private String name;
	@Column(type = Types.VARCHAR,length = 255,name = "phone",displayName = "电话")
	@ApiModelProperty(value = "电话")
	private String phone;

	@Column(type = Types.VARCHAR,length = 255,name = "position_type",displayName = "职位")
	@ApiModelProperty(value = "职位")
	private String positionType;
	@Column(type = Types.VARCHAR,length = 255,name = "position_name",displayName = "职位名称")
	@ApiModelProperty(value = "职位名称")
	private String positionName;
	@Column(type = Types.VARCHAR,length = 255,name = "working_date",displayName = "从业时间")
	@ApiModelProperty(value = "从业时间")
	private String workingDate;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve1",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve1;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve2",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve2;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve3",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve3;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve4",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve4;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve4",displayName = "备用字段")
	@ApiModelProperty(value = "经销商CompanyId")
	private String dealerCompanyId;

	@ApiModelProperty(value = "附件id数组")
	private String fileIds;
	@ApiModelProperty(value = "头像")
	private String headPortraits;

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public String getHeadPortraits() {
		return headPortraits;
	}

	public void setHeadPortraits(String headPortraits) {
		this.headPortraits = headPortraits;
	}
	public String getDealerCompanyId() {
		return dealerCompanyId;
	}

	public void setDealerCompanyId(String dealerCompanyId) {
		this.dealerCompanyId = dealerCompanyId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public DealerEmployee() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 255) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.phone) > 255) {
			validResult.put("phone", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.positionType) > 255) {
			validResult.put("positionType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.positionName) > 255) {
			validResult.put("positionName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.workingDate) > 255) {
			validResult.put("workingDate", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("id","name","phone","positionType","positionName","workingDate","reserve1","reserve2","reserve3","reserve4");

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
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 255) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("phone")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("phone",String.class)) > 255) {
				validResult.put("phone", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("positionType")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("positionType",String.class)) > 255) {
				validResult.put("positionType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("positionName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("positionName",String.class)) > 255) {
				validResult.put("positionName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("workingDate")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("workingDate",String.class)) > 255) {
				validResult.put("workingDate", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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


	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setPositionType(String positionType){
		this.positionType=positionType;
	}

	public String getPositionType(){
		return positionType;
	}

	public void setPositionName(String positionName){
		this.positionName=positionName;
	}

	public String getPositionName(){
		return positionName;
	}

	public void setWorkingDate(String workingDate){
		this.workingDate=workingDate;
	}

	public String getWorkingDate(){
		return workingDate;
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
