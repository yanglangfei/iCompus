package com.example.administrator.myapplication.icompus.db;

/**
 * 日记表字段。
 * 
 */
public interface ClassTable {
	// 表名
	public static final String TABLE_NAME = "class";
	
	// 字段
	/**
	 * id
	 */
	public static final String ID = "_id";
	/**
	 * 标题
	 */
	public static final String NAME = "name";
	/**
	 * 内容
	 */
	public static final String TNAME = "teacherName";

	public static final String START = "startDate";

	public static final String END = "endDate";



	// 字段的索引号
	public static final int INDEX_ID = 0;
	public static final int INDEX_NAME= 1;
	public static final int INDEX_TNAME= 2;
	public static final int INDEX_START= 3;
	public static final int INDEX_END= 4;
	
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
			.append(NAME).append(" TEXT,")
			.append(TNAME).append(" TEXT,")
			.append(START).append(" TEXT, ")
			.append(END).append(" TEXT")
			.append(");").toString();
}
