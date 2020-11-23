package com.lwxf.industry4.webapp.domain.dto.supplier;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;

public class SupplierDtoFowWx extends Supplier {

    @ApiModelProperty(value = "供应商类别名称",example="大板")
    String categoryName;
    @ApiModelProperty(value = "附件列表")
    List<UploadFiles> files;
    @ApiModelProperty(value = "地区名称")
    String areaName;
    @ApiModelProperty(value = "状态名称")
    String supplierStageName;

    public String getSupplierStageName() {
        return this.getSupplierStage()==1?"已签约":"未签约";
    }

    public void setSupplierStageName(String supplierStageName) {
        this.supplierStageName = supplierStageName;
    }

    public String getAreaName() {
        if(areaName!=null&&!areaName.equals(""))
        {
            areaName = areaName.replace(",","-");
        }
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<UploadFiles> getFiles() {
        return files;
    }

    public void setFiles(List<UploadFiles> files) {
        this.files = files;
    }

    List<SupplierProduct> listProduct;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SupplierProduct> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<SupplierProduct> listProduct) {
        this.listProduct = listProduct;
    }
}
