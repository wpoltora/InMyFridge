package com.example.inmyfridge.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.models.ProductUnit;
import com.example.inmyfridge.R;


public class NewProductUnitFragment extends Fragment {

    private int position;
    private View view;
    public NewProductUnitFragment() {
        // Required empty public constructor
    }

    public NewProductUnitFragment(int position){
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_new_unit, container, false);

        Button finishButton = (Button) view.findViewById(R.id.new_unit_add_button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewUnitToProduct(view);
            }
        });
        CheckBox looseCheckBox = (CheckBox)view.findViewById(R.id.new_unit_loose_checkbox);
        LinearLayout weightInputWrapper = (LinearLayout) view.findViewById(R.id.new_unit_weight_input_wrapper);
        looseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(looseCheckBox.isChecked()){
                    weightInputWrapper.setVisibility(View.INVISIBLE);
                }
                else{
                    weightInputWrapper.setVisibility(View.VISIBLE);
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void addNewUnitToProduct(View view){
        ProductUnit productUnit = new ProductUnit.ProductUnitBuilder()
                .weight(parseProductInteger(R.id.new_unit_weight_input))
                .image(parseProductImage(R.id.new_unit_image_input))
                .isLoose(((CheckBox)this.view.findViewById(R.id.new_unit_loose_checkbox)).isChecked())
                .parentId(DataHolder.getInstance().productList.get(position).getId())
                .build();
        DataHolder.getInstance().productList.get(position).getFoodUnitItems().add(productUnit);
        getParentFragmentManager().popBackStack();
    }

    public int parseProductInteger(int id){
        return Integer.parseInt(((EditText)view.findViewById(id)).getText().toString());
    }

    public String parseProductString(int id){
        return ((EditText)view.findViewById(id)).getText().toString();
    }

    public Bitmap parseProductImage(int id){
        ImageView imageView = view.findViewById(id);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        return bitmap;
    }

}