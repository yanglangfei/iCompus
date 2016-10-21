package com.example.administrator.myapplication.icompus.ui.life;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.db.TimeLogDao;
import com.example.administrator.myapplication.icompus.entity.TimeLog;
import com.example.administrator.myapplication.icompus.utils.ImgUtil;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class TimeLogEditActivity extends Activity implements OnClickListener{
	public static final String TAG = TimeLogEditActivity.class.getSimpleName();
	public static final int REQ_CODE_ALBUM = 1;
	// UI views.
	private EditText mEditorLog;
	private ImageView mImg1;
	private ImageView mImg2;
	private ImageView mImg3;
	private int mCurImg = 0;
	
	private String[] mImagePaths = {"","",""};
	private TimeLogDao mLogDao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_edit);
		mLogDao = new TimeLogDao(this);
		initViews();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * 初始化UI各个控件。
	 */
	private void initViews(){
		TextView txtHead = (TextView)findViewById(R.id.txt_head);
		txtHead.setText("心情");
		ImageView imgView = (ImageView)findViewById(R.id.img_menu);
		imgView.setImageResource(R.drawable.ic_ok);
		imgView.setVisibility(View.VISIBLE);
		imgView.setOnClickListener(this);
		mEditorLog = (EditText)findViewById(R.id.editor_log);
		mImg1 = (ImageView)findViewById(R.id.img_1);
		mImg2 = (ImageView)findViewById(R.id.img_2);
		mImg3 = (ImageView)findViewById(R.id.img_3);
		mImg1.setOnClickListener(this);
		mImg2.setOnClickListener(this);
		mImg3.setOnClickListener(this);
	}
	
	public boolean checkLog(){
		String text = mEditorLog.getText().toString().trim();
		if (!TextUtils.isEmpty(text)) {
			return true;
		}
		
		for (String path : mImagePaths) {
			if (!TextUtils.isEmpty(path)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void saveLog(){
		TimeLog logEntity = new TimeLog();
		String text = mEditorLog.getText().toString().trim();
		long date = System.currentTimeMillis();
		logEntity.setText(text);
		logEntity.setDate(date);
		logEntity.setPaths(mImagePaths);
		mLogDao.add(logEntity);
	}
	
	/**
	 * 打开相册
	 */
	public void openAlbum() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		startActivityForResult(intent, REQ_CODE_ALBUM);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_menu:
			if (checkLog()) {
				saveLog();
				TimeLogEditActivity.this.finish();
			}
			return;
		case R.id.img_1:
			mCurImg = 1;
			break;
		case R.id.img_2:
			mCurImg = 2;
			break;
		case R.id.img_3:
			mCurImg = 3;
			break;
		default:
			mCurImg = 0;
			break;
		}
		openAlbum();
	}
	
	public void saveImagePath(String path){
		mImagePaths[mCurImg-1] = path;
	}
	
	public void chooseImageView(Bitmap bm){
		switch (mCurImg) {
		case 1:
			mImg1.setImageBitmap(bm);
			break;
		case 2:
			mImg2.setImageBitmap(bm);
			break;
		case 3:
			mImg3.setImageBitmap(bm);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQ_CODE_ALBUM:
			// 相册图片
			Uri uri = data.getData();
			String path = ImgUtil.getAlbumImagePath(this, uri);
			Bitmap bm = ImgUtil.getBitmap(path);
			if (bm == null) {
				return;
			}
			chooseImageView(bm);
			saveImagePath(path);
			break;
		default:
			break;
		}
	}


}
