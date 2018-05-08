package com.zomato.app.zomatoapp;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	private String userKey;
	public static void main(String[] args) throws MalformedURLException {
		Main main = new Main();
		main.commandlineInterface();
	}

	public void commandlineInterface() throws MalformedURLException {
		System.out.println("Please Enter User key");
		Scanner sc = new Scanner(System.in);
		this.userKey = sc.next();
		System.out.println("List Of Cuisine Options in Bangalore");
		List cuisineList = ZomatoService.getListOfCuisine(userKey);
		for (int i =0 ; i< 10; i++) {
			TreeMap cuisineMap = (TreeMap)cuisineList.get(i);
			System.out.println("Cuisine Id := "+((Double)cuisineMap.get("cuisine_id")).intValue()+ " , "+"Cuisine Name := "+cuisineMap.get("cuisine_name"));
		}
		
		System.out.println("Select the cuisine id to get the top 10 restaurants by rating : -");
		String cuisineId = sc.next();
		List restraList = ZomatoService.getRestraList(cuisineId, userKey);
		System.out.println("Top 10 Restaurants for selected cuisine :-");
		for (int i =0 ; i< restraList.size(); i++) {
			TreeMap restraMap = (TreeMap)restraList.get(i);
			System.out.println("Restaurent Id := "+restraMap.get("id")+ " , " +"Restaurent Name := "+restraMap.get("name"));
		}
		
		System.out.println("Please Select the Restaurent Id to get Restaurent Details : -");
		String restraId = sc.next();
		
		List restraDetailsList = ZomatoService.getRestraDetails(restraId, userKey);
		System.out.println(restraDetailsList);
		if (sc != null){
			sc.close();
		}
	}
}
