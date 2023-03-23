package com.example.inmyfridge.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inmyfridge.data.model.Product;

import java.util.List;
import java.util.UUID;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM PRODUCT")
    List<Product> getAll();

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);

    @Update
    void updateProduct(Product product);

    @Query("SELECT * FROM PRODUCT WHERE ID = :id")
    Product getDataByID(UUID id);

}
