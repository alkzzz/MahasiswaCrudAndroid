package com.example.alkzzz.mahasiswa;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MahasiswaDao {

    // BROWSE
    @Query("SELECT * FROM tb_mahasiswa ORDER BY nama ASC")
    LiveData<List<Mahasiswa>> getAllMahasiswa();

    // EDIT
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMahasiswa(Mahasiswa mahasiswa);

    // ADD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMahasiswa(Mahasiswa mahasiswa);

    // DELETE
    @Delete
    void deleteMahasiswa(Mahasiswa mahasiswa);

    // DELETE ALL
    @Query("DELETE FROM tb_mahasiswa")
    void deleteAll();
}
