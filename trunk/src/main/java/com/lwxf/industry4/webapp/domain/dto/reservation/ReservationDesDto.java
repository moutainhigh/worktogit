package com.lwxf.industry4.webapp.domain.dto.reservation;

import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignFile;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignFile;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;

import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/6 14:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ReservationDesDto extends ReservationDesignRecord {
    List<ReservationDesignFile> reservationDesignFileList;

    List<String> fileList;

    public List<ReservationDesignFile> getReservationDesignFileList() {
        return reservationDesignFileList;
    }

    public void setReservationDesignFileList(List<ReservationDesignFile> reservationDesignFileList) {
        this.reservationDesignFileList = reservationDesignFileList;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}

