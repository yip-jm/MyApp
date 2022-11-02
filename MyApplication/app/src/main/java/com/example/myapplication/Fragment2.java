package com.example.myapplication;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment2 extends Fragment {

    private Fragment2ViewModel mViewModel;

    private OnClickListener listener;

    private Button f2_cert_button, f2_publishgo_button, f2_seego_button;

    private LinearLayout f2_publish_wait, f2_publish_now, f2_publish_finish,
                        f2_join_wait, f2_join_now, f2_join_finish;
    private ImageView f2_publish_wait_iv, f2_publish_now_iv, f2_publish_finish_iv,
                        f2_join_wait_iv, f2_join_now_iv, f2_join_finish_iv;
    private TextView f2_publish_wait_tv, f2_publish_now_tv, f2_publish_finish_tv,
                        f2_join_wait_tv, f2_join_now_tv, f2_join_finish_tv;



    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment2ViewModel.class);
        // TODO: Use the ViewModel

        initView();
    }

    public void initView(){
        View view = getView();
        assert view != null;
        f2_publish_wait = (LinearLayout) view.findViewById(R.id.f2_publish_wait);
        f2_publish_wait_iv = (ImageView) view.findViewById(R.id.f2_publish_wait_iv);
        f2_publish_wait_tv = (TextView) view.findViewById(R.id.f2_publish_wait_tv);

        f2_publish_now = (LinearLayout) view.findViewById(R.id.f2_publish_now);
        f2_publish_now_iv = (ImageView) view.findViewById(R.id.f2_publish_now_iv);
        f2_publish_now_tv = (TextView) view.findViewById(R.id.f2_publish_now_tv);

        f2_publish_finish = (LinearLayout) view.findViewById(R.id.f2_publish_finish);
        f2_publish_finish_iv = (ImageView) view.findViewById(R.id.f2_publish_finish_iv);
        f2_publish_finish_tv = (TextView) view.findViewById(R.id.f2_publish_finish_tv);

        f2_join_wait = (LinearLayout) view.findViewById(R.id.f2_join_wait);
        f2_join_wait_iv = (ImageView) view.findViewById(R.id.f2_join_wait_iv);
        f2_join_wait_tv = (TextView) view.findViewById(R.id.f2_join_wait_tv);

        f2_join_now = (LinearLayout) view.findViewById(R.id.f2_join_now);
        f2_join_now_iv = (ImageView) view.findViewById(R.id.f2_join_now_iv);
        f2_join_now_tv = (TextView) view.findViewById(R.id.f2_join_now_tv);

        f2_join_finish = (LinearLayout) view.findViewById(R.id.f2_join_finish);
        f2_join_finish_iv = (ImageView) view.findViewById(R.id.f2_join_finish_iv);
        f2_join_finish_tv = (TextView) view.findViewById(R.id.f2_join_finish_tv);

        f2_cert_button = (Button) view.findViewById(R.id.f2_cert_button);
        f2_publishgo_button = (Button) view.findViewById(R.id.f2_publishgo_button);
        f2_seego_button = (Button) view.findViewById(R.id.f2_seego_button);


        listener = new OnClickListener();
        f2_publish_wait.setOnClickListener(listener);
        f2_publish_now.setOnClickListener(listener);
        f2_publish_finish.setOnClickListener(listener);

        f2_join_wait.setOnClickListener(listener);
        f2_join_now.setOnClickListener(listener);
        f2_join_finish.setOnClickListener(listener);

        f2_cert_button.setOnClickListener(listener);
        f2_publishgo_button.setOnClickListener(listener);
        f2_seego_button.setOnClickListener(listener);

    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.f2_publish_wait:
                    Clear(f2_publish_now_tv, f2_publish_finish_tv);
                    f2_publish_now_iv.setImageResource(R.drawable.nowlist0);
                    f2_publish_finish_iv.setImageResource(R.drawable.finishlist0);

                    f2_publish_wait_iv.setImageResource(R.drawable.waitlist1);
                    f2_publish_wait_tv.setTextColor(getResources().getColor(R.color.background));
                    f2_publish_wait_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    f2_publish_wait_tv.setTextSize(18);
                    break;

                case R.id.f2_publish_now:
                    Clear(f2_publish_wait_tv, f2_publish_finish_tv);
                    f2_publish_wait_iv.setImageResource(R.drawable.waitlist0);
                    f2_publish_finish_iv.setImageResource(R.drawable.finishlist0);

                    f2_publish_now_iv.setImageResource(R.drawable.nowlist1);
                    f2_publish_now_tv.setTextColor(getResources().getColor(R.color.background));
                    f2_publish_now_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    f2_publish_now_tv.setTextSize(18);
                    break;

                case R.id.f2_publish_finish:
                    Clear(f2_publish_wait_tv, f2_publish_now_tv);
                    f2_publish_wait_iv.setImageResource(R.drawable.waitlist0);
                    f2_publish_now_iv.setImageResource(R.drawable.nowlist0);

                    f2_publish_finish_iv.setImageResource(R.drawable.finishlist1);
                    f2_publish_finish_tv.setTextColor(getResources().getColor(R.color.background));
                    f2_publish_finish_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    f2_publish_finish_tv.setTextSize(18);
                    break;


                case R.id.f2_join_wait:
                    Clear(f2_join_now_tv, f2_join_finish_tv);
                    f2_join_now_iv.setImageResource(R.drawable.nowlist0);
                    f2_join_finish_iv.setImageResource(R.drawable.finishlist0);

                    f2_join_wait_iv.setImageResource(R.drawable.waitlist1);
                    f2_join_wait_tv.setTextColor(getResources().getColor(R.color.background));
                    f2_join_wait_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    f2_join_wait_tv.setTextSize(18);
                    break;

                case R.id.f2_join_now:
                    Clear(f2_join_wait_tv, f2_join_finish_tv);
                    f2_join_wait_iv.setImageResource(R.drawable.waitlist0);
                    f2_join_finish_iv.setImageResource(R.drawable.finishlist0);

                    f2_join_now_iv.setImageResource(R.drawable.nowlist1);
                    f2_join_now_tv.setTextColor(getResources().getColor(R.color.background));
                    f2_join_now_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    f2_join_now_tv.setTextSize(18);
                    break;

                case R.id.f2_join_finish:
                    Clear(f2_join_wait_tv, f2_join_now_tv);
                    f2_join_wait_iv.setImageResource(R.drawable.waitlist0);
                    f2_join_now_iv.setImageResource(R.drawable.nowlist0);

                    f2_join_finish_iv.setImageResource(R.drawable.finishlist1);
                    f2_join_finish_tv.setTextColor(getResources().getColor(R.color.background));
                    f2_join_finish_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    f2_join_finish_tv.setTextSize(18);
                    break;


                case R.id.f2_cert_button:
                    Intent mycert = new Intent(getActivity(), ActivityF3Mycert.class);
                    startActivity(mycert);
                    break;

                case  R.id.f2_publishgo_button:
                    Intent mypublish = new Intent(getActivity(), ActivityF2Publish.class);
                    startActivity(mypublish);
                    break;

                case R.id.f2_seego_button:
                    Intent frag1 = new Intent(getActivity(), MainActivity.class);
                    startActivity(frag1);
                    break;
            }
        }

    }

    private void  Clear(TextView tv1, TextView tv2){
        tv1.setTextColor(getResources().getColor(R.color.deep_grey));
        tv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv1.setTextSize(16);
        tv2.setTextColor(getResources().getColor(R.color.deep_grey));
        tv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv2.setTextSize(16);
    }



}