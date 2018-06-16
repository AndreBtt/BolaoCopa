package com.bolaoliga.bolao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Jogo;

public class preTodasApostas extends AppCompatActivity {

    TextView j1,j2,j3,j4;

    private List<Jogo> jogos = new ArrayList<>();

    private ProgressDialog mProgressDialog;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("/Jogo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pre_aposta);

        j1 = findViewById(R.id.j1);
        j2 = findViewById(R.id.j2);
        j3 = findViewById(R.id.j3);
        j4 = findViewById(R.id.j4);

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("SEU MERDA, TO PENSANDO...");

        mProgressDialog.show();

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogo novo = dataSnapshot.getValue(Jogo.class);

                jogos.add(novo);

                if(jogos.size() == 4){

                    String x = jogos.get(0).t1 + " x " + jogos.get(0).t2;

                    j1.setText(x);

                    x = jogos.get(1).t1 + " x " + jogos.get(1).t2;

                    j2.setText(x);

                    x = jogos.get(2).t1 + " x " + jogos.get(2).t2;

                    j3.setText(x);

                    x = jogos.get(3).t1 + " x " + jogos.get(3).t2;

                    j4.setText(x);

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

        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent proxima_pagina = new Intent(preTodasApostas.this,todasApostas.class);

                proxima_pagina.putExtra("t1",jogos.get(0).t1);
                proxima_pagina.putExtra("t2",jogos.get(0).t2);
                proxima_pagina.putExtra("numeroJogo", 1);

                startActivity(proxima_pagina);
            }
        });

        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent proxima_pagina = new Intent(preTodasApostas.this,todasApostas.class);

                proxima_pagina.putExtra("t1",jogos.get(1).t1);
                proxima_pagina.putExtra("t2",jogos.get(1).t2);
                proxima_pagina.putExtra("numeroJogo", 2);

                startActivity(proxima_pagina);
            }
        });

        j3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent proxima_pagina = new Intent(preTodasApostas.this,todasApostas.class);

                proxima_pagina.putExtra("t1",jogos.get(2).t1);
                proxima_pagina.putExtra("t2",jogos.get(2).t2);
                proxima_pagina.putExtra("numeroJogo", 3);

                startActivity(proxima_pagina);
            }
        });

        j4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent proxima_pagina = new Intent(preTodasApostas.this,todasApostas.class);

                proxima_pagina.putExtra("t1",jogos.get(3).t1);
                proxima_pagina.putExtra("t2",jogos.get(3).t2);
                proxima_pagina.putExtra("numeroJogo", 4);

                startActivity(proxima_pagina);
            }
        });
    }
}
