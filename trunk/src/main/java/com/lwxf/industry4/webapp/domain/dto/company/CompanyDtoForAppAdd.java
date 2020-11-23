package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.domain.entity.company.Company;
import io.swagger.annotations.ApiModelProperty;

public class CompanyDtoForAppAdd extends Company {

    @ApiModelProperty(value="上传文件id,多个用逗号分开",name="leaderName",required=true)
    private String fileIds ="";//图片id集合
    private String leaderName ="";
    private String province;
    private String city;
    private String area;
    @ApiModelProperty(value="Ios地区选择字段，格式为:省-市-区",name="cityNameForIos",required=true)
    private String cityNameForIos = "";  //适配ios终端，由于ios终端无法返回id字段，只能返回名字，格式为：省-市-区，获取后根据名称进行查询具体的cityID

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCityNameForIos() {
        return cityNameForIos;
    }

    public void setCityNameForIos(String cityNameForIos) {
        this.cityNameForIos = cityNameForIos;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getFileIds() {
        return fileIds;
    }
    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

}
