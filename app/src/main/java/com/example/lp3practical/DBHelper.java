package com.example.lp3practical;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "drinks.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "Drink";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_CATEGORY = "category";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String createTable = "CREATE TABLE " + TABLE_NAME + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME + " TEXT," +
				COLUMN_CATEGORY + " TEXT )";
		db.execSQL(createTable);
		Log.i("info", "created tables");

		//create some dummy data in DB table for testing
		Drink[] drinks = {new Drink("Latte", "coffee"),
				new Drink("Cappuccino", "coffee"),
				new Drink("White coffee", "coffee"),
				new Drink("Milk tea", "tea"),
				new Drink("Green tea", "tea")};

		for (int i=0; i<drinks.length; i++) {
			insertItem(db, drinks[i].getName(), drinks[i].getCategory());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void insertItem(SQLiteDatabase db, String name, String category) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_CATEGORY, category);
		db.insert(TABLE_NAME, null, values);
	}

	public ArrayList<String> getItemsOfCategory(String category) {
		ArrayList<String> names = new ArrayList<String>();

		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns = {COLUMN_NAME};
		String condition = COLUMN_CATEGORY + " = " + "'" + category + "'";

		Cursor cursor = db.query(TABLE_NAME, columns, condition, null, null, null, null);

		if (cursor.moveToFirst()){
			do {
				String name = cursor.getString(0);

				names.add(name);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return names;
	}
}
