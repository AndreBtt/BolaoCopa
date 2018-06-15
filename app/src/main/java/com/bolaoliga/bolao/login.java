package com.bolaoliga.bolao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {

    private EditText mEmail;

    private EditText mSenha;

    private Button mLogar;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_logar);

        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.email);

        mSenha = findViewById(R.id.senha);

        mLogar = findViewById(R.id.logar);

        mLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String senha = mSenha.getText().toString().trim();

                if(valido()){
                    SignIn(email,senha);
                }
            }
        });
    }

    private boolean valido() {
        boolean valid = true;

        String email = mEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Tu é burro ? To em branco !");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mSenha.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            mSenha.setError("Tu é burro ? Falta senha !");
            valid = false;
        } else {
            if(password.length() < 6){
                Toast.makeText(login.this,"Mano é no minimo 6 digitos é burro ?",Toast.LENGTH_LONG).show();
                valid = false;
            }
            mSenha.setError(null);
        }

        return valid;
    }

    private void SignIn(String email, String password) {

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("SEU MERDA, TO PENSANDO...");

        mProgressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success
                            startActivity(new Intent(login.this, principal.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "Tu não lembra mais sua senha/nome nao porra ??",
                                    Toast.LENGTH_SHORT).show();
                        }

                        mProgressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) startActivity(new Intent(login.this, principal.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(login.this,login.class));
        super.onBackPressed();
    }
}
