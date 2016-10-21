package com.example.administrator.myapplication.icompus.ui.life;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.db.TimeLogDao;
import com.example.administrator.myapplication.icompus.entity.TimeLog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TimeLogActivity extends Activity implements OnItemClickListener,OnItemLongClickListener{
	public static final String TAG = TimeLogActivity.class.getSimpleName();
	
	// UI views.
	private ListView mListView;

	private ArrayList<TimeLog> mLogList;
	private TimeLogAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		initViews();

	}

	@Override
	protected void onResume() {
		super.onResume();
		initDate();
		mAdapter = new TimeLogAdapter(this, mLogList);
		mListView.setAdapter(mAdapter);
	}

	/**
	 * 初始化UI各个控件。
	 */
	private void initViews(){
		TextView txtHead = (TextView)findViewById(R.id.txt_head);
		txtHead.setText("时光轴");
		ImageView imgNew = (ImageView)findViewById(R.id.img_menu);
		imgNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TimeLogActivity.this, TimeLogEditActivity.class);
				TimeLogActivity.this.startActivity(intent);
			}
		});
		mListView = (ListView)findViewById(R.id.list_note);
//		mListView.setOnItemClickListener(this);
//		mListView.setOnItemLongClickListener(this);
//		ImageView imgNew = (ImageView)findViewById(R.id.img_menu);
//		imgNew.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
				Intent intent = new Intent();
//				intent.setClass(TimeLogActivity.this, NoteEditActivity.class);
//				TimeLogActivity.this.startActivity(intent);
//			}
//		});
		
	}
	
	public void initDate(){
		mLogList = new TimeLogDao(this).queryAll();
		
	}

	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		File f = mBookFiles[position];
//		String name = f.getName();
		
//		Intent intent = new Intent();
//		intent.setAction("android.intent.action.VIEW");
//		intent.addCategory("android.intent.category.DEFAULT"); 
//		Uri uri = Uri.fromFile(f); 
//		if (name.endsWith(".txt")) {
//			intent.setDataAndType(uri, "text/plain"); 
//		}else if (name.endsWith(".pdf")) {
//			intent.setDataAndType(uri, "application/pdf"); 
//		}
//		TimeLogActivity.this.startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//		NoteEntity note = mNoteList.get(position);
//		int noteId = note.getId();
//		showMyDialog(noteId);
		return true;
	}
	
	public void showMyDialog(final int id){
		new AlertDialog.Builder(this)
		.setTitle("注意")
		.setMessage("确定删除本记事？")
		.setPositiveButton("是", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				mNoteDao.delete(id);
//				mNoteList = mNoteDao.queryAll();
//				mAdapter.refreshData(mNoteList);
			}
		})
		.setNegativeButton("否", null)
		.create().show();
		
	}


}
