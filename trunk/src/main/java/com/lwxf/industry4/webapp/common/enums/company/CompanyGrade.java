package com.lwxf.industry4.webapp.common.enums.company;

public enum CompanyGrade {

    LEVELZERO(0,"一级"),
    LEVELONE(1,"二级"),
    LEVELTWO(2,"三级"),
    LEVELTHREE(3,"四级"),
    LEVELFOUR(4,"五级"),
    ;

    private int value;
    private String name;

    CompanyGrade(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * 验证数据有效性
     * @param value
     * @return
     */
    public static boolean contains(int value){
        return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }
    public static CompanyGrade getByValue(int value){
        CompanyGrade allVaues[] = CompanyGrade.values();
        CompanyGrade status;
        for (int i = 0,len=allVaues.length; i <len ; i++) {
            status = allVaues[i];
            if(status.getValue()==value){
                return status;
            }
        }
        return null;
    }
}
