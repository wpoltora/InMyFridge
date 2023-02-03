package com.example.inmyfridge.recipes.fragments;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.recipes.NewRecipeActivity;
import com.example.inmyfridge.recipes.models.Recipe;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;
import com.example.inmyfridge.recipes.viewadapters.NewRecipeProductListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class NewRecipeFragment extends Fragment {
    ArrayList<Product> selectedProducts= new ArrayList<>();
    ArrayList<ProductRecipeAdapter> productsWithAmount = new ArrayList<>();
    View view;

    public NewRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedProducts = ((NewRecipeActivity)getActivity()).getSelectedProducts();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_recipe
                , container, false);
        ListView listView = view.findViewById(R.id.new_recipe_selected_products_list);
        NewRecipeProductListAdapter adapter = new NewRecipeProductListAdapter(this.getActivity(), selectedProducts);
        listView.setAdapter(adapter);
        EditText name = view.findViewById(R.id.new_recipe_name_input);
        EditText recipeText = view.findViewById(R.id.new_recipe_howto_text);
        Button addRecipeButton = view.findViewById(R.id.new_recipe_add_button);

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = parseProductImage(R.id.new_recipe_image_input);
                productsWithAmount = adapter.getProductsWithAmount();

                Recipe recipe = new Recipe(name.getText().toString(),recipeText.getText().toString() ,image, productsWithAmount);
                DataHolder.getInstance().recipeList.add(recipe);
                getActivity().finish();
            }
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
