package com.pack.model;

import com.pack.database.DBAdapter;
import android.database.Cursor;

public class CuisineClass {

	String CuisinesId;
	String CuisinesTitle;
	
	public CuisineClass()
	{
		super();
	}
	
	public CuisineClass(Cursor cursor){
		super();

		this.CuisinesId= cursor.getString(cursor
				.getColumnIndex(DBAdapter.ID));
		this.CuisinesTitle= cursor.getString(cursor
				.getColumnIndex(DBAdapter.NAME));		
		
	}
	
	public String getCuisinesId() {
		return CuisinesId;
	}
	public void setCuisinesId(String cuisinesId) {
		CuisinesId = cuisinesId;
	}
	public String getCuisinesTitle() {
		return CuisinesTitle;
	}
	public void setCuisinesTitle(String cuisinesTitle) {
		CuisinesTitle = cuisinesTitle;
	}

	@Override
	public String toString() {
		return "CuisineClass [CuisinesId=" + CuisinesId + ", CuisinesTitle="
				+ CuisinesTitle + "]";
	}
	
	
	
}
