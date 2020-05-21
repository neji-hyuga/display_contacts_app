package com.alura.displaycontactsapp.db;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alura.displaycontactsapp.model.TypePhoneNumber;

import static com.alura.displaycontactsapp.model.TypePhoneNumber.HOME;

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
    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Student_new` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `name` TEXT, " +
                    "`phone` TEXT, " +
                    "`mobile` TEXT, " +
                    "`email` TEXT," +
                    " `startDay` INTEGER)");

            database.execSQL("INSERT INTO Student_new (id, name, phone, email, startDay )" +
                    "SELECT id, name, phone, email, startDay FROM Student");

            database.execSQL("DROP TABLE Student");

            database.execSQL("ALTER TABLE Student_new RENAME TO Student");
        }
    };
    public static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Student_new` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`name` TEXT, " +
                    "`email` TEXT," +
                    "`startDay` INTEGER)");

            database.execSQL("INSERT INTO Student_new (id, name, phone, email, startDay )" +
                    "SELECT id, name, phone, email, startDay FROM Student");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Phone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `number` TEXT, " +
                    "`typePhone` TEXT," +
                    "`studentId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO Phone (phone, id)" +
                    "SELECT phone, id FROM Student");

            database.execSQL("UPDATE phone SET typePhone =?", new TypePhoneNumber[] {HOME});

            database.execSQL("DROP TABLE Student");

            database.execSQL("ALTER TABLE Student_new RENAME TO Student");
        }
    };
    static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3,
            MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};
}
