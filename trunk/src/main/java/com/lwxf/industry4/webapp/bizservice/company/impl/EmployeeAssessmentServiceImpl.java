package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeAssessmentDto;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeExperience;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeAssessmentDao;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeAssessmentService;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeAssessment;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-04-07 10:00:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("employeeAssessmentService")
public class EmployeeAssessmentServiceImpl extends BaseServiceImpl<EmployeeAssessment, String, EmployeeAssessmentDao> implements EmployeeAssessmentService {


	@Resource

	@Override	public void setDao( EmployeeAssessmentDao employeeAssessmentDao) {
		this.dao = employeeAssessmentDao;
	}

	@Resource(name = "uploadFilesDao")
	private UploadFilesDao uploadFilesDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeAssessment> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<EmployeeAssessmentDto> findListByEid(String eid) {
		List<EmployeeAssessmentDto> listByEid = this.dao.findListByEid(eid);
		for(EmployeeAssessmentDto employeeAssessmentDto:listByEid){
			employeeAssessmentDto.setUploadFilesList(this.uploadFilesDao.findByBelongId(employeeAssessmentDto.getId()));
		}
		return listByEid;
	}

}