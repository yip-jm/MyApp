package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ActivityF3Myinfo extends AppCompatActivity {

    private RelativeLayout myinfo_gender, myinfo_birth, myinfo_hand, myinfo_es, myinfo_eq, myinfo_tel;

    private TextView myinfo_name, myinfo_acc, myinfo_gendertv, myinfo_birthtv, myinfo_handtv,
                        myinfo_estv, myinfo_eqtv, myinfo_teltv;

    private OnClickListener listener; //监听

    String acc, name, birth, tel;
    int gender, hand, es, eq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3myinfo);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);

        acc = getIntent().getStringExtra("ACC");

        new Thread(){
            @Override
            public void run(){
                UserDao userDao = new UserDao();
                User uu = userDao.findUser(acc);
                name = uu.getName();
                birth = uu.getBirth();
                tel = uu.getTel();
                gender = uu.getGender();
                hand = uu.getHand();
                es = uu.getES();
                eq = uu.getEQ();

                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("ACC", acc);
                bundle.putString("NAME", name);
                bundle.putInt("GENDER", gender);
                bundle.putString("BIRTH", birth);
                bundle.putInt("ES", es);
                bundle.putInt("EQ", eq);
                bundle.putString("TEL", tel);
                msg.setData(bundle);
                msg.what = 1;
                handler_info.sendMessage(msg);

            }
        }.start();

        myinfo_name = (TextView) findViewById(R.id.myinfo_name);
        myinfo_acc = (TextView) findViewById(R.id.myinfo_acc);
        myinfo_gendertv = (TextView) findViewById(R.id.myinfo_gendertv);
        myinfo_birthtv = (TextView) findViewById(R.id.myinfo_birthtv);
        myinfo_handtv = (TextView) findViewById(R.id.myinfo_handtv);
        myinfo_estv = (TextView) findViewById(R.id.myinfo_estv);
        myinfo_eqtv = (TextView) findViewById(R.id.myinfo_eqtv);
        myinfo_teltv = (TextView) findViewById(R.id.myinfo_teltv);

        myinfo_gender = (RelativeLayout) findViewById(R.id.myinfo_gender);
        myinfo_birth = (RelativeLayout) findViewById(R.id.myinfo_birth);

        listener = new OnClickListener();
        myinfo_gender.setOnClickListener(listener);
        myinfo_birth.setOnClickListener(listener);

    }

    private class  OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myinfo_birth:
                    DatePickerDialog pickerDialog = new DatePickerDialog(ActivityF3Myinfo.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String date = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                                    myinfo_birthtv.setText(date);
                                    String newbirth = myinfo_birthtv.getText().toString();
                                    new Thread(){
                                        @Override
                                        public void run(){
                                            UserDao userDao = new UserDao();
                                            boolean userupdate = userDao.updateUser(acc, 4, newbirth);
                                            Message msgbirth = new Message();
                                            if(userupdate)  msgbirth.what = 1;
                                            else  msgbirth.what = 2;
                                            handler_newinfo.sendMessage(msgbirth);
                                        }
                                    }.start();

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

    @SuppressLint("HandlerLeak")
    final Handler handler_info = new Handler(){
        @Override
        public  void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    String tname = bundle.getString("NAME");
                    String tbirth = bundle.getString("BIRTH");
                    String ttel = bundle.getString("TEL");
                    int tgender = bundle.getInt("GENDER");
                    int thand = bundle.getInt("HAND");
                    int tes = bundle.getInt("ES");
                    int teq = bundle.getInt("EQ");

                    myinfo_name.setText(tname);
                    myinfo_acc.setText(acc);
                    myinfo_birthtv.setText(tbirth);
                    if(tgender == 1)     myinfo_gendertv.setText("男");
                    else    myinfo_gendertv.setText("女");
                    if(thand == 1)   myinfo_handtv.setText("右");
                    else    myinfo_handtv.setText("左");
                    if(tes == 1)     myinfo_estv.setText("视力度数正常");
                    else if(tes == 2)    myinfo_estv.setText("矫正（戴眼镜）后正常");
                    else if(tes == 3)    myinfo_estv.setText("近视");
                    else    myinfo_estv.setText("远视");
                    if(teq == 1)     myinfo_eqtv.setText("视力屈光正常");
                    else if(teq == 2)    myinfo_eqtv.setText("散光");
                    else if(teq == 3)    myinfo_eqtv.setText("色盲/色弱");
                    else    myinfo_eqtv.setText("其他视力问题");
                    myinfo_teltv.setText(ttel);

                    break;

                default:
                    break;
            }
        }
    };


    @SuppressLint("HandlerLeak")
    final Handler handler_newinfo = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplicationContext(), "出生日期更新成功", Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    Toast.makeText(getApplicationContext(), "出生日期更新失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


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