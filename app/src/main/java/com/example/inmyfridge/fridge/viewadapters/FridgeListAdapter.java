package com.example.inmyfridge.fridge.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.fridge.models.FridgeItem;
import com.example.inmyfridge.R;
import com.example.inmyfridge.foods.models.ProductUnit;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**Used for displaying fridge items list from database*/
public class FridgeListAdapter extends ArrayAdapter<FridgeItem> {
private final Activity context;
ArrayList<FridgeItem> fridgeItems;
ArrayList<FridgeItem> fridgeItemsCopy;

public FridgeListAdapter(Activity context, ArrayList<FridgeItem> fridgeItems){
    super(context, R.layout.list_item_fridge, fridgeItems);
    this.context = context;
    this.fridgeItems = fridgeItems;
    fridgeItemsCopy = new ArrayList<>(fridgeItems);
}

public View getView(int position, View view, ViewGroup parent){
    FridgeItem fridgeItem = fridgeItems.get(position);
    ProductUnit productUnit = fridgeItem.getProductUnit();
    LayoutInflater inflater = context.getLayoutInflater();
    View rowView = inflater.inflate(R.layout.list_item_fridge, null, true);
    TextView nameText =  rowView.findViewById(R.id.list_item_fridge_name);
    TextView weightText = rowView.findViewById(R.id.list_item_fridge_weight);
    ImageView image = rowView.findViewById(R.id.list_item_fridge_picture);
    TextView countText = rowView.findViewById(R.id.list_item_fridge_count);
    nameText.setText(DataHolder.getInstance().getFoodItemById(productUnit.getParentId()).getName());
    weightText.setText(Integer.toString(productUnit.getWeight())+"g");
    countText.setText(Integer.toString(fridgeItem.getCount())+"x");
    image.setImageBitmap(productUnit.getImage());
    return rowView;
}

}
