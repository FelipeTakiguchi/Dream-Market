package sample.model;

public class ListaItem {
    private int idLista;
    private Item item;
    private int quantidade;

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    @Override
    public String toString() {
        return "ListaItem{" +
                "idLista=" + idLista +
                ", item=" + item +
                ", quantidade=" + quantidade +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ListaItem(Item item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ListaItem(int idLista, Item item, int quantidade) {
        this.idLista = idLista;
        this.item = item;
        this.quantidade = quantidade;
    }
}

