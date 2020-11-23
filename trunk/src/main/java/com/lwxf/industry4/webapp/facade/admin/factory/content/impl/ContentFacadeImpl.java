package com.lwxf.industry4.webapp.facade.admin.factory.content.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.bizservice.activity.ActivityInfoService;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsService;
import com.lwxf.industry4.webapp.bizservice.design.DesignSchemeService;
import com.lwxf.industry4.webapp.bizservice.design.SchemeContentService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.facade.admin.factory.content.ContentFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/15/015 16:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "contentFacade")
public class ContentFacadeImpl extends BaseFacadeImpl implements ContentFacade {
	@Resource(name = "designSchemeService")
	private DesignSchemeService designSchemeService;
	@Resource(name = "schemeContentService")
	private SchemeContentService schemeContentService;
	@Resource(name = "activityInfoService")
	private ActivityInfoService activityInfoService;
	@Resource(name = "contentsService")
	private ContentsService contentsService;


	@Override
	public RequestResult findContentByTypeAndId(Integer type, String id) {
		switch (type){
			case 0://设计案例
				MapContext mapContext = new MapContext();
				mapContext.put("designSheme",this.designSchemeService.findOneById(id));
				mapContext.put("designContentList",this.schemeContentService.findBySchemeId(id));
				return ResultFactory.generateRequestResult(mapContext);
			case 1://活动
				return ResultFactory.generateRequestResult(this.activityInfoService.findOneById(id));
			case 2://内容
				return ResultFactory.generateRequestResult(this.contentsService.findContentById(id));
			default:
				return ResultFactory.generateResNotFoundResult();
		}
	}
}
