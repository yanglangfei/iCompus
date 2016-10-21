package com.example.administrator.myapplication.icompus.ui.life;

import java.io.File;
import java.util.ArrayList;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.entity.Note;
import com.example.administrator.myapplication.icompus.entity.TimeLog;
import com.example.administrator.myapplication.icompus.utils.DateUtil;
import com.example.administrator.myapplication.icompus.utils.ImgUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeLogAdapter extends BaseAdapter {
    public static final String TAG = TimeLogAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private ArrayList<TimeLog> mLogList;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public TimeLogAdapter(Context context, ArrayList<TimeLog> list) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLogList = list;
        mImageLoader.init(ImgUtil.getImageConfig(context));
    }

    @Override
    public int getCount() {
        if (mLogList != null) {
            return mLogList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mLogList != null) {
            return mLogList.get(position);
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
            convertView = mInflater.inflate(R.layout.list_item_time_log, null);
        }
        TextView txtDate = (TextView) convertView.findViewById(R.id.txt_date);
        TextView txtLog = (TextView) convertView.findViewById(R.id.txt_log);
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.lay_images);

        //data.
        TimeLog logEntity = mLogList.get(position);
        long date = logEntity.getDate();
        String text = logEntity.getText();

        txtDate.setText(DateUtil.getDate(date));
        txtLog.setText(text);

        String[] paths = logEntity.getPaths();
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            if (!TextUtils.isEmpty(path)) {
//				Bitmap bm = ImgUtil.getBitmap(path);
                String uri = Uri.fromFile(new File(path)).toString();
//				if (bm != null) {
                ImageView imageView = (ImageView) layout.getChildAt(i);
                imageView.setVisibility(View.VISIBLE);
//					imageView.setImageBitmap(bm);
                mImageLoader.displayImage(uri, imageView, ImgUtil.getDisplayOptions());
//				}
            }
        }
        return convertView;
    }

    public void refreshData(ArrayList<Note> noteList) {
        // TODO
        notifyDataSetChanged();
    }

}
