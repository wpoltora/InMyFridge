package com.example.inmyfridge.recipes.viewadapters;

import android.app.Activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.recipes.models.Recipe;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class NewRecipeProductListAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    ArrayList<Product> products;
    //HashMap<Product, Integer> productsWithAmount = new HashMap<>();
    ArrayList<ProductRecipeAdapter> productsWithAmount = new ArrayList<>();

    public NewRecipeProductListAdapter (Activity context, ArrayList<Product> products){
        super(context, R.layout.list_item_new_recipe_selected_products, products);
        this.context = context;
        this.products = products;
        for (Product product: products) {
            productsWithAmount.add(new ProductRecipeAdapter(product, 0));
        }
    }


    public View getView(int position, View view, ViewGroup parent){
        Product product = products.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_new_recipe_selected_products, null, true);
        TextView name = rowView.findViewById(R.id.list_item_new_recipe_selected_products_name);
        EditText amount = rowView.findViewById(R.id.list_item_new_recipe_selected_products_amount);
        Spinner spinner = (Spinner) rowView.findViewById(R.id.list_item_new_recipe_selected_products_spinner);
        name.setText(product.getName());

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                for (ProductRecipeAdapter productRecipeAdapter:productsWithAmount) {
                    if(productRecipeAdapter.getProduct().getId().equals(product.getId())){
                        productRecipeAdapter.setRequiredAmount(Integer.parseInt(amount.getText().toString()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (ProductRecipeAdapter productRecipeAdapter:productsWithAmount) {
                    if(productRecipeAdapter.getProduct().getId().equals(product.getId())){
                        productRecipeAdapter.setType(spinner.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rowView;
    }

    public ArrayList<ProductRecipeAdapter> getProductsWithAmount(){
        return productsWithAmount;
    }

}
