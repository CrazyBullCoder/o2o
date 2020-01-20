package com.nrd.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrd.o2o.dao.ShopCategoryDao;
import com.nrd.o2o.entity.ShopCategory;
import com.nrd.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopcategoryDao;
	@Override
	public List<ShopCategory> queryShopCategory(ShopCategory shopCategoryCondition) {
		return shopcategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
