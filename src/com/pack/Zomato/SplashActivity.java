package com.pack.Zomato;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import com.pack.rest.RestClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.pack.helper.AppStatus;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends Activity 
{

	String cityName;
	String cityID;
	AppStatus appStatus;
	ProgressDialog progress;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		appStatus = AppStatus.getInstance(this);
		handler = new Handler();
		progress = new ProgressDialog(this);
		
		startApp();
		
		
		
	}
	
	private void startApp() {
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				showprogress(true, "Loading", "In Process please wait...");
				if (appStatus.isOnline(SplashActivity.this)) {
					Log.v("SPLASH_SCREEN", "App is online");
					if (appStatus.isRegistered()) {
						Log.v("SPLASH_SCREEN", "App is registered!");
						
						getCityDetails();
						Intent intent = new Intent(SplashActivity.this,ListCuisinesActivity.class);
						intent.putExtra("cityId",cityID);
						
						startActivity(intent);
						finish();
					} 

				} 
				else {
					Log.v("SPLASH_SCREEN", "You are not online!!!!");
					showprogress(false, "", "");
					//message("Please check you internet connection!!");
					
					
					finish();
				}
				showprogress(false, "", "");
			}
		});
		t.start();
	}
	
	public void getCityDetails() 
	{
		
		String url=com.pack.helper.Constants.strLocation;  //"geocode.json";
			
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		
		
		params.add(new BasicNameValuePair("lat","18.52043"));
		params.add(new BasicNameValuePair("lon","73.856744"));
		params.add(new BasicNameValuePair("apikey",com.pack.helper.Constants.API_KEY));
		
		try {
			//Log.w("result","inside try");
			
			String strJsonReponse = RestClient.getInstance(this).doApiCall(
						url, "GET", params);
			Log.i("json responce---",
					String.valueOf(strJsonReponse));


				JSONObject cityJsonObject=new JSONObject(strJsonReponse);
				    JSONObject localityObj=cityJsonObject.getJSONObject("locality");
				    cityID=localityObj.getString("city_id");
				    cityName=localityObj.getString("city_name");
				    
				    Log.d("city_id", ""+cityID);
				    Log.d("city_Name", ""+cityName);
				    
				    
		}	
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
	
		}
	
	void showprogress(final boolean show, final String title, final String msg) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (show) {
					if (progress != null) {
						progress.setTitle(title);
						progress.setMessage(msg);
						progress.show();
					}
				} else {
					progress.cancel();
					progress.dismiss();
				}

			}
		});
	}

	void message(String msg) {
		final String mesage = msg;
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(SplashActivity.this, mesage, 8000);
				toast.show();
			}
		});
	}
	
}