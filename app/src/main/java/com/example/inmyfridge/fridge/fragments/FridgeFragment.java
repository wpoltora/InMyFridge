package com.example.inmyfridge.fridge.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.fridge.viewadapters.ExpandableFridgeListAdapter;


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
        EditText searchBar = view.findViewById(R.id.fridge_search_bar);
        ExpandableListView listView = view.findViewById(R.id.fridge_item_list);
        ExpandableFridgeListAdapter adapter = new ExpandableFridgeListAdapter(this.getActivity());
        listView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}