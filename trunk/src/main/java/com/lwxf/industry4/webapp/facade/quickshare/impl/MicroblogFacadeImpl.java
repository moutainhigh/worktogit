package com.lwxf.industry4.webapp.facade.quickshare.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogCommentService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogPraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.component.BaseFileUploadComponent;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.activity.SystemActivityEvent;
import com.lwxf.industry4.webapp.common.enums.quickshare.MicroblogStatus;
import com.lwxf.industry4.webapp.common.enums.quickshare.MicrologType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Microblog;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogComment;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogPraise;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.quickshare.MicroblogFacade;
import com.lwxf.industry4.webapp.facade.user.impl.UserFacadeImpl;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogCommentService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogPraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.quickshare.MicroblogStatus;
import com.lwxf.industry4.webapp.common.enums.quickshare.MicrologType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.quickshare.MicroblogFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：panchenxiao
 * @create：2018/7/3 19:36
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("microblogFacade")
public class MicroblogFacadeImpl extends BaseFacadeImpl implements MicroblogFacade {
	private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

	@Resource(name = "microblogService")
	private MicroblogService microblogService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource(name = "microblogPraiseService")
	private MicroblogPraiseService microblogPraiseService;
	@Resource(name = "microblogCommentService")
	private MicroblogCommentService microblogCommentService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource
	private BaseFileUploadComponent baseFileUploadComponent;


	/**
	 *  上传帖子并修改图片为正式图片
	 * @param microblog
	 * @return
	 */
	@Override
	@Transactional
	public RequestResult addMicroblogAndImage(Microblog microblog, String[] ids) {

		microblog.setType(MicrologType.COMMON.getValue());
		microblog.setStatus(MicroblogStatus.ENABLED.getValue().intValue());
		microblog.setCreated(DateUtil.getSystemDate());
		microblog.setCreator(WebUtils.getCurrUserId());
		this.microblogService.add(microblog);
		if(null != ids && ids.length != 0){
			String resourceId=microblog.getId();
			String belongId=microblog.getId();
			this.uploadFilesService.updateMicImageStatusAndresourceIdAndbelongId(ids,resourceId,belongId);
		}
		//返回当前帖子
		return ResultFactory.generateRequestResult(this.microblogService.findById(microblog.getId()));
	}


	/**
	 * 上传帖子图片的临时文件
	 * 作者：潘晨霄
	 * @param files
	 * @return
	 */
	@Override
	@Transactional
	public RequestResult uploadMicroblogImage(List<MultipartFile> files) {
		List<UploadFiles> list = new ArrayList<>();
		for (MultipartFile multipartFile : files){
			UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.TEMP,multipartFile,UploadResourceType.QUICKSHARE,"");
			//创建
			UploadFiles  uploadFiles = UploadFiles.create(null,null,uploadInfo,UploadResourceType.QUICKSHARE,UploadType.TEMP);
			//获取上传的ID并赋值
			uploadFiles.setCreator(WebUtils.getCurrUserId());
			//获取上传的时间并赋值
			uploadFiles.setCreated(DateUtil.getSystemDate());
			//获取相对路径
			uploadFiles.setPath(uploadInfo.getRelativePath());
			//TODO 增加公司id
			uploadFiles.setCompanyId(WebUtils.getCurrCompanyId());
			this.uploadFilesService.add(uploadFiles);
			list.add(this.uploadFilesService.findById(uploadFiles.getId()));
		}
		return ResultFactory.generateRequestResult(list);
	}

	/**
	 * 查询上传的贴子的时候图片是否为空（没用）
	 * @param ids
	 * @return
	 */
	@Override
	public RequestResult isNullByIds(String[] ids) {
		return ResultFactory.generateRequestResult(this.uploadFilesService.isNullByIds(ids));
	}

	/**
	 * 删除帖子的图片(无用)
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional
	public RequestResult deleteMicImage(String[] ids) {
		//删除快享图片
		this.uploadFilesService.deleteMicImage(ids);
		//返回剩余的图片
		String creator = WebUtils.getCurrUserId();
		return ResultFactory.generateRequestResult(this.uploadFilesService.selectByCreatorAndTempAndResourceType(creator));
	}

	/**
	 * 删除帖子
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public RequestResult deleteMicroblog(String id) {
		Microblog microblog = this.microblogService.findById(id);
		if(null == microblog){
			return ResultFactory.generateSuccessResult();
		}
		User user = WebUtils.getCurrUser();
		// 验证普通用户权限
		LwxfAssert.notNull(null,"该处还没实现");
		/*CompanyEmployee employee = AppBeanInjector.companyEmployeeService.selectByCompanyIdAndUserId(AppBeanInjector.configuration.getCompanyId(),user.getId());
		if(employee == null){
			if(!microblog.getCreator().equals(WebUtils.getCurrUserId())){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
			}
		}*/

		//埋xxxxxxxxxx_baisi
//		TSManualData tsManualData = WebUtils.getTSManualData();
//		tsManualData.setClazz(Microblog.class);
//		tsManualData.setParams(id);
//		tsManualData.setEvent(SystemActivityEvent.QUICKSHARE_DELETE.getValue());
//		tsManualData.put(WebConstant.KEY_ENTITY_ID,id);
		List<String> listMicImageId = this.uploadFilesService.selectMicImageIdByBlogId(id);
		List<UploadFiles> uploadFiles = this.uploadFilesService.findByBelongId(id);
		if(listMicImageId.size()!=0){
			//list集合转成数组
			String[] MicImageId = listMicImageId.toArray(new String[listMicImageId.size()]);
			//删除帖子图片 TODO：帖子的图片文件也要删除（磁盘上的文件）
			Map<String, UploadResourceType> map = new HashMap<>();
			for (UploadFiles files : uploadFiles) {
				map.put(files.getPath(), UploadResourceType.QUICKSHARE);
			}
			baseFileUploadComponent.deleteFiles(map, (long) uploadFiles.size());
			this.uploadFilesService.deleteMicImage(MicImageId);
		}
		//删除帖子点赞
		this.microblogPraiseService.deleteByBlogId(id);
		//删除帖子评论
		this.microblogCommentService.deleteMicroblogCommentByBlogId(id);
		//删除帖子
		this.microblogService.deleteById(id);
		return ResultFactory.generateSuccessResult(); // TODO：返回成功即可	√
	}





	@Override
	public RequestResult findById(String id) {
		Map<String, Object> result = new HashMap<>();
		result.put("microblogs", this.microblogService.findById(id));
		result.put("comments", this.microblogCommentService.selectMicroblogCommentByBlogId(id));
		result.put("files", this.uploadFilesService.findByBelongId(id));
		Set<String> blogs = new HashSet<>();
		blogs.add(id);
		List<MicroblogPraise> praises;
		if(blogs.size() == 0){
			praises = new ArrayList<>();
		}else {
			praises = this.microblogPraiseService.findByBlogIds(blogs);
		}
		result.put("praises", praises);
		result.put("userPraises", this.microblogPraiseService.findByMemberId(WebUtils.getCurrUserId()));
		return ResultFactory.generateRequestResult(result);
	}



	@Override
	@Transactional
	public RequestResult updateStatus(String id, MapContext mapContext) {
		Object status = mapContext.get(WebConstant.KEY_ENTITY_STATUS);
		// 非法数据验证
		if(null == status || !MicroblogStatus.contains(Byte.valueOf(status.toString()))){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ILLEGAL_DATA_00002, AppBeanInjector.i18nUtil.getMessage("SYS_ILLEGAL_DATA_00002"));
		}
		Microblog microblog = this.microblogService.findById(id);
		if(null == microblog){
			return ResultFactory.generateResNotFoundResult();
		}
		// 权限验证
		User currUser = WebUtils.getCurrUser();
		LwxfAssert.notNull(null,"该处还没实现");
		/*CompanyEmployee employee = AppBeanInjector.companyEmployeeService.selectByCompanyIdAndUserId(AppBeanInjector.configuration.getCompanyId(),currUser.getId());
		if(null == employee){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}*/
		mapContext.clear();
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,status);
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		this.microblogService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult() ;
	}
}
