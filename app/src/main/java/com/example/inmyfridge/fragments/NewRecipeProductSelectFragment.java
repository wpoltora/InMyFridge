package com.example.inmyfridge.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.inmyfridge.R;
import com.example.inmyfridge.adapters.ProductsListAdapter;
import com.example.inmyfridge.adapters.RecipeProductListAdapter;
import com.example.inmyfridge.data.DataHolder;

public class NewRecipeProductSelectFragment extends Fragment {

    public NewRecipeProductSelectFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_recipe_products_select
                , container, false);

        ListView listView = view.findViewById(R.id.new_recipe_product_select_list);
        RecipeProductListAdapter adapter = new RecipeProductListAdapter(this.getActivity(),  DataHolder.getInstance().productList);
        listView.setAdapter(adapter);
        return view;
    }
}
