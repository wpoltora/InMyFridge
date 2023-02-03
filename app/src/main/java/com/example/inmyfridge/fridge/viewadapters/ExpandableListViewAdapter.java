package com.example.inmyfridge.fridge.viewadapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.fridge.models.FridgeItem;
import com.example.inmyfridge.foods.models.ProductUnit;
import com.example.inmyfridge.foods.models.Product;

import java.util.ArrayList;
import java.util.List;


/**Used for an expandable list of products and their individual weight units*/
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private final List<Product> itemList;
    private final LayoutInflater inflater;
    Context context;

    public ExpandableListViewAdapter(Context context, List<Product> itemList) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.context = context;
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

        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_foods_child, null);
        }

        final ProductUnit item = getChild(groupPosition, childPosition);

        final String expandedListText ="x " + item.getWeight() + "g" + "";

        final Bitmap image = item.getImage();

        final EditText weightInput = resultView.findViewById(R.id.expandable_foods_child_weight_input);

        TextView expandedListTextView = resultView.findViewById(R.id.expandable_foods_child_name);

        if (item.isLoose()){
            expandedListTextView.setText("g Loose");
        }
        else{
            expandedListTextView.setText(expandedListText);
        }

        ImageView imageView = resultView.findViewById(R.id.expandable_foods_child_image);
        imageView.setImageBitmap(image);

        final ImageButton addItemButton = resultView.findViewById(R.id.expandable_foods_child_add_button);

        weightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1 && s.toString().equals("0")) {
                    weightInput.setText("");
                }
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(weightInput.getText().toString().isEmpty()){
                    Toast.makeText(context, "Input a correct value", Toast.LENGTH_SHORT).show();
                    return;
                }

                final FridgeItem fridgeItem =  DataHolder.getInstance().getFridgeItemByUnitId(item.getId());
                ArrayList<FridgeItem> fridgeItems = DataHolder.getInstance().fridgeItemList;

                int count = Integer.parseInt(weightInput.getText().toString());

                if(fridgeItem != null){
                    fridgeItem.increaseCount(count);
                }
                else {
                    FridgeItem newFridgeItem = new FridgeItem(item);
                    newFridgeItem.increaseCount(count-1);
                    fridgeItems.add(newFridgeItem);
                }
                Toast.makeText(context, "Added "+ item.getWeight()*count +"g to fridge", Toast.LENGTH_SHORT).show();
                weightInput.setText("");
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
        Bitmap image = getGroup(groupPosition).getImage();

        if (resultView == null) {
            resultView = inflater.inflate(R.layout.expandable_foods_parent, null);
        }

        TextView listTitleTextView = resultView.findViewById(R.id.expandable_foods_parent_name);
        listTitleTextView.setText(listTitle);

        ImageView imageView = resultView.findViewById(R.id.expandable_foods_parent_image);
        imageView.setImageBitmap(image);

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