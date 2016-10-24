package com.example.administrator.myapplication.icompus.ui.study;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.db.ClassDao;
import com.example.administrator.myapplication.icompus.entity.MyClass;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/10/18.
 */
public class HomeWork extends Activity implements View.OnClickListener {
    private ImageButton btn_finish;
    private ListView list_work;
    private Myadapter adapter;
    private ImageView addClass;
    private List<MyClass> list=new ArrayList<>();
    private ClassDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework);
        initData();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list=dao.queryAll();
        adapter = new Myadapter(list,this);
        list_work.setAdapter(adapter);
    }

    private void initData() {
         dao=new ClassDao(this);
    }

    private void init() {
        btn_finish= (ImageButton) findViewById(R.id.btn_finish);
        list_work= (ListView) findViewById(R.id.list_work);
        addClass= (ImageView) findViewById(R.id.addClass);
        addClass.setOnClickListener(this);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeWork.this.finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,ClassEdit.class));
    }
}
