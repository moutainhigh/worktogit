package com.lwxf.industry4.webapp.baseservice.rongcloud.message.system;

import java.util.Map;

import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageCategory;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageResource;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageType;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.ResourceOperation;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageResource;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageType;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.ResourceOperation;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/9 11:06
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CreateReservationStatusMessage extends AbstractSystemMessage {
    protected static final String CONTENT_TEXT = "您的预约单有新消息了";
    protected static final String MSG_KEY_EXTRA_RESERVATION ="reservation";
    protected static final String MSG_EXTRA_DESIGNER ="designer";

    private Reservation reservation;
    private User designer;

    public CreateReservationStatusMessage(Reservation reservation, StoreConfig storeConfig, User creator,User designer) {
        this.reservation = reservation;
        this.storeConfig = storeConfig;
        this.creator = creator;
        this.designer = designer;
        this.setType(MessageType.RESERVATION_STATUS);
        this.setOperation(ResourceOperation.UPDATE);
        this.setResource(MessageResource.RESERVATION);
        // 必设
        this.setContentText(CONTENT_TEXT);
    }

    @Override
    protected void setExtraProperInfo(Map<String, Object> extra) {
        extra.put(MSG_EXTRA_DESIGNER,this.designer);
        extra.put(MSG_KEY_EXTRA_RESERVATION,this.reservation);
    }
}

