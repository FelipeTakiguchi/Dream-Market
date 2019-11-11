package sample.modelDAO;

import sample.model.Cidade;
import sample.model.Estado;
import sample.model.Produto;
import sample.model.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAOImpl implements CidadeDAO {

    private static String INSERE = "insert into Cidade(cidade, Id_estado) values(?, ?)";
    private static String VERIF = "select nome from Cidade where nome like ?";
    private static String LISTA = "select * from Cidade";
    private static String BUSCAID = "select * from Cidade where id like ?";

    @Override
    public Cidade insere(String nome, Estado estado) throws SQLException {
        Cidade c = new Cidade(nome, estado);
        Connection con = FabricaConexao.getConnection();


        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setString(1,c.getNome());
        stm.setInt(1,c.getEstado().getId());
        stm.executeUpdate();

        stm.close();
        con.close();

        return c;
    }

    @Override
    public Cidade verif(String Nome) throws SQLException {

        Cidade c = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id = res.getInt("id");
            String nome = res.getString("nome");

            c = new Cidade(id, nome, estado);
        }

        res.close();
        stm.close();
        con.close();

        return c;
    }

    @Override
    public List<Cidade> lista() throws SQLException{
        ArrayList<Cidade> Cidades = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            Cidade cat = new Cidade(id, nome);

            Cidades.add(cat);
        }

        con.close();
        stm.close();
        rs.close();

        return Cidades;
    }

    @Override
    public Cidade buscaId(int id) throws SQLException{

        Cidade c = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            c = new Cidade(id, nome);
        }

        res.close();
        stm.close();
        con.close();

        return c;
    }
}