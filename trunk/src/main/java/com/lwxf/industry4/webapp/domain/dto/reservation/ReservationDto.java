package com.lwxf.industry4.webapp.domain.dto.reservation;

import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/9/27 9:41
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ReservationDto extends Reservation {

    private  String atoken;

    private String storeName;

    private String statusStr;

    public String getAtoken() {
        return atoken;
    }

    public void setAtoken(String atoken) {
        this.atoken = atoken;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}

