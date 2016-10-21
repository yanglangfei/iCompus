package com.example.administrator.myapplication.icompus.ui.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.icompus.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Myadapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public Myadapter(List<String> list, HomeWork context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book, null);

            TextView tv = (TextView) convertView.findViewById(R.id.tv_item);
            tv.setText(list.get(position));
        }


        return convertView;
    }
}
