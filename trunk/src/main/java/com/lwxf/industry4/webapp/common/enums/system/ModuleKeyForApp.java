package com.lwxf.industry4.webapp.common.enums.system;

public enum ModuleKeyForApp {

    BEMPL("orgmng","员工管理"),
    DEALERMNG_DEALER("dealermng","经销商信息管理"),
    CUSTOMERMNG("customermng","客户管理"),
    ORDERMNG("ordermng","订单管理"),
    DESIGNMNG("designmng","设计管理"),
    FINANCINGMNG("financingmng","财务管理"),
    MANUFACTUREMNG("manufacturemng","生产管理"),
    STOCKMNG_4UJH7687HIPS("stockmng","入库"),
    DISPATCHSTATE("dispatchingmng","发货"),
    BAFTERSALEMNG("aftersalemng","售后管理"),
    SUPPLIERMNG_SUPPLIER("suppliermng","供应商信息管理"),
    PURSHASEMNG_PURCHASE("purchasemng","采购管理"),
    PRODMNG_PROD("prodmng","产品管理"),
    STOCKMNG("stockmng","库存管理");

    private String value;
    private String name;


    ModuleKeyForApp(String value, String name) {
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
        return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }
    public String getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static ModuleKeyForApp getByValue(String value){
        ModuleKeyForApp allVaues[] = ModuleKeyForApp.values();
        ModuleKeyForApp moduleKeyForApp;
        for (int i=0,len = allVaues.length;i<len;i++){
            moduleKeyForApp = allVaues[i];
            if(moduleKeyForApp.getValue().equals(value)){
                return moduleKeyForApp;
            }
        }
        return null;
    }
}
