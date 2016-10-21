package com.example.administrator.myapplication.icompus.db;

import java.util.ArrayList;
import java.util.Locale;

import com.example.administrator.myapplication.icompus.entity.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


/**
 * 日记DB操作类
 * 
 * 
 */
public class NoteDao implements NoteTable {
	private static final String TAG = NoteDao.class.getSimpleName();
	
	private SQLiteDatabase mDb;

	/**
	 * 将上层的context传到Dao层
	 * 
	 * @param context
	 */
	public NoteDao(Context context) {
		mDb = MyDB.getInstance(context).getDB();
		if (mDb == null) {
			Log.e(TAG, "init SQLiteDatabase failed ");
		}
	}

	public long add(Note note) {
		//入库成功后的id。
		long id = -1;  
		if (null == note) {
			Log.w(TAG, "Add note:note is null!");
			return id;
		}
		// 入库数值。
		ContentValues values = new ContentValues();
		values.put(TITLE, note.getTitle());
		values.put(CONTENT, note.getContent());
		values.put(DATE, note.getDate());
		// 入库。
		id = mDb.insert(TABLE_NAME, null, values);
		return id;
	}
	
	public int update(int id,Note note){
		int updateCount = 0;
		//参数检查
		if (id < 0 || (note==null)) {
			Log.w(TAG, "update note,id = " + id);
			return updateCount;
		}
		// 匹配条件
		String whereClause = String.format("%s=?", NoteTable.ID);
		String[] whereArgs = new String[] {String.valueOf(id)};
		//入库值
		ContentValues values = new ContentValues();
		values.put(NoteTable.TITLE, note.getTitle());
		values.put(NoteTable.CONTENT, note.getContent());
		values.put(NoteTable.DATE, note.getDate());
		try {
			updateCount = mDb.update(NoteTable.TABLE_NAME, values, whereClause, whereArgs);
		} catch (Exception e) {
			Log.e(TAG, "update note table error! count = "+updateCount);
		}
		return updateCount;
	}
	
	public int delete(int id){
		//创建返回值
		int delCount = 0;
		//参数检查
		if (id < 0) {
			Log.w(TAG, "query note,id = " + id);
			return delCount;
		}
		// 匹配条件
		String whereClause = String.format("%s=?", NoteTable.ID);
		String[] whereArgs = new String[] {String.valueOf(id)};
		try {
			delCount = mDb.delete(NoteTable.TABLE_NAME, whereClause, whereArgs);
		} catch (Exception e) {
			Log.e(TAG, "delete note table error! id ="+delCount);
		}
		return delCount;
	}
	
	public int deleteAll(){
		int delCount = 0;
		try {
			delCount = mDb.delete(NoteTable.TABLE_NAME, null, null);

		} catch (Exception e) {
			Log.e(TAG, "delete note table error! id ="+delCount);
		}
		return delCount;
	}

	public Note query(int id) {
		//参数检查
		if (id < 0) {
			Log.w(TAG, "query note,id = " + id);
			return null;
		}
		//创建返回值
		Note note = null;
		// 查询条件
		String selection = String.format(Locale.US, "%s='%s'", NoteTable.ID, id);
		//获取游标
		Cursor cursor = null;
		try {
			cursor = mDb.query(NoteTable.TABLE_NAME, null, selection, null, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				//取出数值
				String title = cursor.getString(NoteTable.INDEX_TITLE);
				String content = cursor.getString(NoteTable.INDEX_CONTENT);
				long date = cursor.getLong(NoteTable.INDEX_DATE);
				
				//将数值设置到实体类中
				note = new Note();
				note.setId(id);
				note.setTitle(title);
				note.setContent(content);
				note.setDate(date);
			}
		} catch (SQLiteException e) {
			Log.e(TAG, "---Query failed!" + e);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return note;
	}
	
	public ArrayList<Note> queryAll() {
		// 创建返回值
		ArrayList<Note> noteList = new ArrayList<Note>();
		// 查询条件
		String order = NoteTable.DATE +" DESC";
		// 获取游标
		Cursor cursor = null;
		try {
			cursor = mDb.query(NoteTable.TABLE_NAME, null, null, null, null, null, order);
			if (cursor != null && cursor.moveToFirst()) {
				// 循环取出所有数值
				do {
					int id = cursor.getInt(NoteTable.INDEX_ID);
					String title = cursor.getString(NoteTable.INDEX_TITLE);
					String content = cursor.getString(NoteTable.INDEX_CONTENT);
					long date = cursor.getLong(NoteTable.INDEX_DATE);
					
					//赋值
					Note note = new Note();
					note.setId(id);
					note.setTitle(title);
					note.setContent(content);
					note.setDate(date);
					// 添加到集合中
					noteList.add(note);
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
