package com.nrd.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verfyCodeActual = HttpServletRequestUtil.getString(request, "verfyCodeActual");
		if (verfyCodeActual == null || !verfyCodeActual.equals(verifyCodeExpected)) {
			return false;	
		}
		return true;
	}
}
