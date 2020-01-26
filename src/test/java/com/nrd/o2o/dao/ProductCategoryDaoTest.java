package com.nrd.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nrd.o2o.BaseTest;
import com.nrd.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testDeleteProductCategory() {
		int effectNum = productCategoryDao.deleteProductCategory(1, 31);
		assertEquals(1, effectNum);
	}
	@Test
	public void testQueryProductCategoryList() {
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(1L);
		System.out.println(productCategoryList);
	}
	@Test
	public void testBatchInsertProductCategory() {
		List<ProductCategory> productCategoryList = new ArrayList<>();
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("test1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(31L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("test2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(31L);
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
}
