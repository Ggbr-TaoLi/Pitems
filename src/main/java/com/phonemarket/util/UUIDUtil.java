package com.phonemarket.util;

import java.util.UUID;
//上传需要
public class UUIDUtil {

	public static String getUUID(){
		String str=UUID.randomUUID().toString();
		str=str.replace("-", "");
		return str;
	}
}
