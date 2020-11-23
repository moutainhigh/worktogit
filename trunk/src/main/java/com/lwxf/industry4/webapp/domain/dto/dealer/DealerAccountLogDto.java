package com.lwxf.industry4.webapp.domain.dto.dealer;

import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/11 16:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DealerAccountLogDto extends DealerAccountLog {
    private String creatorName;

    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}

