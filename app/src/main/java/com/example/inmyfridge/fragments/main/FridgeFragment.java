package com.example.inmyfridge.fragments.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.models.FridgeItem;
import com.example.inmyfridge.adapters.FridgeListAdapter;
import com.example.inmyfridge.R;

import java.util.ArrayList;

public class FridgeFragment extends Fragment {

    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);
        ListView listView = (ListView) view.findViewById(R.id.fridge_list);
        FridgeListAdapter adapter = new FridgeListAdapter(this.getActivity(), DataHolder.getInstance().fridgeItemList);
        listView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}