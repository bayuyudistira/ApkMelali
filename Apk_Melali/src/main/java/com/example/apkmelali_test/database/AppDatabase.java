package com.example.apkmelali_test.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apkmelali_test.dao.InvoiceDao;
import com.example.apkmelali_test.dao.TicketDao;
import com.example.apkmelali_test.dao.TiketOrderDao;
import com.example.apkmelali_test.dao.UserDao;
import com.example.apkmelali_test.entity.Invoice;
import com.example.apkmelali_test.entity.Ticket;
import com.example.apkmelali_test.entity.TiketOrder;
import com.example.apkmelali_test.entity.User;

@Database(entities = {Invoice.class, TiketOrder.class, Ticket.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InvoiceDao invoiceDao();
    public abstract TiketOrderDao tiketOrderDao();
    public abstract TicketDao ticketDao();
    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration() // Ensure migration handling
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
