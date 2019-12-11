package sample.modelDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaItemDAOImpl implements ListaItemDAO {

    private static String INSERE = "Insert into Lista_item(id_lista, id_item, quantidade) values(?, ?, ?)";
    private static String LISTA = "select * from Lista_item";
    private static String BUSCAID = "select * from Lista_item where id_lista = ?";

    @Override
    public ListaItem insere(ListaItem listaItem) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setInt(1,listaItem.getIdLista());
        stm.setInt(2,listaItem.getItem().getId());
        stm.setInt(3,listaItem.getQuantidade());

        stm.executeUpdate();

        stm.close();
        con.close();

        return listaItem;
    }

    @Override
    public List<ListaItem> lista() throws SQLException{
        ArrayList<ListaItem> listaItems = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int idLista = rs.getInt("id_produto");
            int idItem = rs.getInt("id_item");
            int quantidade = rs.getInt("quantidade");

            ItemDAO itemDAO = new ItemDAOImpl();
            Item item = itemDAO.buscaId(idItem);

            ListaItem listaItem = new ListaItem(idLista, item, quantidade);

            listaItems.add(listaItem);
        }

        stm.close();
        rs.close();
        con.close();

        return listaItems;
    }

    @Override
    public List<ListaItem> buscaId(int id) throws SQLException{

        ObservableList<ListaItem> listaItem = FXCollections.observableArrayList();
        ListaItem lista;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int idItem = res.getInt("id_item");
            int quantidade = res.getInt("quantidade");

            ItemDAO itemDAO = new ItemDAOImpl();
            Item item = itemDAO.buscaId(idItem);

            lista = new ListaItem(id, item, quantidade);

            listaItem.add(lista);
        }

        res.close();
        stm.close();
        con.close();

        return listaItem;
    }
}
