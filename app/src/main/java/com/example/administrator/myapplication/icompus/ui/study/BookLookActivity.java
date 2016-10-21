package com.example.administrator.myapplication.icompus.ui.study;


import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.utils.FileUtil;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;


public class BookLookActivity extends Activity {
    public static final String TAG = BookLookActivity.class.getSimpleName();
    public static final String KEY_PATH = "key_path";

    //UI views
    private TextView mTxtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_look);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    /**
     * 初始化UI各个控件。
     */
    private void initViews() {
        mTxtContent = (TextView) findViewById(R.id.txt_content);
        String path = getIntent().getStringExtra(KEY_PATH);
        String content = FileUtil.readfile(path);
        mTxtContent.setText(content);
    }


}
