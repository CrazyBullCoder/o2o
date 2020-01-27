package com.nrd.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.nrd.o2o.BaseTest;
import com.nrd.o2o.dto.ImageHolder;
import com.nrd.o2o.dto.ProductExecution;
import com.nrd.o2o.entity.Product;
import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.entity.Shop;
import com.nrd.o2o.enums.EnableStatusEnum;
import com.nrd.o2o.util.ImageUtil;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	@Test
	public void testAddProduct() throws FileNotFoundException {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(31L);
		product.setShop(shop);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(21L);
		product.setProductCategory(productCategory);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1描述");
		product.setPriority(11);
		product.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
		product.setLastEditTime(new Date());
		product.setCreateTime(new Date());
		File thumbnailFile = new File("E:\\Pictures\\1.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		File procuctImg1 = new File("E:\\Pictures\\1.jpg");
		InputStream is1 = new FileInputStream(procuctImg1);
		File procuctImg2 = new File("E:\\Pictures\\2.jpg");
		InputStream is2 = new FileInputStream(procuctImg2);
		List<ImageHolder> productImgList = new ArrayList();
		productImgList.add(new ImageHolder(procuctImg1.getName(), is1));
		productImgList.add(new ImageHolder(procuctImg2.getName(), is2));
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(1, pe.getState());
		System.out.println(pe.getStateInfo());
	}
}
