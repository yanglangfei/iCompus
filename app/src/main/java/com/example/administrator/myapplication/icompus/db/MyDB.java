package com.example.administrator.myapplication.icompus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB {
	private static final String TAG = MyDB.class.getSimpleName();
	private static final String DATABASE_NAME = "dbName";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase mDB;
	private DatabaseHelper mDbHelper;
	private static MyDB mInstance;
	private static Context mContext;

	/*
	 * 打开数据库的操作。 在某些情况下我们会关闭数据库，所以我们先判断数据库是否被关闭了， 如果关闭则重新打开，否则直接使用数据库
	 */
	private MyDB(Context context) {
		mContext = context;
		try {
			// db helper为空则创建一个，避免重复创建
			if (mDbHelper == null) {
				mDbHelper = new DatabaseHelper(context);
			}
			
			if(mDB == null) {
				mDB = mDbHelper.getWritableDatabase();
			}
			
			// 数据库存在并处于打开状态那么直接使用
			if (mDB != null && mDB.isOpen()) {
				return;
			}
			
		} catch (SQLiteException e) {
			Log.w(TAG, "GetWritableDatabase error.\n info=" + e.getMessage());
//			if(mDbHelper!=null) mSqlLiteDb = mDbHelper.getReadableDatabase();
		}
	}
	
	public synchronized static MyDB getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new MyDB(context);
		}
		return mInstance;
	}

	/**
	 * 获取DB对象。
	 * 
	 * @return
	 */
	public SQLiteDatabase getDB() {
		return mDB;
	}

	/**
	 * 关闭数据库的操作
	 */
	public void close() {
		mDB.close();
		mDbHelper.close();
	}

	@Override
	protected void finalize() throws Throwable {
		Log.i(TAG, "---DB close!");
		close();
		super.finalize();
	}

	/**
	 * 清除数据库中的数据 
	 */
	public void clearDataBase() {
		
	}

	/*
	 * 创建内部类DatabaseHelper：创建数据库，创建表，更新表功能
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
//		private File m_dbFile;

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
//			m_dbFile = context.getDatabasePath(DATABASE_NAME);
		}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(NoteTable.SQL_TABLE_CREATE);
			db.execSQL(TimeLogTable.SQL_TABLE_CREATE);
			db.execSQL(ClassTable.SQL_TABLE_CREATE);
			Log.i("111","create。。。");
		}
		
		@Override
		public synchronized SQLiteDatabase getWritableDatabase() {
			return super.getWritableDatabase();
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i(TAG, "DB Upgrade,oldversion=" + oldVersion + ",newVersion=" + newVersion);
		}
	}
}
