package com.example.administrator.myapplication.icompus.ui.study;

import com.example.administrator.myapplication.icompus.R;

import java.util.ArrayList;


public class StudyItemList {
    public static final String TAG = StudyItemList.class.getSimpleName();

    private int[] mImages = {R.drawable.ic_note, R.drawable.ic_book, R.drawable.ic_course,
            R.drawable.ic_exam, R.drawable.ic_question};
    private String[] mNames = {"记事", "书籍", "课程", "习题", "问答"};

    private ArrayList<StudyItem> mItemList = new ArrayList<StudyItem>();
    private static StudyItemList mInstance = null;


    public static StudyItemList getInstance() {
        if (mInstance == null) {
            mInstance = new StudyItemList();
        }
        return mInstance;
    }

    public StudyItemList() {
        initData();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            StudyItem item = new StudyItem();
            item.setImageId(mImages[i]);
            item.setName(mNames[i]);
            mItemList.add(item);
        }
    }

    public ArrayList<StudyItem> getList() {
        return mItemList;
    }

}
