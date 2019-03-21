package com.mindtree.shoppingcart.util;

public class ShoppingCartUtil {

	public static boolean isLong(String value) {
		try {
			Long.parseLong(value);
		}catch (Exception e) {
			return false;	
		}
		return true;				
	}
	
	@SuppressWarnings("finally")
	public static Long getLong(String value) {
		Long l = null;
		try {
			l=Long.parseLong(value);
		}finally { 
			return l;
		}
	}
}
