package com.example.inmyfridge.recipes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.foods.fragments.NewProductUnitFragment;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.foods.viewadapters.ProductUnitListAdapter;
import com.example.inmyfridge.recipes.models.Recipe;
import com.example.inmyfridge.recipes.viewadapters.RecipeDetailsListAdapter;

public class RecipeDetailsFragment extends Fragment {



    private Recipe recipe;
    //stored value of foodItem position in array
    private int position;
    private MainActivity mainActivity;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    public RecipeDetailsFragment(Recipe recipe, int position){
        this.recipe = recipe;
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        ListView listView = view.findViewById(R.id.recipe_details_ingredient_list);
        RecipeDetailsListAdapter adapter = new RecipeDetailsListAdapter(this.getActivity(), this.recipe.getProducts());
        listView.setAdapter(adapter);
        setTextViews(view);

        return view;
    }

    private void setTextViews(View view){
        ((TextView)view.findViewById(R.id.recipe_details_name)).setText(recipe.getName());
        ((TextView)view.findViewById(R.id.recipe_details_recipe_text)).setText(recipe.getRecipe());
    }


}
