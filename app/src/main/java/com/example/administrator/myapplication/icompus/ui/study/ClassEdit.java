package com.example.administrator.myapplication.icompus.ui.study;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.icompus.R;
import com.example.administrator.myapplication.icompus.db.ClassDao;
import com.example.administrator.myapplication.icompus.entity.MyClass;

/**
 * Created by Administrator on 2016/10/24.
 */

public class ClassEdit extends Activity implements View.OnClickListener {

    private TextView mTxtHead;
    private EditText className;
    private EditText tName;
    private EditText start;
    private EditText end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_class_edit);
        initView();
    }

    private void initView() {
        mTxtHead = (TextView) findViewById(R.id.txt_head);
        mTxtHead.setText("新建");
        ImageView imgView = (ImageView) findViewById(R.id.img_menu);
        imgView.setImageResource(R.drawable.ic_ok);
        className= (EditText) findViewById(R.id.className);
        tName= (EditText) findViewById(R.id.tName);
        start= (EditText) findViewById(R.id.start);
        end= (EditText) findViewById(R.id.end);
        imgView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String classN = className.getText().toString();
        String teacherName = tName.getText().toString();
        String startStr = start.getText().toString();
        String endStr = end.getText().toString();
        ClassDao dao=new ClassDao(this);
        long res = dao.add(new MyClass(classN, teacherName, startStr, endStr, false));
        if(res>0){
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            this.finish();
        }else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }

    }
}
