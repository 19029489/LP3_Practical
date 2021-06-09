package com.example.lp3practical;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

	}
}
