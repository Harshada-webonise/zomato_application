package com.pack.helper;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import com.pack.model.CuisineClass;


public class ParseResponse 
{
   
	public ParseResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<CuisineClass> parseCuisineData(String jsonValue)
	{
	
		
		   ArrayList<CuisineClass> cuisinesArray = new ArrayList<CuisineClass>();
		   
			
			
		   try
			  {		 

			   JSONObject cJsonObject = new JSONObject(jsonValue);
	
			  // Log.d("Parser","in json object "+cJsonObject);
			   
			   String strCuisinesObj  = cJsonObject.getString("cuisines");
			   
			  // Log.d("inside parser try","str obj"+strCuisinesObj);
			   
			   JSONArray cuisinesJsonArray=new JSONArray(strCuisinesObj);
			   
 
		      
		   for(int i=0;i<cuisinesJsonArray.length();i++)
		   {
			   
			   
			   CuisineClass cuisineClass=new CuisineClass();
			   String strCuisine = cuisinesJsonArray.getJSONObject(i).getString(
						"cuisine");
			   Log.d("inside array","cuisines"+strCuisine);
			   
			   JSONObject jsonObjectCuisine = new JSONObject(strCuisine);
			   
			   String strCuisineId = jsonObjectCuisine.getString("cuisine_id");
			   String strCuisineName = jsonObjectCuisine.getString("cuisine_name");
			   
			  // Log.d("ID--",strCuisineId);
			  // Log.d("NAME--",strCuisineName);
			   cuisineClass.setCuisinesId(strCuisineId);
			   cuisineClass.setCuisinesTitle(strCuisineName);
			   cuisinesArray.add(cuisineClass);
			   

		
		   }
		   
		 }
			   catch (Exception e) {
				   
				   Log.d("Json exception---", ""+e);
			}
		return cuisinesArray;

		
	}
			
	
	
}
