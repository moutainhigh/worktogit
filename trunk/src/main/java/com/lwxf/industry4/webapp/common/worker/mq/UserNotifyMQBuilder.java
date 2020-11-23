package com.lwxf.industry4.webapp.common.worker.mq;

import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.common.worker.mq.base.AbstractMQBuilder;
import com.lwxf.industry4.webapp.domain.entity.system.SystemActivity;
import com.lwxf.industry4.webapp.common.worker.mq.base.AbstractMQBuilder;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-07-05 11:06
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
//@Component
public class UserNotifyMQBuilder extends AbstractMQBuilder {
	@Override
	public void registerToWorker() {
		this.rabbitMQWorker.register(SystemActivity.class,this);
	}

	@Override
	public Object build(TSManagerEntity tsManagerEntity, SQLType sqlType) {
		return super.build(tsManagerEntity, sqlType);
	}

	@Override
	public Object build(TSManualData tsManualData) {
		return super.build(tsManualData);
	}

	@Override
	public void doBefore(TSManualData tsManualData) {
		super.doBefore(tsManualData);
	}

	@Override
	public void doBefore(TSManagerEntity tsManagerEntity, SQLType sqlType) {
		super.doBefore(tsManagerEntity, sqlType);
	}
}
