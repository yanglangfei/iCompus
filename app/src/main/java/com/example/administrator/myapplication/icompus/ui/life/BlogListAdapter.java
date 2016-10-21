package com.example.administrator.myapplication.icompus.ui.life;

import java.util.ArrayList;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.entity.Blog;
import com.example.administrator.myapplication.icompus.utils.ImgUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BlogListAdapter extends BaseAdapter {
	public static final String TAG = BlogListAdapter.class.getSimpleName();
	
	private ImageLoader mImageLoader;
	private ArrayList<Blog> mBloglist;
	private LayoutInflater mInflater;
	private Context mContext;

	public BlogListAdapter(Context context, ArrayList<Blog> list) {
		mContext = context;
		mBloglist = list;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		mImageLoader = ImageLoader.getInstance();
		mImageLoader.init(ImgUtil.getImageConfig(context));
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_blog, null);
		}
		ImageView imgAvatar = (ImageView)convertView.findViewById(R.id.img_icon);
		TextView txtTitle = (TextView)convertView.findViewById(R.id.txt_title);
		TextView txtContent = (TextView)convertView.findViewById(R.id.txt_content);
		TextView txtTime = (TextView)convertView.findViewById(R.id.txt_time);
		TextView txtRecommend = (TextView)convertView.findViewById(R.id.txt_recommend);
		TextView txtComment = (TextView)convertView.findViewById(R.id.txt_comment);
		TextView txtLook = (TextView)convertView.findViewById(R.id.txt_look);
		
		
		Blog blog = mBloglist.get(position);
		if (blog == null) {
			return convertView;
		}
		txtTitle.setText(blog.GetBlogTitle());
		txtContent.setText(blog.GetSummary());
		txtTime.setText("发表：");
		txtRecommend.setText("推荐："+blog.GetDiggsNum());
		txtComment.setText("评论："+blog.GetCommentNum());
		txtLook.setText("浏览："+blog.GetViewNum());
		
		String strAvatar = blog.GetAvator();
		if (strAvatar.contains("?")) {
			strAvatar = strAvatar.substring(0, strAvatar.indexOf("?"));
		}
		mImageLoader.displayImage(strAvatar, imgAvatar,ImgUtil.getDisplayOptions());

		return convertView;
	}

	
	public ArrayList<Blog> GetData() {
		return mBloglist;
	}

	public void refreshDate(ArrayList<Blog> list) {
		mBloglist = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return mBloglist.size();
	}

	public Object getItem(int position) {
		return mBloglist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	public class ViewHolder {
		TextView text_title;
		TextView text_desc;
		ImageView imageIcon;
		TextView text_diggs;
		TextView text_view;
		TextView text_comments;
		TextView text_author;
		TextView text_date;
		TextView text_formatdate;
		TextView text_url;
		TextView text_domain;
		TextView text_blog_id;
		TextView text_user_name;
		ImageView icon_downloaded;
	}
}
