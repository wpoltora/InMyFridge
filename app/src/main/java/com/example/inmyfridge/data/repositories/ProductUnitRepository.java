package com.example.inmyfridge.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.inmyfridge.data.AppDatabase;
import com.example.inmyfridge.data.dao.ProductUnitDAO;
import com.example.inmyfridge.data.model.ProductUnit;

import java.util.List;
import java.util.UUID;

public class ProductUnitRepository {
    private ProductUnitDAO productUnitDAO;
    private List<ProductUnit> allProductUnits;

    public ProductUnitRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        productUnitDAO = database.productUnitDAO();
        allProductUnits = productUnitDAO.getAll();
    }

    public List<ProductUnit> getAllProductUnits(){
        return allProductUnits;
    }

    public void insert(ProductUnit productUnit){
        new ProductUnitRepository.InsertProductUnitAsyncTask(productUnitDAO).execute(productUnit);
    }

    public void delete(ProductUnit productUnit){
        new ProductUnitRepository.DeleteProductUnitAsyncTask(productUnitDAO).execute(productUnit);
    }

    public void update(ProductUnit productUnit){
        new ProductUnitRepository.UpdateProductUnitAsyncTask(productUnitDAO).execute(productUnit);
    }

    public ProductUnit getProductUnitByID(UUID id){
        //return productUnitDAO.getDataByID(id);
        return allProductUnits.stream().filter(productUnit -> productUnit.getId().equals(id)).findFirst().orElse(null);
    }

    public ProductUnit getProductUnitByBarcode(String barcode){
        //return productUnitDAO.getDataByBarcode(barcode);
        return allProductUnits.stream().filter(productUnit -> productUnit.getBarcode().equals(barcode)).findFirst().get();
    }

    public void removeProductUnitByParentID(UUID id){
        new DeleteProductUnitByParentIDAsyncTask(productUnitDAO).execute(id);
    }

    public List<ProductUnit> getAllProductUnitsByProductID(UUID id){
        return productUnitDAO.getAllByProductId(id);
    }

    private static class DeleteProductUnitByParentIDAsyncTask extends AsyncTask<UUID, Void, Void> {
        private ProductUnitDAO productUnitDAO;

        private DeleteProductUnitByParentIDAsyncTask(ProductUnitDAO productUnitDAO) {
            this.productUnitDAO = productUnitDAO;
        }

        @Override
        protected Void doInBackground(UUID... uuids) {
            productUnitDAO.removeByProductID(uuids[0]);
            return null;
        }
    }

    private static class InsertProductUnitAsyncTask extends AsyncTask<ProductUnit, Void, Void> {
        private ProductUnitDAO productUnitDAO;

        private InsertProductUnitAsyncTask(ProductUnitDAO productUnitDAO){
            this.productUnitDAO = productUnitDAO;
        }

        @Override
        protected Void doInBackground(ProductUnit... productUnits) {
            productUnitDAO.insertAll(productUnits);
            return null;
        }
    }

    private static class DeleteProductUnitAsyncTask extends AsyncTask<ProductUnit, Void, Void>{
        private ProductUnitDAO productUnitDAO;

        private DeleteProductUnitAsyncTask(ProductUnitDAO productUnitDAO){
            this.productUnitDAO = productUnitDAO;
        }

        @Override
        protected Void doInBackground(ProductUnit... productUnits) {
            productUnitDAO.delete(productUnits[0]);
            return null;
        }
    }

    private static class UpdateProductUnitAsyncTask extends AsyncTask<ProductUnit, Void, Void>{
        private ProductUnitDAO productUnitDAO;

        private UpdateProductUnitAsyncTask(ProductUnitDAO productUnitDAO){
            this.productUnitDAO = productUnitDAO;
        }

        @Override
        protected Void doInBackground(ProductUnit... productUnits) {
            productUnitDAO.updateProductUnit(productUnits[0]);
            return null;
        }
    }


}
