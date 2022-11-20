package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityRegister extends AppCompatActivity {
    public static final String VAL_ACC = "^[0-9a-zA-Z]{3,10}$";
    public static final String VAL_PWD = "^[0-9a-zA-Z]{8,16}$";

    private static final String TAG = "mysql-register";

    private Button reg_button;
    private OnClickListener listener;
    private OnCheckedChangeListener rglistener;

    private EditText reg_acc, reg_pwd, reg_pwd2, reg_name, reg_birth, reg_tel;

    private RadioGroup reg_gender, reg_hand, reg_ES, reg_EQ;

    private TextView reg_acctip, reg_pwd1tip, reg_pwd2tip, backtologin, reg_birthtip;

    private int in_gender = 1, in_hand = 1, in_es = 0, in_eq = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_button = (Button) findViewById(R.id.reg_button);

        reg_acctip = (TextView) findViewById(R.id.reg_acctip);
        reg_pwd1tip = (TextView) findViewById(R.id.reg_pwd1tip);
        reg_pwd2tip = (TextView) findViewById(R.id.reg_pwd2tip);
        reg_birthtip = (TextView) findViewById(R.id.reg_birthtip);
        backtologin = (TextView) findViewById(R.id.backtologin);

        reg_acc = (EditText) findViewById(R.id.reg_acc);
        reg_acc.addTextChangedListener(new JumpTextWatcher());
        reg_acc.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        reg_acc.setSingleLine(true);

        reg_pwd = (EditText) findViewById(R.id.reg_pwd1);
        reg_pwd.addTextChangedListener(new ValTextWatcher());
        reg_pwd2 = (EditText) findViewById(R.id.reg_pwd2);
        reg_pwd2.addTextChangedListener(new RepTextWatcher());
        reg_name = (EditText) findViewById(R.id.reg_name);
        reg_name.setImeOptions(EditorInfo.IME_ACTION_DONE);
        reg_name.setSingleLine(true);
        reg_tel = (EditText) findViewById(R.id.reg_tel);
        reg_birth = (EditText) findViewById(R.id.reg_birth);
        reg_birth.addTextChangedListener(new ValDateWatcher());

        rglistener = new OnCheckedChangeListener();
        reg_gender = (RadioGroup) findViewById(R.id.reg_gender);
        reg_gender.setOnCheckedChangeListener(rglistener);
        reg_hand = (RadioGroup) findViewById(R.id.reg_hand);
        reg_hand.setOnCheckedChangeListener(rglistener);
        reg_ES = (RadioGroup) findViewById(R.id.reg_ES);
        reg_ES.setOnCheckedChangeListener(rglistener);
        reg_EQ = (RadioGroup) findViewById(R.id.reg_EQ);
        reg_EQ.setOnCheckedChangeListener(rglistener);

        listener = new OnClickListener();
        reg_birth.setOnClickListener(listener);
        reg_button.setOnClickListener(listener);
        backtologin.setOnClickListener(listener);
    }


    private class JumpTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();

            if(Pattern.matches(VAL_ACC, s)){
                reg_acctip.setVisibility(View.INVISIBLE);
            }else{
                reg_acctip.setVisibility(View.VISIBLE);
            }
        }
    }

    private class ValTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(Pattern.matches(VAL_PWD, s)){
                reg_pwd1tip.setVisibility(View.INVISIBLE);
            }else {
                reg_pwd1tip.setVisibility(View.VISIBLE);
            }
        }
    }

    private class RepTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String pwd = reg_pwd.getText().toString().trim();
            String pwd2 = s.toString().trim();

            if(pwd.equals(pwd2)){
                reg_pwd2tip.setVisibility(View.INVISIBLE);
            }else{
                reg_pwd2tip.setVisibility(View.VISIBLE);
            }

        }
    }

    private class ValDateWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date nowdate = new Date();
            String today = sdf.format(nowdate);

            try{
                sdf.setLenient(false);
                Date inn = sdf.parse(str);
                int flag = inn.compareTo(nowdate);
                if(flag >= 0){}
                else{
                    inn.toString();
                    reg_birthtip.setVisibility(View.INVISIBLE);
                }
            }catch (Exception e){
                reg_birthtip.setVisibility(View.VISIBLE);
            }
        }
    }


    private class OnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.reg_man:
                    in_gender = 0;
                    break;

                case R.id.reg_woman:
                    in_gender = 1;
                    break;

                case R.id.reg_hand_right:
                    in_hand = 0;
                    break;

                case R.id.reg_hand_left:
                    in_hand = 1;
                    break;

                case R.id.reg_ESnorm:
                    in_es = 0;
                    break;

                case R.id.reg_EScornorm:
                    in_es = 1;
                    break;

                case R.id.reg_ESmyopia:
                    in_es = 2;
                    break;

                case R.id.reg_EShyper:
                    in_es = 3;
                    break;

                case R.id.reg_EQnorm:
                    in_eq = 0;
                    break;

                case R.id.reg_EQast:
                    in_eq = 1;
                    break;

                case R.id.reg_EQach:
                    in_eq = 2;
                    break;

                case R.id.reg_EQother:
                    in_eq = 3;
                    break;
            }
        }
    }

    private class  OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.reg_button:

                    String in_acc = reg_acc.getText().toString().trim();
                    String in_pwd = reg_pwd.getText().toString().trim();
                    String in_pwd2 = reg_pwd2.getText().toString().trim();
                    String in_name = reg_name.getText().toString().trim();
                    String in_birth = reg_birth.getText().toString().trim();
                    String in_tel = reg_tel.getText().toString().trim();


                    if(in_acc.equals("") || reg_acctip.getVisibility() == View.VISIBLE){
                        reg_acc.requestFocus();
                        Toast.makeText(getApplicationContext(), "账号输入为空或错误", Toast.LENGTH_SHORT).show();
                    }
                    else if(in_pwd.equals("") || in_pwd2.equals("") || reg_pwd1tip.getVisibility() == View.VISIBLE
                            || reg_pwd2tip.getVisibility() == View.VISIBLE || !in_pwd.equals(in_pwd2)){
                        reg_pwd.requestFocus();
                        Toast.makeText(getApplicationContext(), "密码输入为空或错误", Toast.LENGTH_SHORT).show();
                    }
                    else if(in_name.equals("")){
                        reg_name.requestFocus();
                        Toast.makeText(getApplicationContext(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else if(in_birth.equals("") || reg_birthtip.getVisibility() == View.VISIBLE){
                        reg_birth.requestFocus();
                        Toast.makeText(getApplicationContext(), "日期输入错误", Toast.LENGTH_SHORT).show();
                    }
                    else if(in_tel.length() != 11 || !isTel(in_tel)){
                        reg_tel.requestFocus();
                        Toast.makeText(getApplicationContext(), "手机号码输入错误", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        User user = new User();

                        user.setAcc(in_acc);
                        user.setPwd(in_pwd);
                        user.setName(in_name);
                        user.setGender(in_gender);
                        user.setBirth(in_birth);
                        user.setHand(in_hand);
                        user.setES(in_es);
                        user.setEQ(in_eq);
                        user.setTel(in_tel);
                        user.setCert(0);

                        new Thread(){
                            @Override
                            public void run(){
                                int msg = 0;
                                UserDao userDao = new UserDao();
                                User uu = userDao.findUser(user.getAcc());

                                if(uu != null)  msg = 1;
                                else{
                                    boolean flag = userDao.register(user);
                                    if(flag)    msg = 2;
                                }
                                hand.sendEmptyMessage(msg);
                            }
                        }.start();
                    }

                    break;


                case R.id.backtologin:
                    Intent log2 = new Intent(ActivityRegister.this, ActivityLogin.class);
                    startActivity(log2);
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

    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            if(msg.what == 0)
                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
            else if(msg.what == 1) {
                Toast.makeText(getApplicationContext(), "该账号已存在，请换一个账号", Toast.LENGTH_SHORT).show();
                reg_acc.requestFocus();
            }
            else if(msg.what == 2){
                Toast.makeText(getApplicationContext(), "注册成功，正在跳转至登陆界面...", Toast.LENGTH_SHORT).show();

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.putExtra("acc", reg_acc.getText().toString().trim());
                        intent.putExtra("pwd", reg_pwd.getText().toString().trim());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                };
                timer.schedule(timerTask, 1000);
            }

        }
    };



}