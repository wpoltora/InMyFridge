package com.example.inmyfridge.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.adapters.ExpandableListViewAdapter;
import com.example.inmyfridge.data.DataHolder;


public class NewFridgeItemFragment extends Fragment {

    public NewFridgeItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_fridge_item, container, false);
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.new_fridge_item_foods_list);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this.getActivity(),  DataHolder.getInstance().productList);
        listView.setAdapter(adapter);

    //   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //       @Override
    //       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    //           FoodItem foodItem = (FoodItem)adapterView.getItemAtPosition(i);
    //           DataHolder.getInstance().fridgeItemList.add(new FridgeItem(foodItem.getId()));
    //           getParentFragmentManager().popBackStack();
    //       }
    //   });
        return view;
    }
}