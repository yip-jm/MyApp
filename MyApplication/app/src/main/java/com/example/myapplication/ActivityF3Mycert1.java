package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.dao.CertDao;
import com.example.myapplication.entity.Cert;

public class ActivityF3Mycert1 extends AppCompatActivity {

    private String acc;

    private TextView cert_unit, cert_name, cert_stuid, cert_tel, cert_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3mycert1);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);

        acc = getIntent().getStringExtra("ACC");
        System.out.println("cert1:" + acc);

        new Thread(() ->{
            CertDao certDao = new CertDao();
            Cert cert = certDao.findCert(acc);

            String unit = cert.getUnit();
            String name = cert.getName();
            String stuid = cert.getStuid();
            String tel = cert.getTel();
            String mail = cert.getMail();
            Bundle bundle = new Bundle();
            bundle.putString("UNIT", unit);
            bundle.putString("NAME", name);
            bundle.putString("STUID", stuid);
            bundle.putString("TEL", tel);
            bundle.putString("MAIL", mail);
            Message msg = new Message();
            msg.setData(bundle);
            msg.what = 1;
            handler3cert1.sendMessage(msg);
        }).start();

        cert_unit = (TextView) findViewById(R.id.cert_unit);
        cert_name = (TextView) findViewById(R.id.cert_name);
        cert_stuid = (TextView) findViewById(R.id.cert_stuid);
        cert_tel = (TextView) findViewById(R.id.cert_tel);
        cert_mail = (TextView) findViewById(R.id.cert_mail);


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

    @SuppressLint("HandlerLeak")
    final Handler handler3cert1 = new Handler(){
        @Override
        public  void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    String unit = bundle.getString("UNIT");
                    String name = bundle.getString("NAME");
                    String stuid = bundle.getString("STUID");
                    String tel = bundle.getString("TEL");
                    String mail = bundle.getString("MAIL");

                    cert_unit.setText(unit);
                    cert_name.setText(name);
                    cert_stuid.setText(stuid);
                    cert_tel.setText(tel);
                    cert_mail.setText(mail);
                    break;

                default:
                    break;
            }
        }
    };

}