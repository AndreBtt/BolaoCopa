package com.bolaoliga.bolao;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import Model.Usuario;

public class aposta extends AppCompatActivity {

    private TextView mNomeT1, mNomeT2;

    private EditText mGol1, mGol2;

    private String t1,t2;

    private ProgressDialog mProgressDialog;

    private Button sorte,enviar;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("/Usuario");

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_aposta);

        mNomeT1 = findViewById(R.id.nome_t1);

        mNomeT2 = findViewById(R.id.nome_t2);

        mGol1 = findViewById(R.id.gols_t1);

        mGol2 = findViewById(R.id.gols_t2);

        sorte = findViewById(R.id.sorte);

        enviar = findViewById(R.id.enviar);

        sorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random rand = new Random();

                int n = rand.nextInt(10);

                mGol1.setText(Integer.toString(n));

                n = rand.nextInt(10);

                mGol2.setText(Integer.toString(n));

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Bundle b = this.getIntent().getExtras();

        t1 = "";
        t2 = "";

        if(b != null){
            t1 = b.getString("t1");
            t2 = b.getString("t2");
        }

        mNomeT1.setText(t1);
        mNomeT2.setText(t2);

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("SEU MERDA, TO PENSANDO...");

        mProgressDialog.show();

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Usuario novo = dataSnapshot.getValue(Usuario.class);

                if(novo.email.equals(user.getEmail())){

                    mGol1.setText(Integer.toString(novo.time1));
                    mGol2.setText(Integer.toString(novo.time2));

                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
