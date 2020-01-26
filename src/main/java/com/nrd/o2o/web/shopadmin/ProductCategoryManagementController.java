package com.nrd.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nrd.o2o.dto.ProductCategoryExecution;
import com.nrd.o2o.dto.Result;
import com.nrd.o2o.entity.ProductCategory;
import com.nrd.o2o.entity.Shop;
import com.nrd.o2o.enums.ProductCategoryStateEnum;
import com.nrd.o2o.exceptions.ProductCategoryOperationException;
import com.nrd.o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping("/getproductcategorylist")
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if (currentShop == null) {
			ProductCategoryStateEnum pc = ProductCategoryStateEnum.EDIT_ERROR;
			int state = pc.getState();
			String errorMsg = pc.getStateInfo();
			return new Result<List<ProductCategory>>(false, errorMsg, state);
		}
		List<ProductCategory> queryProductCategoryList = productCategoryService
				.queryProductCategoryList(currentShop.getShopId());
		Result<List<ProductCategory>> result = new Result<List<ProductCategory>>(true, queryProductCategoryList);
		return result;
	}

	@PostMapping("/addproductcategorys")
	@ResponseBody
	private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		for (ProductCategory pc : productCategoryList) {
			pc.setShopId(currentShop.getShopId());
		}
		if (productCategoryList == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		} else {
			try {
				ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}
		return modelMap;
	}

	@PostMapping("/removeproductcategory")
	@ResponseBody
	private Map<String, Object> removeProductCategorys(Long productCategoryId,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if (productCategoryId == null && productCategoryId <= 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		} else {
			try {
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,
						currentShop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}
		return modelMap;
	}
}
