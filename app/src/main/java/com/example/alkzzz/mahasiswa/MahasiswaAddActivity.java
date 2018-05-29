package com.example.alkzzz.mahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MahasiswaAddActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.mahasiswa.REPLY";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);

        editText = findViewById(R.id.et_mahasiswa);

        final Button button = findViewById(R.id.btn_simpan);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editText.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nama = editText.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, nama);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
