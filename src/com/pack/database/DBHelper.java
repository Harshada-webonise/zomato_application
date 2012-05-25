package com.pack.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "ZomatoDatabase";
    public static final String DATABASE_TABLE = "cuisinse";
    private static final int DATABASE_VERSION = 1;
    
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String DATABASE_CREATE =
		            "create table cuisinse (_id integer primary key autoincrement, "
		            + "cuisinesid text not null, cuisinesname text not null);";
		 
		   db.execSQL(DATABASE_CREATE);
		 Log.d("Database :", "created-----");
		 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	       db.execSQL("DROP TABLE IF EXISTS" +DATABASE_TABLE);
           onCreate(db);
	}

}
