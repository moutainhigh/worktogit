package com.lwxf.industry4.webapp.domain.dao.dealer.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAddressDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;

import java.util.List;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerAddressDao")
public class DealerAddressDaoImpl extends BaseDaoImpl<DealerAddress, String> implements DealerAddressDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAddress> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DealerAddress> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<WxDealerAddressDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<WxDealerAddressDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public WxDealerAddressDto selectDtoById(String id) {
		String sqlId = this.getNamedSqlId("selectDtoById");
		return this.getSqlSession().selectOne(sqlId, id);
	}

    @Override
    public WxDealerAddressDto selectDefaultDto(String cid) {
        String sqlId = this.getNamedSqlId("selectDefaultDto");
        return this.getSqlSession().selectOne(sqlId, cid);
    }

    @Override
	public Integer countByCid(String cid) {
		String sqlId = this.getNamedSqlId("countByCid");
		return this.getSqlSession().selectOne(sqlId, cid);
	}

    @Override
    public Integer cancelCheckedByCid(String cid) {
	    String sqlId = this.getNamedSqlId("cancelCheckedByCid");
        return this.getSqlSession().selectOne(sqlId, cid);
    }

	@Override
	public List<DealerAddress> findListByCid(String companyId) {
		String sqlId = this.getNamedSqlId("findListByCid");
		return this.getSqlSession().selectList(sqlId, companyId);
	}


}