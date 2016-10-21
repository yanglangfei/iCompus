package com.example.administrator.myapplication.icompus.config;

import android.os.Environment;

/**
 * 各种全局常量。
 * 
 * @author wanglu
 */
public class MyConfig {
	
	//SDcard,文件系统。
	public static final String ROOT = Environment.getExternalStorageDirectory().getPath();
	public static final String PATH_ICAMPUS = ROOT + "/icampus";
	public static final String PATH_BOOK = PATH_ICAMPUS + "/books";
	public static final String PATH_IMAGES = PATH_ICAMPUS + "/img";
	
	//博客园域名。
	public static final String CNBLOGS_URL = "http://www.cnblogs.com/";
	//App更新。
	public static final String APP_UPDATE_URL = "http://android.walkingp.com/api/update_app.ashx?alias={alias}&action=update";
	
	//博客相关。
	//博客每页条数。
	public static final int BLOG_PAGE_SIZE = 10;
	//博客列表Url。
	public static final String URL_BLOG_LIST = "http://wcf.open.cnblogs.com/blog/sitehome/paged/{pageIndex}/{pageSize}";
	//博客细节Url。
	public static final String URL_BLOG_DETAIL = "http://wcf.open.cnblogs.com/blog/post/body/{0}";
	//48小时阅读排行列表。
	public static final String URL_BLOG_48HOURS_LIST="http://wcf.open.cnblogs.com/blog/48HoursTopViewPosts/{size}";
	//48小时排行每页条数。
	public static final int BLOG_PAGE_SIZE_48HOURS = 20;
	//10天内推荐列表。
	public static final String URL_BLOG_10DAYS_LIST="http://wcf.open.cnblogs.com/blog/TenDaysTopDiggPosts/{size}";
	//10天推荐列表每页条数。
	public static final int NUM_TENDAYS_TOP_DIGG=20;
	

	
	
	
	public static final int NEWS_PAGE_SIZE = 10;// ���ŷ�ҳ����
	public static final String URL_GET_NEWS_LIST = "http://wcf.open.cnblogs.com/news/recent/paged/{pageIndex}/{pageSize}";// ���ҳ�루��1��ʼ)
	public static final String URL_GET_NEWS_DETAIL = "http://wcf.open.cnblogs.com/news/item/{0}";// ��ݱ��ȡ����
	
	public static final String URL_RECOMMEND_NEWS_LIST="http://wcf.open.cnblogs.com/news/recommend/paged/{pageIndex}/{pageSize}";//�Ƽ�����
	
	public static final int COMMENT_PAGE_SIZE = 10;// ���۷�ҳ����
	
	public static final String URL_NEWS_GET_COMMENT_LIST = "http://wcf.open.cnblogs.com/news/item/{contentId}/comments/{pageIndex}/{pageSize}";// �õ��������۷�ҳ
	public static final String URL_BLOG_GET_COMMENT_LIST = "http://wcf.open.cnblogs.com/blog/post/{contentId}/comments/{pageIndex}/{pageSize}";// �õ��������۷�ҳ
	
	public static final String URL_USER_SEARCH_AUTHOR_LIST = "http://wcf.open.cnblogs.com/blog/bloggers/search?t={username}";// �û�����
	
	public static final int NUM_RECOMMEND_USER=10;//�Ƽ�ͷ�ҳ����
	public static final String URL_RECOMMEND_USER_LIST="http://wcf.open.cnblogs.com/blog/bloggers/recommend/{pageIndex}/{pageSize}";//�Ƽ������
	
	public static final int BLOG_LIST_BY_AUTHOR_PAGE_SIZE = 10;// ���������б��ҳ
	public static final String URL_GET_BLOG_LIST_BY_AUTHOR = "http://wcf.open.cnblogs.com/blog/u/{author}/posts/{pageIndex}/{pageSize}";// ���������б�

	public static final String LOCAL_PATH = "file:///android_asset/";// ����html
	// ����΢��api
	public static final String consumerKey = "4216444778";
	public static final String consumerSecret = "1f6960b6dfe01c1ab71c417d29b439a8";
	public static final String callBackUrl = "myapp://AboutActivity";

	public static final String AuthorWeiboUserId = "1240794802";// �Լ�������΢���û����
	public static final String AuthorWeiboUserName = "walkingp";// ���ߵ�����΢���û��ǳ�

	public static final String DB_BLOG_TABLE = "BlogList";// ������ݱ���
	public static final String DB_NEWS_TABLE = "NewsList";// ������ݱ���
	public static final String DB_COMMENT_TABLE = "CommentList";// ������ݱ���
	public static final String DB_RSSLIST_TABLE = "RssList";// ���Ĳ�����ݱ���
	public static final String DB_RSSITEM_TABLE = "RssItem";// ����������ݱ���
	public static final String DB_FAV_TABLE="FavList";//�ղر�

	public static final boolean IS_SYNCH2DB_AFTER_READ = true;// �Ķ�ʱ�Ƿ�ͬ������ݿ�

	public static final String URL_RSS_CATE_URL = "http://m.walkingp.com/api/xml/cnblogs_rsscate.xml";// ��ѡRSS�ļ���ַ
	public static final String URL_RSS_LIST_URL = "http://m.walkingp.com/api/xml/cnblogs_rss_item_{0}.xml";// ��ѡRSS�ļ���ַ
}
