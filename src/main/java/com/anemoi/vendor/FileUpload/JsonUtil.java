package com.anemoi.vendor.FileUpload;

import org.json.simple.JSONObject;




public class JsonUtil {

	public static String getString(JSONObject jsonObject, String jsonKey)  {
		String jsonValue = (String) jsonObject.get(jsonKey);
		if (jsonValue == null) {
//			throw new JsonUtillException("JsonObject [" + jsonKey + "] key not found ");
		}
		return jsonValue;

	}
	
	public static Long getLong(JSONObject jsonObject, String jsonKey)  {
		Object jsonValue = jsonObject.get(jsonKey);
		if (jsonValue == null) {
//			throw new JsonUtillException("JsonObject [" + jsonKey + "] key not found ");
		}

		Long jsonValuelong = Long.valueOf((long) jsonValue);
		return jsonValuelong;

	}
	
	public static int getInt(JSONObject jsonObject, String jsonKey)  {
		Object jsonValue = jsonObject.get(jsonKey);
		if (jsonValue == null) {
//			throw new JsonUtillException("JsonObject [" + jsonKey + "] key not found ");
		}

		int jsonValueInt = Integer.parseInt(jsonValue + "");
		return jsonValueInt;

	}

}
