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
import com.example.inmyfridge.models.ProductUnit;
import com.example.inmyfridge.models.Product;

import java.util.ArrayList;
import java.util.List;


/**Used for an expandable list of products and their individual weight units*/
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private final List<Product> itemList;
    private final LayoutInflater inflater;

    public ExpandableListViewAdapter(Context context, List<Product> itemList) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
    }

    @Override
    public ProductUnit getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).getFoodUnitItems().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).getFoodUnitItems().size();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        View resultView = convertView;
        final ProductUnit item = getChild(groupPosition, childPosition);

        final String expandedListText = item.getWeight() + "g" +
                "";
        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_foods_child, null);
        }
        TextView expandedListTextView = resultView.findViewById(R.id.expandable_foods_child_name);
        expandedListTextView.setText(expandedListText);

        final ImageButton addItemButton = resultView.findViewById(R.id.expandable_foods_child_add_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FridgeItem fridgeItem =  DataHolder.getInstance().getFridgeItemById(item.getId());
                ArrayList<FridgeItem> fridgeItems = DataHolder.getInstance().fridgeItemList;

                if(fridgeItem != null){
                    fridgeItem.increaseCount();
                }
                else {
                    fridgeItems.add(new FridgeItem(item));
                }
            }
        });

        return resultView;
    }

    @Override
    public Product getGroup(int groupPosition) {
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
        String listTitle = getGroup(groupPosition).getName();
        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_foods_parent, null);
        }

        TextView listTitleTextView = resultView.findViewById(R.id.expandable_foods_parent_name);
        listTitleTextView.setText(listTitle);

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