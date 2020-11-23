package com.lwxf.industry4.webapp.facade.reservation.impl;

import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignFileService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignRecordService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationService;
import com.lwxf.industry4.webapp.common.component.BaseFileUploadComponent;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.reservation.ReservationDesDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignFile;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.reservation.ReservationDesignRecordFacade;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignFileService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignRecordService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationService;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.reservation.ReservationDesignRecordFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/9/28 19:27
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("reservationDesignRecordFacade")
public class ReservationDesignRecordFacadeImpl extends BaseFacadeImpl implements ReservationDesignRecordFacade {
    @Resource(name = "reservationDesignRecordService")
    private ReservationDesignRecordService reservationDesignRecordService;
    @Resource(name = "uploadFilesService")
    private UploadFilesService uploadFilesService;
    @Resource(name = "reservationDesignFileService")
    private ReservationDesignFileService reservationDesignFileService;
    @Resource(name = "baseFileUploadComponent")
    private BaseFileUploadComponent baseFileUploadComponent;
    @Resource(name = "reservationService")
    private ReservationService reservationService;

    /**
     * 添加预约单设计记录
     * @param reservationDesignRecord
     * @return
     */
    @Override
    @Transactional
    public RequestResult addResDesRec(ReservationDesignRecord reservationDesignRecord) {
        reservationDesignRecord.setCreator(WebUtils.getCurrUserId());
        reservationDesignRecord.setCreated(DateUtil.getSystemDate());
        reservationDesignRecord.setConfirmed(false);
        reservationDesignRecord.setPublished(false);
        this.reservationDesignRecordService.add(reservationDesignRecord);
        MapContext mapContext = MapContext.newOne();
        mapContext.put(WebConstant.KEY_ENTITY_STATUS,4);
        mapContext.put(WebConstant.KEY_ENTITY_ID,reservationDesignRecord.getReservationId());
        this.reservationService.updateByMapContext(mapContext);

        return ResultFactory.generateRequestResult(reservationDesignRecord);
    }


    /**
     * 添加预约设计记录的图片
     * @param designRecordId
     * @param files
     * @return
     */
    @Override
    @Transactional
    public RequestResult addResDesFiles(List<MultipartFile> files, String designRecordId,String reservationId) {
        ReservationDesignRecord reservationDesignRecord = this.reservationDesignRecordService.findById(designRecordId);
        if (reservationDesignRecord == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
        }
        List<ReservationDesignFile> list = new ArrayList<>();
        for (MultipartFile multipartFile : files) {

            UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.RESERVATIONDESIGNRECORD,designRecordId);

            //赋值给uploadinfo,数据进行处理
               UploadFiles uploadFiles = UploadFiles.create(reservationId, designRecordId, uploadInfo, UploadResourceType.RESERVATIONDESIGNRECORD, UploadType.FORMAL);
                uploadFiles.setCompanyId(WebUtils.getCurrCompanyId());//TODO 需要公司的id
               this.uploadFilesService.add(uploadFiles);
                ReservationDesignFile rdfiles = new ReservationDesignFile();
                rdfiles.setDesignRecordId(designRecordId);
                rdfiles.setMime(uploadFiles.getMime());
                rdfiles.setName(uploadFiles.getName());
                rdfiles.setPath(uploadFiles.getPath());
                rdfiles.setUploadFilesId(uploadFiles.getId());
                this.reservationDesignFileService.add(rdfiles);
                list.add(rdfiles);
            }

        return ResultFactory.generateRequestResult(list);

    }

    /**
     *  (无用)
     * @param multipartFile
     * @param resDesRecId  设计表id
     * @param resDesFileId 设计图片id
     * @return
     */
    @Override
    @Transactional
    public RequestResult updateResFiles(MultipartFile multipartFile, String resDesRecId, String resDesFileId,String name) {
        ReservationDesignRecord reservationDesignRecord = this.reservationDesignRecordService.findById(resDesRecId);
        if (reservationDesignRecord == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
        }
        //根据名字查询出图片的信息
        UploadFiles uploadFiles = this.uploadFilesService.selectByName(name);


        if (null!=uploadFiles){
            //删除本地文件
            AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadFiles.getPath()));
            //上传新的图片到本地
            UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.RESERVATIONDESIGNRECORD, resDesRecId);
            MapContext update = MapContext.newOne();
            update.put(WebConstant.KEY_ENTITY_ID, uploadFiles.getId());
            update.put("path", uploadInfo.getRelativePath());
            update.put("name", uploadInfo.getFileName());
            update.put("originalMime", uploadInfo.getFileMimeType().getOriginalType());
            update.put("mime", uploadInfo.getFileMimeType().getRealType());
            update.put("creator", WebUtils.getCurrUserId());
            update.put("created", DateUtil.getSystemDate());
            //修改数据库图片
            this.uploadFilesService.updateByMapContext(update);
            //修改设计图表中的图片属性
            MapContext param = MapContext.newOne();
            param.put(WebConstant.KEY_ENTITY_ID,resDesFileId);
            param.put("name", uploadInfo.getFileName());
            param.put("mime", uploadInfo.getFileMimeType().getRealType());
            param.put("path", uploadInfo.getRelativePath());
            this.reservationDesignFileService.updateByMapContext(param);

        }else {
            return ResultFactory.generateResNotFoundResult();
        }

        return ResultFactory.generateRequestResult(this.reservationDesignFileService.findById(resDesFileId));
    }

    @Override
    @Transactional
    public RequestResult deleteResFiles(String resDesFileId, String uploadFileId) {
        UploadFiles uploadFiles = this.uploadFilesService.findById(uploadFileId);
        this.reservationDesignFileService.deleteById(resDesFileId);
        this.uploadFilesService.deleteById(uploadFileId);
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadFiles.getPath()));
        return ResultFactory.generateSuccessResult();
    }

    /**
     * 删除设计图
     * @param ResDesRecId
     * @return
     */
    @Override
    @Transactional
    public RequestResult deleteResDesRec(String ResDesRecId,String reservationId) {
        List<String> listResImageIds = this.uploadFilesService.selectMicImageIdByBlogId(ResDesRecId);
        List<UploadFiles> uploadFiles = this.uploadFilesService.findListByBelongIdAndResourceIdAndResourceType(ResDesRecId,reservationId,UploadResourceType.RESERVATIONDESIGNRECORD.getType());
        if(listResImageIds.size()!=0){
            //list集合转成数组
            String[] ResImageIds = listResImageIds.toArray(new String[listResImageIds.size()]);
            //删除帖子图片 TODO：帖子的图片文件也要删除（磁盘上的文件）
            Map<String, UploadResourceType> map = new HashMap<>();
            for (UploadFiles files : uploadFiles) {
                map.put(files.getPath(), UploadResourceType.RESERVATIONDESIGNRECORD);
            }
            this.reservationDesignFileService.deleteByDesRecordId(ResDesRecId);
            this.uploadFilesService.deleteMicImage(ResImageIds);
            this.baseFileUploadComponent.deleteFiles(map, (long) uploadFiles.size());
        }

        this.reservationDesignRecordService.deleteById(ResDesRecId);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult selectResDesRecAndFiles( String reservationId) {
        List<ReservationDesDto> reservationDesDtoList = new ArrayList<>();
        List<ReservationDesignRecord> reservationDesignRecordsList = this.reservationDesignRecordService.selectByReservationId(reservationId);
        if (null!=reservationDesignRecordsList&&reservationDesignRecordsList.size()>0){
            for (ReservationDesignRecord resDesRec :reservationDesignRecordsList){
                ReservationDesDto reservationDesDto = new ReservationDesDto();
                String resDesRecId = resDesRec.getId();
                List<ReservationDesignFile> resDesFileList = this.reservationDesignFileService.selectByResDesRecId(resDesRecId);
                reservationDesDto.setReservationDesignFileList(resDesFileList);
                reservationDesDto.setId(resDesRec.getId());
                reservationDesDto.setName(resDesRec.getName());
                reservationDesDto.setCreator(resDesRec.getCreator());
                reservationDesDto.setCreated(resDesRec.getCreated());
                reservationDesDto.setConfirmed(resDesRec.getConfirmed());//提交
                reservationDesDto.setPublished(resDesRec.getPublished());//发布
                reservationDesDto.setReservationId(resDesRec.getReservationId());
                reservationDesDto.setContent(resDesRec.getContent());

                reservationDesDtoList.add(reservationDesDto);
            }
        }
        return ResultFactory.generateRequestResult(reservationDesDtoList);
    }

    @Override
    @Transactional
    public RequestResult updateResDesRec(String reservationId, String resDesRecId, MapContext params) {
        Reservation reservation = this.reservationService.findById(reservationId);
        ReservationDesignRecord designRecord = this.reservationDesignRecordService.findById(resDesRecId);
        if(null==reservation){//判断预约单是否存在
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
        }
        if(null==designRecord){//判断预约设计记录是否存在
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
        }
        params.put(WebConstant.KEY_ENTITY_ID,resDesRecId);
        int i = this.reservationDesignRecordService.updateByMapContext(params);
        MapContext mapContext = MapContext.newOne();
        mapContext.put(WebConstant.KEY_ENTITY_STATUS,4);
        mapContext.put(WebConstant.KEY_ENTITY_ID,reservationId);
        this.reservationService.updateByMapContext(mapContext);

            return ResultFactory.generateSuccessResult();


    }

    @Override
    public RequestResult findDesignInfoById(String reservationId, String designId) {
        ReservationDesDto designRecord = (ReservationDesDto) this.reservationDesignRecordService.findById(designId);
        designRecord.setFileList(this.reservationDesignRecordService.selectPathByResDesId(designId));
        if(designRecord==null||!designRecord.getReservationId().equals(reservationId)){
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateRequestResult(designRecord);
    }
}

