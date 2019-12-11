package sample.modelDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.*;
import java.sql.*;
import java.util.List;

public class ListaDAOImpl implements ListaDAO {

    private static String INSERE = "Insert into Lista(id_dono, Data_atualizacao, nome) values(?, ?, ?)";
    private static String VERIF = "select * from Lista where nome = ?";
    private static String LISTA = "select * from Lista where id_dono = ?";
    private static String BUSCAID = "select * from Lista where id_lista = ?";
    private static String BUSCANOME = "select * from Lista where nome = ?";
    private static String MAX = "select max(id_lista) from Lista";

    @Override
    public Lista insere(Lista lista) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con
                .prepareStatement(INSERE);

        java.sql.Date d = new java.sql.Date(lista.getDataAtualizacao().getTime());
        stm.setInt(1,lista.getUsuario().getId());
        stm.setDate(2, d);
        stm.setString(3,lista.getNome());

        stm.executeUpdate();

        stm.close();
        con.close();

        return lista;
    }

    @Override
    public Lista verif(String Nome) throws SQLException {
        Lista lista = null;
        ObservableList<ListaItem> listaItems;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id_lista = res.getInt("id_lista");
            int id_dono = res.getInt("id_dono");
            Date data_atualizacao = res.getDate("Data_atualizacao");
            String nome = res.getString("nome");

            UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_dono);
            ListaItemDAO listaItemDAO = new ListaItemDAOImpl();
            listaItems = FXCollections.observableArrayList();
            listaItems.addAll(listaItemDAO.buscaId(id_lista));

            lista = new Lista(usuarioAdm, data_atualizacao, nome, listaItems);
        }

        res.close();
        stm.close();
        con.close();

        return lista;
    }

    @Override
    public List<Lista> lista() throws SQLException {
        ListaItemDAO listaItemDAO = new ListaItemDAOImpl();
        ObservableList<Lista> listas = FXCollections.observableArrayList();

        ObservableList<ListaItem> listaItems;
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);
        stm.setInt(1, Controle.getUsuario().getId());
        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id_lista = rs.getInt("id_lista");
            Date data_atualizacao = rs.getDate("Data_atualizacao");
            String nome = rs.getString("nome");
            listaItems = FXCollections.observableArrayList();
            listaItems.addAll(listaItemDAO.buscaId(id_lista));
            Lista lista = new Lista(Controle.getUsuario(), data_atualizacao, nome, listaItems);
            listas.add(lista);
            System.out.println("lista: "+lista);
            System.out.println("listas: "+listas);
        }

        stm.close();
        rs.close();
        con.close();

        return listas;
    }

    @Override
    public Lista buscaId(int id) throws SQLException {
        Lista lista = null;

        ObservableList<ListaItem> listaItems;
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id_lista = res.getInt("id_lista");
            int id_dono = res.getInt("id_dono");
            Date data_atualizacao = res.getDate("Data_atualizacao");
            String nome = res.getString("nome");

            listaItems = FXCollections.observableArrayList();
           UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_dono);
            ListaItemDAO listaItemDAO = new ListaItemDAOImpl();
            listaItems.addAll(listaItemDAO.buscaId(id_lista));

            lista = new Lista(usuarioAdm, data_atualizacao, nome, listaItems);
        }

        res.close();
        stm.close();
        con.close();

        return lista;
    }

    @Override
    public List<Lista> buscaNome(String nome) throws SQLException {
        List<Lista> listas = FXCollections.observableArrayList();

        ObservableList<ListaItem> listaItems;
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCANOME);

        stm.setString(1,nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id_lista = res.getInt("id_lista");
            int id_dono = res.getInt("id_dono");
            Date data_atualizacao = res.getDate("Data_atualizacao");

            listaItems = FXCollections.observableArrayList();
            UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_dono);
            ListaItemDAO listaItemDAO = new ListaItemDAOImpl();
            listaItems.addAll(listaItemDAO.buscaId(id_lista));

            Lista lista = new Lista(usuarioAdm, data_atualizacao, nome, listaItems);
            listas.add(lista);
        }

        res.close();
        stm.close();
        con.close();

        return listas;
    }

    public int retornaMax() throws SQLException{
        int idMax = 0;
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(MAX);

        ResultSet res = stm.executeQuery();

        while(res.next()) {
            idMax = res.getInt("max(id_lista)");
        }

        return idMax;
    }
}
