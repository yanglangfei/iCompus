package com.example.administrator.myapplication.icompus.ui.study;

import java.io.File;
import java.util.ArrayList;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.entity.Note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookListAdapter extends BaseAdapter {
    public static final String TAG = BookListAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private File[] mBookFiles;

    public BookListAdapter(Context context, File[] books) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBookFiles = books;
    }

    @Override
    public int getCount() {
        if (mBookFiles != null) {
            return mBookFiles.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mBookFiles != null) {
            return mBookFiles[position];
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
        ImageView imgView = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView txtName = (TextView) convertView.findViewById(R.id.txt_name);

        //data.
        File book = mBookFiles[position];
        String bookName = book.getName();
        if (bookName.endsWith(".txt")) {
            imgView.setImageResource(R.drawable.ic_text);
        } else if (bookName.endsWith(".pdf")) {
            imgView.setImageResource(R.drawable.ic_pdf);
        }
        int end = bookName.indexOf('.');
        String name = bookName.substring(0, end);
        // Set data on View.
        txtName.setText(name);

        return convertView;
    }

    public void refreshData(ArrayList<Note> noteList) {
        // TODO
        notifyDataSetChanged();
    }

}
