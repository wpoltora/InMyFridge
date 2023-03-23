package com.example.inmyfridge.recipes.viewadapters;

import android.app.Activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;

import java.util.ArrayList;


public class NewRecipeProductListAdapter extends RecyclerView.Adapter<NewRecipeProductListAdapter.ViewHolder> {
    private final Activity context;
    ArrayList<Product> products;
    ArrayList<ProductRecipeAdapter> productsWithAmount = new ArrayList<>();

    public NewRecipeProductListAdapter (Activity context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
        for (Product product: products) {
            productsWithAmount.add(new ProductRecipeAdapter(product.getId(), 0));
        }
    }


    public ArrayList<ProductRecipeAdapter> getProductsWithAmount(){
        return productsWithAmount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_new_recipe_selected_products, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());

        holder.amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                for (ProductRecipeAdapter productRecipeAdapter:productsWithAmount) {
                    if(productRecipeAdapter.getProductID().equals(product.getId())){
                        productRecipeAdapter.setRequiredAmount(Integer.parseInt(holder.amount.getText().toString()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (ProductRecipeAdapter productRecipeAdapter:productsWithAmount) {
                    if(productRecipeAdapter.getProductID().equals(product.getId())){
                        productRecipeAdapter.setType(holder.spinner.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public int getItemCount() {
       return this.products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private EditText amount;
        private Spinner spinner;
        public ViewHolder(@NonNull View view) {
            super(view);

            this.name= view
                    .findViewById(R.id.list_item_new_recipe_selected_products_name);
            this.amount = view.findViewById(R.id.list_item_new_recipe_selected_products_amount);
            this.spinner = view.findViewById(R.id.list_item_new_recipe_selected_products_spinner);
        }
    }
}
