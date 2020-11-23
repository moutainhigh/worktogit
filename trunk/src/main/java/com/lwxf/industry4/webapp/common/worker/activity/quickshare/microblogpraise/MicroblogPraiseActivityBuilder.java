package com.lwxf.industry4.webapp.common.worker.activity.quickshare.microblogpraise;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogPraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.common.enums.activity.SystemActivityEvent;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.worker.activity.BaseActivityBuilder;
import com.lwxf.industry4.webapp.common.worker.activity.base.ActivityInfoEntity;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Microblog;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogPraise;
import com.lwxf.industry4.webapp.domain.entity.system.SystemActivity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogPraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.common.enums.activity.SystemActivityEvent;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.worker.activity.BaseActivityBuilder;
import com.lwxf.industry4.webapp.common.worker.activity.base.ActivityInfoEntity;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Microblog;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogPraise;
import com.lwxf.industry4.webapp.domain.entity.system.SystemActivity;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/7/19 15:06
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public class MicroblogPraiseActivityBuilder extends BaseActivityBuilder {
	JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
	@Resource(name = "microblogPraiseService")
	private MicroblogPraiseService microblogPraiseService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "microblogService")
	private MicroblogService microblogService;


	@Override
	public void registerToWorker() {
		this.systemActivityWorker.register(MicroblogPraise.class,this);
	}

	@Override
	public Object build(TSManagerEntity tsManagerEntity, SQLType sqlType) {
			//实例化一个日志对象
			SystemActivity systemActivity = newSystemActivityInstance();
			//得到mapperParams（对象或者map）
			Object params = tsManagerEntity.getMapperParams();
			//活动日志r3实体类
			ActivityInfoEntity activityInfoEntity = new ActivityInfoEntity();
			//
			MicroblogPraiseResEntity microblogPraiseResEntity = new MicroblogPraiseResEntity();
			Map<String, Object> parentMap = new HashMap<>();
			Map<String,Object> userMap;
			MicroblogPraise microblogPraise;
			Microblog microblog;
			String id;

			switch (sqlType){
				case INSERT:
					microblogPraise = (MicroblogPraise) params;
					microblog = this.microblogService.findById(microblogPraise.getMicroblogId());
					systemActivity.setR1(microblogPraise.getMicroblogId());
					systemActivity.setEvent(SystemActivityEvent.QUICKSHARE_PRAISE_CREATE.getValue());
					userMap= this.userService.findUserById(microblogPraise.getMemberId());
					microblogPraiseResEntity.setName((String) userMap.get("name"));
					microblogPraiseResEntity.setPath((String) userMap.get("avatar"));
					parentMap.put("microblogId",microblogPraise.getMicroblogId());
					parentMap.put("microblogContent",microblog.getContent());
					parentMap.put("microblogCreator",microblog.getCreator());
					activityInfoEntity.setData(parentMap);
					break;
				case DELETE:
					Map<String,String> map = (Map<String,String>) tsManagerEntity.getMapperParams();
					systemActivity.setEvent(SystemActivityEvent.QUICKSHARE_PRAISE_DELETE.getValue());
					systemActivity.setR1(map.get("microblogId"));
					userMap= this.userService.findUserById(map.get("memberId"));
					microblogPraiseResEntity.setName((String) userMap.get("name"));
					microblogPraiseResEntity.setPath((String) userMap.get("avatar"));
					microblog = this.microblogService.findById(map.get("microblogId"));
					parentMap.put("microblogId",map.get("microblogId"));
					parentMap.put("microblogContent",microblog.getContent());
					parentMap.put("microblogCreator",microblog.getCreator());
					activityInfoEntity.setData(parentMap);
					break;

			}


		activityInfoEntity.setRes(microblogPraiseResEntity);
		systemActivity.setCompanyId(WebUtils.getCurrCompanyId());
		systemActivity.setR3(this.jsonMapper.toJson(activityInfoEntity));
		return systemActivity;

	}

	@Override
	public Object build(TSManualData tsManualData) {
		return null;
	}
}
