package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeCertificateDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeCertificateDao;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeCertificateService;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeCertificate;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-04-04 15:32:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("employeeCertificateService")
public class EmployeeCertificateServiceImpl extends BaseServiceImpl<EmployeeCertificate, String, EmployeeCertificateDao> implements EmployeeCertificateService {


	@Resource

	@Override	public void setDao( EmployeeCertificateDao employeeCertificateDao) {
		this.dao = employeeCertificateDao;
	}
	@Resource(name = "uploadFilesDao")
	private UploadFilesDao uploadFilesDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeCertificate> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<EmployeeCertificateDto> findListByEid(String eid) {
		List<EmployeeCertificateDto> listByEid = this.dao.findListByEid(eid);
		for(EmployeeCertificateDto employeeCertificateDto: listByEid){
			employeeCertificateDto.setUploadFilesList(this.uploadFilesDao.findByBelongId(employeeCertificateDto.getId()));
		}
		return listByEid;
	}

}