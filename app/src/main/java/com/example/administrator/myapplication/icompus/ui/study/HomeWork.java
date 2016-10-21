package com.example.administrator.myapplication.icompus.ui.study;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.administrator.myapplication.icompus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class HomeWork extends Activity {
    private ImageButton btn_finish;
    private ListView list_work;
    private Myadapter adapter;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework);
        init();
    }

    private void init() {
        btn_finish= (ImageButton) findViewById(R.id.btn_finish);
        list_work= (ListView) findViewById(R.id.list_work);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeWork.this.finish();
            }
        });

        AddDate();
        adapter=new Myadapter(list,this);
        list_work.setAdapter(adapter);

    }

    private void AddDate() {
        list.add("大学英语");
        list.add("信号与系统");
        list.add("电磁场与电磁波");
        list.add("数字电子技术");
        list.add("毛泽东思想和特色社会主义");
        list.add("旅游文化");
        list.add("演讲与口才");
        list.add("电子电路制作");
        list.add("微机原理与接口技术");
        list.add("大学英语");
        list.add("高等数学");

    }
}
