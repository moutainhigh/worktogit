package com.lwxf.industry4.webapp.domain.entity.design;
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
 * 功能：design_scheme 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-15 09:42
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "design_scheme",displayName = "design_scheme")
@ApiModel(value = "设计案例信息",discriminator = "设计案例信息")
public class DesignScheme extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "名称")
	@ApiModelProperty(value = "名称")
	private String name;
	@Column(type = Types.CHAR,length = 13,name = "designer",displayName = "设计师")
	@ApiModelProperty(value = "设计师")
	private String designer;
	@Column(type = Types.CHAR,length = 13,name = "door_state",displayName = "户型")
	@ApiModelProperty(value = "户型")
	private String doorState;
	@Column(type = Types.FLOAT,name = "area",displayName = "面积")
	@ApiModelProperty(value = "面积")
	private Float area;
	@Column(type = Types.CHAR,length = 13,name = "styles",displayName = "风格")
	@ApiModelProperty(value = "风格")
	private String styles;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "cost",displayName = "造价")
	@ApiModelProperty(value = "造价")
	private BigDecimal cost;
	@Column(type = Types.INTEGER,name = "plural_forms",displayName = "工期")
	@ApiModelProperty(value = "工期")
	private Integer pluralForms;
	@Column(type = Types.VARCHAR,length = 500,name = "notes",displayName = "描述")
	@ApiModelProperty(value = "描述")
	private String notes;
	@Column(type = Types.VARCHAR,length = 150,name = "cover",displayName = "封面")
	@ApiModelProperty(value = "封面")
	private String cover;
	@Column(type = Types.VARCHAR,length = 100,name = "community_address",displayName = "小区地址")
	@ApiModelProperty(value = "小区地址")
	private String communityAddress;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "公司id（厂家是工厂id，经销商id）")
	@ApiModelProperty(value = "公司id（厂家是工厂id，经销商id）")
	private String companyId;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 草稿；1 - 待审核；2 - 已发布;3 - 已下架;4 - 删除;默认为0")
	@ApiModelProperty(value = "状态：0 - 草稿；1 - 待审核；2 - 已发布;3 - 已下架;4 - 删除;默认为0")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 100,name = "theme_color_brand",displayName = "主题色")
	@ApiModelProperty(value = "主题色")
	private String themeColorBrand;
	@Column(type = Types.TINYINT,nullable = false,name = "source",displayName = "来源（0工厂，1经销商）")
	@ApiModelProperty(value = "来源（0工厂，1经销商）")
	private Integer source;
	@Column(type = Types.INTEGER,nullable = false,name = "page_view",displayName = "浏览量")
	@ApiModelProperty(value = "浏览量")
	private Integer pageView;
	@Column(type = Types.INTEGER,nullable = false,name = "praise",displayName = "点赞量")
	@ApiModelProperty(value = "点赞量")
	private Integer praise;
	@Column(type = Types.INTEGER,nullable = false,name = "share",displayName = "分享量")
	@ApiModelProperty(value = "分享量")
	private Integer share;
	@Column(type = Types.INTEGER,nullable = false,name = "attention",displayName = "收藏量")
	@ApiModelProperty(value = "收藏量")
	private Integer attention;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "no",displayName = "设计编号")
	@ApiModelProperty(value = "设计编号")
	private String no;
	@Column(type = Types.VARCHAR,length = 200,name = "result",displayName = "处理结果")
	@ApiModelProperty(value = "处理结果")
	private String result;
	@Column(type = Types.VARCHAR,length = 200,name = "vr_video_path",displayName = "3D_VR的路径")
	@ApiModelProperty(value = "3D_VR的路径")
	private String vrVideoPath;
	@Column(type = Types.VARCHAR,length = 150,name = "link",displayName = "链接路径")
	@ApiModelProperty(value = "链接路径")
	private String link;

	public DesignScheme() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.designer) > 13) {
			validResult.put("designer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.doorState) > 13) {
			validResult.put("doorState", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.styles) > 13) {
			validResult.put("styles", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.cover) > 150) {
			validResult.put("cover", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.communityAddress) > 100) {
			validResult.put("communityAddress", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
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
		if (LwxfStringUtils.getStringLength(this.themeColorBrand) > 100) {
			validResult.put("themeColorBrand", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.source == null) {
			validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.pageView == null) {
			validResult.put("pageView", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.praise == null) {
			validResult.put("praise", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.share == null) {
			validResult.put("share", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.attention == null) {
			validResult.put("attention", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.no == null) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.no) > 50) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.result) > 200) {
			validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(this.area!=null){
			if(this.area>=100000){
				validResult.put("area",AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(this.cost!=null){
			if(this.cost.compareTo(new BigDecimal(100000000))!=-1){
				validResult.put("cost",AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","doorState","area","styles","cost","pluralForms","notes","designer","cover","communityAddress","companyId","created","creator","status","themeColorBrand","source","pageView","praise","share","attention","no","result");

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
		if(map.containsKey("area")) {
			if (!DataValidatorUtils.isFloat(map.getTypedValue("area",String.class))) {
				validResult.put("area", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("cost")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("cost",String.class))) {
				validResult.put("cost", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("pluralForms")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("pluralForms",String.class))) {
				validResult.put("pluralForms", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("source")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("source",String.class))) {
				validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("pageView")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("pageView",String.class))) {
				validResult.put("pageView", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("praise")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("praise",String.class))) {
				validResult.put("praise", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("share")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("share",String.class))) {
				validResult.put("share", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("attention")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("attention",String.class))) {
				validResult.put("attention", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("designer")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("designer",String.class)) > 13) {
				validResult.put("designer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("doorState")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorState",String.class)) > 100) {
				validResult.put("doorState", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("styles")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("styles",String.class)) > 100) {
				validResult.put("styles", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cover")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cover",String.class)) > 150) {
				validResult.put("cover", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("communityAddress")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("communityAddress",String.class)) > 100) {
				validResult.put("communityAddress", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("themeColorBrand")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("themeColorBrand",String.class)) > 100) {
				validResult.put("themeColorBrand", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("source")) {
			if (map.get("source")  == null) {
				validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("pageView")) {
			if (map.get("pageView")  == null) {
				validResult.put("pageView", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("praise")) {
			if (map.get("praise")  == null) {
				validResult.put("praise", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("share")) {
			if (map.get("share")  == null) {
				validResult.put("share", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("attention")) {
			if (map.get("attention")  == null) {
				validResult.put("attention", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("no")) {
			if (map.getTypedValue("no",String.class)  == null) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
					validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("result")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("result",String.class)) > 200) {
				validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public void setDesigner(String designer){
		this.designer=designer;
	}

	public String getDesigner(){
		return designer;
	}

	public void setDoorState(String doorState){
		this.doorState=doorState;
	}

	public String getDoorState(){
		return doorState;
	}

	public void setArea(Float area){
		this.area=area;
	}

	public Float getArea(){
		return area;
	}

	public void setStyles(String styles){
		this.styles=styles;
	}

	public String getStyles(){
		return styles;
	}

	public void setCost(BigDecimal cost){
		this.cost=cost;
	}

	public BigDecimal getCost(){
		return cost;
	}

	public void setPluralForms(Integer pluralForms){
		this.pluralForms=pluralForms;
	}

	public Integer getPluralForms(){
		return pluralForms;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setCover(String cover){
		this.cover=cover;
	}

	public String getCover(){
		return cover;
	}

	public void setCommunityAddress(String communityAddress){
		this.communityAddress=communityAddress;
	}

	public String getCommunityAddress(){
		return communityAddress;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
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

	public void setThemeColorBrand(String themeColorBrand){
		this.themeColorBrand=themeColorBrand;
	}

	public String getThemeColorBrand(){
		return themeColorBrand;
	}

	public void setSource(Integer source){
		this.source=source;
	}

	public Integer getSource(){
		return source;
	}

	public void setPageView(Integer pageView){
		this.pageView=pageView;
	}

	public Integer getPageView(){
		return pageView;
	}

	public void setPraise(Integer praise){
		this.praise=praise;
	}

	public Integer getPraise(){
		return praise;
	}

	public void setShare(Integer share){
		this.share=share;
	}

	public Integer getShare(){
		return share;
	}

	public void setAttention(Integer attention){
		this.attention=attention;
	}

	public Integer getAttention(){
		return attention;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setResult(String result){
		this.result=result;
	}

	public String getResult(){
		return result;
	}

	public String getVrVideoPath() {
		return vrVideoPath;
	}

	public void setVrVideoPath(String vrVideoPath) {
		this.vrVideoPath = vrVideoPath;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
