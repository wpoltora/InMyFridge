package com.example.inmyfridge.fridge.viewadapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.models.ProductUnit;
import com.example.inmyfridge.fridge.objectadapters.ProductFridgeItemAdapter;
import com.example.inmyfridge.fridge.models.FridgeItem;
import com.example.inmyfridge.foods.models.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExpandableFridgeListAdapter extends BaseExpandableListAdapter {
    private final List<ProductFridgeItemAdapter> itemList = new ArrayList<>();
    private final LayoutInflater inflater;
    private final List<ProductFridgeItemAdapter> itemListCopy;

    public ExpandableFridgeListAdapter (Context context) {

        for (FridgeItem fridgeItem:DataHolder.getInstance().fridgeItemList) {
            this.itemList.add(new ProductFridgeItemAdapter(fridgeItem.getProduct()));
        }
        itemList.sort((Comparator.comparing(t -> t.getProduct().getName().toLowerCase())));
        this.inflater = LayoutInflater.from(context);
        itemListCopy =  new ArrayList<>(itemList);
    }

    @Override
    public FridgeItem getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).getFridgeItems().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).getFridgeItems().size();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        View resultView = convertView;
        final FridgeItem item = getChild(groupPosition, childPosition);

        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_fridge_child, null);
        }

        TextView weightTextView = resultView.findViewById(R.id.expandable_fridge_child_weight);
        weightTextView.setText(item.getProductUnit().getWeight()+"g");
        TextView countTextView = resultView.findViewById(R.id.expandable_fridge_child_count);
        countTextView.setText("x"+item.getCount());


        return resultView;
    }

    @Override
    public ProductFridgeItemAdapter getGroup(int groupPosition) {
        return itemList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return itemList.size();
    }

    @Override
    public long getGroupId(final int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View theConvertView, ViewGroup parent) {
        View resultView = theConvertView;
        String listTitle = getGroup(groupPosition).getProduct().getName();
        String weight = getGroup(groupPosition).getCombinedWeight() +"g";
        Bitmap image = getGroup(groupPosition).getProduct().getImage();
        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_fridge_parent, null);
        }

        ImageView parentImageView = resultView.findViewById(R.id.expandable_fridge_parent_image);
        parentImageView.setImageBitmap(image);
        TextView listTitleTextView = resultView.findViewById(R.id.expandable_fridge_parent_name);
        listTitleTextView.setText(listTitle);
        TextView weightTextView = resultView.findViewById(R.id.expandable_fridge_parent_weight);
        weightTextView.setText(weight);

        return resultView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filter(String query) {
        itemList.clear();
        if (query.isEmpty()) {
            itemList.addAll(itemListCopy);
        } else {
            itemList.addAll(itemListCopy.stream()
                    .filter(fridgeItem -> fridgeItem.getProduct().getName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList()));
        }
        notifyDataSetChanged();
    }
}
