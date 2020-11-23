package com.lwxf.industry4.webapp.domain.dto.activity;

import com.lwxf.industry4.webapp.domain.entity.activity.ActivityJoin;
import io.swagger.annotations.ApiModel;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/3/11 17:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="活动参与者列表",description="活动参与者列表")
public class ActivityJoinDto extends ActivityJoin {

    private String name;//姓名
    private String mobile;//电话
    private String companyName;//公司名称
    private String companyLeader;//公司负责人名称
    private String companyLeaderTel;//公司电话

    public String getCompanyName() {return companyName;}

    public void setCompanyName(String companyName) {this.companyName = companyName; }

    public String getCompanyLeader() {return companyLeader;}

    public void setCompanyLeader(String companyLeader) {this.companyLeader = companyLeader; }

    public String getCompanyLeaderTel() {return companyLeaderTel; }

    public void setCompanyLeaderTel(String companyLeaderTel) {this.companyLeaderTel = companyLeaderTel;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
