package com.bolaoliga.bolao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Random;

import Model.Jogo;
import Model.Usuario;

public class aposta extends AppCompatActivity {

    private TextView mNomeT1, mNomeT2;

    private EditText mGol1, mGol2;

    private String t1,t2;

    private ProgressDialog mProgressDialog;

    private String chave;

    private Button sorte,enviar;

    private Usuario usuarioAtual;

    private int numeroJogo;

    private DatabaseReference mBancoJogo = FirebaseDatabase.getInstance().getReference("/Jogo");

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

                final List<Jogo> jogos = new ArrayList<>();

                mBancoJogo.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Jogo novo = dataSnapshot.getValue(Jogo.class);

                        jogos.add(novo);

                        if(jogos.size() == 4){

                            if(numeroJogo == 1) {
                                if(jogos.get(0).fim){
                                    Toast.makeText(aposta.this, "Pode votar mais nao, brother",
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                usuarioAtual.jogo1.t1 = Integer.parseInt(mGol1.getText().toString());
                                usuarioAtual.jogo1.t2 = Integer.parseInt(mGol2.getText().toString());
                                usuarioAtual.jogo1.voto = true;
                            }

                            if(numeroJogo == 2) {
                                if(jogos.get(1).fim){
                                    Toast.makeText(aposta.this, "Não vai roubar nao !!",
                                            Toast.LENGTH_SHORT).show();

                                    return;
                                }
                                usuarioAtual.jogo2.t1 = Integer.parseInt(mGol1.getText().toString());
                                usuarioAtual.jogo2.t2 = Integer.parseInt(mGol2.getText().toString());
                                usuarioAtual.jogo2.voto = true;
                            }

                            if(numeroJogo == 3) {
                                if(jogos.get(2).fim){
                                    Toast.makeText(aposta.this, "Não vai roubar nao !!",
                                            Toast.LENGTH_SHORT).show();

                                    return;
                                }
                                usuarioAtual.jogo3.t1 = Integer.parseInt(mGol1.getText().toString());
                                usuarioAtual.jogo3.t2 = Integer.parseInt(mGol2.getText().toString());
                                usuarioAtual.jogo3.voto = true;
                            }

                            if(numeroJogo == 4) {
                                if(jogos.get(3).fim){
                                    Toast.makeText(aposta.this, "Não vai roubar nao !!",
                                            Toast.LENGTH_SHORT).show();

                                    return;
                                }
                                usuarioAtual.jogo4.t1 = Integer.parseInt(mGol1.getText().toString());
                                usuarioAtual.jogo4.t2 = Integer.parseInt(mGol2.getText().toString());
                                usuarioAtual.jogo4.voto = true;
                            }

                            mBanco.child(chave).setValue(usuarioAtual);

                            startActivity(new Intent(aposta.this, principal.class));

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

        Bundle b = this.getIntent().getExtras();

        t1 = "";
        t2 = "";
        numeroJogo = 0;

        if(b != null){
            t1 = b.getString("t1");
            t2 = b.getString("t2");
            numeroJogo = b.getInt("numeroJogo");
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

                    chave = dataSnapshot.getKey();

                    usuarioAtual = novo;

                    int gol1,gol2;

                    gol1 = gol2 = 0;

                    if(numeroJogo == 1) {
                        gol1 = usuarioAtual.jogo1.t1;
                        gol2 = usuarioAtual.jogo1.t2;
                    }

                    if(numeroJogo == 2) {
                        gol1 = usuarioAtual.jogo2.t1;
                        gol2 = usuarioAtual.jogo2.t2;
                    }

                    if(numeroJogo == 3) {
                        gol1 = usuarioAtual.jogo3.t1;
                        gol2 = usuarioAtual.jogo3.t2;
                    }

                    if(numeroJogo == 4) {
                        gol1 = usuarioAtual.jogo4.t1;
                        gol2 = usuarioAtual.jogo4.t2;
                    }

                    mGol1.setText(Integer.toString(gol1));
                    mGol2.setText(Integer.toString(gol2));

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
