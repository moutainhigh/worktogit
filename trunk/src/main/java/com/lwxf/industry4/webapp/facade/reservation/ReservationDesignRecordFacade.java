package com.lwxf.industry4.webapp.facade.reservation;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/9/28 19:27
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ReservationDesignRecordFacade extends BaseFacade {

    RequestResult addResDesRec(ReservationDesignRecord reservationDesignRecord);

    RequestResult addResDesFiles(List<MultipartFile> files,String designRecordId,String reservationId);

    RequestResult updateResFiles( MultipartFile multipartFile, String resDesRecId, String resDesFileId,String name);

    RequestResult deleteResDesRec(String ResDesRecId,String reservationId);

    RequestResult selectResDesRecAndFiles(String reservationId);

    RequestResult deleteResFiles(String resDesFileId,String uploadFileId);

    RequestResult updateResDesRec( String reservationId, String resDesRecId, MapContext params);

    RequestResult findDesignInfoById(String reservationId,String designId);
}
