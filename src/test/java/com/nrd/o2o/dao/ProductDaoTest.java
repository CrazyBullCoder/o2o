package com.nrd.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nrd.o2o.BaseTest;
import com.nrd.o2o.entity.Product;
import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.entity.Shop;
import com.nrd.o2o.enums.EnableStatusEnum;

public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;
	@Test
	public void testInsertProduct() {
		Shop shop1 = new Shop();
		shop1.setShopId(31L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(21L);
		
		Product product1 = new Product();
		product1.setProductName("test1");
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		product1.setPriority(60);
		product1.setEnableStatus(1);
		Product product2 = new Product();
		product2.setShop(shop1);
		product2.setProductCategory(pc1);
		product2.setProductName("test2");
		Product product3 = new Product();
		product3.setProductName("test3");
		product3.setShop(shop1);
		product3.setProductCategory(pc1);
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
	}
}
