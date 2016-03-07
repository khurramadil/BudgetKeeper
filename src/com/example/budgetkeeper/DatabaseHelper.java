package com.example.budgetkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "budget.db";
	public static final String TABLE_NAME = "record";
	public static final String COL_1 = "ID";
	public static final String COL_2 = "TITLE";
	public static final String COL_3 = "DAY";
	public static final String COL_4 = "MONTH";
	public static final String COL_5 = "YEAR";
	public static final String COL_6 = "CATEGORY";
	public static final String COL_7 = "AMOUNT";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "
				+ TABLE_NAME
				+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DAY TEXT, MONTH TEXT, YEAR TEXT, CATEGORY TEXT, AMOUNT TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);

	}

	public boolean insertData(String title, String day, String month,
			String year, String category, String amount) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(COL_2, title);
		contentValues.put(COL_3, day);
		contentValues.put(COL_4, month);
		contentValues.put(COL_5, year);
		contentValues.put(COL_6, category);
		contentValues.put(COL_7, amount);
		long result = db.insert(TABLE_NAME, null, contentValues);
		if (result == -1)
			return false;
		else
			return true;

	}

	public Cursor viewData() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
		return res;
	}
	
	public Cursor searchData(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID = "	 + id, null);
		return res;
	}
	
	
	
	public Cursor searchTitle(String x) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where TITLE = '" + x +"'", null);
	
//		Cursor res = db.rawQuery("select * from " + TABLE_NAME , null);
		return res;
	}


	public Cursor searchData(String day) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where "
				+ COL_3 + " = " + day, null);
		return res;
	}

	public Cursor searchData(String month, String year) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where "
				+ COL_4 + " = " + month + " and " + COL_5 + " = " + year, null);
		return res;
	}

	public Cursor searchData(String month, String year, String day) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where "
				+ COL_3 + " = " + day + " and " + COL_4 + " = " + month
				+ " and " + COL_5 + " = " + year, null);
		return res;
	}

	public int deleteData(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		int deleted_rows = db.delete(TABLE_NAME, "ID = " + id, null);
		db.close();

		return deleted_rows;
	}

	public int deleteData(String d, String m, String y) {
		SQLiteDatabase db = this.getWritableDatabase();
		int deleted_rows = db.delete(TABLE_NAME, COL_3 + " = " + d + " and "
				+ COL_4 + " = " + m + " and " + COL_5 + " = " + y, null);
		db.close();

		return deleted_rows;
	}

	public int deleteData() {
		SQLiteDatabase db = this.getWritableDatabase();
		int deleted_rows = db.delete(TABLE_NAME, "1", null);
		db.close();

		return deleted_rows;
	}

	// to update data in database

	public boolean updateData(int id, String title, String day, String month,
			String year, String category, String amount) {
		SQLiteDatabase updatedb = this.getWritableDatabase();

		ContentValues updateValues = new ContentValues();
		updateValues.put(COL_2, title);
		updateValues.put(COL_3, day);
		updateValues.put(COL_4, month);
		updateValues.put(COL_5, year);
		updateValues.put(COL_6, category);
		updateValues.put(COL_7, amount);

		long result = updatedb.update(TABLE_NAME, updateValues, "ID = " + id, null);

		if (result == -1)
			return false;
		else
			return true;

	}

}
