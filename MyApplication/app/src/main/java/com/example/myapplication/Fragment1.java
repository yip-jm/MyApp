package com.example.myapplication;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private Fragment1ViewModel mViewModel;
    private OnClickListener listener;
    private LinearLayout f1_quesexp, f1_normexp, f1_brainexp, f1_otherexp, f1_proexp;
    private TextView f1_exprecom, f1_rewardsort, f1_timesort, f1_distsort;

    ViewPager2 viewpager;
    TabLayout tablayout;


    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment1ViewModel.class);
        // TODO: Use the ViewModel

        initView();



    }

    public void initView(){
        View view = getView();
        assert view != null;

        f1_quesexp = (LinearLayout) view.findViewById(R.id.f1_quesexp);
        f1_normexp = (LinearLayout) view.findViewById(R.id.f1_normexp);
        f1_brainexp = (LinearLayout) view.findViewById(R.id.f1_brainexp);
        f1_otherexp = (LinearLayout) view.findViewById(R.id.f1_otherexp);
        f1_proexp = (LinearLayout) view.findViewById(R.id.f1_proexp);



        listener = new OnClickListener();
        f1_quesexp.setOnClickListener(listener);
        f1_normexp.setOnClickListener(listener);
        f1_brainexp.setOnClickListener(listener);
        f1_otherexp.setOnClickListener(listener);
        f1_proexp.setOnClickListener(listener);


        viewpager = view.findViewById(R.id.viewpager);
        tablayout = view.findViewById(R.id.tablayout);
        viewpager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new FragmentF1Exprecom();
                    case 1:
                        return new FragmentF1Rewardsort();
                    case 2:
                        return new FragmentF1Timesort();
                    default:
                        return new FragmentF1Distsort();
                }
            }

            @Override
            public int getItemCount() {
                return 4;
            }
        });

        new TabLayoutMediator(tablayout, viewpager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("实验推荐");
                    break;
                case 1:
                    tab.setText("报酬优先");
                    break;
                case 2:
                    tab.setText("时长优先");
                    break;
                default:
                    tab.setText("距离优先");
            }
        }).attach();

//        监听tablayout，选中时字体改变
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView == null){
                    tab.setCustomView(R.layout.tab_tv);
                }
                TextView tv = tab.getCustomView().findViewById(android.R.id.text1);
                tv.setTextAppearance(getActivity(), R.style.TabSelected);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView == null) {
                    tab.setCustomView(R.layout.tab_tv);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(getActivity(), R.style.TabUnSelected);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }



    private class  OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.f1_quesexp:
                    Intent quesexp = new Intent(getActivity(), ActivityF1Quesexp.class);
                    startActivity(quesexp);
                    break;

                case R.id.f1_normexp:
                    Intent normexp = new Intent(getActivity(), ActivityF1Normexp.class);
                    startActivity(normexp);
                    break;

                case R.id.f1_brainexp:
                    Intent brainexp = new Intent(getActivity(), ActivityF1Brainexp.class);
                    startActivity(brainexp);
                    break;

                case R.id.f1_otherexp:
                    Intent otherexp = new Intent(getActivity(), ActivityF1Otherexp.class);
                    startActivity(otherexp);
                    break;

                case R.id.f1_proexp:
                    Intent proexp = new Intent(getActivity(), ActivityF1Proexp.class);
                    startActivity(proexp);
                    break;

            }
        }
    }



}