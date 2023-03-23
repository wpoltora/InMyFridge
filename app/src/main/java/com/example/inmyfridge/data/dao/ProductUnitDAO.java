package com.example.inmyfridge.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inmyfridge.data.model.ProductUnit;

import java.util.List;
import java.util.UUID;

@Dao
public interface ProductUnitDAO {
    @Query("SELECT * FROM PRODUCTUNIT")
   List<ProductUnit> getAll();

    @Query("SELECT * FROM PRODUCTUNIT WHERE productId = :productId")
    List<ProductUnit> getAllByProductId(UUID productId);

    @Query("SELECT * FROM PRODUCTUNIT WHERE ID = :id")
    ProductUnit getDataByID(UUID id);

    @Query("SELECT * FROM PRODUCTUNIT WHERE BARCODE = :barcode")
    ProductUnit getDataByBarcode(String barcode);

    @Query("DELETE FROM PRODUCTUNIT WHERE PRODUCTID = :id")
    void removeByProductID(UUID id);

    @Update
    void updateProductUnit(ProductUnit productUnit);


    @Insert
    void insertAll(ProductUnit... productUnits);

    @Delete
    void delete(ProductUnit productUnit);
}
