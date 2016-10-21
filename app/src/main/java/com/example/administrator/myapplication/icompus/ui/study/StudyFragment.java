package com.example.administrator.myapplication.icompus.ui.study;

import java.util.ArrayList;


import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.icompus.R;

public class StudyFragment extends Fragment implements OnItemClickListener {
    public static final String TAG = StudyFragment.class.getSimpleName();

    public static final int ITEM_NOTE = 0;
    public static final int ITEM_BOOK = 1;
    public static final int ITEM_COURSE = 2;
    public static final int ITEM_HOMEWORK = 3;
    public static final int ITEM_ASK = 4;

    private ListView mListView;
    private StudyListAdapter mAdapter;
    private ArrayList<StudyItem> mItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_study, container, false);
        mListView = (ListView) view.findViewById(R.id.list_study);
        mItemList = StudyItemList.getInstance().getList();
        mAdapter = new StudyListAdapter(inflater, mItemList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case ITEM_NOTE:
                intent.setClass(getActivity(), NoteActivity.class);
                startActivity(intent);
                break;
            case ITEM_BOOK:
                intent.setClass(getActivity(), BookActivity.class);
                startActivity(intent);
                break;
            case ITEM_COURSE:
                intent.setClass(getActivity(), HomeWork.class);
                startActivity(intent);
                break;
            case ITEM_HOMEWORK:
                Toast.makeText(getActivity(), "还未开通此功能，敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case ITEM_ASK:
                Toast.makeText(getActivity(), "还未开通此功能，敬请期待", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

}
