package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDao;

import java.util.Timer;
import java.util.TimerTask;


public class ActivityLogin extends AppCompatActivity {

    private static final String TAG = "mysql-login";

    private Button login_button;
    private TextView login_register;
    private EditText login_acc, login_pwd;
    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_register = (TextView) findViewById(R.id.login_register);

        login_button = (Button) findViewById(R.id.login_button);

        login_acc = (EditText) findViewById(R.id.login_account);
        login_acc.setImeOptions(EditorInfo.IME_ACTION_DONE);
        login_acc.setSingleLine();

        login_pwd = (EditText) findViewById(R.id.login_pwd);

        listener = new OnClickListener();
        login_register.setOnClickListener(listener);
        login_button.setOnClickListener(listener);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                if(resultCode == RESULT_OK){
                    String acc = data.getStringExtra("acc");
                    String pwd = data.getStringExtra("pwd");
                    login_acc.setText(acc);
                    login_pwd.setText(pwd);
                }
                break;
        }
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_button:

                    String in_acc = login_acc.getText().toString().trim();
                    String in_pwd = login_pwd.getText().toString().trim();

                    if(in_acc.equals("")){
                        login_acc.requestFocus();
                        Toast.makeText(getApplicationContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else if(in_pwd.equals("")){
                        login_pwd.requestFocus();
                        Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        new Thread(){
                            @Override
                            public void run(){
                                UserDao userDao = new UserDao();
                                int msg = userDao.login(in_acc, in_pwd);
                                hand.sendEmptyMessage(msg);
                            }
                        }.start();



                    }
                    break;

                case R.id.login_register:
                    Intent reg  = new Intent(ActivityLogin.this, ActivityRegister.class);
                    startActivityForResult(reg, 100);
                    break;
            }
        }
    }


    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            if(msg.what == 0)
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
            else if(msg.what == 1){
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent frag1 = new Intent(ActivityLogin.this, MainActivity.class);
                        frag1.putExtra("ACC", login_acc.getText().toString().trim());
                        startActivity(frag1);
                        finish();
                    }
                };
                timer.schedule(timerTask, 1000);

            }
            else if(msg.what == 2)
                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
            else if(msg.what == 3)
                Toast.makeText(getApplicationContext(), "账号不存在", Toast.LENGTH_SHORT).show();
        }
    };


}