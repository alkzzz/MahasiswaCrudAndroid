package com.example.alkzzz.mahasiswa;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MahasiswaListAdapter.OnItemClickListener {

    private MahasiswaViewModel mahasiswaViewModel;
    private MahasiswaListAdapter mahasiswaListAdapter;
    public static final int NEW_MAHASISWA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MahasiswaAddActivity.class);
                startActivityForResult(intent, NEW_MAHASISWA_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv_mahasiswa);
        mahasiswaListAdapter = new MahasiswaListAdapter(this, this);
        recyclerView.setAdapter(mahasiswaListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mahasiswaViewModel = ViewModelProviders.of(this).get(MahasiswaViewModel.class);
        mahasiswaViewModel.getListMahasiswa().observe(this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(@Nullable List<Mahasiswa> mahasiswas) {
                mahasiswaListAdapter.setListMahasiswa(mahasiswas);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Mahasiswa mahasiswa = mahasiswaListAdapter.getMahasiswaAtPosition(position);

                        mahasiswaViewModel.deleteMahasiswa(mahasiswa);
                    }
                });

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_MAHASISWA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Mahasiswa mahasiswa = new Mahasiswa(data.getStringExtra(MahasiswaAddActivity.EXTRA_REPLY));
            mahasiswaViewModel.addMahasiswa(mahasiswa);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Tidak dapat menyimpan data kosong",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTextClick(View v, Mahasiswa mahasiswa) {
        Intent intent = new Intent(this, MahasiswaDetailActivity.class);
        intent.putExtra("mahasiswa", mahasiswa);
        startActivity(intent);
    }
}
