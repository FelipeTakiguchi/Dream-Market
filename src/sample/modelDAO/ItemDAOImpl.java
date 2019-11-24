package sample.modelDAO;

import sample.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static String INSERE = "insert into Item(Estoque, preco, id_produto, id_comercio, Data_atualizacao, Id_responsavel) values(?, ?, ?, ?, ?, ?)";
    private static String VERIF = "select nome from Item where nome like ?";
    private static String LISTA = "select * from Item";
    private static String BUSCAID = "select * from Item where id like ?";
    private static String CIDADE = "select * from Cidade where id = ?";
    private static String ESTADO = "select * from Estado where id = ?";
    private static String PRODUTO = "select * from Produto where Id_produto = ?";
    private static String USUARIOADM = "select * from UsuarioADM where id_usuario like ?";
    private static String COMERCIO = "select * from Comercio where id_comercio like ?";

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
        stm.setInt(6,item.getResponsavel().getId());


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

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("Id_estado");
            Boolean estoque = rs.getBoolean("estoque");
            float preco = rs.getFloat("preco");
            int id_produto = rs.getInt("id_produto");
            int id_comercio = rs.getInt("id_comercio");
            Date data_atualizacao = rs.getDate("Data_atualizacao");
            int id_responsavel = rs.getInt("Id_responsavel");

            Produto produto = getProduto(id_produto);
            Comercio comercio = getComercio(id_comercio);
            UsuarioAdm usuarioAdm = getResponsavel(id_responsavel);

            Item item = new Item(id, estoque, preco, produto, comercio, data_atualizacao, usuarioAdm);

            itens.add(item);
        }

        con.close();
        stm.close();
        rs.close();

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

    private Cidade getCidade(int id_cidade) throws SQLException {
        int id_estado = -1;
        Estado estado = null;
        Cidade cidade = null;

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(CIDADE);

        stm.setInt(1,id_cidade);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome_cidade = rs.getString("nome");
            id_estado = rs.getInt("Id_estado");

            PreparedStatement stm2 = con.prepareStatement(ESTADO);

            stm2.setInt(1, id_estado);

            ResultSet res2 = stm.executeQuery();

            while (res2.next()) {
                String nome_Estado;

                nome_Estado = res2.getString("nome");

                estado = new Estado(id_estado, nome_Estado);
            }

            res2.close();
            stm2.close();

            cidade = new Cidade(id, nome_cidade, estado);
        }

        return cidade;
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
            float valor = rs.getFloat("valor");
            int id_responsavel = rs.getInt("id_responsavel");

            produto = new Produto(id_produto, nome, marca, descricao, valor, id_responsavel);
        }

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

        UsuarioAdm u = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(USUARIOADM);

        stm.setInt(1,id_responsavel);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String email = res.getString("email");
            String senha = res.getString("senha");
            int id_cidade = res.getInt("id_cidade");
            String telefone = res.getString("telefone");
            String cpf = res.getString("cpf");

            Cidade cidade = getCidade(id_cidade);

            u = new UsuarioAdm(id_responsavel, nome, email, senha, cidade, telefone, cpf);
        }

        res.close();
        stm.close();
        con.close();

        return u;
    }
}
