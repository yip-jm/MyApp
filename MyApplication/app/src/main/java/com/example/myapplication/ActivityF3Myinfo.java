package com.example.myapplication;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.User;


import java.util.Date;


public class ActivityF3Myinfo extends AppCompatActivity {

    private RelativeLayout myinfo_gender, myinfo_birth, myinfo_hand, myinfo_es, myinfo_eq, myinfo_tel;

    private TextView myinfo_name, myinfo_acc, myinfo_gendertv, myinfo_birthtv, myinfo_handtv,
                        myinfo_estv, myinfo_eqtv, myinfo_teltv;

    private OnClickListener listener; //监听


    private String acc, name, birth, tel;


    int gender, hand, es, eq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3myinfo);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);

        acc = getIntent().getStringExtra("ACC");

        new Thread(() -> {
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

        }).start();

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
        myinfo_hand = (RelativeLayout) findViewById(R.id.myinfo_hand);
        myinfo_es = (RelativeLayout) findViewById(R.id.myinfo_es);
        myinfo_eq = (RelativeLayout) findViewById(R.id.myinfo_eq);
        myinfo_tel = (RelativeLayout) findViewById(R.id.myinfo_tel);

        listener = new OnClickListener();
        myinfo_gender.setOnClickListener(listener);
        myinfo_birth.setOnClickListener(listener);
        myinfo_hand.setOnClickListener(listener);
        myinfo_es.setOnClickListener(listener);
        myinfo_eq.setOnClickListener(listener);
        myinfo_tel.setOnClickListener(listener);



    }

    private class  OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myinfo_birth:
                    DatePickerDialog pickerDialog = new DatePickerDialog(ActivityF3Myinfo.this,
                            (view, year, month, dayOfMonth) -> {
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

                            }, 2022, 01, 01);
                    Date date =  new Date();
                    long today = date.getTime();
                    pickerDialog.getDatePicker().setMaxDate(today);
                    pickerDialog.show();

                    break;

                case R.id.myinfo_gender:
                    final String[] chooose_gender = new String[]{"男", "女"};
                    AlertDialog alertDialog_gender = new AlertDialog.Builder(ActivityF3Myinfo.this)
                            .setTitle("请选择性别")
                            .setSingleChoiceItems(chooose_gender, gender, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    gender = which;
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if (gender == 0)  myinfo_gendertv.setText("男");
                                    else    myinfo_gendertv.setText("女");
                                    new Thread(){
                                        @Override
                                        public void run(){
                                            UserDao userDao = new UserDao();
                                            boolean userupdate = userDao.updateUser(acc, 5, Integer.toString(gender));
                                            Message msggender = new Message();
                                            if(userupdate)  msggender.what = 3;
                                            else  msggender.what = 4;
                                            handler_newinfo.sendMessage(msggender);
                                        }
                                    }.start();

                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();

                    alertDialog_gender.show();
                    break;

                case R.id.myinfo_hand:
                    final String[] choose_hand = new String[]{"右", "左"};
                    AlertDialog alertDialog_hand = new AlertDialog.Builder(ActivityF3Myinfo.this)
                            .setTitle("请选择习惯用手")
                            .setSingleChoiceItems(choose_hand, hand, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    hand = which;
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    System.out.println(hand);
                                    if (hand == 0)  myinfo_handtv.setText("右");
                                    else    myinfo_handtv.setText("左");
                                    new Thread(){
                                        @Override
                                        public void run(){
                                            UserDao userDao = new UserDao();
                                            boolean userupdate = userDao.updateUser(acc, 6, Integer.toString(hand));
                                            Message msghand = new Message();
                                            if(userupdate)  msghand.what = 5;
                                            else  msghand.what = 6;
                                            handler_newinfo.sendMessage(msghand);
                                        }
                                    }.start();

                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();

                    alertDialog_hand.show();
                    break;

                case R.id.myinfo_es:
                    final String[] choose_es = new String[]{"视力度数正常", "矫正（戴眼镜）后正常", "近视", "远视"};
                    AlertDialog alertDialog_es = new AlertDialog.Builder(ActivityF3Myinfo.this)
                            .setTitle("请选择视力情况")
                            .setSingleChoiceItems(choose_es, es, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    es = which;
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.out.println(es);
                                    if (es == 0)  myinfo_estv.setText("视力度数正常");
                                    else if(es == 1)   myinfo_estv.setText("矫正（戴眼镜）后正常");
                                    else if(es == 2)   myinfo_estv.setText("近视");
                                    else   myinfo_estv.setText("远视");
                                    new Thread(){
                                        @Override
                                        public void run(){
                                            UserDao userDao = new UserDao();
                                            boolean userupdate = userDao.updateUser(acc, 7, Integer.toString(es));
                                            Message msges = new Message();
                                            if(userupdate)  msges.what = 7;
                                            else  msges.what = 8;
                                            handler_newinfo.sendMessage(msges);
                                        }
                                    }.start();

                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();

                    alertDialog_es.show();
                    break;

                case R.id.myinfo_eq:
                    final String[] choose_eq = new String[]{"视力屈光正常", "散光", "色盲/色弱", "其他视力问题"};
                    AlertDialog alertDialog_eq = new AlertDialog.Builder(ActivityF3Myinfo.this)
                            .setTitle("请选择视觉质量")
                            .setSingleChoiceItems(choose_eq, eq, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    eq = which;
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.out.println(eq);
                                    if (eq == 0)  myinfo_eqtv.setText("视力屈光正常");
                                    else if(eq == 1)   myinfo_eqtv.setText("散光");
                                    else if(eq == 2)   myinfo_eqtv.setText("色盲/色弱");
                                    else   myinfo_eqtv.setText("其他视力问题");
                                    new Thread(){
                                        @Override
                                        public void run(){
                                            UserDao userDao = new UserDao();
                                            boolean userupdate = userDao.updateUser(acc, 8, Integer.toString(eq));
                                            Message msgeq = new Message();
                                            if(userupdate)  msgeq.what = 9;
                                            else  msgeq.what = 10;
                                            handler_newinfo.sendMessage(msgeq);
                                        }
                                    }.start();

                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();

                    alertDialog_eq.show();
                    break;

                case R.id.myinfo_tel:



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
                    if(tgender == 0)     myinfo_gendertv.setText("男");
                    else    myinfo_gendertv.setText("女");
                    if(thand == 0)   myinfo_handtv.setText("右");
                    else    myinfo_handtv.setText("左");
                    if(tes == 0)     myinfo_estv.setText("视力度数正常");
                    else if(tes == 1)    myinfo_estv.setText("矫正（戴眼镜）后正常");
                    else if(tes == 2)    myinfo_estv.setText("近视");
                    else    myinfo_estv.setText("远视");
                    if(teq == 0)     myinfo_eqtv.setText("视力屈光正常");
                    else if(teq == 1)    myinfo_eqtv.setText("散光");
                    else if(teq == 2)    myinfo_eqtv.setText("色盲/色弱");
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

                case 3:
                    Toast.makeText(getApplicationContext(), "性别更新成功", Toast.LENGTH_SHORT).show();
                    break;

                case 4:
                    Toast.makeText(getApplicationContext(), "性别更新失败", Toast.LENGTH_SHORT).show();
                    break;

                case 5:
                    Toast.makeText(getApplicationContext(), "习惯用手更新成功", Toast.LENGTH_SHORT).show();
                    break;

                case 6:
                    Toast.makeText(getApplicationContext(), "习惯用手更新失败", Toast.LENGTH_SHORT).show();
                    break;

                case 7:
                    Toast.makeText(getApplicationContext(), "视力情况更新成功", Toast.LENGTH_SHORT).show();
                    break;

                case 8:
                    Toast.makeText(getApplicationContext(), "视力情况更新失败", Toast.LENGTH_SHORT).show();
                    break;

                case 9:
                    Toast.makeText(getApplicationContext(), "视觉质量更新成功", Toast.LENGTH_SHORT).show();
                    break;

                case 10:
                    Toast.makeText(getApplicationContext(), "视觉质量更新失败", Toast.LENGTH_SHORT).show();
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