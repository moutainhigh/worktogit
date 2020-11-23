package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeExperienceDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeExperienceDao;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeExperienceService;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeExperience;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-04-04 15:32:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("employeeExperienceService")
public class EmployeeExperienceServiceImpl extends BaseServiceImpl<EmployeeExperience, String, EmployeeExperienceDao> implements EmployeeExperienceService {


	@Resource

	@Override	public void setDao( EmployeeExperienceDao employeeExperienceDao) {
		this.dao = employeeExperienceDao;
	}

	@Resource(name = "uploadFilesDao")
	private UploadFilesDao uploadFilesDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeExperience> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<EmployeeExperienceDto> findListByEid(String eid) {
		List<EmployeeExperienceDto> listByEid = this.dao.findListByEid(eid);
		for(EmployeeExperienceDto employeeExperienceDto:listByEid){
			employeeExperienceDto.setUploadFilesList(this.uploadFilesDao.findByBelongId(employeeExperienceDto.getId()));
		}
		return listByEid;
	}

}