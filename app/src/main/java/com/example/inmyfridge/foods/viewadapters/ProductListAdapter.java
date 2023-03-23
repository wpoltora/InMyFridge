package com.example.inmyfridge.foods.viewadapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.fragments.ProductDetailsFragment;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**Used for displaying food items list from database*/
public class ProductListAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    ArrayList<Product> products;
    ArrayList<Product> productsCopy;
    private ItemCallback callback;

    public ProductListAdapter(Activity context, ArrayList<Product> products, ItemCallback callback){
        super(context, R.layout.list_item_products, products);
        this.context = context;
        this.products = products;
        this.callback = callback;

        products.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
        this.products = new ArrayList<>(products);
        productsCopy = new ArrayList<>(this.products);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_products, parent, false);
        }

        TextView nameText = view.findViewById(R.id.list_item_products_name);
        ImageView foodImage = view.findViewById(R.id.list_item_products_image);
        ImageButton basketButton = view.findViewById(R.id.list_item_products_basket_button);
        ImageButton eatButton = view.findViewById(R.id.list_item_products_eat_button);
        ImageButton toFridgeButton = view.findViewById(R.id.list_item_products_fridge_button);
        ImageButton removeButton = view.findViewById(R.id.list_item_products_remove_button);
        ImageButton settingsButton = view.findViewById(R.id.list_item_products_settings);

        Product product = products.get(position);
        nameText.setText(product.getName());
        foodImage.setImageBitmap(product.getImage());

        basketButton.setOnClickListener(v -> {
            ArrayList<Product> shoppingList = DataHolder.getInstance().shoppingList;
            if (!shoppingList.contains(product)) {
                shoppingList.add(product);
            }
        });

        settingsButton.setOnClickListener(view13 -> {
            MainActivity activity = (MainActivity) context;
            activity.replaceFragment(new ProductDetailsFragment(product, position), true);
        });

        removeButton.setOnClickListener(view12 -> {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Remove");
            alertDialog.setMessage("Are you sure you want to remove " + product.getName() + "?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    (dialog, which) -> {
                        product.setVisible(false);
                        callback.onProductUpdated(product);
                        products.remove(product);
                        notifyDataSetChanged();
                    });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });

        eatButton.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = inflater.inflate(R.layout.eat_dialog_layout, null);

            // Add an EditText widget to the dialog
            final TextView name = dialogView.findViewById(R.id.eat_dialog_name);
            name.setText("Eat " + product.getName());
            final EditText amountInput = dialogView.findViewById(R.id.eat_dialog_amount);
            builder.setView(dialogView);

            builder.setPositiveButton("OK", (dialog, which) -> {
                int amount = Integer.parseInt(amountInput.getText().toString());
                LocalDate currentDate = LocalDate.now();
                DailyData storedData = callback.getDailyDataByDate(currentDate);
                if(storedData == null){
                    DailyData dailyData = new DailyData(currentDate);
                    dailyData.addMeal(product, amount);
                    callback.onDailyDataInserted(dailyData);
                }
                else{
                    storedData.addMeal(product,amount);
                    callback.onDailyDataUpdated(storedData);
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                // Add action to be performed when Cancel is clicked
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        toFridgeButton.setOnClickListener(new View.OnClickListener() {
            ProductUnit selectedUnit = null;

            @Override
            public void onClick(View view) {
                ArrayList<ProductUnit> productUnits = new ArrayList<>(callback.getProductUnitsByProductID(product.getId()));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogView = inflater.inflate(R.layout.add_fridge_dialog_layout, null);
                final TextView name = dialogView.findViewById(R.id.add_fridge_dialog_name);
                name.setText("Add " + product.getName() +" to fridge");
                final EditText amountInput = dialogView.findViewById(R.id.add_fridge_dialog_amount);
                Spinner spinner = dialogView.findViewById(R.id.add_fridge_dialog_spinner);
                spinner.setAdapter(new ProductUnitAdapter(getContext(), productUnits));

                spinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent,
                                                       View view, int position, long id)
                            {

                                // It returns the clicked item.
                                selectedUnit = (ProductUnit) parent.getItemAtPosition(position);
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent)
                            {
                            }
                        });

                builder.setView(dialogView);

                builder.setPositiveButton("OK", (dialog, which) -> {
                    int amount = Integer.parseInt(amountInput.getText().toString());
                    selectedUnit.increaseCount(amount);
                    callback.onProductUnitUpdated(selectedUnit);
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    // Add action to be performed when Cancel is clicked
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });



        return view;
    }



    public void update(ArrayList<Product> products){
        this.products = products;
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

    public interface ItemCallback {
        void onProductUpdated(Product product);
        DailyData getDailyDataByDate(LocalDate date);
        void onDailyDataInserted(DailyData dailyData);
        void onDailyDataUpdated(DailyData dailyData);
        List<ProductUnit> getProductUnitsByProductID(UUID id);
        void onProductUnitUpdated(ProductUnit productUnit);
    }

}