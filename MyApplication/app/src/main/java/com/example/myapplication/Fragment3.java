package com.example.myapplication;


import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.User;


public class Fragment3 extends Fragment {

    private Fragment3ViewModel mViewModel;

    private OnClickListener listener;
    private RelativeLayout f3_my_cert, f3_my_setting, f3_my_about, f3_my_opin;

    private TextView f3_name, f3_acc;
    private Button logout_button;
    private Intent intent;
    private String acc, name;
    private int cert;


    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("REFRESH");
                if("refresh".equals(msg)){
                    initView();
                }
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);

        intent = getActivity().getIntent();
        acc = intent.getStringExtra("ACC");

        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment3ViewModel.class);
        // TODO: Use the ViewModel

        initView();
    }

    public void initView(){
        View view = getView();
        assert view != null;

        new Thread(() -> {
            UserDao userDao = new UserDao();
            User uu = userDao.findUser(acc);
            name = uu.getName();
            cert = uu.getCert();
            Bundle bundle = new Bundle();
            bundle.putString("NAME", name);
            bundle.putInt("CERT", cert);
            Message msg = new Message();
            msg.setData(bundle);
            msg.what = 1;
            handler3.sendMessage(msg);
        }).start();

        f3_name = (TextView) view.findViewById(R.id.f3_name);
        f3_acc = (TextView) view.findViewById(R.id.f3_acc);
        f3_acc.setText(acc);

        f3_my_cert = (RelativeLayout) view.findViewById(R.id.f3_my_cert);
        f3_my_setting = (RelativeLayout) view.findViewById(R.id.f3_my_setting);
        f3_my_about = (RelativeLayout) view.findViewById(R.id.f3_my_about);
        f3_my_opin = (RelativeLayout) view.findViewById(R.id.frag3_my_opin);

        logout_button = (Button) view.findViewById(R.id.logout_button);

        listener = new OnClickListener();
        f3_my_cert.setOnClickListener(listener);
        f3_my_setting.setOnClickListener(listener);
        f3_my_about.setOnClickListener(listener);
        f3_my_opin.setOnClickListener(listener);
        logout_button.setOnClickListener(listener);

    }



    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.f3_my_cert:
                    if (cert == 0) {
                        Intent mycert = new Intent(getActivity(), ActivityF3Mycert.class);
                        mycert.putExtra("ACC", acc);
                        startActivity(mycert);
                    }
                    else if (cert == 1){
                        Intent mycert1 = new Intent(getActivity(), ActivityF3Mycert1.class);
                        mycert1.putExtra("ACC", acc);
                        startActivity(mycert1);
                    }else if (cert == 2)
                        Toast.makeText(getContext(), "您的主试认证正在审核中，请耐心等待...", Toast.LENGTH_SHORT).show();

                    break;

                case R.id.f3_my_setting:
                    Intent myinfo = new Intent(getActivity(), ActivityF3Myinfo.class);
                    myinfo.putExtra("ACC", acc);
                    startActivity(myinfo);
                    break;

                case R.id.f3_my_about:
                    Intent myabout = new Intent(getActivity(), ActivityF3Aboutus.class);
                    startActivity(myabout);
                    break;

                case R.id.frag3_my_opin:
                    Toast.makeText(getActivity(), "功能开发中，敬请期待...", Toast.LENGTH_SHORT).show();
                    break;


                case R.id.logout_button:
                    Intent mylogin = new Intent(getActivity(), ActivityLogin.class);
                    startActivity(mylogin);
//                    getActivity().finish();
                    System.exit(0);
                    break;

            }
        }
    }

    @SuppressLint("HandlerLeak")
    final Handler handler3 = new Handler(){
        @Override
        public  void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    String tname = bundle.getString("NAME");
                    f3_name.setText(tname);
                    break;

                default:
                    break;
            }
        }
    };
}
