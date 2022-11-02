package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;


//+++++++++++++++++++待补充
//+++++++++++++++++++关联数据库判断有无 用户 和 用户信息验证


public class ActivityLogin extends AppCompatActivity {


    private Button login_button;
    private TextView login_register;
    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_register = (TextView) findViewById(R.id.login_register);


        login_button = (Button) findViewById(R.id.login_button);

        listener = new OnClickListener();
        login_register.setOnClickListener(listener);
        login_button.setOnClickListener(listener);

    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_button:
                    Intent frag1 = new Intent(ActivityLogin.this, MainActivity.class);
                    startActivity(frag1);
                    break;

                case R.id.login_register:
                    Intent reg  = new Intent(ActivityLogin.this, ActivityRegister.class);
                    startActivity(reg);
                    break;
            }
        }
    }


}