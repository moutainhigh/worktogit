package com.lwxf.industry4.webapp.domain.dto.design;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/4 9:35
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "设计案例信息",discriminator = "设计案例信息")
public class DesignSchemeDto extends DesignScheme {

    @ApiModelProperty(value = "设计师名字")
    private String designer_name;//设计师名字
    @ApiModelProperty(value = "风格名称")
    private String design_style_name;//风格名称
    @ApiModelProperty(value = "公司名称")
    private String company_name;//公司名称
    @ApiModelProperty(value = "设计师头像")
    private String designer_avatar;//设计师头像
    @ApiModelProperty(value = "是否点赞")
    private boolean collect;//是否点赞
    @ApiModelProperty(value = "案例数")
    private Integer scheme_count;//案例数
    @ApiModelProperty(value = "关注设计师的人数")
    private Integer designer_count;//关注设计师的人数
    @ApiModelProperty(value = "???")
    private String caseId;
    @ApiModelProperty(value = "分享路径")
    private String sharePath;//分享路径
    @ApiModelProperty(value = "创建人名称")
    private String creatorName;//创建人名称
    @ApiModelProperty(value = "户型名称")
    private String doorStateName;//户型名称

    public String getSharePath() {
        return sharePath;
    }

    public void setSharePath(String sharePath) {
        this.sharePath = sharePath;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getDesigner_count() {
        return designer_count;
    }

    public void setDesigner_count(Integer designer_count) {
        this.designer_count = designer_count;
    }

    public Integer getScheme_count() {
        return scheme_count;
    }

    public void setScheme_count(Integer scheme_count) {
        this.scheme_count = scheme_count;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getDesigner_avatar() {
        return designer_avatar;
    }

    public void setDesigner_avatar(String designer_avatar) {
        this.designer_avatar = designer_avatar;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDesigner_name() {
        return designer_name;
    }

    public void setDesigner_name(String designer_name) {
        this.designer_name = designer_name;
    }

    public String getDesign_style_name() {
        return design_style_name;
    }

    public void setDesign_style_name(String design_style_name) {
        this.design_style_name = design_style_name;
    }

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

    public String getDoorStateName() {
        return doorStateName;
    }

    public void setDoorStateName(String doorStateName) {
        this.doorStateName = doorStateName;
    }
}

