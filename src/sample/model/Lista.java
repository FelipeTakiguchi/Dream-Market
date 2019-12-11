package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class Lista {
    private int id_lista;
    private Usuario usuario;
    private Date dataAtualizacao;
    private String nome;
    private ObservableList<ListaItem> listaItems;

    public void addItem(ListaItem listaItem){
        listaItems.add(listaItem);
    }

    public Lista(){
        listaItems = FXCollections.observableArrayList();
    }

    public void setId(int id){
        int i;

        for(i = 0; i < listaItems.size(); i++){
            listaItems.get(i).setIdLista(id);
        }
    }

    public ObservableList<ListaItem> getListaItems() {
        return listaItems;
    }

    public void setListaItems(ObservableList<ListaItem> listaItems) {
        this.listaItems = listaItems;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lista(Usuario usuario, Date dataAtualizacao, String nome, ObservableList<ListaItem> listaItems) {
        this.usuario = usuario;
        this.dataAtualizacao = dataAtualizacao;
        this.nome = nome;
        this.listaItems = listaItems;
    }

    public Lista(int id_lista, Usuario usuario, Date dataAtualizacao, String nome, ObservableList<ListaItem> listaItems) {
        this.id_lista = id_lista;
        this.usuario = usuario;
        this.dataAtualizacao = dataAtualizacao;
        this.nome = nome;
        this.listaItems = listaItems;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return nome;
    }
}
