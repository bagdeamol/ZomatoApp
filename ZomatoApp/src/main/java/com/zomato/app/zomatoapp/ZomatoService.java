package com.zomato.app.zomatoapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.zomato.app.util.ZomatoAPICall;

public class ZomatoService {

	public static List getListOfCuisine(String userKey) throws MalformedURLException {
		URL url = new URL("https://developers.zomato.com/api/v2.1/cuisines?city_id=4");
		return ZomatoAPICall.callCuisineAPI(url, userKey);
	}
	
	public static List getRestraList(String cuisineId, String userKey) throws MalformedURLException{
		URL url = new URL("https://developers.zomato.com/api/v2.1/search?entity_type=city&q=bangalore&count=10"
				+ "&lat=12.972442&lon=77.580643&radius=20000&sort=rating&order=desc&cuisines="+cuisineId);
		return ZomatoAPICall.callRestaurantsAPI(url, userKey);
	}
	
	public static List getRestraDetails(String restraId, String userKey) throws MalformedURLException {
		URL url = new URL("https://developers.zomato.com/api/v2.1/restaurant?res_id="+restraId);
		return ZomatoAPICall.getRestraDetails(url, userKey);
	}
}
