package com.pack.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class DBAdapter {
	
	protected static SQLiteDatabase db;
	protected static DBHelper dbHelper;
	protected String dbName;
	protected String[] dbColumns;
	protected Context context;
	
	public static final String ID = "cuisinesid";
	public static final String NAME = "cuisinesname";

	
	public DBAdapter() {
		// TODO Auto-generated constructor stub
		
	}
	
	public DBAdapter(Context context, String strTableName) {
		super();
		this.context = context;
		this.dbName = strTableName;
		if (unopened())
			DBAdapter.open(context);
	}
	
	final public static void open(Context context) throws SQLException {
		dbHelper = new DBHelper(context);
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.w("POLS2",
					"ProjectsDbAdapter::getWritableDatabase error: "
							+ e.getMessage());
		}
	}
	
	
	final public static Boolean unopened() {
		return db == null || !db.isOpen();
	}

	final public static void close() {
		if (!unopened()) {
			dbHelper.close();
		}
	}

	public long create(ContentValues initialValues) {
		return db.insert(dbName, null, initialValues);
	}
	
	
	
}
