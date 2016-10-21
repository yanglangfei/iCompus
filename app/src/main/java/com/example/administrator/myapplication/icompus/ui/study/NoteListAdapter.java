package com.example.administrator.myapplication.icompus.ui.study;

import java.util.ArrayList;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.entity.Note;
import com.example.administrator.myapplication.icompus.utils.DateUtil;

import android.R.integer;
import android.R.raw;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteListAdapter extends BaseAdapter {
    public static final String TAG = NoteListAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private ArrayList<Note> mItemList;

    public NoteListAdapter(Context context, ArrayList<Note> itemList) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = mInflater.inflate(R.layout.list_item_note, null);
        }
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
        TextView txtContent = (TextView) convertView.findViewById(R.id.txt_content);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txt_date);
        TextView txtTime = (TextView) convertView.findViewById(R.id.txt_time);

        //data.
        Note note = mItemList.get(position);

        // Set data on View.
        txtTitle.setText(note.getTitle());
        txtContent.setText(note.getContent());
        txtDate.setText(DateUtil.getDate(note.getDate()));
        txtTime.setText(DateUtil.getTime(note.getDate()));

        return convertView;
    }

    public void refreshData(ArrayList<Note> noteList) {
        mItemList.clear();
        mItemList = noteList;
        notifyDataSetChanged();
    }

}
