package com.example.administrator.myapplication.icompus.ui.life;

import com.example.administrator.myapplication.icompus.R;

import java.util.ArrayList;


public class LifeItemList {
    public static final String TAG = LifeItemList.class.getSimpleName();

    private int[] mImages = {R.drawable.ic_step, R.drawable.ic_news, R.drawable.ic_office};
    private String[] mTitles = {"时光轴", "文具"};
    private String[] mContents = {"一点一滴，记录美好时光", "创意,个性，珍藏"};

    private ArrayList<LifeItem> mItemList = new ArrayList<LifeItem>();
    private static LifeItemList mInstance = null;


    public static LifeItemList getInstance() {
        if (mInstance == null) {
            mInstance = new LifeItemList();
        }
        return mInstance;
    }

    public LifeItemList() {
        initData();
    }

    private void initData() {
        for (int i = 0; i < 2; i++) {
            LifeItem item = new LifeItem();
            item.setImageId(mImages[i]);
            item.setTitle(mTitles[i]);
            item.setContent(mContents[i]);
            mItemList.add(item);
        }
    }

    public ArrayList<LifeItem> getList() {
        return mItemList;
    }

}
