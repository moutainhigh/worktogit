package com.lwxf.industry4.webapp.domain.dao.org.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dept.DeptDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.org.DeptDao;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-08 15:14:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept, String> implements DeptDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Dept> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Dept> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Dept findDeptByNameAndParentId(String name,String parentId,String branchId) {
		String sql = this.getNamedSqlId("findDeptByNameAndParentId");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		mapContext.put(WebConstant.KEY_ENTITY_PARENTID,parentId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public Dept findDeptByKey(String key,String branchId) {
		String sql = this.getNamedSqlId("findDeptByKey");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		mapContext.put(WebConstant.KEY_WxPay_KEY,key);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public List<Dept> selectDeptByCompanyIdAndParentId(String companyId, String parentId) {
		String sql = this.getNamedSqlId("selectDeptByCompanyIdAndParentId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
		mapContext.put(WebConstant.KEY_ENTITY_PARENTID,parentId);
		return this.getSqlSession().selectList(sql,mapContext);
	}


	@Override
	public Dept selectByEmpId(String empId) {
		String sqlId = this.getNamedSqlId("selectByEmpId");
		return this.getSqlSession().selectOne(sqlId,empId);
	}


	@Override
	public Dept findOneByKeyOrNameAndCompanyId(MapContext mapContext) {
		String sql = this.getNamedSqlId("findOneByKeyOrNameAndCompanyId");
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public List<Dept> findListByCompanyId(String companyId) {
		String sql = this.getNamedSqlId("findListByCompanyId");
		return this.getSqlSession().selectList(sql,companyId);
	}
	@Override
	public List<Dept> findAll(String companyId) {
		String sql = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sql,companyId);
	}

	@Override
	public EmployeeDeptDto findOneByUserId(String id) {
		String sql = this.getNamedSqlId("findOneByUserId");
		return this.getSqlSession().selectOne(sql,id);
	}

	@Override
	public List<Dept> findListByParentId(String id) {
		String sql = this.getNamedSqlId("findListByParentId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public List<Dept> findListByEmployeeId(String id) {
		String sql = this.getNamedSqlId("findListByEmployeeId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public List<Dept> findListByCompanyIdAndParent(String companyId) {
		String sql = this.getNamedSqlId("findListByCompanyIdAndParent");
		return this.getSqlSession().selectList(sql,companyId);
	}

	@Override
	public List<Dept> findListByUserId(String currUserId) {
		String sqlId = this.getNamedSqlId("findListByUserId");
		return this.getSqlSession().selectList(sqlId,currUserId);
	}
	@Override
	public List<Dept> findAllDeptByBranchId(String branchId) {
		String sqlId = this.getNamedSqlId("findAllDeptByBranchId");
		return this.getSqlSession().selectList(sqlId,branchId);
	}

}