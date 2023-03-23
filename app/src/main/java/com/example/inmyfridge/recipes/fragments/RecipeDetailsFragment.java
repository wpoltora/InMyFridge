package com.example.inmyfridge.recipes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.Recipe;
import com.example.inmyfridge.recipes.viewadapters.RecipeDetailsListAdapter;
import com.example.inmyfridge.recipes.viewmodels.RecipeDetailsViewModel;

import java.util.UUID;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsListAdapter.ItemCallback{
    private RecipeDetailsViewModel viewModel;
    private Recipe recipe;
    private MainActivity mainActivity;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    public RecipeDetailsFragment(Recipe recipe){
        this.recipe = recipe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
        viewModel = ViewModelProviders.of(this).get(RecipeDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        ListView listView = view.findViewById(R.id.recipe_details_ingredient_list);
        RecipeDetailsListAdapter adapter = new RecipeDetailsListAdapter(this.getActivity(), this.recipe.getProducts(), this);
        listView.setAdapter(adapter);
        setTextViews(view);

        return view;
    }

    private void setTextViews(View view){
        ((TextView)view.findViewById(R.id.recipe_details_name)).setText(recipe.getName());
        ((TextView)view.findViewById(R.id.recipe_details_recipe_text)).setText(recipe.getRecipe());
    }


    @Override
    public Product getProductByID(UUID id) {
       return viewModel.getProductByID(id);
    }
}
