package com.example.administrator.myapplication.icompus.ui.life;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.administrator.myapplication.icompus.R;

public class LifeFragment extends Fragment implements OnItemClickListener {
    public static final String TAG = LifeFragment.class.getSimpleName();
    public static final int ITEM_TIME = 0;
    public static final int ITEM_BLOG = 1;
    public static final int ITEM_NEWS = 2;
    private ListView mListView;
    private LifeListAdapter mAdapter;
    private ArrayList<LifeItem> mItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_life, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        mItemList = LifeItemList.getInstance().getList();
        mAdapter = new LifeListAdapter(inflater, mItemList);

        return view;
    }

    @Override
    public void onResume() {
        mListView.setAdapter(mAdapter);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), TimeLogActivity.class);

        switch (position) {
            case ITEM_TIME:
                intent.setClass(getActivity(), TimeLogActivity.class);
                startActivity(intent);
                break;
            case ITEM_BLOG:
                intent.setClass(getActivity(), BlogActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
