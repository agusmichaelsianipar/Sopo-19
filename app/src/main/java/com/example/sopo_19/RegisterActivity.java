package com.example.sopo_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText DEmailId, DPasswordId, NohpId;
    Button DMasukId, DDaftarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        DEmailId = findViewById(R.id.txtDEmail);
        DPasswordId = findViewById(R.id.txtDPassword);
        NohpId = findViewById(R.id.txtDNohp);
        DMasukId = findViewById(R.id.btnDMasuk);
        DDaftarId = findViewById(R.id.btnDDaftar);
        DDaftarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = DEmailId.getText().toString();
                String pwd = DPasswordId.getText().toString();
                String nohp = NohpId.getText().toString();

                if(email.isEmpty()){
                    DEmailId.setError("Silahkan Masukkan Email Anda");
                    DEmailId.requestFocus();
                }else if(pwd.isEmpty()){
                    DPasswordId.setError("Silahkan Masukkan Password Anda");
                    DPasswordId.requestFocus();
                }else if(nohp.isEmpty()){
                    NohpId.setError("Tolong Masukkan Nomor Yang Valid");
                    NohpId.requestFocus();
                }else if(email.isEmpty()&&pwd.isEmpty()&&nohp.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Silahkan Isi Data Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty()&&pwd.isEmpty()&&nohp.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Proses Daftar Gagal, Silahkan Coba Lagi",Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(RegisterActivity.this,ApdTersediaActivity.class));
                                finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Silahkan Coba Lagi",Toast.LENGTH_SHORT).show();
                }
            }
        });

        DMasukId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
