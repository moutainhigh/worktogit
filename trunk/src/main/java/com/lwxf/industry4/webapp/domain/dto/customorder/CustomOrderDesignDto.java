package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDesign;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;

import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/15 16:43
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单设计信息",discriminator = "订单设计信息")
public class CustomOrderDesignDto extends CustomOrderDesign {

    @ApiModelProperty(value = "设计师名称")
    private String designName;//设计师名称
    @ApiModelProperty(value = "设计师电话")
    private String designMobile;//设计师电话
    @ApiModelProperty(value = "设计单状态")
    private String statusName;
    @ApiModelProperty(value = "设计单资源集合")
    private List<CustomOrderFiles> fileList;

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public String getDesignMobile() {
        return designMobile;
    }

    public void setDesignMobile(String designMobile) {
        this.designMobile = designMobile;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<CustomOrderFiles> getFileList() {
        return fileList;
    }

    public void setFileList(List<CustomOrderFiles> fileList) {
        this.fileList = fileList;
    }
}

