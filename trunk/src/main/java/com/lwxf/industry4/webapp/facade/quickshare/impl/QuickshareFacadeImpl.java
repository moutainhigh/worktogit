package com.lwxf.industry4.webapp.facade.quickshare.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuickshareCommentService;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuicksharePraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuickshareService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.component.BaseFileUploadComponent;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.quickshare.MicroblogStatus;
import com.lwxf.industry4.webapp.common.enums.quickshare.MicrologType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuickshareCommentDto;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuickshareDto;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuicksharePraiseDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.quickshare.*;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.quickshare.QuickshareFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/2 0002 14:21
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("quickshareFacade")
public class QuickshareFacadeImpl extends BaseFacadeImpl implements QuickshareFacade {
	@Resource(name="quickshareService")
	private QuickshareService quickshareService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="quickshareCommentService")
	private QuickshareCommentService quickshareCommentService;
	@Resource(name="quicksharePraiseService")
	private QuicksharePraiseService quicksharePraiseService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource
	private BaseFileUploadComponent baseFileUploadComponent;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	/**
	 * 快享列表
	 * @param pageNum
	 * @param pageSize
	 * @param mapContext
	 * @param request
	 * @return
	 */
	@Override
	public RequestResult findQuickshareList(Integer pageNum, Integer pageSize, MapContext mapContext, HttpServletRequest request) {
		//分頁查詢
		String uid=request.getHeader("X-UID");
		PaginatedFilter filter = PaginatedFilter.newOne();
		String status=((Integer)MicroblogStatus.ENABLED.getValue().intValue()).toString();
		mapContext.put("status",status);
		String type=((Integer)MicrologType.COMMON.getValue()).toString();
		mapContext.put("type",type);
		filter.setFilters(mapContext);
		Pagination pagination = Pagination.newOne();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		filter.setPagination(pagination);
//		Map dataSort = new HashMap();
//		List sorts = new ArrayList();
//		dataSort.put("created","desc");
//		sorts.add(dataSort);
//		filter.setSorts(sorts);
		PaginatedList<Quickshare> list = this.quickshareService.selectByFilter(filter);
		List<String> quickshareIds = new ArrayList<>();
		for(Quickshare quickshare : list.getRows()){
			quickshareIds.add(quickshare.getId());
		}
		// 返回
		List<QuickshareDto> quickshareDtos=new ArrayList<>();
		if(quickshareIds.size() != 0){
			for(String quickshareId:quickshareIds) {
				QuickshareDto quickshareDto=new QuickshareDto();
				Quickshare quickshare=this.quickshareService.findById(quickshareId);
				User user=this.userService.findByUserId(quickshare.getCreator());
				String name=user.getName();
				String avatar=user.getAvatar();
				List<QuickshareCommentDto> quickshareComments=this.quickshareCommentService.findQuickshareCommentListByqsId(quickshareId);
				List<QuicksharePraiseDto> quicksharePraises = this.quicksharePraiseService.selectByQuickshareId(quickshareId);
				List<UploadFiles> files = this.uploadFilesService.findByBelongId(quickshareId);
				quickshareDto.setCreatorName(name);
				quickshareDto.setCreatorAvatar(avatar);
				quickshareDto.setComments(quickshareComments);
				quickshareDto.setPraises(quicksharePraises);
				quickshareDto.setFiles(files);
				quickshareDto.setCommentSize(quickshareComments.size());
				quickshareDto.setPraiseSize(quicksharePraises.size());
				quickshareDto.setId(quickshareId);
				quickshareDto.setCreator(quickshare.getCreator());
				quickshareDto.setContent(quickshare.getContent());
				quickshareDto.setType(quickshare.getType());
				quickshareDto.setStatus(quickshare.getStatus());
				quickshareDto.setCreated(quickshare.getCreated());
				//判断是否点赞
				String memberId=uid;
				QuicksharePraise quicksharePraise=this.quicksharePraiseService.selectByMemberIdAndQuickshareId(memberId,quickshareId);
				if(quicksharePraise==null){
					quickshareDto.setPraise(false);
				}else {
					quickshareDto.setPraise(true);
				}
				quickshareDtos.add(quickshareDto);
			}
		}
		return ResultFactory.generateRequestResult(quickshareDtos);
	}


	//上传图片文件
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadQuickshareImage(List<MultipartFile> files,HttpServletRequest request,String quickshareId) {
		String uid=request.getHeader("X-UID");
		List<Map<String,Object>> list=new ArrayList<>();
		UploadFiles  uploadFiles =new UploadFiles();
		UploadInfo uploadInfo ;
		for (MultipartFile multipartFile : files){
			uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL,multipartFile, UploadResourceType.QUICKSHARE,"");
			//创建
			uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
			uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
			uploadFiles.setName(uploadInfo.getFileName());
			uploadFiles.setResourceType(UploadResourceType.QUICKSHARE.getType());
			uploadFiles.setStatus(UploadType.FORMAL.getValue());
			uploadFiles.setBelongId(quickshareId);
			uploadFiles.setResourceId(quickshareId);
			uploadFiles.setCreator(uid);
			uploadFiles.setCreated(DateUtil.getSystemDate());
			uploadFiles.setPath(uploadInfo.getRelativePath());
			uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
			String cid=request.getHeader("X-CID");
			if(cid!=null&&!cid.equals("")) {
				uploadFiles.setCompanyId(cid);
			}
			this.uploadFilesService.add(uploadFiles);
			MapContext mapContext=MapContext.newOne();
			String imagePath=WebUtils.getDomainUrl() + uploadInfo.getRelativePath();
			mapContext.put("imagePath", imagePath);
			list.add(mapContext);
		}
		return ResultFactory.generateRequestResult(list);
	}

	/**
	 * 添加帖子
	 * @param quickshare
	 * @param userId
	 * @param
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addQuickshareAndImage(Quickshare quickshare,String userId) {
		quickshare.setType(MicrologType.COMMON.getValue());
		quickshare.setStatus(MicroblogStatus.ENABLED.getValue().intValue());
		quickshare.setCreated(DateUtil.getSystemDate());
		quickshare.setCreator(userId);
		this.quickshareService.add(quickshare);
		//返回当前帖子
		MapContext mapContext=MapContext.newOne();
		mapContext.put("quickshare",quickshare);
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteQuickshare(String quickshareId, HttpServletRequest request) {
		String uid=request.getHeader("X-UID");
		Quickshare quickshare=this.quickshareService.findById(quickshareId);
		if(quickshare==null){
			return  ResultFactory.generateResNotFoundResult();
		}
		String creator=quickshare.getCreator();
		if(!creator.equals(uid)){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}
		//删除图片
		List<String> listMicImageId = this.uploadFilesService.selectMicImageIdByBlogId(quickshareId);
		List<UploadFiles> uploadFiles = this.uploadFilesService.findByBelongId(quickshareId);
		if(listMicImageId.size()!=0){
			String[] QuickshareId = listMicImageId.toArray(new String[listMicImageId.size()]);
			//删除帖子图片
			Map<String, UploadResourceType> map = new HashMap<>();
			for (UploadFiles files : uploadFiles) {
				map.put(files.getPath(), UploadResourceType.QUICKSHARE);
			}
			baseFileUploadComponent.deleteFiles(map, (long) uploadFiles.size());
			this.uploadFilesService.deleteMicImage(QuickshareId);
		}
		//删除帖子点赞
		this.quicksharePraiseService.deleteQuiPraByQuiId(quickshareId);
		//删除帖子评论
		this.quickshareCommentService.deleteByqsId(quickshareId);
		//删除帖子
		this.quickshareService.deleteById(quickshareId);
		return ResultFactory.generateSuccessResult();
	}

}
