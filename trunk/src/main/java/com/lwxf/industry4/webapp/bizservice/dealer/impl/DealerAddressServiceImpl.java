package com.lwxf.industry4.webapp.bizservice.dealer.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAddressService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAddressDao;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：经销商收货地址管理
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerAddressService")
public class DealerAddressServiceImpl extends BaseServiceImpl<DealerAddress, String, DealerAddressDao> implements DealerAddressService {

	@Resource
	@Override
	public void setDao( DealerAddressDao dealerAddressDao) {
		this.dao = dealerAddressDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAddress> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	/**
	 * 查询经销商的所有收货地址分页数据
	 * @param paginatedFilter
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<WxDealerAddressDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter);
	}

    /**
     * 查询指定的收货地址
     * @param id 地址id
     * @return
     */
	@Override
	public WxDealerAddressDto findDtoById(String id) {
		return this.dao.selectDtoById(id);
	}

    @Override
    public WxDealerAddressDto findDefaultDto(String cid) {
        return this.dao.selectDefaultDto(cid);
    }

    /**
	 * 统计指定经销商的收货地址总数
	 * @param cid
	 * @return
	 */
	@Override
	public Integer countByCid(String cid) {
		return this.dao.countByCid(cid);
	}

	/**
	 * 取消指定经销商的默认收货地址设置
	 * @param cid
	 * @return
	 */
	@Override
	public Integer cancelCheckedByCid(String cid) {
		return this.dao.cancelCheckedByCid(cid);
	}

	@Override
	public List<DealerAddress> findListByCid(String companyId) {
		return this.dao.findListByCid(companyId);
	}

}