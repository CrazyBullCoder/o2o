package com.nrd.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nrd.o2o.entity.Shop;
public interface ShopDao {
	/**
	 * 
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * 通过shop id查询店铺
	 */
	Shop queryByShopId(long shopId);
	/**
	 * 新增shop
	 * @author admin
	 *
	 */
	int insertShop(Shop shop);
	
	int updateShop(Shop shop);
}
