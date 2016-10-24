package com.example.administrator.myapplication.icompus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.administrator.myapplication.icompus.entity.MyClass;
import com.example.administrator.myapplication.icompus.entity.Note;

import java.util.ArrayList;
import java.util.Locale;


/**
 * 日记DB操作类
 * 
 * 
 */
public class ClassDao implements ClassTable {
	private static final String TAG = ClassDao.class.getSimpleName();

	private SQLiteDatabase mDb;

	/**
	 * 将上层的context传到Dao层
	 *
	 * @param context
	 */
	public ClassDao(Context context) {
		mDb = MyDB.getInstance(context).getDB();
		if (mDb == null) {
			Log.e(TAG, "init SQLiteDatabase failed ");
		}
	}

	public long add(MyClass myClass) {
		//入库成功后的id。
		long id = -1;  
		if (null == myClass) {
			Log.w(TAG, "Add class:class is null!");
			return id;
		}
		// 入库数值。
		ContentValues values = new ContentValues();
		values.put(NAME, myClass.getName());
		values.put(TNAME, myClass.getTeacherName());
		values.put(START, myClass.getStartDate());
		values.put(END,myClass.getEndDate());
		// 入库。
		id = mDb.insert(TABLE_NAME, null, values);
		return id;
	}
	
	public int update(int id,MyClass myClass){
		int updateCount = 0;
		//参数检查
		if (id < 0 || (myClass==null)) {
			Log.w(TAG, "update class,id = " + id);
			return updateCount;
		}
		// 匹配条件
		String whereClause = String.format("%s=?", ClassTable.ID);
		String[] whereArgs = new String[] {String.valueOf(id)};
		//入库值
		ContentValues values = new ContentValues();
		values.put(NAME, myClass.getName());
		values.put(TNAME, myClass.getTeacherName());
		values.put(START, myClass.getStartDate());
		values.put(END,myClass.getEndDate());
		try {
			updateCount = mDb.update(ClassTable.TABLE_NAME, values, whereClause, whereArgs);
		} catch (Exception e) {
			Log.e(TAG, "update class table error! count = "+updateCount);
		}
		return updateCount;
	}
	
	public int delete(int id){
		//创建返回值
		int delCount = 0;
		//参数检查
		if (id < 0) {
			Log.w(TAG, "query class,id = " + id);
			return delCount;
		}
		// 匹配条件
		String whereClause = String.format("%s=?", ClassTable.ID);
		String[] whereArgs = new String[] {String.valueOf(id)};
		try {
			delCount = mDb.delete(ClassTable.TABLE_NAME, whereClause, whereArgs);
		} catch (Exception e) {
			Log.e(TAG, "delete class table error! id ="+delCount);
		}
		return delCount;
	}
	
	public int deleteAll(){
		int delCount = 0;
		try {
			delCount = mDb.delete(ClassTable.TABLE_NAME, null, null);

		} catch (Exception e) {
			Log.e(TAG, "delete class table error! id ="+delCount);
		}
		return delCount;
	}

	public MyClass query(int id) {
		//参数检查
		if (id < 0) {
			Log.w(TAG, "query class,id = " + id);
			return null;
		}
		//创建返回值
		MyClass myClass = null;
		// 查询条件
		String selection = String.format(Locale.US, "%s='%s'", ClassTable.ID, id);
		//获取游标
		Cursor cursor = null;
		try {
			cursor = mDb.query(ClassTable.TABLE_NAME, null, selection, null, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				//取出数值
				String name = cursor.getString(ClassTable.INDEX_NAME);
				String teacherName = cursor.getString(ClassTable.INDEX_TNAME);
				String start = cursor.getString(ClassTable.INDEX_START);
				String end = cursor.getString(ClassTable.INDEX_END);
				
				//将数值设置到实体类中
				myClass = new MyClass(name,teacherName,start,end,false);
			}
		} catch (SQLiteException e) {
			Log.e(TAG, "---Query failed!" + e);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return myClass;
	}
	
	public ArrayList<MyClass> queryAll() {
		// 创建返回值
		ArrayList<MyClass> noteList = new ArrayList<MyClass>();
		// 查询条件
		String order = ClassTable.ID +" DESC";
		// 获取游标
		Cursor cursor = null;
		try {
			cursor = mDb.query(ClassTable.TABLE_NAME, null, null, null, null, null, order);
			if (cursor != null && cursor.moveToFirst()) {
				// 循环取出所有数值
				do {
					int id = cursor.getInt(ClassTable.INDEX_ID);
					String name = cursor.getString(ClassTable.INDEX_NAME);
					String tname = cursor.getString(ClassTable.INDEX_TNAME);
					String start = cursor.getString(ClassTable.INDEX_START);
					String end = cursor.getString(ClassTable.INDEX_END);
					
					//赋值
					MyClass myClass = new MyClass(name,tname,start,end,false);
					// 添加到集合中
					noteList.add(myClass);
				} while (cursor.moveToNext());
			}
		} catch (SQLiteException e) {
			Log.e(TAG, "Query all note failed!" + e);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return noteList;
	}
}
