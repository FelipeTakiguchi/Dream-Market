package sample.model;

import java.sql.Date;

public class Item {
    private int id;
    private Boolean estoque;
    private float preco;
    private Produto produto;
    private Comercio comercio;
    private Date dataAtualizacao;
    private UsuarioAdm responsavel;

    public String mostra(){
        return produto.getDescricao();
    }

    @Override
    public String toString() {
        return getProduto().getNome()
                +"["+
                getComercio().getNome()
                +"]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEstoque() {
        return estoque;
    }

    public void setEstoque(Boolean estoque) {
        this.estoque = estoque;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public UsuarioAdm getResponsavel() {
        return responsavel;
    }

    public String getComercioEstado(){
        return this.comercio.getCidade().getEstado().getNome();
    }

    public String getComercioCidade(){
        return this.comercio.getCidade().getNome();
    }

    public String getProdutoNome(){
        return this.produto.getNome();
    }

    public String getProdutoMarca(){
        return this.produto.getMarcca();
    }

    public String getComercioNome() {
        return this.comercio.getNome();
    }

    public void setResponsavel(UsuarioAdm responsavel) {
        this.responsavel = responsavel;
    }

    public Item(Boolean estoque, float preco, Produto produto, Comercio comercio, Date dataAtualizacao, UsuarioAdm responsavel) {
        this.estoque = estoque;
        this.preco = preco;
        this.produto = produto;
        this.comercio = comercio;
        this.dataAtualizacao = dataAtualizacao;
        this.responsavel = responsavel;
    }

    public Item(int id, Boolean estoque, float preco, Produto produto, Comercio comercio, Date dataAtualizacao, UsuarioAdm responsavel) {
        this.id = id;
        this.estoque = estoque;
        this.preco = preco;
        this.produto = produto;
        this.comercio = comercio;
        this.dataAtualizacao = dataAtualizacao;
        this.responsavel = responsavel;
    }
}
