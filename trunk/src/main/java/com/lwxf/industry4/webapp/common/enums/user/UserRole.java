package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：用户角色枚举
 *
 * @author：DJL
 * @create：2020/1/3 13:48
 * @version：2020 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UserRole {
    PLACE_ORDER_ROLE("017"), // 拆单人员/下单人
    MERCHANDISER_ROLE("018"), // 跟单人员
    ORDER_LEADER_ROLE("ddzg"), // 订单部主管
    FINANCE_LEADER_ROLE("011"), // 财务主管
    FINANCE_STAFF_ROLE("012"), // 财务员
    STOREKEEPER_ROLE("019"), // 库管
    AFTERSALE_LEADER_ROLE("025"), // 售后主管
    SHIPMENTS_LEADER_ROLE("027"), // 发货主管
    SHIPMENTS_STAFF_ROLE("028"); // 发货员

    private String roleKey;

    UserRole(String roleKey) {
        this.roleKey = roleKey;
    }

    /**
     * 数据范围校验
     *
     * @param roleKey
     * @return
     */
    public static boolean contains(String roleKey) {
        return null == getByValue(roleKey) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
    }

    public String getValue(){
        return this.roleKey;
    }

    public static UserRole getByValue(String value){
        UserRole allVaues[] = UserRole.values();
        UserRole role;
        for (int i=0,len = allVaues.length;i<len;i++){
            role = allVaues[i];
            if(role.getValue().equals(value)){
                return role;
            }
        }
        return null;
    }
}
