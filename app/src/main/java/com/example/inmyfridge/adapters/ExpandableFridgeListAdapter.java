package com.example.inmyfridge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.models.FridgeItem;
import com.example.inmyfridge.models.Product;
import com.example.inmyfridge.models.ProductUnit;

import java.util.ArrayList;
import java.util.List;

public class ExpandableFridgeListAdapter extends BaseExpandableListAdapter {
    private final List<ProductFridgeItemAdapter> itemList = new ArrayList<>();
    private final LayoutInflater inflater;

    public ExpandableFridgeListAdapter (Context context) {
        for (Product product:DataHolder.getInstance().productList
        ) {
            /**TEMPORARY SOLUTION!!!*/
            boolean exists = false;
            for (FridgeItem fridgeItem:DataHolder.getInstance().fridgeItemList
                 ) {

                if( fridgeItem.getProductUnit().getParentId() == product.getId()){
                    exists = true;
                }
            }
            if(exists) {
                this.itemList.add(new ProductFridgeItemAdapter(product));
            }
        }

        /**-------------------------------------------------*/
        this.inflater = LayoutInflater.from(context);
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
        String listTitle = getGroup(groupPosition).product.getName();
        String weight = getGroup(groupPosition).getCombinedWeight() +"g";
        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_fridge_parent, null);
        }

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
}
