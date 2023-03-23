package com.example.inmyfridge.shopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.shopping.viewadapters.ShoppingListAdapter;


public class ShoppingFragment extends Fragment {

    private MainActivity mainActivity;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ListView listView = view.findViewById(R.id.shopping_list);
        ShoppingListAdapter adapter = new ShoppingListAdapter(this.getActivity(),  DataHolder.getInstance().shoppingList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view1, i, l) -> {

        });
        return view;
    }
}