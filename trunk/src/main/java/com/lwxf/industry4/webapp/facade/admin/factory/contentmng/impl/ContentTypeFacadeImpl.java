package com.lwxf.industry4.webapp.facade.admin.factory.contentmng.impl;

import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsService;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsTypeService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.contentmng.ContentTypeFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.internet.ContentType;
import java.util.ArrayList;
import java.util.List;

@Component("fContentTypeFacade")
public class ContentTypeFacadeImpl extends BaseFacadeImpl implements ContentTypeFacade {

    @Resource(name = "contentsTypeService")
    private ContentsTypeService contentsTypeService;

    @Resource(name = "contentsService")
    private ContentsService contentsService;

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "添加内容",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.CONTENTS)
    public RequestResult addContentType(ContentsType contentType) {
        contentType.setBranchId(WebUtils.getCurrBranchId());
        //判断code是否重复
        ContentsType content=contentsTypeService.findContentsListByCode(contentType.getCode());
        if(content!=null){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_EXECUTE_FAIL_00001,"分类编码Code不能重复");
        }
        String res = this.contentsTypeService.addContentType(contentType);
        if(res!=null && res.equals("error")){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_EXECUTE_FAIL_00001,"数据保存失败");
        }else{
            return ResultFactory.generateRequestResult(res);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findContentTypes() {
        return ResultFactory.generateRequestResult(this.contentsTypeService.findContentsTypeListByBranchId(WebUtils.getCurrBranchId()));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "修改内容",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.CONTENTS)
    public RequestResult updateContentType(MapContext mapContext) {
        return  ResultFactory.generateRequestResult(this.contentsTypeService.updateByMapContext(mapContext));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "删除内容",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.CONTENTS)
    public RequestResult deleteContentType(String id) {
//        List<ContentsDto> list = this.contentsService.(id);
//        if(list!=null && list.size()>0){
//            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_CONTENTS_TYPE_IN_USE_ERROR_10085,"类型已被使用，无法删除");
//        }
        this.contentsTypeService.deleteById(id);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findByContentId(String contentId) {
        return  ResultFactory.generateRequestResult(this.contentsTypeService.findById(contentId));
    }

}
