package Model;

public class JogoUsuario {

    public int t1,t2;

    public boolean voto;

    public JogoUsuario(int t1, int t2, boolean voto){
        this.t1 = t1;
        this.t2 = t2;
        this.voto = voto;
    }

    public JogoUsuario(boolean ini){
        this.t1 = 0;
        this.t2 = 0;
        this.voto = false;
    }

    public JogoUsuario(){};

}
