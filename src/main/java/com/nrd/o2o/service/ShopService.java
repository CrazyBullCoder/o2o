package com.nrd.o2o.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.nrd.o2o.dto.ShopExecution;
import com.nrd.o2o.entity.Shop;
import com.nrd.o2o.exceptions.ShopOperationException;

public interface ShopService {
	Shop getByShopId(long shopId);

	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}
