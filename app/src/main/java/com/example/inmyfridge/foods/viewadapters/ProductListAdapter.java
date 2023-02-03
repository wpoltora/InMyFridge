package com.example.inmyfridge.foods.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**Used for displaying food items list from database*/
public class ProductListAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    ArrayList<Product> products;
    ArrayList<Product> productsCopy;

    public ProductListAdapter(Activity context, ArrayList<Product> products){
        super(context, R.layout.list_item_products, products);
        this.context = context;
        products.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
        this.products = new ArrayList<>(products);
        productsCopy = new ArrayList<>(this.products);
    }

//    public View getView(int position, View view, ViewGroup parent){
//        LayoutInflater inflater = context.getLayoutInflater();
//        View rowView = inflater.inflate(R.layout.list_item_products, null, true);
//        TextView nameText = rowView.findViewById(R.id.list_item_products_name);
//        nameText.setText(products.get(position).getName());
//        ImageView foodImage = rowView.findViewById(R.id.list_item_products_image);
//        foodImage.setImageBitmap(products.get(position).getImage());
//        ImageButton basketButton = rowView.findViewById(R.id.list_item_products_basket_button);
//        basketButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Product product = products.get(position);
//                ArrayList<Product> shoppingList =  DataHolder.getInstance().shoppingList;
//                if(!shoppingList.contains(product)) {
//                    shoppingList.add(products.get(position));
//                }
//            }
//        });
//        return rowView;
//    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.list_item_products, parent, false);
        }

        TextView nameText = view.findViewById(R.id.list_item_products_name);
        ImageView foodImage = view.findViewById(R.id.list_item_products_image);
        ImageButton basketButton = view.findViewById(R.id.list_item_products_basket_button);

        Product product = products.get(position);
        nameText.setText(product.getName());
        foodImage.setImageBitmap(product.getImage());

        basketButton.setOnClickListener(v -> {
            ArrayList<Product> shoppingList = DataHolder.getInstance().shoppingList;
            if (!shoppingList.contains(product)) {
                shoppingList.add(product);
            }
        });

        return view;
    }



    @Override
    public int getCount() {
        return products.size();
    }

    public void filter(String query) {
        products.clear();
        if (query.isEmpty()) {
            products.addAll(productsCopy);
        } else {
            products.addAll(productsCopy.stream()
                    .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList()));
        }
        notifyDataSetChanged();
    }


}