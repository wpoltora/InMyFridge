package com.example.inmyfridge.fridge.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.fridge.viewadapters.ExpandableFridgeListAdapter;
import com.example.inmyfridge.fridge.viewmodels.FridgeViewModel;


public class FridgeFragment extends Fragment implements ExpandableFridgeListAdapter.ItemCallback {
    FridgeViewModel viewModel;
    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(FridgeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);
        EditText searchBar = view.findViewById(R.id.fridge_search_bar);
        ExpandableListView listView = view.findViewById(R.id.fridge_item_list);
        ExpandableFridgeListAdapter adapter = new ExpandableFridgeListAdapter(this.getActivity(), viewModel.getFridgeItemsWithProducts(), this);
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

    @Override
    public void onItemUpdated(ProductUnit item) {
        viewModel.update(item);
    }
}