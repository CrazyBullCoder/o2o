package com.nrd.o2o.util;

public class PageCalculator {

	/**
	 * 根据页数和每页数量查询行数
	 * 
	 * @param pageIndex 页码数
	 * @param pageSize 每页数量
	 * @return
	 */
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
