package com.lwxf.industry4.webapp.common.enums.product;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/4/16 11:03
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProductCategoryKey {

    finished("cp","成品");


    private String id;
    private String name;


    ProductCategoryKey(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
