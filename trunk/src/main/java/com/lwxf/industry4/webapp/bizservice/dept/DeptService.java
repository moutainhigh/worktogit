package com.lwxf.industry4.webapp.bizservice.dept;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-08 15:14:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DeptService extends BaseService <Dept, String> {
	PaginatedList<Dept> selectByFilter(PaginatedFilter paginatedFilter);

	Dept findDeptByNameAndParentId(String name,String parentId,String branchId);

	Dept findDeptByKey(String key,String branchId);

	List<Dept> selectDeptByCompanyIdAndParentId(String companyId, String parentId);


	Dept selectByEmpId(String empId);

	Dept findOneByKeyOrNameAndCompanyId(MapContext mapContext);

	List<Dept> findListByCompanyId(String companyId);

	List<Dept> findAll(String companyId);

	EmployeeDeptDto findOneByUserId(String id);

	List<Dept> findListByParentId(String id);

	List<Dept> findListByCompanyIdAndParent(String companyId);

	List<Dept> findListByUserId(String currUserId);

	List<Dept> findAllDeptByBranchId(String branchId);
}