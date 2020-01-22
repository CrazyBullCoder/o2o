package com.nrd.o2o.service;

import java.io.InputStream;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.nrd.o2o.dto.ShopExecution;
import com.nrd.o2o.entity.Shop;
import com.nrd.o2o.exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	Shop getByShopId(long shopId);

	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}
