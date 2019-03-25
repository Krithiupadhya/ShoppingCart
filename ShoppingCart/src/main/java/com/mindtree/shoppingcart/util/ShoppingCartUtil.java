package com.mindtree.shoppingcart.util;

import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	
	public static <T> Collector<T, ?, T> toSingleton() {
	    return Collectors.collectingAndThen(
	            Collectors.toList(),
	            list -> {
	                if (list.size() != 1) {
	                    throw new IllegalStateException();
	                }
	                return list.get(0);
	            }
	    );
	}
}
