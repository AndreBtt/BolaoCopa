package Model;

public class Usuario {

    public int pontos;

    public String nome,email;

    public JogoUsuario jogo1, jogo2, jogo3, jogo4;

    public Usuario(){};

    public Usuario(String nome, String email){
        jogo1 = new JogoUsuario(true);
        jogo2 = new JogoUsuario(true);
        jogo3 = new JogoUsuario(true);
        jogo4 = new JogoUsuario(true);
        this.nome = nome;
        this.email = email;
    }

}
