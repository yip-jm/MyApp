package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//+++++++++++++++待补充
//+++++++++++++++头像 修改需要获取手机相册+++++++++++++++++++
//+++++++++++++++信息修改需要更新数据库

public class ActivityF3Myinfo extends AppCompatActivity {

    private RelativeLayout myinfo_gender, myinfo_birth, myinfo_nickname;

    private TextView myinfo_birth_yes;

    private OnClickListener listener; //监听







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3myinfo);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);


        myinfo_gender = (RelativeLayout) findViewById(R.id.myinfo_gender);
        myinfo_birth = (RelativeLayout) findViewById(R.id.myinfo_birth);
        myinfo_nickname = (RelativeLayout)findViewById(R.id.myinfo_nickname) ;
        myinfo_birth_yes = (TextView) findViewById(R.id.myinfo_birth_yes);

        listener = new OnClickListener();
        myinfo_nickname.setOnClickListener(listener);
        myinfo_gender.setOnClickListener(listener);
        myinfo_birth.setOnClickListener(listener);

    }

    private class  OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myinfo_nickname:
                    Intent setnickname = new Intent(ActivityF3Myinfo.this, ActivityF3MyinfoSetnickname.class);
                    startActivity(setnickname);
                    break;

                case R.id.myinfo_birth:
                    DatePickerDialog pickerDialog = new DatePickerDialog(ActivityF3Myinfo.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String date = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                                    myinfo_birth_yes.setText(date);
                                }
                            }, 2022, 01, 01);
                    Date date =  new Date();
                    long today = date.getTime();
                    pickerDialog.getDatePicker().setMaxDate(today);
                    pickerDialog.show();
                    break;
            }
        }
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onPause();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}