package com.example.administrator.myapplication.icompus.ui.life;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.utils.AppUtil;
import com.example.administrator.myapplication.icompus.web.BlogHelper;
import com.example.administrator.myapplication.icompus.web.NetHelper;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ImageButton;
//import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class BlogDetailActivity extends Activity {
	public static final String TAG = BlogDetailActivity.class.getSimpleName();
	
	private WebView mWebView;
	
	private int mId = 0;
	private String mTitle = "";
	private String mAuthor = ""; 
	private String mTime = "";

	private String mContent = "";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_blog_detail);
		initData();
		initViews();
		new BlogTask(0, true).execute();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	@SuppressLint("JavascriptInterface")
	private void initViews() {
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.addJavascriptInterface(this, "javatojs");
		mWebView.setSelected(true);
		mWebView.setScrollBarStyle(0);
		WebSettings setting = mWebView.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setNeedInitialFocus(false);
		setting.setSupportZoom(true);
		setting.setDefaultTextEncodingName("utf-8");
		setting.setDefaultFontSize(12);
		setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
	}
	
	private void initData(){
		Intent intent = getIntent();
		mId = intent.getIntExtra("id", 0);
		mTitle = intent.getStringExtra("title");
		mAuthor = intent.getStringExtra("author");
		mTime = intent.getStringExtra("time");
	}


	public class BlogTask extends AsyncTask<String, Integer, String> {
		
		boolean isRefresh = false;
		int page = 0;
		boolean isLocalData = false;	
		
		public BlogTask(int pageIndex, boolean isRefresh) {
			page = pageIndex;
			this.isRefresh = isRefresh;
		}

		protected String doInBackground(String... params) {
			
			mContent = BlogHelper.GetBlog(mId);
			
			return mContent;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	
		@Override
		protected void onPostExecute(String result) {
			String htmlContent = "";
			try {
				InputStream in = getAssets().open("NewsDetail.html");
				byte[] temp = NetHelper.readInputStream(in);
				htmlContent = new String(temp);
			} catch (Exception e) {
				Log.e("error", e.toString());
			}

			String blogInfo = "作者: " + mAuthor + " 发表时间:" + mTime;
			// 格式化html
		/*_blogContent = AppUtil.FormatContent(getApplicationContext(),
				_blogContent);*/

			htmlContent = htmlContent.replace("#title#", mTitle)
					.replace("#time#", blogInfo)
					.replace("#content#", mContent);
//			String htmlContent = AppUtil.FormatContent(getApplicationContext(),mContent);
			mWebView.loadDataWithBaseURL("file:///android_asset/", htmlContent, "text/html", "utf-8", null);
		}
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
		}
	}
}
