package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityF3MyinfoSetnickname extends AppCompatActivity {

    private EditText set_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3myinfo_setnickname);


        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);

        set_nickname = (EditText) findViewById(R.id.set_nickname);
        set_nickname.setImeOptions(EditorInfo.IME_ACTION_DONE);
        set_nickname.setSingleLine();  // 这句话也是必不可少的



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onPause();
                finish();
                break;

            case R.id.finish_button:
//                需要对数据库信息进行更新并保存
//                消息弹窗提示用户正在保存
                onPause();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finish_button, menu);
        return true;
    }

}