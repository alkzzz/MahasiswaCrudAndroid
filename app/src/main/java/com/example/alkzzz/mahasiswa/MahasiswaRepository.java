package com.example.alkzzz.mahasiswa;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MahasiswaRepository {
    private MahasiswaDao mahasiswaDao;
    private LiveData<List<Mahasiswa>> listMahasiswa;

    MahasiswaRepository(Application application) {
        MahasiswaDatabase db = MahasiswaDatabase.getDbMahasiswa(application);
        mahasiswaDao = db.mahasiswaDao();
        listMahasiswa = mahasiswaDao.getAllMahasiswa();
    }

    LiveData<List<Mahasiswa>> getListMahasiswa() {
        return listMahasiswa;
    }

    public void addMahasiswa (Mahasiswa mahasiswa) {
        new insertAsyncTask(mahasiswaDao).execute(mahasiswa);
    }

    private static class insertAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDao mAsyncTaskDao;

        insertAsyncTask(MahasiswaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            mAsyncTaskDao.addMahasiswa(mahasiswas[0]);
            return null;
        }
    }

    public void updateMahasiswa (Mahasiswa mahasiswa) {
        new updateAsyncTask(mahasiswaDao).execute(mahasiswa);
    }

    private static class updateAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDao mAsyncTaskDao;

        updateAsyncTask(MahasiswaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            mAsyncTaskDao.updateMahasiswa(mahasiswas[0]);
            return null;
        }
    }

    public void deleteMahasiswa (Mahasiswa mahasiswa) {
        new deleteAsyncTask(mahasiswaDao).execute(mahasiswa);
    }

    private static class deleteAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDao mAsyncTaskDao;

        deleteAsyncTask(MahasiswaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            mAsyncTaskDao.deleteMahasiswa(mahasiswas[0]);
            return null;
        }
    }

}
