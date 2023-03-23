package com.example.inmyfridge.tracking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.inmyfridge.R;
import com.google.android.material.tabs.TabLayout;

public class TrackerFragment extends Fragment {


    public TrackerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        replaceFragment(new DailyViewFragment(), false);
        View view = inflater.inflate(R.layout.fragment_tracker, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tracker_tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        replaceFragment(new DailyViewFragment(), false);
                        break;
                    case 1:
                        replaceFragment(new WeeklyViewFragment(), false);
                        break;
                    case 2:
                        replaceFragment(new MonthlyViewFragment(), false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }




    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.tracker_frame_layout, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
