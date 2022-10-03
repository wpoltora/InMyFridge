package com.example.inmyfridge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.models.ProductUnit;
import com.example.inmyfridge.models.Product;
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
        final String expandedListText = (String) Integer.toString(getChild(groupPosition, childPosition).getWeight());
        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_foods_child, null);
        }
        TextView expandedListTextView = (TextView) resultView.findViewById(R.id.expandable_foods_child_name);
        expandedListTextView.setText(expandedListText);
        final ProductUnit item = getChild(groupPosition, childPosition);
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
        String listTitle = (String) getGroup(groupPosition).getName();
        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_foods_parent, null);
        }

        TextView listTitleTextView = (TextView) resultView.findViewById(R.id.expandable_foods_parent_name);
        listTitleTextView.setText(listTitle);
        final Product item = getGroup(groupPosition);

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