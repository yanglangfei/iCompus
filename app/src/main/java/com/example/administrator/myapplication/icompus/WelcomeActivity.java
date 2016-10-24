package com.example.administrator.myapplication.icompus;

import java.util.Timer;
import java.util.TimerTask;

import com.example.administrator.myapplication.icompus.db.MyDB;
import com.example.administrator.myapplication.icompus.ui.MainActivity;
import com.example.administrator.myapplication.icompus.utils.FileUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 欢迎界面。
 * 
 * 
 * @author wanglu
 */
public class WelcomeActivity extends Activity {
	public static final String TAG = WelcomeActivity.class.getSimpleName();
	
	// UI views.
	private TextView mTxtName;
	private ImageView mImgLogo;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		initViews();
		FileUtil.initDir();
		
		MyTimerTask task = new MyTimerTask();
		Timer timer = new Timer();
		timer.schedule(task, 3500);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		startAnim();
	}

	/**
	 * 初始化UI各个控件。
	 */
	private void initViews(){
		mTxtName = (TextView)findViewById(R.id.txt_name);
		mImgLogo = (ImageView)findViewById(R.id.img_logo);
	}
	
	/**
	 * 开始动画。
	 */
	private void startAnim(){
		// 文字渐变
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f); 
		alphaAnimation.setDuration(3000);
		
		// Logo移动
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_SELF, 0);
		
		translateAnimation.setDuration(3000);
		translateAnimation.setInterpolator(new BounceInterpolator());
		
		// 动画设置到控件中
		mTxtName.setAnimation(alphaAnimation);
		mImgLogo.setAnimation(translateAnimation);
	}
	
	/**
	 * 打开主页。
	 */
	private void startMainPage(){
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		this.startActivity(intent);
	}

	/**
	 * 定时的任务。
	 * 
	 * @author wanglu
	 */
	private class MyTimerTask extends TimerTask{

		@Override
		public void run() {
			startMainPage();
			WelcomeActivity.this.finish();
		}
	}

}
