package com.bolaoliga.bolao;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Usuario;

public class todasApostas extends AppCompatActivity {

    private TextView p1,p2,p3,p4,p5,p6,p7,p8,times;

    private ProgressDialog mProgressDialog;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("/Usuario");

    private List<Usuario> usuarios = new ArrayList<>();

    private String t1,t2;

    private int numeroJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_todas_apostas);

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("SEU MERDA, TO PENSANDO...");

        mProgressDialog.show();

        Bundle b = this.getIntent().getExtras();

        t1 = "";
        t2 = "";
        numeroJogo = 0;

        if(b != null){
            t1 = b.getString("t1");
            t2 = b.getString("t2");
            numeroJogo = b.getInt("numeroJogo");
        }

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);
        p7 = findViewById(R.id.p7);
        p8 = findViewById(R.id.p8);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Usuario novo = dataSnapshot.getValue(Usuario.class);
                if(novo.nome != null && !novo.nome.isEmpty()) usuarios.add(novo);

                if(usuarios.size() == 8){

                    times = findViewById(R.id.times);

                    StringBuilder ss = new StringBuilder();

                    ss.append("");
                    ss.append(t1);
                    ss.append(" x ");
                    ss.append(t2);

                    times.setText(ss);

                    Collections.sort(usuarios, new Comparator<Usuario>() {
                        public int compare(Usuario c1, Usuario c2) {
                            if(c1.pontos > c2.pontos) return -1;
                            return 1;
                        }
                    });

                    if(numeroJogo == 1){

                        ss = new StringBuilder();

                        ss.append("");
                        ss.append(usuarios.get(0).nome);
                        ss.append(" ");
                        if(usuarios.get(0).jogo1.voto){
                            ss.append(usuarios.get(0).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(0).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p1.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(1).nome);
                        ss.append(" ");
                        if(usuarios.get(1).jogo1.voto){
                            ss.append(usuarios.get(1).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(1).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p2.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(2).nome);
                        ss.append(" ");
                        if(usuarios.get(2).jogo1.voto){
                            ss.append(usuarios.get(2).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(2).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p3.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(3).nome);
                        ss.append(" ");
                        if(usuarios.get(3).jogo1.voto){
                            ss.append(usuarios.get(3).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(3).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p4.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(4).nome);
                        ss.append(" ");
                        if(usuarios.get(4).jogo1.voto){
                            ss.append(usuarios.get(4).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(4).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p5.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(5).nome);
                        ss.append(" ");
                        if(usuarios.get(5).jogo1.voto){
                            ss.append(usuarios.get(5).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(5).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p6.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(6).nome);
                        ss.append(" ");
                        if(usuarios.get(6).jogo1.voto){
                            ss.append(usuarios.get(6).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(6).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p7.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(7).nome);
                        ss.append(" ");
                        if(usuarios.get(7).jogo1.voto){
                            ss.append(usuarios.get(7).jogo1.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(7).jogo1.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p8.setText(ss);
                    }

                    if(numeroJogo == 2){

                        ss = new StringBuilder();

                        ss.append("");
                        ss.append(usuarios.get(0).nome);
                        ss.append(" ");
                        if(usuarios.get(0).jogo2.voto){
                            ss.append(usuarios.get(0).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(0).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p1.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(1).nome);
                        ss.append(" ");
                        if(usuarios.get(1).jogo2.voto){
                            ss.append(usuarios.get(1).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(1).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p2.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(2).nome);
                        ss.append(" ");
                        if(usuarios.get(2).jogo2.voto){
                            ss.append(usuarios.get(2).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(2).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p3.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(3).nome);
                        ss.append(" ");
                        if(usuarios.get(3).jogo2.voto){
                            ss.append(usuarios.get(3).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(3).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p4.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(4).nome);
                        ss.append(" ");
                        if(usuarios.get(4).jogo2.voto){
                            ss.append(usuarios.get(4).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(4).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p5.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(5).nome);
                        ss.append(" ");
                        if(usuarios.get(5).jogo2.voto){
                            ss.append(usuarios.get(5).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(5).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p6.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(6).nome);
                        ss.append(" ");
                        if(usuarios.get(6).jogo2.voto){
                            ss.append(usuarios.get(6).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(6).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p7.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(7).nome);
                        ss.append(" ");
                        if(usuarios.get(7).jogo2.voto){
                            ss.append(usuarios.get(7).jogo2.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(7).jogo2.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p8.setText(ss);
                    }

                    if(numeroJogo == 3){

                        ss = new StringBuilder();

                        ss.append("");
                        ss.append(usuarios.get(0).nome);
                        ss.append(" ");
                        if(usuarios.get(0).jogo3.voto){
                            ss.append(usuarios.get(0).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(0).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p1.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(1).nome);
                        ss.append(" ");
                        if(usuarios.get(1).jogo3.voto){
                            ss.append(usuarios.get(1).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(1).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p2.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(2).nome);
                        ss.append(" ");
                        if(usuarios.get(2).jogo3.voto){
                            ss.append(usuarios.get(2).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(2).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p3.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(3).nome);
                        ss.append(" ");
                        if(usuarios.get(3).jogo3.voto){
                            ss.append(usuarios.get(3).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(3).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p4.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(4).nome);
                        ss.append(" ");
                        if(usuarios.get(4).jogo3.voto){
                            ss.append(usuarios.get(4).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(4).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p5.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(5).nome);
                        ss.append(" ");
                        if(usuarios.get(5).jogo3.voto){
                            ss.append(usuarios.get(5).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(5).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p6.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(6).nome);
                        ss.append(" ");
                        if(usuarios.get(6).jogo3.voto){
                            ss.append(usuarios.get(6).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(6).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p7.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(7).nome);
                        ss.append(" ");
                        if(usuarios.get(7).jogo3.voto){
                            ss.append(usuarios.get(7).jogo3.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(7).jogo3.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p8.setText(ss);
                    }

                    if(numeroJogo == 4){

                        ss = new StringBuilder();

                        ss.append("");
                        ss.append(usuarios.get(0).nome);
                        ss.append(" ");
                        if(usuarios.get(0).jogo4.voto){
                            ss.append(usuarios.get(0).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(0).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p1.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(1).nome);
                        ss.append(" ");
                        if(usuarios.get(1).jogo4.voto){
                            ss.append(usuarios.get(1).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(1).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p2.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(2).nome);
                        ss.append(" ");
                        if(usuarios.get(2).jogo4.voto){
                            ss.append(usuarios.get(2).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(2).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p3.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(3).nome);
                        ss.append(" ");
                        if(usuarios.get(3).jogo4.voto){
                            ss.append(usuarios.get(3).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(3).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p4.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(4).nome);
                        ss.append(" ");
                        if(usuarios.get(4).jogo4.voto){
                            ss.append(usuarios.get(4).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(4).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p5.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(5).nome);
                        ss.append(" ");
                        if(usuarios.get(5).jogo4.voto){
                            ss.append(usuarios.get(5).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(5).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p6.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(6).nome);
                        ss.append(" ");
                        if(usuarios.get(6).jogo4.voto){
                            ss.append(usuarios.get(6).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(6).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p7.setText(ss);

                        ss = new StringBuilder();
                        ss.append("");
                        ss.append(usuarios.get(7).nome);
                        ss.append(" ");
                        if(usuarios.get(7).jogo4.voto){
                            ss.append(usuarios.get(7).jogo4.t1);
                            ss.append(" x ");
                            ss.append(usuarios.get(7).jogo4.t2);
                        }
                        else ss.append(" - Troxa n votou");

                        p8.setText(ss);
                    }

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
