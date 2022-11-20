package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.dao.CertDao;
import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.Cert;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityF3Mycert extends AppCompatActivity {

    private String acc;

    private EditText cert_unit, cert_name, cert_stuid, cert_tel, cert_mail;

    private Button certgo;

    private OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3mycert);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);


        acc = getIntent().getStringExtra("ACC");

        cert_unit = (EditText) findViewById(R.id.cert_unit);
        cert_unit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        cert_unit.setSingleLine(true);

        cert_name = (EditText) findViewById(R.id.cert_name);
        cert_name.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        cert_name.setSingleLine();

        cert_stuid = (EditText) findViewById(R.id.cert_stuid);
        cert_tel = (EditText) findViewById(R.id.cert_tel);
        cert_mail = (EditText) findViewById(R.id.cert_mail);

        certgo = (Button) findViewById(R.id.certgo);

        listener = new OnClickListener();
        certgo.setOnClickListener(listener);

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


    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.certgo:
                    String unit = cert_unit.getText().toString().trim();
                    String name = cert_name.getText().toString().trim();
                    String stuid = cert_stuid.getText().toString().trim();
                    String tel = cert_tel.getText().toString().trim();
                    String mail = cert_mail.getText().toString().trim();

                    if(unit.equals("")){
                        cert_unit.requestFocus();
                        Toast.makeText(getApplicationContext(), "注册单位不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else if(name.equals("")){
                        cert_name.requestFocus();
                        Toast.makeText(getApplicationContext(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else if(stuid.equals("")){
                        cert_stuid.requestFocus();
                        Toast.makeText(getApplicationContext(), "学号不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else if(tel.length() != 11 || !isTel(tel)){
                        cert_tel.requestFocus();
                        Toast.makeText(getApplicationContext(), "手机号码输入错误", Toast.LENGTH_SHORT).show();
                    }
                    else if(mail.equals("") || !isMail(mail)){
                        cert_mail.requestFocus();
                        Toast.makeText(getApplicationContext(), "邮箱地址输入错误", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Cert cert = new Cert();

                        cert.setAcc(acc);
                        cert.setUnit(unit);
                        cert.setName(name);
                        cert.setStuid(stuid);
                        cert.setTel(tel);
                        cert.setMail(mail);

                        new Thread(){
                            @Override
                            public void run(){
                                int msg = 0;
                                CertDao certDao = new CertDao();
                                boolean flag = certDao.register(cert);
                                System.out.println(flag);
                                if(flag) msg = 1;
                                handlercert.sendEmptyMessage(msg);
                            }
                        }.start();

                    }
                    break;
            }


        }
    }

    private boolean isTel(String s) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    private boolean isMail(String s) {
        String regExp = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    @SuppressLint("HandlerLeak")
    final Handler handlercert = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            if(msg.what == 0)
                Toast.makeText(getApplicationContext(), "认证失败", Toast.LENGTH_SHORT).show();
            else if(msg.what == 1){
                Toast.makeText(getApplicationContext(), "认证资料已提交审核，正在返回...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("REFRESH", "refresh");
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        UserDao userDao = new UserDao();
                        boolean userupdate = userDao.updateUser(acc, 10, String.valueOf(2));
                        LocalBroadcastManager.getInstance(ActivityF3Mycert.this).sendBroadcast(intent);
                        sendBroadcast(intent);
                        finish();
                    }
                };
                timer.schedule(timerTask, 1000);
            }

        }
    };




}