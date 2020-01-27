/**
 * 
 */
package com.nrd.o2o.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrd.o2o.dto.ImageHolder;
import com.nrd.o2o.dto.ProductExecution;
import com.nrd.o2o.entity.Product;
import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.entity.Shop;
import com.nrd.o2o.enums.OperationStatusEnum;
import com.nrd.o2o.enums.ProductStateEnum;
import com.nrd.o2o.exceptions.ProductOperationException;
import com.nrd.o2o.service.ProductCategoryService;
import com.nrd.o2o.service.ProductService;
import com.nrd.o2o.util.CodeUtil;
import com.nrd.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 支持上传商品详情图的最大数量
	 */
	private static final int IMAGE_MAX_COUNT = 6;

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		Product product = null;
		MultipartHttpServletRequest multiparRequest = null;
		ImageHolder thumbnail = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			if (multipartResolver.isMultipart(request)) {
				multiparRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile thumbailFile = (CommonsMultipartFile) multiparRequest.getFile("thumbnail");
				thumbnail = new ImageHolder(thumbailFile.getOriginalFilename(), thumbailFile.getInputStream());
				for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
					CommonsMultipartFile productImgFile = (CommonsMultipartFile) multiparRequest
							.getFile("productImg" + i);
					if (productImgFile != null) {
						ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
								productImgFile.getInputStream());
						productImgList.add(productImg);
					} else {
						break;
					}
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		try {
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				// 从session中获取shop信息，不依赖前端的传递更加安全
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				Shop shop = new Shop();
				shop.setShopId(currentShop.getShopId());
				product.setShop(shop);
				// 调用addProduct
				ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
				if (pe.getState() == OperationStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductStateEnum.PRODUCT_EMPTY.getStateInfo());
		}
		return modelMap;
	}
}