package com.pack.Zomato;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.pack.adapter.CuisinesListAdapter;
import com.pack.database.DBAdapter;
import com.pack.database.DBHelper;
import com.pack.helper.Constants;
import com.pack.helper.ParseResponse;
import com.pack.model.*;
import com.pack.rest.RestClient;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class ListCuisinesActivity extends Activity {

	String cityId;
	ListView list;
	ArrayList<CuisineClass> cuisineArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cusineslist);
		Intent intent=getIntent();
		cityId=(String)intent.getSerializableExtra("cityId");
		Log.d("city_id :", ""+cityId);
		getCuisinesInfo();
		
		
		
	}
	
	private void getCuisinesInfo() {
		
		try {
			
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);			
			params.add(new BasicNameValuePair("city_id",cityId));
			params.add(new BasicNameValuePair("apikey", Constants.API_KEY));

			String strJsonReponse = RestClient.getInstance(this).doApiCall(Constants.strCuisines, "GET", params);
			
			Log.d("Cuisines ---",String.valueOf(strJsonReponse));
			
			ParseResponse parser=new ParseResponse();
			
			cuisineArray=parser.parseCuisineData(strJsonReponse);
			
			//insert cuisine data into database
			accessToDatabase(cuisineArray);
			
			list=(ListView)findViewById(R.id.cuisinesListView);
			
			Log.d("before","adapter");
			
			CuisinesListAdapter adapter=new CuisinesListAdapter(ListCuisinesActivity.this, cuisineArray);
			Log.d("after","adapter");
			list.setAdapter(adapter);

			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub

					CuisineClass cuisine=cuisineArray.get(position);
					Intent intent=new Intent(ListCuisinesActivity.this,RestaurantsActivity.class);
					intent.putExtra("cuisineId", cuisine.getCuisinesId());
					intent.putExtra("cityId", cityId);
					startActivity(intent);
					
				}
			
			
			});
				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void accessToDatabase(ArrayList<CuisineClass> arrayCuisine)
	{

		DBAdapter dbAdapt=new DBAdapter(ListCuisinesActivity.this, DBHelper.DATABASE_TABLE);
		DBAdapter.open(ListCuisinesActivity.this);
		for(CuisineClass cuisine:cuisineArray)
		{
			
			ContentValues values=new ContentValues();
			values.put("cuisinesid",cuisine.getCuisinesId());
			values.put("cuisinesname",cuisine.getCuisinesTitle());
			dbAdapt.create(values);
			Log.d("insert","inserting data-");
			
		}
		
		
		
	}

}
