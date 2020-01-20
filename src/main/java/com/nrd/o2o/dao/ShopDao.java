package com.nrd.o2o.dao;

import com.nrd.o2o.entity.Shop;
public interface ShopDao {
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
