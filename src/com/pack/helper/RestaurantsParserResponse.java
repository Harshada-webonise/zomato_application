package com.pack.helper;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pack.model.RestaurantClass;


public class RestaurantsParserResponse {
	ArrayList<RestaurantClass> restaurantsArray;

	public ArrayList<RestaurantClass> parseRestaurants(String strJsonReponse)
	{

		restaurantsArray=new ArrayList<RestaurantClass>();
		try
		{

			JSONObject responseResto = new JSONObject(strJsonReponse);
			JSONArray restoArray = responseResto.getJSONArray("results");

			for(int i=0;i < restoArray.length();i++)
			{
				RestaurantClass restoclass=new RestaurantClass();
				JSONObject jsonObject=restoArray.getJSONObject(i);
				JSONObject restaurant=jsonObject.getJSONObject("result");

				restoclass.setRestoName(restaurant.getString("name").toString());
				restoclass.setRestoAddress(restaurant.getString("address").toString());

				restaurantsArray.add(restoclass);

			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}

		return restaurantsArray;
	}


}
