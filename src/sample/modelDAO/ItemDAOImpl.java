package sample.modelDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static String INSERE = "insert into Item(Estoque, preco, id_produto, id_comercio, Data_atualizacao, Id_responsavel) values(?, ?, ?, ?, ?, ?)";
    private static String VERIF = "select nome from Item where nome like ?";
    private static String LISTA = "select * from Item";
    private static String LISTAADM = "select * from Item where id_responsavel = ?";
    private static String BUSCAID = "select * from Item where id like ?";
    private static String BUSCANOME = "select * from Item where nome like ?";
    private static String CIDADE = "select * from Cidade where Id_cidade = ?";
    private static String ITEMPRODUTO = "select * from Item where id_produto = ?";
    private static String BUSCAPRODUTO = "select Id_produto from Produto where Nome like ?";
    private static String ESTADO = "select * from Estado where Id_estado = ?";
    private static String PRODUTO = "select * from Produto where Id_produto = ?";
    private static String USUARIOADM = "select * from UsuarioADM where id_usuario like ?";
    private static String COMERCIO = "select * from Comercio where id_comercio like ?";
    private static String ATUALIZA = "UPDATE Item set Preco = ? where id_item = ?";
    private static String PESQUISACOMERCIO = "select * from Item where id_comercio = ?";
    private static String PESQUISACIDADE = "select * from Item i, Comercio c where i.id_comercio = c.id_comercio and c.id_cidade = ?";

    @Override
    public Item insere(Item item) throws SQLException {
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setBoolean(1,item.getEstoque());
        stm.setFloat(2,item.getPreco());
        stm.setInt(3,item.getProduto().getId());
        stm.setInt(4,item.getComercio().getId());
        stm.setDate(5,item.getDataAtualizacao());
        stm.setInt(6,Controle.getUsuario().getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return item;
    }

    @Override
    public Item verif(String Nome) throws SQLException {

        Item item = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id = res.getInt("id_item");
            boolean estoque = res.getBoolean("estoque");
            float preco = res.getFloat("preco");
            int id_produto = res.getInt("id_produto");
            int id_comercio = res.getInt("id_comercio");
            Date data_atualizacao = res.getDate("data_atualizacao");
            int id_responsavel = res.getInt("Id_responsavel");

            Produto produto = getProduto(id_produto);
            Comercio comercio = getComercio(id_comercio);
            UsuarioAdm usuarioAdm = getResponsavel(id_responsavel);

            item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);
        }

        res.close();
        stm.close();
        con.close();

        return item;
    }

    @Override
    public List<Item> lista() throws SQLException{
        ArrayList<Item> itens = new ArrayList<>();
        UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
        ProdutoDAO produtoDAO = new ProdutoDAOImpl();
        ComercioDAO comercioDAO = new ComercioDAOImpl();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("Id_item");
            Boolean estoque = rs.getBoolean("estoque");
            float preco = rs.getFloat("Preco");
            int id_produto = rs.getInt("id_produto");
            int id_comercio = rs.getInt("id_comercio");
            Date data_atualizacao = rs.getDate("Data_atualizacao");
            int id_responsavel = rs.getInt("Id_responsavel");

            Produto produto = produtoDAO.buscaId(id_produto);
            Comercio comercio = comercioDAO.buscaId(id_comercio);
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_responsavel);

            Item item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);

            System.out.println(item);
            itens.add(item);
        }

        stm.close();
        rs.close();
        con.close();

        return itens;
    }

    @Override
    public Item buscaId(int id) throws SQLException{

        Item item= null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            Boolean estoque = res.getBoolean("estoque");
            float preco = res.getFloat("preco");
            int id_produto = res.getInt("id_produto");
            int id_comercio = res.getInt("id_comercio");
            Date data_atualizacao = res.getDate("Data_atualizacao");
            int id_responsavel = res.getInt("Id_responsavel");

            Produto produto = getProduto(id_produto);
            Comercio comercio = getComercio(id_comercio);
            UsuarioAdm usuarioAdm = getResponsavel(id_responsavel);

            item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);
        }

        res.close();
        stm.close();
        con.close();

        return item;
    }

    private Produto getProduto(int id_produto) throws SQLException{
        Produto produto = null;
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(PRODUTO);

        stm.setInt(1, id_produto);
        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            String nome = rs.getString("nome");
            String marca = rs.getString("marca");
            String descricao = rs.getString("descricao");
            int id_responsavel = rs.getInt("id_responsavel");

            produto = new Produto(id_produto, nome, marca, descricao, id_responsavel);
        }

        stm.close();
        rs.close();
        con.close();

        return produto;
    }

    private Comercio getComercio(int id_comercio) throws SQLException{
        Comercio comercio= null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(COMERCIO);

        stm.setInt(1,id_comercio);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String endereco = res.getString("nome");
            Time horarioInicio = res.getTime("Horario_inicio");
            Time horarioFim = res.getTime("Horario_fim");
            int id_cidade = res.getInt("id_cidade");

            Cidade cidade = getCidade(id_cidade);

            comercio = new Comercio(id_comercio, nome, endereco, horarioInicio, horarioFim, cidade);
        }

        res.close();
        stm.close();
        con.close();

        return comercio;
    }

    private UsuarioAdm getResponsavel(int id_responsavel) throws SQLException{
        UsuarioAdmDAO usuarioAdm = new UsuarioAdmDAOImpl();
        return usuarioAdm.buscaId(id_responsavel);
    }

    private Cidade getCidade(int id_cidade) throws SQLException {
        int id_estado = -1;
        Estado estado = null;
        Cidade cidade = null;

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(CIDADE);
        stm.setInt(1,id_cidade);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            String nome_cidade = rs.getString("nome");
            id_estado = rs.getInt("Id_estado");

            PreparedStatement stm2 = con.prepareStatement(ESTADO);

            stm2.setInt(1, id_estado);

            ResultSet res2 = stm2.executeQuery();

            while (res2.next()) {
                String nome_Estado;

                nome_Estado = res2.getString("nome");

                estado = new Estado(id_estado, nome_Estado);
            }

            res2.close();
            stm2.close();

            cidade = new Cidade(id_cidade, nome_cidade, estado);
        }

        rs.close();
        stm.close();
        con.close();

        return cidade;
    }

    public List buscaNome(String nome) throws SQLException {
        ObservableList<Item> itens = FXCollections.observableArrayList();
        Item item= null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAPRODUTO);

        stm.setString(1,"%"+nome+"%");

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id_produto = res.getInt("Id_produto");

            PreparedStatement stm2 = con.prepareStatement(ITEMPRODUTO);

            stm2.setInt(1,id_produto);

            ResultSet res2 = stm2.executeQuery();

            while(res2.next()) {
                int id = res2.getInt("id_item");
                Boolean estoque = res2.getBoolean("estoque");
                float preco = res2.getFloat("preco");
                int id_comercio = res2.getInt("id_comercio");
                Date data_atualizacao = res2.getDate("Data_atualizacao");
                int id_responsavel = res2.getInt("Id_responsavel");

                Produto produto = getProduto(id_produto);
                Comercio comercio = getComercio(id_comercio);
                UsuarioAdm usuarioAdm = getResponsavel(id_responsavel);

                item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);
                itens.add(item);
            }

            res2.close();
            stm2.close();
        }

        res.close();
        stm.close();
        con.close();

        return itens;
    }

    @Override
    public void atualiza(Item item, Float valor) throws SQLException{
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(ATUALIZA);

        stm.setFloat(1, valor);
        stm.setInt(2, item.getId());
        stm.executeUpdate();

        stm.close();
        con.close();
    }

    @Override
    public List<Item> listaAdm(Usuario usuario) throws SQLException{
        ArrayList<Item> itens = new ArrayList<>();
        UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
        ProdutoDAO produtoDAO = new ProdutoDAOImpl();
        ComercioDAO comercioDAO = new ComercioDAOImpl();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTAADM);

        stm.setInt(1, usuario.getId());
        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("Id_item");
            Boolean estoque = rs.getBoolean("estoque");
            float preco = rs.getFloat("Preco");
            int id_produto = rs.getInt("id_produto");
            int id_comercio = rs.getInt("id_comercio");
            Date data_atualizacao = rs.getDate("Data_atualizacao");
            int id_responsavel = rs.getInt("Id_responsavel");

            Produto produto = produtoDAO.buscaId(id_produto);
            Comercio comercio = comercioDAO.buscaId(id_comercio);
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_responsavel);

            Item item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);

            System.out.println(item);
            itens.add(item);
        }

        con.close();
        stm.close();
        rs.close();

        return itens;
    }

    @Override
    public List<Item> pesquisaComercio(Comercio c) throws SQLException{
        ArrayList<Item> itens = new ArrayList<>();
        UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
        ProdutoDAO produtoDAO = new ProdutoDAOImpl();
        ComercioDAO comercioDAO = new ComercioDAOImpl();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(PESQUISACOMERCIO);

        stm.setInt(1, c.getId());
        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("Id_item");
            Boolean estoque = rs.getBoolean("estoque");
            float preco = rs.getFloat("Preco");
            int id_produto = rs.getInt("id_produto");
            int id_comercio = rs.getInt("id_comercio");
            Date data_atualizacao = rs.getDate("Data_atualizacao");
            int id_responsavel = rs.getInt("Id_responsavel");

            Produto produto = produtoDAO.buscaId(id_produto);
            Comercio comercio = comercioDAO.buscaId(id_comercio);
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_responsavel);

            Item item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);

            itens.add(item);
        }

        stm.close();
        rs.close();
        con.close();

        return itens;
    }

    public List<Item> pesquisaCidade(Cidade cidade) throws SQLException{
        ArrayList<Item> itens = new ArrayList<>();
        UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
        ProdutoDAO produtoDAO = new ProdutoDAOImpl();
        ComercioDAO comercioDAO = new ComercioDAOImpl();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(PESQUISACIDADE);

        stm.setInt(1, cidade.getId());
        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("Id_item");
            Boolean estoque = rs.getBoolean("estoque");
            float preco = rs.getFloat("Preco");
            int id_produto = rs.getInt("id_produto");
            int id_comercio = rs.getInt("id_comercio");
            Date data_atualizacao = rs.getDate("Data_atualizacao");
            int id_responsavel = rs.getInt("Id_responsavel");

            Produto produto = produtoDAO.buscaId(id_produto);
            Comercio comercio = comercioDAO.buscaId(id_comercio);
            UsuarioAdm usuarioAdm = usuarioAdmDAO.buscaId(id_responsavel);

            Item item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);

            itens.add(item);
        }

        stm.close();
        rs.close();
        con.close();

        return itens;
    }
}
