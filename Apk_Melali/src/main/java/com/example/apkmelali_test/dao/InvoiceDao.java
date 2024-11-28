package com.example.apkmelali_test.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apkmelali_test.entity.Invoice;

@Dao
public interface InvoiceDao {
    @Insert
    long insert(Invoice invoice);

    @Query("SELECT * FROM invoice WHERE id = :id")
    Invoice getInvoiceById(int id);
}


