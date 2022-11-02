package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityRegister extends AppCompatActivity {

    private Button reg_button;
    private OnClickListener listener;

    private TextView backtologin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_button = (Button) findViewById(R.id.reg_button);
        backtologin = (TextView) findViewById(R.id.backtologin);

        listener = new OnClickListener();
        reg_button.setOnClickListener(listener);
        backtologin.setOnClickListener(listener);
    }

    private class  OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.reg_button:
                    Intent log1 = new Intent(ActivityRegister.this, ActivityLogin.class);
                    startActivity(log1);
                    break;

                case R.id.backtologin:
                    Intent log2 = new Intent(ActivityRegister.this, ActivityLogin.class);
                    startActivity(log2);
                    break;
            }
        }
    }
}