package com.lwxf.industry4.webapp.facade.admin.factory.contentmng;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentsFacade extends BaseFacade {

    RequestResult addContent(ContentsDto contentsDto);

    RequestResult findContents(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult uploadCover(MultipartFile multipartFile);

    MapContext uploadContentsImages(MultipartFile multipartFileList, String contentsId);

    RequestResult updateCover(MultipartFile multipartFile, String contentsId);

    RequestResult updateContent(MapContext mapContext);

    /*
        文章发布
     */
    RequestResult publishContent(String contentId);

    RequestResult deleteContent(String id);

    RequestResult findByContentId(String contentId);

}
