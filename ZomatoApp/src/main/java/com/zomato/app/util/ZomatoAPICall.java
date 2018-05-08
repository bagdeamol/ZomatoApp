package com.zomato.app.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

public class ZomatoAPICall {
	
	public static JsonStreamParser proccessAPI(HttpURLConnection conn, String userKey) {
		JsonStreamParser jsonStreamParser = null;
		try {
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("user-key",""+userKey);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
		    jsonStreamParser = new JsonStreamParser(new InputStreamReader((conn.getInputStream())));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStreamParser;
	}
	
	public static List callRestaurantsAPI(URL url, String userKey) {
		ArrayList json=new ArrayList();
		HttpURLConnection conn;
		JsonStreamParser p= null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			p = proccessAPI(conn, userKey);
			Gson gson = new GsonBuilder().create();
			while (p.hasNext()) {
			   JsonElement e = p.next();
			   if (e.isJsonObject()) {
			      JsonObject jsonObject = e.getAsJsonObject();
			      JsonArray jsonArray = jsonObject.getAsJsonArray("restaurants");
			      for (JsonElement jElement : jsonArray) {
			        JsonObject jObj = jElement.getAsJsonObject();
			        JsonElement je = jObj.get("restaurant");
			        Map m = gson.fromJson(je, TreeMap.class);
			        json.add(m);
			       }
			    }
			}
			if (conn!= null) {
				conn.disconnect();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return json;
	}
	
	public static List callCuisineAPI(URL url, String userKey) {
		ArrayList json=new ArrayList();
		HttpURLConnection conn;
		JsonStreamParser p= null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			p = proccessAPI(conn, userKey);
			Gson gson = new GsonBuilder().create();
			while (p.hasNext()) {
			   JsonElement e = p.next();
			   if (e.isJsonObject()) {
			      JsonObject jsonObject = e.getAsJsonObject();
			      JsonArray jsonArray = jsonObject.getAsJsonArray("cuisines");
			      for (JsonElement jElement : jsonArray) {
			        JsonObject jObj = jElement.getAsJsonObject();
			        JsonElement je = jObj.get("cuisine");
			        Map m = gson.fromJson(je, TreeMap.class);
			        json.add(m);
			       }
			    }
			 }
			if (conn!= null) {
				conn.disconnect();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return json;
	}
	
	public static List getRestraDetails(URL url, String userKey) {
		String result = null;
		ArrayList json=new ArrayList();
		HttpURLConnection conn;
		JsonStreamParser p= null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			p = proccessAPI(conn, userKey);
			Gson gson = new GsonBuilder().create();
			while (p.hasNext()) {
			  JsonElement e = p.next();
			  if (e.isJsonObject()) {
			    JsonObject jsonObject = e.getAsJsonObject();
			    Set<Entry<String, JsonElement>> s = jsonObject.entrySet();
			    Iterator<Entry<String, JsonElement>> it = s.iterator();
			    while(it.hasNext()) {
			    	Entry<String, JsonElement> m = it.next();
			    	String key = m.getKey();
			    	if (key.equals("name") || key.equals("url") || key.equals("location") ||
			    		key.equals("aggregate_rating") || key.equals("phone_numbers")) {
			    		json.add(m);
			    	}
			      }
			   }
			}
			if (conn!= null) {
				conn.disconnect();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return json;
	}
}
