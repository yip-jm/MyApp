package com.example.myapplication;


import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.User;


public class Fragment3 extends Fragment {

    private Fragment3ViewModel mViewModel;

    private OnClickListener listener;
    private RelativeLayout f3_my_cert, f3_my_setting, f3_my_about;

    private TextView f3_name, f3_acc;
    private Button logout_button;
    private Intent intent;
    String acc, name;


    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        intent = getActivity().getIntent();
        acc = intent.getStringExtra("ACC");

        new Thread(){
            @Override
            public void run(){
                UserDao userDao = new UserDao();
                User uu = userDao.findUser(acc);
                name = uu.getName();
                Bundle bundle = new Bundle();
                bundle.putString("NAME", name);
                Message msg = new Message();
                msg.setData(bundle);
                msg.what = 1;
                handler3.sendMessage(msg);
            }
        }.start();

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

        f3_name = (TextView) view.findViewById(R.id.f3_name);
        f3_acc = (TextView) view.findViewById(R.id.f3_acc);
        f3_acc.setText(acc);

        f3_my_cert = (RelativeLayout) view.findViewById(R.id.f3_my_cert);
        f3_my_setting = (RelativeLayout) view.findViewById(R.id.f3_my_setting);
        f3_my_about = (RelativeLayout) view.findViewById(R.id.f3_my_about);

        logout_button = (Button) view.findViewById(R.id.logout_button);

        listener = new OnClickListener();
        f3_my_cert.setOnClickListener(listener);
        f3_my_setting.setOnClickListener(listener);
        f3_my_about.setOnClickListener(listener);
        logout_button.setOnClickListener(listener);

    }



    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.f3_my_cert:
                    Intent mycert = new Intent(getActivity(), ActivityF3Mycert.class);
                    mycert.putExtra("ACC", acc);
                    startActivity(mycert);
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

                case R.id.logout_button:
                    Intent mylogin = new Intent(getActivity(), ActivityLogin.class);
                    startActivity(mylogin);
                    getActivity().finish();
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
