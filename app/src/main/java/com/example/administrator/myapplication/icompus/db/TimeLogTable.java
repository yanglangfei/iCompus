package com.example.administrator.myapplication.icompus.db;

/**
 * 日记表字段。
 * 
 */
public interface TimeLogTable {
	// 表名
	public static final String TABLE_NAME = "time";
	
	// 字段
	
	public static final String ID = "_id";
	public static final String TXTLOG = "txtlog";
	public static final String PATH0 = "path0";
	public static final String PATH1 = "path1";
	public static final String PATH2 = "path2";
	public static final String DATE = "date";

	// 字段的索引号
	public static final int INDEX_ID = 0;
	public static final int INDEX_TEXT = 1;
	public static final int INDEX_PATH0 = 2;
	public static final int INDEX_PATH1 = 3;
	public static final int INDEX_PATH2 = 4;
	public static final int INDEX_DATE = 5;
	
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
			.append(TXTLOG).append(" TEXT,")
			.append(PATH0).append(" TEXT,")
			.append(PATH1).append(" TEXT,")
			.append(PATH2).append(" TEXT,")
			.append(DATE).append(" INTEGER")
			.append(");").toString();
}
