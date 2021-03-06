package com.nrd.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
