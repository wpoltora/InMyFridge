package com.example.inmyfridge.recipes.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Recipe;
import com.example.inmyfridge.recipes.viewadapters.RecipeListAdapter;
import com.example.inmyfridge.recipes.viewmodels.RecipeViewModel;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    private MainActivity mainActivity;
    private RecipeViewModel viewModel;
    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ListView listView = view.findViewById(R.id.recipe_list);
        RecipeListAdapter adapter = new RecipeListAdapter(this.getActivity(), new ArrayList<>(viewModel.getAllRecipes()));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Recipe recipe = (Recipe)adapterView.getItemAtPosition(i);
            mainActivity.replaceFragment(new RecipeDetailsFragment(recipe), true);
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}