package com.bolaoliga.bolao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.JogoUsuario;
import Model.Usuario;

public class configuracoes extends AppCompatActivity {

    private Button fim,trocar,calcula;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("/Jogo");

    private DatabaseReference mBancoUsuario = FirebaseDatabase.getInstance().getReference("/Usuario");

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_configuracoes);

        fim = findViewById(R.id.fim);

        trocar = findViewById(R.id.trocar);

        calcula = findViewById(R.id.fimJogo);

        calcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int gol1 = Integer.parseInt( ((EditText) findViewById(R.id.g1)).getText().toString());
                final int gol2 = Integer.parseInt( ((EditText) findViewById(R.id.g2)).getText().toString());

                EditText num = findViewById(R.id.numero);

                final int numero = Integer.parseInt(num.getText().toString());

                // 1 se t1 ganhou
                // 2 se t2 ganhou
                // 0 se empate
                final int venceu;

                if(gol1 > gol2) venceu = 1;
                else if(gol1 < gol2) venceu = 2;
                else venceu = 0;

                mBancoUsuario.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Usuario novo = dataSnapshot.getValue(Usuario.class);
                        if(novo.nome != null && !novo.nome.isEmpty()) usuarios.add(novo);

                        if(usuarios.size() == 8){

                            if(numero == 1){

                                for(int i = 0; i < 8; i++){

                                    if(usuarios.get(i).jogo1.voto == false) continue;

                                    int t1 = usuarios.get(i).jogo1.t1;
                                    int t2 = usuarios.get(i).jogo1.t2;

                                    int venceuAux;

                                    if(t1 > t2) venceuAux = 1;
                                    else if(t1 < t2) venceuAux = 2;
                                    else venceuAux = 0;

                                    if(t1 == gol1 && t2 == gol2) usuarios.get(i).pontos += 5;

                                    else if(venceuAux == venceu) usuarios.get(i).pontos += 3;

                                    mBancoUsuario.child(Integer.toString(i+1)).setValue(usuarios.get(i));
                                }
                            }

                            if(numero == 2){

                                for(int i = 0; i < 8; i++){

                                    if(usuarios.get(i).jogo1.voto == false) continue;

                                    int t1 = usuarios.get(i).jogo2.t1;
                                    int t2 = usuarios.get(i).jogo2.t2;

                                    int venceuAux;

                                    if(t1 > t2) venceuAux = 1;
                                    else if(t1 < t2) venceuAux = 2;
                                    else venceuAux = 0;

                                    if(t1 == gol1 && t2 == gol2) usuarios.get(i).pontos += 5;

                                    else if(venceuAux == venceu) usuarios.get(i).pontos += 3;

                                    mBancoUsuario.child(Integer.toString(i+1)).setValue(usuarios.get(i));
                                }
                            }

                            if(numero == 3){

                                for(int i = 0; i < 8; i++){

                                    if(usuarios.get(i).jogo1.voto == false) continue;

                                    int t1 = usuarios.get(i).jogo3.t1;
                                    int t2 = usuarios.get(i).jogo3.t2;

                                    int venceuAux;

                                    if(t1 > t2) venceuAux = 1;
                                    else if(t1 < t2) venceuAux = 2;
                                    else venceuAux = 0;

                                    if(t1 == gol1 && t2 == gol2) usuarios.get(i).pontos += 5;

                                    else if(venceuAux == venceu) usuarios.get(i).pontos += 3;

                                    mBancoUsuario.child(Integer.toString(i+1)).setValue(usuarios.get(i));
                                }
                            }

                            if(numero == 4){

                                for(int i = 0; i < 8; i++){

                                    if(usuarios.get(i).jogo1.voto == false) continue;

                                    int t1 = usuarios.get(i).jogo4.t1;
                                    int t2 = usuarios.get(i).jogo4.t2;

                                    int venceuAux;

                                    if(t1 > t2) venceuAux = 1;
                                    else if(t1 < t2) venceuAux = 2;
                                    else venceuAux = 0;

                                    if(t1 == gol1 && t2 == gol2) usuarios.get(i).pontos += 5;

                                    else if(venceuAux == venceu) usuarios.get(i).pontos += 3;

                                    mBancoUsuario.child(Integer.toString(i+1)).setValue(usuarios.get(i));
                                }
                            }

                            startActivity(new Intent(configuracoes.this, principal.class));
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

        fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText num = findViewById(R.id.numero);

                String numero = num.getText().toString();

                //LimparBanco();

                mBanco.child(numero).child("fim").setValue(true);

                startActivity(new Intent(configuracoes.this, principal.class));
            }
        });

        trocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText num = findViewById(R.id.numero);

                String numero = num.getText().toString();

                if(numero.equals("1")){

                    for(int i = 1; i <= 8; i++) {

                        String index = Integer.toString(i);

                        mBancoUsuario.child(index).child("jogo1").setValue(new JogoUsuario(true));
                        mBancoUsuario.child(index).child("jogo2").setValue(new JogoUsuario(true));
                        mBancoUsuario.child(index).child("jogo3").setValue(new JogoUsuario(true));
                        mBancoUsuario.child(index).child("jogo4").setValue(new JogoUsuario(true));
                    }
                }

                String t1 = ((EditText) findViewById(R.id.t1)).getText().toString();

                String t2 = ((EditText) findViewById(R.id.t2)).getText().toString();

                mBanco.child(numero).child("fim").setValue(false);
                mBanco.child(numero).child("t1").setValue(t1);
                mBanco.child(numero).child("t2").setValue(t2);

                startActivity(new Intent(configuracoes.this, principal.class));
            }
        });
    }

    void LimparBanco(){
        mBancoUsuario.child("1").setValue(new Usuario("Rafa", "rafael@unb.com"));
        mBancoUsuario.child("2").setValue(new Usuario("Andrezinho", "andre@unb.com"));
        mBancoUsuario.child("3").setValue(new Usuario("Nonato", "arthur@unb.com"));
        mBancoUsuario.child("4").setValue(new Usuario("Pedrão", "pedro@unb.com"));
        mBancoUsuario.child("5").setValue(new Usuario("Marcão", "marcos@unb.com"));
        mBancoUsuario.child("6").setValue(new Usuario("Valim", "vitor@unb.com"));
        mBancoUsuario.child("7").setValue(new Usuario("Fetu", "alfredo@unb.com"));
        mBancoUsuario.child("8").setValue(new Usuario("Skilo", "alexandre@unb.com"));
    }
}
