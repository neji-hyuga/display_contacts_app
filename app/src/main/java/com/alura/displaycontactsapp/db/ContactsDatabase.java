package com.alura.displaycontactsapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.alura.displaycontactsapp.db.converter.ConvertCalendar;
import com.alura.displaycontactsapp.db.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;

import static com.alura.displaycontactsapp.db.ContactsMigrations.ALL_MIGRATIONS;

@Database(entities = {Student.class}, version = 4, exportSchema = false)
@TypeConverters({ConvertCalendar.class})
public abstract class ContactsDatabase extends RoomDatabase {

    private static final String CONTACTS_DB = "contacts.db";


    public abstract StudentDAO getRoomStudentDAO();

    public static ContactsDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, ContactsDatabase.class, CONTACTS_DB)
                .allowMainThreadQueries()
                .addMigrations(ALL_MIGRATIONS)
                .build();
    }
}

