package com.example.administrator.myapplication.icompus.ui;
import java.util.ArrayList;
import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.ui.life.LifeFragment;
import com.example.administrator.myapplication.icompus.ui.study.StudyFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends FragmentActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int PAGE_STUDY = 0;
    public static final int PAGE_LIFE = 1;

    private RadioButton mRadioStudy;
    private RadioButton mRadioLife;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFragments() {
        StudyFragment studyFragment = new StudyFragment();
        LifeFragment lifeFragment = new LifeFragment();
        mFragmentList.add(studyFragment);
        mFragmentList.add(lifeFragment);
    }

    private void initViews() {
        mRadioStudy = (RadioButton) findViewById(R.id.radio_study);
        mRadioLife = (RadioButton) findViewById(R.id.radio_life);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_tab);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int item = 0;
                switch (checkedId) {
                    case R.id.radio_study:
                        item = 0;
                        break;
                    case R.id.radio_life:
                        item = 1;
                        break;
                    default:
                        break;
                }
                mViewPager.setCurrentItem(item, true);
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentManager fm = this.getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(fm, mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new PageChangeListener());
    }

    private class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case PAGE_STUDY:
                    mRadioStudy.setChecked(true);
                    break;
                case PAGE_LIFE:
                    mRadioLife.setChecked(true);
                    break;
                default:
                    mRadioStudy.setChecked(true);
                    break;
            }
        }
    }


}
