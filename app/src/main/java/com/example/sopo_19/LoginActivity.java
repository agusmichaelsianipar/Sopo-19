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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText DEmailId, DPasswordId;
    Button DMasukId, DDaftarId;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        DEmailId = findViewById(R.id.txtMEmail);
        DPasswordId = findViewById(R.id.txtMPassword);
        DMasukId = findViewById(R.id.btnMMasuk);
        DDaftarId = findViewById(R.id.btnMDaftar);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this,"Anda Sudah Login",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Silahkan Login Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                }
            }
        };
        DMasukId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = DEmailId.getText().toString();
                String pwd = DPasswordId.getText().toString();

                if(email.isEmpty()){
                    DEmailId.setError("Silahkan Masukkan Email Anda");
                    DEmailId.requestFocus();
                }else if(pwd.isEmpty()){
                    DPasswordId.setError("Silahkan Masukkan Password Anda");
                    DPasswordId.requestFocus();
                }else if(email.isEmpty()&&pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Silahkan Isi Data Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty()&&pwd.isEmpty())){
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Gagal Silahkan Coba Lagi",Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intToHome = new Intent(LoginActivity.this,ApdTersediaActivity.class);
                                startActivity(intToHome);
                                finish();
                            }
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this,"Silahkan Coba Lagi",Toast.LENGTH_SHORT).show();
                }

            }
        });
        DDaftarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToDaftar = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intToDaftar);
            }
        });
    }
    //    @Override
    protected void OnStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
