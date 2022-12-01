package com.anemoi.vendor.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseName {
	
	private static Properties dataBaseName;
	
	static {
		dataBaseName = ReadProperties.getPropertiesFromResources("database.properties");
	}
	public static List<String> getAllTenant() {
		
		List<String> tenantsList = new ArrayList<String>();
		dataBaseName.keySet().forEach(tenant -> tenantsList.add((String) tenant));
		return tenantsList;
	}

	
	public static String dataBaseName(String key) {
		String value = dataBaseName.getProperty(key);
//		System.out.println(value);
		return value;
	}

}
