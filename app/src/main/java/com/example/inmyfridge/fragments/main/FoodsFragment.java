package com.example.inmyfridge.fragments.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.fragments.ProductDetailsFragment;
import com.example.inmyfridge.models.Product;
import com.example.inmyfridge.adapters.ProductsListAdapter;
import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;


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
        ListView listView = (ListView) view.findViewById(R.id.foods_list);
        ProductsListAdapter adapter = new ProductsListAdapter(this.getActivity(),  DataHolder.getInstance().productList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product)adapterView.getItemAtPosition(i);
                mainActivity.replaceFragment(new ProductDetailsFragment(product, i), true);
            }
        });
        return view;
    }
}