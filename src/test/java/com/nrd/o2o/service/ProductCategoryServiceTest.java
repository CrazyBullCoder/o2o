package com.nrd.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nrd.o2o.BaseTest;
import com.nrd.o2o.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest {
	@Autowired
	private ProductCategoryService productCategoryService;

	@Test
	public void testQueryProductCategoryList() {
		List<ProductCategory> productCategoryList = productCategoryService.queryProductCategoryList(31L);
		System.out.println(productCategoryList);
	}
}
