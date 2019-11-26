package sample.modelDAO;

import sample.model.Produto;
import sample.model.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    private static String INSERE = "insert into Produto(nome, marca, descricao, valor, id_responsavel) values(?, ?, ?, ?, ?)";
    private static String VERIF = "select nome from Produto where nome like ?";
    private static String LISTA = "select * from Produto";
    private static String BUSCAID = "select * from Produto where id_produto = ?";

    @Override
    public Produto insere(String nome, String marca, String descricao, int id_responsavel) throws SQLException {
        Produto p = new Produto(nome, marca, descricao, id_responsavel);
        Connection con = FabricaConexao.getConnection();


        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setString(1,p.getNome());

        stm.executeUpdate();

        stm.close();
        con.close();

        return p;
    }

    @Override
    public Produto verif(String Nome) throws SQLException {

        Produto p = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String marca = res.getString("marca");
            String descricao = res.getString("descricao");
            int id_responsavel = res.getInt("id_responsavel");

            p = new Produto(nome, marca, descricao, id_responsavel);
        }

        res.close();
        stm.close();
        con.close();

        return p;
    }

    @Override
    public List<Produto> lista() throws SQLException{
        ArrayList<Produto> categorias = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String marca = rs.getString("marca");
            String descricao = rs.getString("descricao");
            int id_responsavel = rs.getInt("id_responsavel");
            Produto cat = new Produto(id, nome, marca, descricao, id_responsavel);

            categorias.add(cat);
        }

        con.close();
        stm.close();
        rs.close();

        return categorias;
    }

    @Override
    public Produto buscaId(int id) throws SQLException{

        Produto p = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String marca = res.getString("marca");
            String descricao = res.getString("descricao");
            int id_responsavel = res.getInt("id_responsavel");
            p = new Produto(id, nome, marca, descricao, id_responsavel);
        }

        res.close();
        stm.close();
        con.close();

        return p;
    }
}
