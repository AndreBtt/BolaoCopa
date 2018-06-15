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

public class rank extends AppCompatActivity {

    private TextView p1,p2,p3,p4,p5,p6,p7,p8;

    private ProgressDialog mProgressDialog;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("/Usuario");

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_rank);

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("SEU MERDA, TO PENSANDO...");

        mProgressDialog.show();

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

                    Collections.sort(usuarios, new Comparator<Usuario>() {
                        public int compare(Usuario c1, Usuario c2) {
                            if(c1.pontos > c2.pontos) return -1;
                            return 1;
                        }
                    });

                    StringBuilder ss = new StringBuilder();

                    ss.append("1. ");
                    ss.append(usuarios.get(0).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(0).pontos);

                    p1.setText(ss);

                    ss = new StringBuilder();
                    ss.append("2. ");
                    ss.append(usuarios.get(1).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(1).pontos);

                    p2.setText(ss);

                    ss = new StringBuilder();
                    ss.append("3. ");
                    ss.append(usuarios.get(2).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(2).pontos);

                    p3.setText(ss);

                    ss = new StringBuilder();
                    ss.append("4. ");
                    ss.append(usuarios.get(3).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(3).pontos);

                    p4.setText(ss);

                    ss = new StringBuilder();
                    ss.append("5. ");
                    ss.append(usuarios.get(4).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(4).pontos);

                    p5.setText(ss);

                    ss = new StringBuilder();
                    ss.append("6. ");
                    ss.append(usuarios.get(5).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(5).pontos);

                    p6.setText(ss);

                    ss = new StringBuilder();
                    ss.append("7. ");
                    ss.append(usuarios.get(6).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(6).pontos);

                    p7.setText(ss);

                    ss = new StringBuilder();
                    ss.append("8. ");
                    ss.append(usuarios.get(7).nome);
                    ss.append(" ");
                    ss.append(usuarios.get(7).pontos);

                    p8.setText(ss);

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
