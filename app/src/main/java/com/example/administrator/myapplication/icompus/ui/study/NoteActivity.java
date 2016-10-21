package com.example.administrator.myapplication.icompus.ui.study;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.db.NoteDao;
import com.example.administrator.myapplication.icompus.entity.Note;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * 欢迎界面。
 *
 * @author wanglu
 */
public class NoteActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {
    public static final String TAG = NoteActivity.class.getSimpleName();

    // UI views.
    private ListView mListView;

    private ArrayList<Note> mNoteList;
    private NoteDao mNoteDao;
    private NoteListAdapter mAdapter;

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
        mNoteList = mNoteDao.queryAll();
        mAdapter = new NoteListAdapter(this, mNoteList);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化UI各个控件。
     */
    private void initViews() {
        TextView txtHead = (TextView) findViewById(R.id.txt_head);
        txtHead.setText("记事");
        mListView = (ListView) findViewById(R.id.list_note);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        ImageView imgNew = (ImageView) findViewById(R.id.img_menu);
        imgNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NoteActivity.this, NoteEditActivity.class);
                NoteActivity.this.startActivity(intent);
            }
        });

    }

    public void initData() {
        mNoteDao = new NoteDao(this);
//		mNoteList = mNoteDao.queryAll();
    }


    /**
     * 打开主页。
     */
    private void startEditPage() {
        Intent intent = new Intent();
        intent.setClass(this, NoteEditActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = mNoteList.get(position);

        Intent intent = new Intent();
        intent.setClass(NoteActivity.this, NoteEditActivity.class);
        intent.putExtra(NoteEditActivity.KEY_NOTE_ID, note.getId());
        NoteActivity.this.startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = mNoteList.get(position);
        int noteId = note.getId();
        showMyDialog(noteId);
        return true;
    }

    public void showMyDialog(final int id) {
        new AlertDialog.Builder(this)
                .setTitle("注意")
                .setMessage("确定删除本记事？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mNoteDao.delete(id);
                        mNoteList = mNoteDao.queryAll();
                        mAdapter.refreshData(mNoteList);
                    }
                })
                .setNegativeButton("否", null)
                .create().show();

    }


}
