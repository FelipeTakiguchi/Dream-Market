package sample.model;

public class Produto {
    private int id;
    private String nome;
    private String marcca;
    private String descricao;
    private float valor;
    private int id_responsavel;

    public Produto(int id, String nome, String marcca, String descricao, float valor, int id_responsavel) {
        this.id = id;
        this.nome = nome;
        this.marcca = marcca;
        this.descricao = descricao;
        this.valor = valor;
        this.id_responsavel = id_responsavel;
    }

    public Produto(String nome, String marcca, String descricao, float valor, int id_responsavel) {
        this.nome = nome;
        this.marcca = marcca;
        this.descricao = descricao;
        this.valor = valor;
        this.id_responsavel = id_responsavel;
    }

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

    public String getMarcca() {
        return marcca;
    }

    public void setMarcca(String marcca) {
        this.marcca = marcca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(int id_responsavel) {
        this.id_responsavel = id_responsavel;
    }
}
