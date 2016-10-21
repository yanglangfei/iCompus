package com.example.administrator.myapplication.icompus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.administrator.myapplication.icompus.entity.TimeLog;

import java.util.ArrayList;


public class TimeLogDao implements TimeLogTable {
	private static final String TAG = TimeLogDao.class.getSimpleName();
	
	private SQLiteDatabase mDb;

	/**
	 * 将上层的context传到Dao层
	 * 
	 * @param context
	 */
	public TimeLogDao(Context context) {
		mDb = MyDB.getInstance(context).getDB();
		if (mDb == null) {
			Log.e(TAG, "init SQLiteDatabase failed");
		}
	}

	public long add(TimeLog entity) {
		//入库成功后的id。
		long id = -1;
		if (null == entity) {
			Log.w(TAG, "Add note:note is null!");
			return id;
		}
		// 入库数值。
		ContentValues values = new ContentValues();
		values.put(TXTLOG, entity.getText());
		values.put(PATH0, entity.getPaths()[0]);
		values.put(PATH1, entity.getPaths()[1]);
		values.put(PATH2, entity.getPaths()[2]);
		values.put(DATE, entity.getDate());
		
		// 入库。
		id = mDb.insert(TABLE_NAME, null, values);
		return id;
	}
	
	public ArrayList<TimeLog> queryAll() {
		// 创建返回值
		ArrayList<TimeLog> logList = new ArrayList<TimeLog>();
		// 查询条件
		String order = DATE +" DESC";
		// 获取游标
		Cursor cursor = null;
		try {
			cursor = mDb.query(TABLE_NAME, null, null, null, null, null, order);
			if (cursor != null && cursor.moveToFirst()) {
				// 循环取出所有数值
				do {
					int id = cursor.getInt(INDEX_ID);
					String text = cursor.getString(INDEX_TEXT);
					String path0 = cursor.getString(INDEX_PATH0);
					String path1 = cursor.getString(INDEX_PATH1);
					String path2 = cursor.getString(INDEX_PATH2);
					long date = cursor.getLong(INDEX_DATE);
					String[] paths = { path0,path1,path2 };
					//赋值
					TimeLog timeLog = new TimeLog();
					timeLog.setId(id);
					timeLog.setText(text);
					timeLog.setPaths(paths);
					timeLog.setDate(date);
					// 添加到集合中
					logList.add(timeLog);
				} while (cursor.moveToNext());
			}
		} catch (SQLiteException e) {
			Log.e(TAG, "Query all note failed!" + e);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return logList;
	}
}
