package com.example.administrator.myapplication.icompus.db;

/**
 * 日记表字段。
 * 
 */
public interface NoteTable {
	// 表名
	public static final String TABLE_NAME = "note";
	
	// 字段
	/**
	 * id
	 */
	public static final String ID = "_id";
	/**
	 * 标题
	 */
	public static final String TITLE = "title";
	/**
	 * 内容
	 */
	public static final String CONTENT = "content";
	/**
	 * 日期
	 */
	public static final String  DATE = "date";

	// 字段的索引号
	public static final int INDEX_ID = 0;
	public static final int INDEX_TITLE = 1;
	public static final int INDEX_CONTENT = 2;
	public static final int INDEX_DATE = 3;
	
	/**
	 * SQL,删除表
	 */
	public static final String SQL_TABLE_DROP = String.format("DROP TABLE IF EXISTS %s;", TABLE_NAME);
	/**
	 * SQL,创建表
	 */
	public static final String SQL_TABLE_CREATE = new StringBuilder()
			.append("CREATE TABLE IF NOT EXISTS ") 
			.append(TABLE_NAME).append("(")
			.append(ID).append(" INTEGER PRIMARY KEY,")
			.append(TITLE).append(" TEXT,")
			.append(CONTENT).append(" TEXT,")
			.append(DATE).append(" INTEGER")
			.append(");").toString();
}
