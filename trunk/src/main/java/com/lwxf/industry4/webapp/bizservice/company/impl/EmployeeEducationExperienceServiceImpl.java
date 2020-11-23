package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeEducationExperienceDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeEducationExperienceDao;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeEducationExperienceService;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeEducationExperience;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-04-04 15:32:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("employeeEducationExperienceService")
public class EmployeeEducationExperienceServiceImpl extends BaseServiceImpl<EmployeeEducationExperience, String, EmployeeEducationExperienceDao> implements EmployeeEducationExperienceService {


	@Resource

	@Override	public void setDao( EmployeeEducationExperienceDao employeeEducationExperienceDao) {
		this.dao = employeeEducationExperienceDao;
	}

	@Resource(name = "uploadFilesDao")
	private UploadFilesDao uploadFilesDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeEducationExperience> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<EmployeeEducationExperienceDto> findListByEid(String eid) {
		List<EmployeeEducationExperienceDto> listByEid = this.dao.findListByEid(eid);
		for(EmployeeEducationExperienceDto employeeEducationExperienceDto:listByEid){
			employeeEducationExperienceDto.setUploadFilesList(this.uploadFilesDao.findByBelongId(employeeEducationExperienceDto.getId()));
		}
		return listByEid;
	}

}