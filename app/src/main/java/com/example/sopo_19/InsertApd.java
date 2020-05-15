package com.example.sopo_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sopo_19.fragment.KananFragment;
import com.example.sopo_19.fragment.TengahFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertApd extends AppCompatActivity {
    EditText txt_namacmp,txt_coverall,txt_mask,txt_kota,txt_kontak;
    Button tbl_simpan;
    DatabaseReference databaseReference;
//    Apd apd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_apd);

//        TengahFragment fragment = new TengahFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().add(R.id.insert_layout,fragment).commit();

        databaseReference= FirebaseDatabase.getInstance().getReference("dataapd");

        txt_namacmp = (EditText)findViewById(R.id.et_namacmp);
        txt_coverall = (EditText)findViewById(R.id.et_coverall);
        txt_mask = (EditText)findViewById(R.id.et_mask);
        txt_kota = (EditText)findViewById(R.id.et_kota);
        txt_kontak = (EditText)findViewById(R.id.et_kontak);
        tbl_simpan = (Button)findViewById(R.id.tbl_simpan);


        tbl_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addApd();
            }
        });
    }

    private void addApd(){
        String namacmp = txt_namacmp.getText().toString().trim();
        long coverall = Long.parseLong(txt_coverall.getText().toString().trim());
        long mask = Long.parseLong(txt_mask.getText().toString().trim());
        String kota = txt_kota.getText().toString().trim();
        String kontak = txt_kontak.getText().toString().trim();

        if(!TextUtils.isEmpty(namacmp)){
            String id = databaseReference.push().getKey();
            Apd apd = new Apd(namacmp,kota,coverall,mask,kontak);
            databaseReference.child(id).setValue(apd);
            Toast.makeText(InsertApd.this,"Sukses Menambahkan Data APD",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(InsertApd.this,"Tolong isi kolom yang tersedia",Toast.LENGTH_LONG).show();
        }

    }
}
