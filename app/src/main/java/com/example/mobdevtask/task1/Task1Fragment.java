package com.example.mobdevtask.task1;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobdevtask.task1.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobdevtask.R;

public class Task1Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment tab1Fragment, tab2Fragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tab1Fragment = new Tab1Fragment();
        tab2Fragment = new Tab2Fragment();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_1, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewpager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);



        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(tab1Fragment, "Input");
        viewPagerAdapter.addFragment(tab2Fragment, "List");
        viewPager.setAdapter(viewPagerAdapter);

    }

}


