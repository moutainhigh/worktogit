package com.lwxf.industry4.webapp.bizservice.company.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.CompanyCertificatesService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyCertificatesDao;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyCertificatesDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyCertificates;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-08-20 10:15:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("companyCertificatesService")
public class CompanyCertificatesServiceImpl extends BaseServiceImpl<CompanyCertificates, String, CompanyCertificatesDao> implements CompanyCertificatesService {

	@Resource(name = "uploadFilesDao")
    private UploadFilesDao uploadFilesDao;
	@Resource

	@Override	public void setDao( CompanyCertificatesDao companyCertificatesDao) {
		this.dao = companyCertificatesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyCertificates> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<CompanyCertificatesDto> findByCid(String cid) {
		List<CompanyCertificatesDto> certificatesDtos=this.dao.findByCid(cid);
		if(certificatesDtos!=null&&certificatesDtos.size()>0){
			for(CompanyCertificatesDto certificatesDto:certificatesDtos) {
				List<UploadFiles> byResourceId = this.uploadFilesDao.findByResourceId(certificatesDto.getId());
				certificatesDto.setFilesList(byResourceId);
			}
		}
		return certificatesDtos;
	}

}