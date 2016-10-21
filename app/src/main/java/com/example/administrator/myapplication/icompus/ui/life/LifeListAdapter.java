package com.example.administrator.myapplication.icompus.ui.life;

import java.util.ArrayList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.icompus.R;

public class LifeListAdapter extends BaseAdapter{
	public static final String TAG = LifeListAdapter.class.getSimpleName();
	
	private LayoutInflater mInflater;
	private ArrayList<LifeItem> mItemList;
	
	public LifeListAdapter(LayoutInflater inflater,ArrayList<LifeItem> itemList){
		mInflater = inflater;
		mItemList = itemList;
	}
	
	@Override
	public int getCount() {
		if (mItemList != null) {
			return mItemList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mItemList != null) {
			return mItemList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// View
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_life, null);
		}
		ImageView imgView = (ImageView)convertView.findViewById(R.id.img_icon);
		TextView txtTitle = (TextView)convertView.findViewById(R.id.txt_title);
		TextView txtContent = (TextView)convertView.findViewById(R.id.txt_content);
		
		// Data
		LifeItem item = mItemList.get(position);
		imgView.setImageResource(item.getImageId());
		txtTitle.setText(item.getTitle());
		txtContent.setText(item.getContent());
		
		
		// TODO Auto-generated method stub
		return convertView;
	}

}
