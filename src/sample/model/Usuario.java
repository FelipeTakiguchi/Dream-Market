package sample.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private Cidade cidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Usuario(String nome, String email, String senha, Cidade cidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
    }

    public Usuario(int id, String nome, String email, String senha, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
    }
}
