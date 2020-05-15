package com.example.sopo_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertRs extends AppCompatActivity {
    EditText txt_namars,txt_coverall,txt_mask,txt_kota,txt_kontak;
    Button tbl_simpan;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rs_daftar);

        databaseReference= FirebaseDatabase.getInstance().getReference("datars");

        txt_namars = (EditText)findViewById(R.id.et_namars);
        txt_coverall = (EditText)findViewById(R.id.et_coverallrs);
        txt_mask = (EditText)findViewById(R.id.et_maskrs);
        txt_kota = (EditText)findViewById(R.id.et_kotars);
        txt_kontak = (EditText)findViewById(R.id.et_kontakrs);
        tbl_simpan = (Button)findViewById(R.id.tbl_simpanrs);

        tbl_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRs();
            }
        });
    }
    private void addRs(){
        String namars = txt_namars.getText().toString().trim();
        long coverall = Long.parseLong(txt_coverall.getText().toString().trim());
        long mask = Long.parseLong(txt_mask.getText().toString().trim());
        String kota = txt_kota.getText().toString().trim();
        String kontak = txt_kontak.getText().toString().trim();

        if(!TextUtils.isEmpty(namars)){
            String id = databaseReference.push().getKey();
            Rs rs = new Rs(namars,kota,coverall,mask,kontak);
            databaseReference.child(id).setValue(rs);
            Toast.makeText(InsertRs.this,"Sukses Menambahkan Data APD",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(InsertRs.this,"Tolong isi kolom yang tersedia",Toast.LENGTH_LONG).show();
        }

    }
}
