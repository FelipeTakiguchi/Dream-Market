package sample.modelDAO;

import sample.model.Cidade;
import sample.model.Estado;
import sample.model.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAOImpl implements CidadeDAO {

    private static String INSERE = "insert into Cidade(cidade, Id_estado) values(?, ?)";
    private static String VERIF = "select * from Cidade where nome like ?";
    private static String LISTA = "select * from Cidade";
    private static String BUSCAID = "select * from Cidade where id like ?";
    private static String ESTADO = "select * from Estado where Id_estado = ?";

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

        String nome_cidade = "";
        Cidade cidade = null;
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id = res.getInt("id_cidade");
            String nome = res.getString("nome");
            id_estado = res.getInt("Id_estado");

            PreparedStatement stm2 = con.prepareStatement(ESTADO);

            stm2.setInt(1, id_estado);

            ResultSet res2 = stm.executeQuery();

            while (res2.next()) {
                String nome_Estado;

                nome_Estado = res2.getString("nome");

                Estado estado = new Estado(nome_Estado);
                cidade = new Cidade(nome_cidade, estado);
            }

            res2.close();
            stm2.close();
        }

        res.close();
        stm.close();
        con.close();

        return cidade;
    }

    @Override
    public List<Cidade> lista() throws SQLException{
        ArrayList<Cidade> Cidades = new ArrayList<>();
        Estado estado = null;
        String nome = "";
        int id = -1;
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            id = rs.getInt("id_cidade");
            nome = rs.getString("Nome");
            id_estado = rs.getInt("Id_estado");

            PreparedStatement stm2 = con.prepareStatement(ESTADO);

            stm2.setInt(1, id_estado);

            ResultSet res2 = stm2.executeQuery();

            while (res2.next()) {
                String nome_Estado;

                nome_Estado = res2.getString("nome");

                estado = new Estado(nome_Estado);
            }

            Cidade cat = new Cidade(id, nome, estado);
            System.out.println(cat);
            Cidades.add(cat);

            res2.close();
            stm2.close();
        }

        stm.close();
        rs.close();

        return Cidades;
    }

    @Override
    public Cidade buscaId(int id) throws SQLException{

        Cidade c = null;
        String nome_cidade = "";
        Cidade cidade = null;
        Estado estado = null;
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            id_estado = res.getInt("Id_estado");

            PreparedStatement stm2 = con.prepareStatement(ESTADO);

            stm2.setInt(1, id_estado);

            ResultSet res2 = stm.executeQuery();

            while (res2.next()) {
                String nome_Estado;

                nome_Estado = res2.getString("nome");

                estado = new Estado(nome_Estado);
            }

            res2.close();
            stm2.close();

            c = new Cidade(id, nome_cidade, estado);
        }

        res.close();
        stm.close();
        con.close();

        return c;
    }
}