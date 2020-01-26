package com.nrd.o2o.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nrd.o2o.dto.ProductCategoryExecution;
import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	List<ProductCategory> queryProductCategoryList(long shopId);

	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
