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
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-10-07 16:03
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CreateReservationMessage extends AbstractSystemMessage {
	protected static final String DEFAULT_CONTENT_TEXT = "恭喜，您有新的预约来了！";
	protected static final String MSG_KEY_EXTRA_RESERVATION ="reservation";
	private Reservation reservation;

	public CreateReservationMessage(Reservation reservation,StoreConfig storeConfig,User creator){
		this(DEFAULT_CONTENT_TEXT,reservation,storeConfig,creator);
	}

	public CreateReservationMessage(String contentText,Reservation reservation,StoreConfig storeConfig,User creator){
		this.setContentText(contentText);
		this.reservation = reservation;
		this.storeConfig = storeConfig;
		this.creator = creator;
		this.setType(MessageType.RESERVATION);
		this.setOperation(ResourceOperation.CREATE);
		this.setResource(MessageResource.RESERVATION);

	}

	@Override
	protected void setExtraProperInfo(Map<String, Object> extra) {
		extra.put(MSG_KEY_EXTRA_RESERVATION,this.reservation);
	}
}
