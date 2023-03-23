package com.example.inmyfridge.recipes.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.recipes.NewRecipeActivity;
import com.example.inmyfridge.data.model.Recipe;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;
import com.example.inmyfridge.recipes.viewadapters.NewRecipeProductListAdapter;
import com.example.inmyfridge.recipes.viewmodels.NewRecipeFragmentViewModel;

import java.util.ArrayList;

public class NewRecipeFragment extends Fragment {
    ArrayList<Product> selectedProducts= new ArrayList<>();
    ArrayList<ProductRecipeAdapter> productsWithAmount = new ArrayList<>();
    View view;
    NewRecipeFragmentViewModel viewModel;

    public NewRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedProducts = ((NewRecipeActivity)getActivity()).getSelectedProducts();
        viewModel = ViewModelProviders.of(this).get(NewRecipeFragmentViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_recipe
                , container, false);
        RecyclerView recyclerView = view.findViewById(R.id.new_recipe_selected_products_list);
        NewRecipeProductListAdapter adapter = new NewRecipeProductListAdapter(this.getActivity(), selectedProducts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        EditText name = view.findViewById(R.id.new_recipe_name_input);
        EditText recipeText = view.findViewById(R.id.new_recipe_howto_text);
        Button addRecipeButton = view.findViewById(R.id.new_recipe_add_button);

        addRecipeButton.setOnClickListener(view -> {
            Bitmap image = parseProductImage(R.id.new_recipe_image_input);
            productsWithAmount = adapter.getProductsWithAmount();

            Recipe recipe = new Recipe(name.getText().toString(),recipeText.getText().toString() ,image, productsWithAmount);
            viewModel.insertRecipe(recipe);
            getActivity().finish();
        });
        return view;
    }

    public Bitmap parseProductImage(int id){
        ImageView imageView = view.findViewById(id);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        if(drawable != null) {
            return drawable.getBitmap();
        }
        return null;
    }

}
