package com.example.alkzzz.mahasiswa;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract class MahasiswaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "db_mahasiswa";
    private static MahasiswaDatabase dbMahasiswa;
    public abstract MahasiswaDao mahasiswaDao();

    public static MahasiswaDatabase getDbMahasiswa(Context context) {
        if(dbMahasiswa == null) {
            synchronized (MahasiswaDatabase.class) {
                if(dbMahasiswa==null) {
                    dbMahasiswa = Room.databaseBuilder(context.getApplicationContext(), MahasiswaDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return dbMahasiswa;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new IsiDatabase(dbMahasiswa).execute();
        }
    };

    private static class IsiDatabase extends AsyncTask<Void, Void, Void> {

        private final MahasiswaDao mDao;

        IsiDatabase(MahasiswaDatabase db) {
            mDao = db.mahasiswaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

//            Mahasiswa mahasiswa = new Mahasiswa("Halimah");
//            mDao.addMahasiswa(mahasiswa);
//            mahasiswa = new Mahasiswa("Heru");
//            mDao.addMahasiswa(mahasiswa);
            return null;
        }
    }

}
