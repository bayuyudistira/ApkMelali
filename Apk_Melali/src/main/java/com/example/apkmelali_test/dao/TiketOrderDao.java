package com.example.apkmelali_test.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apkmelali_test.entity.TiketOrder;

@Dao
public interface TiketOrderDao {
    @Insert
    void insert(TiketOrder tiketOrder);

    @Query("SELECT * FROM pemesanan_tiket WHERE id = :id")
    TiketOrder getInvoiceById(int id);
}
