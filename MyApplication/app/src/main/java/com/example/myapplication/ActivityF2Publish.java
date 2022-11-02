package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;
import java.util.Date;



//++++++++++++++++++此部分有数据关联实验数据库+++++++++++++++
//++++++++++++++++++获取手机相册图片+++++++++++++++++

public class ActivityF2Publish extends AppCompatActivity {

    private TextView publish_expdate, publish_exp_statime, publish_exp_endtime;
    private Button publish_button;
    private OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f2publish);

        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);

        publish_expdate = (TextView) findViewById(R.id.publish_expdate);
        publish_exp_statime = (TextView) findViewById(R.id.publish_exp_statime);
        publish_exp_endtime = (TextView) findViewById(R.id.publish_exp_endtime);


        publish_button = (Button) findViewById(R.id.publish_button);

        listener = new OnClickListener();
        publish_expdate.setOnClickListener(listener);
        publish_exp_statime.setOnClickListener(listener);
        publish_exp_endtime.setOnClickListener(listener);
        publish_button.setOnClickListener(listener);

    }

    private class OnClickListener implements View.OnClickListener {
        @Override

        public void onClick(View v) {
            final Calendar calendar = Calendar.getInstance();
            switch (v.getId()) {
                case R.id.publish_expdate:
                    DatePickerDialog pickerDialog = new DatePickerDialog(ActivityF2Publish.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String date = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                                    publish_expdate.setText(date);
                                }
                            }, 2022, 01, 01);

                    //时间选择不得早于今天
                    Date date =  new Date();
                    long today = date.getTime();
                    pickerDialog.getDatePicker().setMinDate(today);
                    pickerDialog.show();
                    break;

                case R.id.publish_exp_statime:
                    TimePickerDialog stapickerDialog = new TimePickerDialog(ActivityF2Publish.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hour, int minute) {
                                    String hr = "0", min = "0";
                                    if(hour<10){
                                        hr = String.format("0%d", hour);
                                    }
                                    if(minute<10){
                                        min = String.format("0%d", minute);
                                    }
                                    String statime = String.format("%d:%d", hr, min);
                                    publish_exp_statime.setText(statime);
                                }
                            },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                    stapickerDialog.show();
                    break;

                case R.id.publish_exp_endtime:

                    TimePickerDialog endpickerDialog = new TimePickerDialog(ActivityF2Publish.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hour, int minute) {
                                    String hr = "0", min = "0";
                                    if(hour<10){
                                        hr = String.format("0%d", hour);
                                    }
                                    if(minute<10){
                                        min = String.format("0%d", minute);
                                    }
                                    String endtime = String.format("%s:%s", hr, min);
                                    publish_exp_endtime.setText(endtime);
                                }
                            },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

                    endpickerDialog.show();
                    break;


                case R.id.publish_button:
//                    弹出消息框： 已上传实验，请等待审核
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