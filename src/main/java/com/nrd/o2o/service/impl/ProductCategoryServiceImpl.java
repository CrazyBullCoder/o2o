package com.nrd.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrd.o2o.dao.ProductCategoryDao;
import com.nrd.o2o.dto.ProductCategoryExecution;
import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.enums.ProductCategoryStateEnum;
import com.nrd.o2o.exceptions.ProductCategoryOperationException;
import com.nrd.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> queryProductCategoryList(long shopId) {
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		return productCategoryList;
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if (productCategoryList == null) {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPETY_LIST);
		} else {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("店铺类别创建失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
			}
		}
	}

	@Override
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO 将此商品类别下的商品类别id置为空
		try {
			int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectNum <= 0) {
				throw new ProductCategoryOperationException("delect fail");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProductCategoryOperationException("deletePriductCategory error:" + e.getMessage());
		}
	}

}
