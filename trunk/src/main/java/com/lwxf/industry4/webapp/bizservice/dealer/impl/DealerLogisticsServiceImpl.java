package com.lwxf.industry4.webapp.bizservice.dealer.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerLogisticsService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerLogisticsDao;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：经销商物流公司管理
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerLogisticsService")
public class DealerLogisticsServiceImpl extends BaseServiceImpl<DealerLogistics, String, DealerLogisticsDao> implements DealerLogisticsService {

	@Resource
	@Override
    public void setDao( DealerLogisticsDao dealerLogisticsDao) {
		this.dao = dealerLogisticsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerLogistics> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	/**
	 * 分页查询经销商物流信息列表
	 * @param paginatedFilter
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<WxDealerLogisticsDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter);
	}

	/**
	 * 根据id查询物流公司信息
	 * @param id 经销商物流表id
	 * @return
	 */
	@Override
	public WxDealerLogisticsDto findDtoById(String id) {
		return this.dao.selectDtoById(id);
	}

    /**
     * 查询指定经销商的默认物流公司信息
     * @param cid
     * @return
     */
	@Override
	public WxDealerLogisticsDto findDefaultDto(String cid) {
		return this.dao.selectDefaultDto(cid);
	}

	@Override
	public DealerLogistics findByCidAndLid(String cid, String logisticsId) {
		MapContext mapContext = MapContext.newOne();
		mapContext.put("companyId", cid);
		mapContext.put("logisticsCompanyId", logisticsId);
		return this.dao.selectByMap(mapContext);
	}

	/**
     * 统计指定经销商物流公司总数量
     * @param cid
     * @return
     */
	@Override
	public Integer countByCid(String cid) {
		return this.dao.countByCid(cid);
	}

    /**
     * 取消默认的物流公司设置
     * @param cid
     * @return
     */
	@Override
	public Integer cancelCheckedByCid(String cid) {
		return this.dao.cancelCheckedByCid(cid);
	}

	@Override
	public List<WxDealerLogisticsDto> findByCid(String cid) {
		return this.dao.findByCid(cid);
	}

	@Override
	public Integer deleteByCid(String cid) {
		return  this.dao.deleteByCid(cid);
	}

}