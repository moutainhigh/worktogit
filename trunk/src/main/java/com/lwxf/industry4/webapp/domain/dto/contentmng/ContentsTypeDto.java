package com.lwxf.industry4.webapp.domain.dto.contentmng;

import javax.mail.internet.ContentType;

public class ContentsTypeDto extends ContentType {

    String pName; //父节点名称

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
