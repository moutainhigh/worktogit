package com.lwxf.industry4.webapp.common.enums.financing;

public enum PaymentSimpleFunds {

    //一级收入
    SHOURU_SGLRHK(11,"手工录入货款"),
    SHOURU_SGLRHKJLX(12,"手工录入贷款及利息"),
    SHOURU_JK(13,"借款"),
    SHOURU_QT(14,"其他"),
    //一级支出
    ZHICHU_GYSFK(21,"供应商付款"),
    ZHICHU_JXSHWTK(22,"经销商货物退款"),
    ZHICHU_HJK(23,"还借款"),
    ZHICHU_GMZC(24,"购买资产"),
    ZHICHU_XHLZC(25,"消耗类支出"),
    ZHICHU_CWF(26,"财务费（手续费/利息）"),
    ZHICHU_QT(27,"其他"),
    //一级银行转账
    YINHANG_ZHUANZHANG(30,"银行转账"),


    //二级款项
    ZHICHU_XHLZC_SCZZFY(251,"生产制造费用"),
    ZHICHU_XHLZC_XSFY_(252,"销售费用"),
    ZHICHU_XHLZC_GLFY_(253,"管理费用"),

    //三级款项
    ZHICHU_XHLZC_SCZZFY_ZGXC(25101,"职工薪酬"),
    ZHICHU_XHLZC_SCZZFY_SDF(25102,"水电费"),
    ZHICHU_XHLZC_SCZZFY_BGF(25103,"办公费"),
    ZHICHU_XHLZC_SCZZFY_ZJF(25104,"折旧费"),
    ZHICHU_XHLZC_SCZZFY_ZLF(25105,"租赁费"),
    ZHICHU_XHLZC_SCZZFY_WLF(25106,"物流费"),
    ZHICHU_XHLZC_SCZZFY_CLF(25107,"差旅费"),
    ZHICHU_XHLZC_SCZZFY_WXF(25108,"维修费"),
    ZHICHU_XHLZC_SCZZFY_CHELIANGFEI(25109,"车辆费"),
    ZHICHU_XHLZC_SCZZFY_RYBXF(25110,"人员保险费"),
    ZHICHU_XHLZC_SCZZFY_FLF(25111,"福利费"),
    ZHICHU_XHLZC_SCZZFY_WLXH(25112,"物料消耗"),

    ZHICHU_XHLZC_XSFY_GZXC(25201,"职工薪酬"),
    ZHICHU_XHLZC_XSFY_ZJF(25202,"折旧费"),
    ZHICHU_XHLZC_XSFY_SDF(25203,"水电费"),
    ZHICHU_XHLZC_XSFY_BGF(25204,"办公费"),
    ZHICHU_XHLZC_XSFY_CLF(25205,"差旅费"),
    ZHICHU_XHLZC_XSFY_CHELIANGFEI(25206,"车辆费"),
    ZHICHU_XHLZC_XSFY_ZLF(25207,"租赁费"),
    ZHICHU_XHLZC_XSFY_GXF(25208,"广宣费"),
    ZHICHU_XHLZC_XSFY_ZDF(25209,"招待费"),
    ZHICHU_XHLZC_XSFY_CKHF(25210,"创客会费"),
    ZHICHU_XHLZC_XSFY_HYF(25211,"会议费"),
    ZHICHU_XHLZC_XSFY_CXF(25212,"促销费"),
    ZHICHU_XHLZC_XSFY_QT(25213,"其他"),
    ZHICHU_XHLZC_XSFY_FLF(25214,"福利费"),

    ZHICHU_XHLZC_GLFY_ZGXC(25301,"职工薪酬"),
    ZHICHU_XHLZC_GLFY_ZJF(25302,"折旧费"),
    ZHICHU_XHLZC_GLFY_BGF(25303,"办公费"),
    ZHICHU_XHLZC_GLFY_CLF(25304,"差旅费"),
    ZHICHU_XHLZC_GLFY_ZDF(25305,"招待费"),
    ZHICHU_XHLZC_GLFY_HSF(25306,"伙食费"),
    ZHICHU_XHLZC_GLFY_SF(25307,"税费"),
    ZHICHU_XHLZC_GLFY_SBF(25308,"社保费"),
    ZHICHU_XHLZC_GLFY_TXFY(25309,"推销费用"),
    ZHICHU_XHLZC_GLFY_FLF(25310,"福利费"),
    ZHICHU_XHLZC_GLFY_CHELIANGFEI(25311,"车辆费"),
    ZHICHU_XHLZC_GLFY_YGBX(25312,"员工保险"),
    ZHICHU_XHLZC_GLFY_QT(25313,"其他");

    Integer value;
    String name;
    PaymentSimpleFunds(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    /**
     * 数据范围校验
     *
     * @param value
     * @return
     */
    public static boolean contains(Integer value) {
        return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static PaymentSimpleFunds getByValue(Integer value){
        PaymentSimpleFunds allVaues[] = PaymentSimpleFunds.values();
        PaymentSimpleFunds funds;
        for (int i=0,len = allVaues.length;i<len;i++){
            funds = allVaues[i];
            if(funds.getValue()==value.intValue()){
                return funds;
            }
        }
        return null;
    }
}
