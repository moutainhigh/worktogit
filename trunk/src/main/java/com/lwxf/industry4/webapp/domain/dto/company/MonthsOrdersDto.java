package com.lwxf.industry4.webapp.domain.dto.company;

/**
 * 用于app经销商账户首页统计
 * create By zhangxiaolin
 */
public class MonthsOrdersDto {

    private String companyId; //公司id
    private String month;  //某月
    private Integer orderCount;  //订单数
    private String companyName; //公司名称

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrderCount() {return orderCount;}

    public void setOrderCount(Integer orderCount) {this.orderCount = orderCount; }
}
