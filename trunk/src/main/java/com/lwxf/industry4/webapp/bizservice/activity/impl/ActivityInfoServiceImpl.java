package com.lwxf.industry4.webapp.bizservice.activity.impl;


import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityInfoService;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.common.enums.activity.ActivityClassify;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityFilesDao;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityInfoDao;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityParamsDao;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityDto;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityInfoDto;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.uniquneCodeGenerator;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("activityInfoService")
public class ActivityInfoServiceImpl extends BaseServiceImpl<ActivityInfo, String, ActivityInfoDao> implements ActivityInfoService {


	@Resource
	@Override	public void setDao( ActivityInfoDao activityInfoDao) {
		this.dao = activityInfoDao;
	}

	@Resource(name = "activityParamsDao")
	private ActivityParamsDao activityParamsDao;

	@Resource(name = "activityFilesDao")
	private ActivityFilesDao activityFilesDao;

	public int addActivityInfo(ActivityDto activityDto) {
		//创建活动信息
		if(activityDto.getId()==null ||activityDto.getId()==""){
			activityDto.setId(UniqueKeyUtil.getStringId());
		}
		ActivityInfo activityInfo = new ActivityInfo();
		BeanUtils.copyProperties(activityDto,activityInfo);
		activityInfo.setViews(0);//浏览数
		activityInfo.setStatus(0);//状态 （0 未发布，1已发布，2活动结束）
		activityInfo.setType(0);//创建人类型（0厂家、1经销商）
		activityInfo.setNo(uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.ACTIVITY_NO));
		activityInfo.setCreator(WebUtils.getCurrUserId());
		activityInfo.setCreated(DateUtil.getSystemDate());
		activityInfo.setCompanyId(WebUtils.getCurrCompanyId());
		int res = this.dao.insert(activityInfo);

		//如果不是海报，则包含项目信息
		if(activityInfo.getClassify()== ActivityClassify.HAS_PARAMS.getValue()){
			//创建项目信息
			List<ActivityParams> list = activityDto.getParamlist();
			if(list!=null && list.size()>0){
				for(ActivityParams param: list){
					ActivityParams ap = new ActivityParams();
					BeanUtils.copyProperties(param,ap);
					ap.setActivityId(activityDto.getId());
					ap.setId(UniqueKeyUtil.getStringId());
					activityParamsDao.insert(ap);
				}
			}
		}
		//更新上传图片的状态
		//activityFilesDao.updateByMapContext()
		return res;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityInfo> findIdAndCover(PaginatedFilter paginatedFilter) {
		//
		return this.dao.findIdAndCover(paginatedFilter) ;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Map<String,String>> findBAndFActivity(PaginatedFilter paginatedFilter) {
		//
		return this.dao.findBAndFActivity(paginatedFilter) ;
	}


	@Override
	public PaginatedList<Map> findByCompanyId(PaginatedFilter paginatedFilter) {
		return this.dao.findByCompanyId(paginatedFilter);
	}

	@Override
	public PaginatedList<Map> findBJoinActivity(PaginatedFilter paginatedFilter) {
		return this.dao.findBJoinActivity(paginatedFilter);
	}

	@Override
	public PaginatedList<Map> findFActivity(PaginatedFilter paginatedFilter) {
		return this.dao.findFActivity(paginatedFilter);
	}


	@Override
	public PaginatedList<Map> findByFCompanyId(PaginatedFilter paginatedFilter) {
		return this.dao.findByFCompanyId(paginatedFilter);
	}

	@Override
	public ActivityInfoDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public int deleteById(String id) {
		activityParamsDao.deleteByActivityId(id);
		return this.dao.deleteById(id);
	}
}