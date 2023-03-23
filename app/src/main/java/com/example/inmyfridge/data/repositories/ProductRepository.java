package com.example.inmyfridge.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.inmyfridge.data.AppDatabase;
import com.example.inmyfridge.data.dao.ProductDAO;
import com.example.inmyfridge.data.model.Product;

import java.util.List;
import java.util.UUID;

public class ProductRepository {
    private ProductDAO productDAO;
    private List<Product> allProducts;

    public ProductRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        productDAO = database.productDao();
        allProducts = productDAO.getAll();
    }

    public List<Product> getAllProducts(){
        return allProducts;
    }

    public void insert(Product product){
        new InsertProductAsyncTask(productDAO).execute(product);
    }

    public void delete(Product product){
        new DeleteProductAsyncTask(productDAO).execute(product);
    }

    public void update(Product product){
        new UpdateProductAsyncTask(productDAO).execute(product);
    }

    public Product getProductByID(UUID id){
        return allProducts.stream().filter(product -> product.getId().equals(id)).findFirst().get();
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDAO productDAO;

        private InsertProductAsyncTask(ProductDAO productDAO){
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.insertAll(products);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDAO productDAO;

        private DeleteProductAsyncTask(ProductDAO productDAO){
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.delete(products[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDAO productDAO;

        private UpdateProductAsyncTask(ProductDAO productDAO){
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.updateProduct(products[0]);
            return null;
        }
    }


}
