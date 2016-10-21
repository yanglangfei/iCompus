package com.example.administrator.myapplication.icompus.ui.study;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.utils.FileUtil;

import android.R.integer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class BookActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {
    public static final String TAG = BookActivity.class.getSimpleName();
    private ListView mListView;
    private File[] mBookFiles;
    private BookListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initData();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new BookListAdapter(this, mBookFiles);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化UI各个控件。
     */
    private void initViews() {
        TextView txtHead = (TextView) findViewById(R.id.txt_head);
        txtHead.setText("书籍");
        mListView = (ListView) findViewById(R.id.list_note);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        ImageView imgNew = (ImageView) findViewById(R.id.img_menu);
        imgNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BookActivity.this, NoteEditActivity.class);
                BookActivity.this.startActivity(intent);
            }
        });

    }

    public void initData() {
        mBookFiles = FileUtil.getBooks();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File f = mBookFiles[position];
        String name = f.getName();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        Uri uri = Uri.fromFile(f);
        if (name.endsWith(".txt")) {
            intent.setDataAndType(uri, "text/plain");
        } else if (name.endsWith(".pdf")) {
            intent.setDataAndType(uri, "application/pdf");
        }
        BookActivity.this.startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//		NoteEntity note = mNoteList.get(position);
//		int noteId = note.getId();
//		showMyDialog(noteId);
        return true;
    }



}
