package com.alura.displaycontactsapp.db;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

 class ContactsMigrations {
     public static final Migration MIGRATION_1_2 = new Migration(1, 2) {

         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE student ADD COLUMN surname TEXT");
         }
     };
     public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             // to create new table with new attributes
             database.execSQL("CREATE TABLE IF NOT EXISTS `Student_new`" +
                     " (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                     "`name` TEXT," +
                     " `phone` TEXT," +
                     " `email` TEXT)");

             //copy old data to new table
             database.execSQL("INSERT INTO Student_new (id, name, phone, email )" +
                     "SELECT id, name, phone, email FROM Student");

             //remove old table
             database.execSQL("DROP TABLE Student");

             // rename new table with old's name table
             database.execSQL("ALTER TABLE Student_new RENAME TO Student");
         }
     };
     private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE Student ADD COLUMN startDay INTEGER");
         }
     };
     static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};
}
