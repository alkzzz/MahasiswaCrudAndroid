package com.example.alkzzz.mahasiswa;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private MahasiswaRepository mahasiswaRepository;
    private LiveData<List<Mahasiswa>> listMahasiswa;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);

        mahasiswaRepository = new MahasiswaRepository(application);
        listMahasiswa = mahasiswaRepository.getListMahasiswa();
    }

    LiveData<List<Mahasiswa>> getListMahasiswa() {
        return listMahasiswa;
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaRepository.addMahasiswa(mahasiswa);
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) { mahasiswaRepository.updateMahasiswa(mahasiswa); }

    public void deleteMahasiswa (Mahasiswa mahasiswa) { mahasiswaRepository.deleteMahasiswa(mahasiswa); }
}
