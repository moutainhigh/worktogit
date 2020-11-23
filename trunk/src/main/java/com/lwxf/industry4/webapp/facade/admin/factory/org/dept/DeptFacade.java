package com.lwxf.industry4.webapp.facade.admin.factory.org.dept;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeCertificateDto;
import com.lwxf.industry4.webapp.domain.dto.dept.UpdateUserRoleDeptDto;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeAssessment;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeEducationExperience;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeExperience;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/11/30/030 14:22
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DeptFacade extends BaseFacade {
	RequestResult findAllDeptList();
	RequestResult addDept(Dept department);


	RequestResult updateDept(MapContext mapContext, String id);

	RequestResult deleteById(String id);

	RequestResult findMemberList(Integer pageNum,Integer pageSize,MapContext mapContext,String deptId);

	RequestResult updateMemberInfo(String eid, UpdateUserRoleDeptDto updateDto);

	RequestResult findMemberInfo(String eid);

	RequestResult updateEmployeeInfo(String eid, MapContext mapContext);

	RequestResult addEmployeeExperience(String eid, EmployeeExperience employeeExperience);

	RequestResult updataEmployeeExperience(String eid, String id, MapContext mapContext);

	RequestResult deleteEmployeeExperience(String eid, String id);

	RequestResult addEmployeeAssessment(String eid, EmployeeAssessment employeeAssessment);

	RequestResult updataEmployeeAssessment(String eid, String id, MapContext mapContext);

	RequestResult deleteEmployeeAssessment(String eid, String id);

	RequestResult addEmployeeCertificate(String eid, EmployeeCertificateDto employeeCertificate);

	RequestResult updataEmployeeCertificate(String eid, String id, MapContext mapContext);

	RequestResult deleteEmployeeCertificate(String eid, String id);

	RequestResult addEmployeeEducationExperience(String eid, EmployeeEducationExperience employeeEducationExperience);

	RequestResult updataEmployeeEducationExperience(String eid, String id, MapContext mapContext);

	RequestResult deleteEmployeeEducationExperience(String eid, String id);

	RequestResult uploadEmployeeFiles(String eid, String resId,List<MultipartFile> multipartFiles);

	RequestResult deleteUploadFiles(String eid,String fileId);

	RequestResult findDeptAndMemberList(String currBranchId);
}
