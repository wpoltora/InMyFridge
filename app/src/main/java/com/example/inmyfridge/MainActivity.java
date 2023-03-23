package com.example.inmyfridge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.inmyfridge.data.AppDatabase;
import com.example.inmyfridge.databinding.ActivityMainBinding;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.recipes.NewRecipeActivity;
import com.example.inmyfridge.foods.fragments.ProductsFragment;
import com.example.inmyfridge.fridge.fragments.FridgeFragment;
import com.example.inmyfridge.foods.fragments.NewProductFragment;
import com.example.inmyfridge.recipes.fragments.RecipeFragment;
import com.example.inmyfridge.shopping.ShoppingFragment;
import com.example.inmyfridge.tracking.fragments.TrackerFragment;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel viewModel;
    ActivityMainBinding binding;
    ProductUnit scannedUnit = null;
    AppDatabase appDatabase = MyApp.getInstance().getDatabase();
    /**ACTIVITY METHODS*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.fridge:
                    replaceFragment(new FridgeFragment(), false);
                    break;
                case R.id.recipes:
                    replaceFragment(new RecipeFragment(), false);
                    break;
                case R.id.foods:
                    replaceFragment(new ProductsFragment(), false);
                    break;
                case R.id.tracker:
                    replaceFragment(new TrackerFragment(), false);
                    break;
                case R.id.shopping:
                    replaceFragment(new ShoppingFragment(), false);
                    break;

            }
        return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data");
            int width = photo.getWidth();
            int height = photo.getHeight();
            int newDimension = Math.min(width, height);
            Bitmap square = Bitmap.createBitmap(photo, (width - newDimension) / 2, (height - newDimension) / 2, newDimension, newDimension);

            ImageView imageView = findViewById(R.id.new_product_image_input);
            imageView.setImageTintList(null);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = imageView.getHeight() * 2;
            params.width = imageView.getWidth() *2;
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(square);
        }

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data");
            int width = photo.getWidth();
            int height = photo.getHeight();
            int newDimension = Math.min(width, height);
            Bitmap square = Bitmap.createBitmap(photo, (width - newDimension) / 2, (height - newDimension) / 2, newDimension, newDimension);
            ImageView imageView = findViewById(R.id.new_unit_image_input);
            imageView.setImageTintList(null);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = imageView.getHeight() * 2;
            params.width = imageView.getWidth() *2;
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(square);
        }

        if(requestCode==10){
            replaceFragment(new RecipeFragment(), false);
        }

        if(requestCode == 11  && resultCode == RESULT_OK){
            String resultString = data.getStringExtra(Intents.Scan.RESULT);
            if (resultString == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
            else {
                if(scannedUnit != null) {
                    scannedUnit.setBarcode(resultString);
                    viewModel.updateProductUnit(scannedUnit);
                    Toast.makeText(getBaseContext(), "Added barcode " + resultString, Toast.LENGTH_LONG).show();
                    scannedUnit = null;
                }
            }
        }

        if(requestCode == 12  && resultCode == RESULT_OK){
            String resultString = data.getStringExtra(Intents.Scan.RESULT);
            if (resultString == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
            else {
                ProductUnit productUnit = viewModel.getProductUnitByBarcode(resultString);
                productUnit.increaseCount(1);
                String productName = viewModel.getProductById(productUnit.getProductId()).getName();
                Toast.makeText(getBaseContext(), "Added " + productName + " To fridge", Toast.LENGTH_LONG).show();
                replaceFragment(new FridgeFragment(), false);
            }
        }
    }

    /**FRAGMENT MANAGEMENT*/
    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void addProductPage(View view){
        replaceFragment(new NewProductFragment(), true);
    }

    public void addToFridge(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setRequestCode(12);
        intentIntegrator.initiateScan();
    }

    public void addRecipePage(View view){
        Intent switchActivityIntent = new Intent(this, NewRecipeActivity.class);
        startActivityForResult(switchActivityIntent,10);}



    public void returnToPrev(View view){
        onBackPressed();
    }

    /**PRODUCT MANAGEMENT*/
    public void selectProductImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    public void selectUnitImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 2);
        }
    }

    public void scanBarcode(ProductUnit productUnit){
        this.scannedUnit = productUnit;
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setRequestCode(11);
        intentIntegrator.initiateScan();
    }
}