package com.example.inmyfridge.foods.viewadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.ProductUnit;

import java.util.ArrayList;

public class ProductUnitAdapter extends ArrayAdapter<ProductUnit> {
    Context context;

    public ProductUnitAdapter (Context context, ArrayList<ProductUnit> algorithmList)
    {
        super(context, 0, algorithmList);
        this.context = context;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.text_view);
        ProductUnit currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            if (currentItem.isLoose()){
                textViewName.setText("Loose");
            }
            else {
                textViewName.setText(currentItem.getWeight() + "g");
            }
        }
        return convertView;
    }
}