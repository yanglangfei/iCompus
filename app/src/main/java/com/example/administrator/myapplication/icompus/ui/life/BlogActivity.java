package com.example.administrator.myapplication.icompus.ui.life;
import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.entity.Blog;
import com.example.administrator.myapplication.icompus.web.BlogHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ImageButton;
//import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BlogActivity extends Activity implements OnItemClickListener{
	
	
	private ArrayList<Blog> mBlogList = new ArrayList<Blog>();

	private int mPageIndex = 1;

	private ListView mListView;
	private BlogListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_note);

		initViews();
		new BlogListTask(1, true).execute();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mAdapter = new BlogListAdapter(this, mBlogList);
		mListView.setAdapter(mAdapter);
		
	}

	private void initViews() {
		mListView = (ListView) findViewById(R.id.list_note);
		mListView.setOnItemClickListener(this);
	}


	public class BlogListTask extends AsyncTask<String, Integer, ArrayList<Blog>> {
		
		boolean isRefresh = false;
		int page = 0;
		boolean isLocalData = false;	
		
		public BlogListTask(int pageIndex, boolean isRefresh) {
			page = pageIndex;
			this.isRefresh = isRefresh;
		}

		protected ArrayList<Blog> doInBackground(String... params) {
			
			mBlogList = BlogHelper.GetBlogList(page);
			
			return mBlogList;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	
		@Override
		protected void onPostExecute(ArrayList<Blog> result) {
			// 刷新数据
		mAdapter = new BlogListAdapter(BlogActivity.this, result);
			mAdapter.refreshDate(result);
			mListView.setAdapter(mAdapter);
		}
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Blog blog = mBlogList.get(position);
		Intent intent = new Intent();
		intent.setClass(this, BlogDetailActivity.class);
		intent.putExtra("id", blog.GetBlogId());
		intent.putExtra("title", blog.GetBlogTitle());
		intent.putExtra("author", blog.GetAuthor());
		intent.putExtra("time", blog.GetAddTime().toString());
		startActivity(intent);
	}
}
