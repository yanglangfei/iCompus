package com.example.administrator.myapplication.icompus.ui.study;

import java.util.ArrayList;


import android.R.integer;
import android.R.raw;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.icompus.R;

public class StudyListAdapter extends BaseAdapter {
    public static final String TAG = StudyListAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private ArrayList<StudyItem> mItemList;

    public StudyListAdapter(LayoutInflater inflater, ArrayList<StudyItem> itemList) {
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
        // View.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_study, null);
        }
        ImageView ImgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView TxtName = (TextView) convertView.findViewById(R.id.txt_name);

        //Item data.
        StudyItem item = mItemList.get(position);

        // Set data on UI.
        ImgIcon.setImageResource(item.getImageId());
        TxtName.setText(item.getName());

        // TODO Auto-generated method stub
        return convertView;
    }

}
