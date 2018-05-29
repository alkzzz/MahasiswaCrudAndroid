package com.example.alkzzz.mahasiswa;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MahasiswaDetailActivity extends AppCompatActivity {
    private MahasiswaViewModel mahasiswaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_detail);

        Intent intent = getIntent();
        final Mahasiswa mahasiswa = intent.getParcelableExtra("mahasiswa");

        final TextView textView = findViewById(R.id.tv_id);
        final EditText editText = findViewById(R.id.et_nama);
        final Button buttonUpdate = findViewById(R.id.bt_update);

        editText.setText(mahasiswa.getNama());
        textView.setText("ID = "+mahasiswa.getId());


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText.getText())) {
                    Toast.makeText(getApplicationContext(), "Tidak bisa mengupdate data kosong", Toast.LENGTH_SHORT).show();
                } else {
                    mahasiswa.setNama(editText.getText().toString());
                    mahasiswaViewModel = ViewModelProviders.of(MahasiswaDetailActivity.this).get(MahasiswaViewModel.class);
                    mahasiswaViewModel.updateMahasiswa(mahasiswa);
                    finish();
                }
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                buttonUpdate.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonUpdate.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                buttonUpdate.setEnabled(true);
            }
        };

        editText.addTextChangedListener(textWatcher);

    }
}
