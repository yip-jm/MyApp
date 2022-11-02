package com.example.myapplication;


import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class Fragment3 extends Fragment {

    private Fragment3ViewModel mViewModel;

    private OnClickListener listener;
    private RelativeLayout f3_my_cert, f3_my_setting, f3_my_about;
    private Button logout_button;



    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment3ViewModel.class);
        // TODO: Use the ViewModel

        initView();
    }

    public void initView(){
        View view = getView();
        assert view != null;
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
                    startActivity(mycert);
                    break;

                case R.id.f3_my_setting:
                    Intent myinfo = new Intent(getActivity(), ActivityF3Myinfo.class);
                    startActivity(myinfo);
                    break;

                case R.id.f3_my_about:
                    Intent myabout = new Intent(getActivity(), ActivityF3Aboutus.class);
                    startActivity(myabout);
                    break;

                case R.id.logout_button:
                    Intent mylogin = new Intent(getActivity(), ActivityLogin.class);
                    startActivity(mylogin);
                    break;

            }
        }
    }
}
