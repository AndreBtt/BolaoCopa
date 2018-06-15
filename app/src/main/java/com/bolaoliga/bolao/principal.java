package com.bolaoliga.bolao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Objects;

import Model.Jogo;
import Model.Usuario;

public class principal extends AppCompatActivity {

    private TextView mAposta, mRank, mConfig, mSair, mOutrasApostas;

    private String t1,t2;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("/Jogo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        mAposta = findViewById(R.id.apostar);

        mRank = findViewById(R.id.ranking);

        mSair = findViewById(R.id.sair);

        mConfig = findViewById(R.id.config);

        mOutrasApostas = findViewById(R.id.outras_apostas);

        mOutrasApostas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                t1 = "";
                t2 = "";

                mBanco.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Jogo novo = dataSnapshot.getValue(Jogo.class);


                        Intent proxima_pagina = new Intent(principal.this,todasApostas.class);

                        proxima_pagina.putExtra("t1",novo.t1);
                        proxima_pagina.putExtra("t2",novo.t2);

                        startActivity(proxima_pagina);
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
        });

        mSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(principal.this,login.class));
            }
        });

        mAposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                t1 = "";
                t2 = "";

                mBanco.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Jogo novo = dataSnapshot.getValue(Jogo.class);

                        Intent proxima_pagina = new Intent(principal.this,aposta.class);

                        proxima_pagina.putExtra("t1",novo.t1);
                        proxima_pagina.putExtra("t2",novo.t2);

                        if(!novo.fim) startActivity(proxima_pagina);
                        else{
                            Toast.makeText(principal.this, "Tu acha que eu sou troxa ?? Quer votar no meio do jogo ??",
                                    Toast.LENGTH_SHORT).show();
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
        });

        mRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(principal.this,rank.class));
            }
        });

        mConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user == null)
                    Toast.makeText(principal.this, "Tu deve ser idiota ne ??", Toast.LENGTH_SHORT).show();
                else if(user.getEmail().equals("andre@unb.com"))
                    startActivity(new Intent(principal.this, configuracoes.class));
                else
                    Toast.makeText(principal.this, "Tu deve ser idiota ne ??", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
