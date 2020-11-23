package com.lwxf.industry4.webapp.domain.entity.customorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
/**
 * 功能：order_product 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-11 05:36 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "order_product",displayName = "order_product")
@ApiModel(value = "订单产品信息表",discriminator = "订单产品信息表")
public class OrderProduct extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "custom_order_id",displayName = "订单主键ID")
	@ApiModelProperty(value = "订单主键ID",required = true)
	private String customOrderId;
	@Column(type = Types.VARCHAR,length = 20,name = "no",displayName = "产品编号")
	@ApiModelProperty(value = "产品编号")
	private String no;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "类型:0 橱柜(J) 1 衣柜(B) 2 成品家具(J) 3电器(J) 4 五金(J) 5 样块(Y)")
	@ApiModelProperty(value = "类型:0 橱柜(J) 1 衣柜(B) 2 成品家具(J) 3电器(J) 4 五金(J) 5 样块(Y)",required = true)
	private Integer type;
	@Column(type = Types.TINYINT,nullable = false,name = "stock_count",displayName = "")
	@ApiModelProperty(value = "产品包裹数",required = true)
	private Integer stockCount;
	@Column(type = Types.TINYINT,name = "series",displayName = "系列 0 定制实木 1 特供实木 2 美克 3 康奈 4 慧娜 5 模压")
	@ApiModelProperty(value = "系列 0 定制实木 1 特供实木 2 美克 3 康奈 4 慧娜 5 模压",required = true)
	private Integer series;
	@Column(type = Types.TINYINT,name = "status",displayName = "状态： 0-待财务审核、1-待生产、2-生产中、3-已入库、4-待发货、5-已发货")
	@ApiModelProperty(value = "状态： 0-待财务审核、1-待生产、2-生产中、3-已入库、4-待发货、5-已发货",required = true)
	private Integer status;
	@Column(type = Types.VARCHAR,length = 50,name = "door_color",displayName = "颜色")
	@ApiModelProperty(value = "门板颜色")
	private String doorColor;
	@Column(type = Types.VARCHAR,length = 50,name = "body_color",displayName = "颜色")
	@ApiModelProperty(value = "柜体颜色")
	private String bodyColor;
	@Column(type = Types.TINYINT,name = "body_material",displayName = "柜体材质")
	@ApiModelProperty(value = "柜体材质")
	private String bodyMaterial;
	@Column(type = Types.TINYINT,name = "body_tec",displayName = "衣柜柜体工艺")
	@ApiModelProperty(value = "衣柜柜体工艺")
	private String bodyTec;
	@Column(type = Types.VARCHAR,length = 50,name = "door",displayName = "门型")
	@ApiModelProperty(value = "门型")
	private String door;
	@Column(type = Types.VARCHAR,length = 300,name = "notes",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String notes;
	@Column(type = Types.DATE,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间",required = true)
	private Date created;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人",required = true)
	private String creator;
	@Column(type = Types.DATE,name = "update_time",displayName = "修改时间")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	@Column(type = Types.CHAR,length = 13,name = "update_user",displayName = "修改人")
	@ApiModelProperty(value = "修改人")
	private String updateUser;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "price",displayName = "价格")
	@ApiModelProperty(value = "价格")
	private BigDecimal price;
	@Column(type = Types.CHAR,length = 13,name = "after_apply_id",displayName = "售后单id")
	@ApiModelProperty(value = "售后单id")
	private String afterApplyId;
	@Column(type = Types.VARCHAR,length = 100,name = "install",displayName = "安装位置")
	@ApiModelProperty(value = "安装位置")
	private String install;
	@Column(type = Types.DATE,name = "stock_input",displayName = "入库时间")
	@ApiModelProperty(value = "入库时间")
	private Date stockInput;
	@Column(type = Types.CHAR,length = 13,name = "stock_input_creator",displayName = "入库人")
	@ApiModelProperty(value = "入库人")
	private String stockInputCreator;
	@Column(type = Types.DATE,name = "delivery_time",displayName = "发货时间")
	@ApiModelProperty(value = "发货时间")
	private Date deliveryTime;
	@Column(type = Types.CHAR,length = 13,name = "delivery_creator",displayName = "发货人")
	@ApiModelProperty(value = "发货人")
	private String deliveryCreator;
	@Column(type = Types.DATE,name = "plan_finish_time",displayName = "计划完成时间")
	@ApiModelProperty(value = "计划完成时间")
	private Date planFinishTime;
	@Column(type = Types.DATE,name = "finish_time",displayName = "实际生产完成时间")
	@ApiModelProperty(value = "实际生产完成时间")
	private Date finishTime;
	@Column(type = Types.DATE,name = "plan_order_finish_time",displayName = "计划交付时间")
	@ApiModelProperty(value = "计划交付时间")
	private Date planOrderFinishTime;
	@Column(type = Types.DATE,name = "plan_delivery_verify_time",displayName = "发货审核时间")
	@ApiModelProperty(value = "发货审核时间")
	private Date planDeliveryVerifyTime;
	@Column(type = Types.DATE,name = "pay_time",displayName = "财务审核时间/下单时间")
	@ApiModelProperty(value = "财务审核时间/下单时间")
	private Date payTime;
	@Column(type = Types.VARCHAR,length = 100,name = "logistics_company_id",displayName = "物流公司id")
	@ApiModelProperty(value = "物流公司id")
	private String logisticsCompanyId;
	@Column(type = Types.VARCHAR,length = 50,name = "logistics_no",displayName = "物流单号")
	@ApiModelProperty(value = "物流单号")
	private String logisticsNo;
	@Column(type = Types.DATE,name = "start_produce_time",displayName = "产品开始生产时间")
	@ApiModelProperty(value = "产品开始生产时间")
	private Date startProduceTime;
	@Column(type = Types.VARCHAR, length = 50, nullable = false, name = "old_no", displayName = "原订单编号（跨月审核时，更新订单编号时保存老的编号）")
	@ApiModelProperty(value = "原生产单编号（跨月审核时，更新生产单单编号时保存老的编号）")
	private String oldNo;
	@Column(type = Types.TINYINT, length = 2, nullable = false, name = "is_change", displayName = "跨越单是否已更换编号 0-未更换 1-已更换")
	@ApiModelProperty(value = "跨越单是否已更换编号 0-未更换 1-已更换")
	private Integer change;
	@Column(type = Types.VARCHAR, length = 200, nullable = false, name = "packge_notes", displayName = "打包入库备注")
	@ApiModelProperty(value = "打包入库备注")
	private String packgeNotes;
	@Column(type = Types.TINYINT,name = "flag",displayName = "订单产品的存在状态：0-正常 1-废除")
	@ApiModelProperty(value = "订单产品的存在状态：0-正常 1-废除")
	private Integer flag;
	@Column(type = Types.TINYINT,length = 2,nullable = false,updatable = false,name = "part_stock",displayName = "部分入库 0-不是 1-是")
	@ApiModelProperty(value = "部分入库 0-不是 1-是")
	private Integer partStock;
	@Column(type = Types.TINYINT,length = 20,name = "has_deliver_count",displayName = "已发货数量")
	@ApiModelProperty(value = "已发货数量")
	private Integer hasDeliverCount;
	@Column(type = Types.TINYINT,length = 2,name = "aftersale_num",displayName = "售后次数")
	@ApiModelProperty(value = "售后次数")
	private Integer aftersaleNum;
	@Column(type = Types.VARCHAR, length = 100, name = "delay_reason",displayName = "延期原因,引用basecode表code,多个使用逗号分割")
	@ApiModelProperty(value = "延期原因")
	private String delayReason;
	@Column(type = Types.VARCHAR, length = 255, name = "delay_reason_remark", displayName = "延期备注")
	@ApiModelProperty(value = "延期备注")
	private String delayReasonRemark;

	public OrderProduct() {
     }

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.customOrderId == null) {
			validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
				validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.doorColor) > 50) {
			validResult.put("doorColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.bodyColor) > 50) {
			validResult.put("bodyColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.door) > 50) {
			validResult.put("door", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 300) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.updateUser) > 13) {
			validResult.put("updateUser", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(this.price!=null){
			if(this.price.compareTo(new BigDecimal(100000000))!=-1){
				validResult.put("price",AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("type","series","doorColor","bodyColor","door","notes","updateTime","updateUser","price","install","hasDeliverCount");

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
		if(map.containsKey("series")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("series",String.class))) {
				validResult.put("series", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("series")) {
			if (map.get("series")  == null) {
				validResult.put("series", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("doorColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorColor",String.class)) > 50) {
				validResult.put("doorColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("bodyColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bodyColor",String.class)) > 50) {
				validResult.put("bodyColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("door")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("door",String.class)) > 50) {
				validResult.put("door", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 300) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("updateUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updateUser",String.class)) > 13) {
				validResult.put("updateUser", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("price")) {
			if (!DataValidatorUtils.isDecmal4(new BigDecimal(map.getTypedValue("price",String.class)).toPlainString())) {
				validResult.put("price", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
			if (map.getTypedValue("price",BigDecimal.class).compareTo(new BigDecimal(100000000))!=-1){
				validResult.put("price",AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("install")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("install",String.class)) > 100) {
				validResult.put("install", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public String getBodyTec() {return bodyTec;}

	public void setBodyTec(String bodyTec) {this.bodyTec = bodyTec;}

	public String getBodyMaterial() {return bodyMaterial;}

	public void setBodyMaterial(String bodyMaterial) {this.bodyMaterial = bodyMaterial;}

	public Date getPlanDeliveryVerifyTime() {return planDeliveryVerifyTime;}

	public void setPlanDeliveryVerifyTime(Date planDeliveryVerifyTime) {this.planDeliveryVerifyTime = planDeliveryVerifyTime;}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPlanOrderFinishTime() {
		return planOrderFinishTime;
	}

	public void setPlanOrderFinishTime(Date planOrderFinishTime) {
		this.planOrderFinishTime = planOrderFinishTime;
	}

	public Date getStockInput() {
		return stockInput;
	}

	public void setStockInput(Date stockInput) {
		this.stockInput = stockInput;
	}

	public String getStockInputCreator() {
		return stockInputCreator;
	}

	public void setStockInputCreator(String stockInputCreator) {
		this.stockInputCreator = stockInputCreator;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryCreator() {
		return deliveryCreator;
	}

	public void setDeliveryCreator(String deliveryCreator) {
		this.deliveryCreator = deliveryCreator;
	}

	public Date getPlanFinishTime() {
		return planFinishTime;
	}

	public void setPlanFinishTime(Date planFinishTime) {
		this.planFinishTime = planFinishTime;
	}

	public String getNo() {return no;}

	public void setNo(String no) {this.no = no;}

	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setSeries(Integer series){
		this.series=series;
	}

	public Integer getSeries(){
		return series;
	}

	public void setDoorColor(String doorColor){
		this.doorColor=doorColor;
	}

	public String getDoorColor(){
		return doorColor;
	}

	public void setDoor(String door){
		this.door=door;
	}

	public String getDoor(){
		return door;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
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

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}

	public String getUpdateUser(){
		return updateUser;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBodyColor() {
		return bodyColor;
	}

	public void setBodyColor(String bodyColor) {
		this.bodyColor = bodyColor;
	}

	public String getAfterApplyId() {
		return afterApplyId;
	}

	public void setAfterApplyId(String afterApplyId) {
		this.afterApplyId = afterApplyId;
	}

	public String getInstall() {
		return install;
	}

	public void setInstall(String install) {
		this.install = install;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getStartProduceTime() {
		return startProduceTime;
	}

	public void setStartProduceTime(Date startProduceTime) {
		this.startProduceTime = startProduceTime;
	}

	public String getOldNo() {
		return oldNo;
	}

	public void setOldNo(String oldNo) {
		this.oldNo = oldNo;
	}

	public Integer getChange() {
		return change;
	}

	public void setChange(Integer change) {
		this.change = change;
	}

	public String getPackgeNotes() {
		return packgeNotes;
	}

	public void setPackgeNotes(String packgeNotes) {
		this.packgeNotes = packgeNotes;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getPartStock() {
		return partStock;
	}

	public void setPartStock(Integer partStock) {
		this.partStock = partStock;
	}

	public Integer getHasDeliverCount() {
		return hasDeliverCount;
	}

	public void setHasDeliverCount(Integer hasDeliverCount) {
		this.hasDeliverCount = hasDeliverCount;
	}

	public Integer getAftersaleNum() {
		return aftersaleNum;
	}

	public void setAftersaleNum(Integer aftersaleNum) {
		this.aftersaleNum = aftersaleNum;
	}

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    public String getDelayReasonRemark() {
        return delayReasonRemark;
    }

    public void setDelayReasonRemark(String delayReasonRemark) {
        this.delayReasonRemark = delayReasonRemark;
    }
}
