package com.lwxf.industry4.webapp.bizservice.contentmng.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsTypeDao;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsTypeService;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("contentsTypeService")
public class ContentsTypeServiceImpl extends BaseServiceImpl<ContentsType, String, ContentsTypeDao> implements ContentsTypeService {

	@Resource
	@Override	public void setDao( ContentsTypeDao contentsTypeDao) {
		this.dao = contentsTypeDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsType> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ContentsType> findContentsTypeListByBranchId(String branchId) {
		return this.dao.findContentsTypeListByBranchId(branchId);
	}

	@Override
	public ContentsType findContentsListByCode(String code) {
		return this.dao.findContentsListByCode(code);
	}

	public String addContentType(ContentsType contentType) {
		String id = UniqueKeyUtil.getStringId();
		contentType.setId(id);
		int i  = this.dao.insert(contentType);
		if(i>0){
			return id;
		}
		return "error";
	}

}