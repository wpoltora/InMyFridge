package com.example.inmyfridge.recipes.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.fragments.ProductDetailsFragment;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.foods.viewadapters.ProductListAdapter;
import com.example.inmyfridge.recipes.models.Recipe;
import com.example.inmyfridge.recipes.viewadapters.RecipeListAdapter;

public class RecipeFragment extends Fragment {
    private MainActivity mainActivity;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ListView listView = view.findViewById(R.id.recipe_list);
        RecipeListAdapter adapter = new RecipeListAdapter(this.getActivity(),  DataHolder.getInstance().recipeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Recipe recipe = (Recipe)adapterView.getItemAtPosition(i);
            mainActivity.replaceFragment(new RecipeDetailsFragment(recipe, i), true);
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}