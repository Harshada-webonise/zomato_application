package com.pack.Zomato;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.pack.adapter.RestaurantListAdapter;
import com.pack.helper.*;
import com.pack.model.RestaurantClass;
import com.pack.rest.RestClient;

public class RestaurantsActivity extends Activity 
{
	String cuisine_id;
	String city_id;
	ArrayList<RestaurantClass> restaurantsArray;
	ListView restoListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.restaurants_listview);
		
		Intent intent=getIntent();
		cuisine_id=(String)intent.getSerializableExtra("cuisineId");
		city_id=(String)intent.getSerializableExtra("cityId");
		getRestoDetails();		
		
		
	}

	
	public void getRestoDetails()
	{
		try {
			
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(2);			
			params.add(new BasicNameValuePair("city_id",city_id));
			params.add(new BasicNameValuePair("cuisine_id", cuisine_id));
			params.add(new BasicNameValuePair("apikey", Constants.API_KEY));

			String strJsonReponse = RestClient.getInstance(this).doApiCall(com.pack.helper.Constants.strSearch, "GET", params);
			
			
		  RestaurantsParserResponse parser=new RestaurantsParserResponse();
			 restaurantsArray=parser.parseRestaurants(strJsonReponse);
			
			for(RestaurantClass response:restaurantsArray)
			{
				Log.d("Restaurants: ", response.toString());
			}
			
			restoListView=(ListView)findViewById(R.id.restolist);
			
		RestaurantListAdapter adapter=new RestaurantListAdapter(RestaurantsActivity.this, restaurantsArray);
			restoListView.setAdapter(adapter);
			

				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
