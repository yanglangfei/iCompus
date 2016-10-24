package com.example.administrator.myapplication.icompus.ui.study;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.entity.MyClass;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Myadapter extends BaseAdapter {
    private List<MyClass> list;
    private Context context;

    public Myadapter(List<MyClass> list, HomeWork context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<MyClass> list) {
        this.list = list;
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

           TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_tName= (TextView) convertView.findViewById(R.id.tv_tName);
            TextView tv_start= (TextView) convertView.findViewById(R.id.tv_start);
            TextView tv_end= (TextView) convertView.findViewById(R.id.tv_end);
            tv_name.setText(Html.fromHtml("<font color='black'>课程名称:</font>&nbsp;&nbsp;"+list.get(position).getName()));
            tv_tName.setText(Html.fromHtml("<font color='black'>授课讲师:</font>&nbsp;&nbsp;"+list.get(position).getTeacherName()));
            tv_start.setText(Html.fromHtml("<font color='black'>开始时间:</font>&nbsp;&nbsp;"+list.get(position).getStartDate()));
            tv_end.setText(Html.fromHtml("<font color='black'>结束时间:</font>&nbsp;&nbsp;"+list.get(position).getEndDate()));
        }
        return convertView;
    }
}
