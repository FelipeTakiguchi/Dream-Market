package sample.modelDAO;

import sample.model.Estado;
import sample.model.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAOImpl implements EstadoDAO {

    private static String INSERE = "insert into Estado(id, cidade) values(?, ?)";
    private static String VERIF = "select nome from Estado where nome like ?";
    private static String LISTA = "select * from Estado";
    private static String BUSCAID = "select * from Estado where id like ?";

    @Override
    public Estado insere(int id, String nome) throws SQLException {
        Estado e = new Estado(id, nome);
        Connection con = FabricaConexao.getConnection();


        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setString(1,e.getNome());

        stm.executeUpdate();

        stm.close();
        con.close();

        return e;
    }

    @Override
    public Estado verif(String Nome) throws SQLException {

        Estado estado = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id = res.getInt("id");
            String nome = res.getString("nome");
            estado = new Estado(id, nome);
        }

        res.close();
        stm.close();
        con.close();

        return estado;
    }

    @Override
    public List<Estado> lista() throws SQLException{
        ArrayList<Estado> Estados = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            Estado cat = new Estado(id, nome);

            Estados.add(cat);
        }

        con.close();
        stm.close();
        rs.close();

        return Estados;
    }

    @Override
    public Estado buscaId(int id) throws SQLException{

        Estado estado= null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            estado = new Estado(id, nome);
        }

        res.close();
        stm.close();
        con.close();

        return estado;
    }
}
