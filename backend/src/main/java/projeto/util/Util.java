package projeto.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Util {

	public static Map<String, Object> getMap(String json) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
		}.getType());
	}
	
	public static String getDataYYYYMMdd() {

		StringBuilder pattern = new StringBuilder("yyyyMMdd");

		SimpleDateFormat formatBra = new SimpleDateFormat(pattern.toString());
		try {
			return (formatBra.format(new Date()));
		} catch (Exception ex) {
			return null;
		}
	}
	
}
