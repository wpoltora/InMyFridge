package com.example.inmyfridge.foods.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.viewadapters.ProductListAdapter;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;

/**This is the main fragment for displaying foods saved in a database*/
public class FoodsFragment extends Fragment {

    private MainActivity mainActivity;

    public FoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods, container, false);
        EditText searchBar = view.findViewById(R.id.foods_search_bar);
        ListView listView = view.findViewById(R.id.foods_list);
        ProductListAdapter adapter = new ProductListAdapter(this.getActivity(),  DataHolder.getInstance().productList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Product product = (Product)adapterView.getItemAtPosition(i);
            mainActivity.replaceFragment(new ProductDetailsFragment(product, i), true);
        });

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
        return view;
    }
}