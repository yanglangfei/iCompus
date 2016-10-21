package com.example.administrator.myapplication.icompus.ui.study;


import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.db.NoteDao;
import com.example.administrator.myapplication.icompus.entity.Note;

import android.os.Bundle;
import android.text.TextUtils;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class NoteEditActivity extends Activity {
    public static final String TAG = NoteEditActivity.class.getSimpleName();
    public static final String KEY_NOTE_ID = "key_id";
    //UI views
    private TextView mTxtHead;
    private EditText mEditorTitle;
    private EditText mEditorContent;

    private int mId = -1;
    private Note mNote;
    private NoteDao mNoteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        initViews();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String title = mEditorTitle.getText().toString().trim();
        String content = mEditorContent.getText().toString().trim();
        long time = System.currentTimeMillis();
        mNote = new Note();
        mNote.setTitle(title);
        mNote.setContent(content);
        mNote.setDate(time);
        if (TextUtils.isEmpty(title)) {
            return;
        }
        if (mId != -1) {
            mNoteDao.update(mId, mNote);
            return;
        }
        mNoteDao.add(mNote);
    }

    private void initData() {
        mNoteDao = new NoteDao(this);
        mId = getIntent().getIntExtra(KEY_NOTE_ID, -1);
        if (mId >= 0) {
            mNote = mNoteDao.query(mId);
        }
        if (mNote != null) {
            String title = mNote.getTitle();
            String content = mNote.getContent();
            mEditorTitle.setText(title);
            mEditorContent.setText(content);
        }
    }

    /**
     * 初始化UI各个控件。
     */
    private void initViews() {
        mTxtHead = (TextView) findViewById(R.id.txt_head);
        mTxtHead.setText("新建");
        ImageView imgView = (ImageView) findViewById(R.id.img_menu);
        imgView.setImageResource(R.drawable.ic_ok);
        imgView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                NoteEditActivity.this.finish();
            }
        });
//		imgView.setOnClickListener(l)
        mEditorTitle = (EditText) findViewById(R.id.editor_title);
        mEditorContent = (EditText) findViewById(R.id.editor_content);

    }

    public Note getNote() {
        String title = mEditorTitle.getText().toString().trim();
        String content = mEditorContent.getText().toString().trim();
        long time = System.currentTimeMillis();
        mNote = new Note();
        mNote.setTitle(title);
        mNote.setContent(content);
        mNote.setDate(time);
        return mNote;
    }


}
