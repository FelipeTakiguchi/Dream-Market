package sample.modelDAO;

import sample.model.Cidade;
import sample.model.Comercio;
import sample.model.Estado;
import sample.model.FabricaConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComercioDAOImpl implements ComercioDAO {

    private static String INSERE = "insert into Comercio(Nome, Endereco, Horario_inicio, Horario_fim, id_cidade) values(?, ?, ?, ?, ?)";
    private static String VERIF = "select nome from Comercio where nome like ?";
    private static String LISTA = "select * from Comercio";
    private static String BUSCAID = "select * from Comercio where id_comercio = ?";
    private static String CIDADE = "select * from Cidade where id_cidade = ?";
    private static String ESTADO = "select * from Estado where id_estado = ?";

    @Override
    public Comercio insere(Comercio comercio) throws SQLException {
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setString(1,comercio.getNome());
        stm.setString(2,comercio.getEndereco());
        stm.setTime(3,comercio.getHorarioInicio());
        stm.setTime(4,comercio.getHorarioFim());
        stm.setInt(5,comercio.getCidade().getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return comercio;
    }

    @Override
    public Comercio verif(String Nome) throws SQLException {

        Comercio comercio = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            int id = res.getInt("Id_estado");
            String nome = res.getString("nome");
            String endereco = res.getString("nome");
            Time horarioInicio = res.getTime("Horario_inicio");
            Time horarioFim = res.getTime("Horario_fim");
            int id_cidade = res.getInt("id_cidade");

            Cidade cidade = getCidade(id_cidade);

            comercio = new Comercio(id, nome, endereco, horarioInicio, horarioFim, cidade);
        }

        res.close();
        stm.close();
        con.close();

        return comercio;
    }

    @Override
    public List<Comercio> lista() throws SQLException{
        ArrayList<Comercio> comercios = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id_comercio");
            String nome = rs.getString("nome");
            String endereco = rs.getString("Endereco");
            Time horarioInicio = rs.getTime("Horario_inicio");
            Time horarioFim = rs.getTime("Horario_fim");
            int id_cidade = rs.getInt("id_cidade");

            Cidade cidade = getCidade(id_cidade);

            Comercio comercio = new Comercio(id, nome, endereco, horarioInicio, horarioFim, cidade);

            comercios.add(comercio);
        }

        stm.close();
        rs.close();
        con.close();

        return comercios;
    }

    @Override
    public Comercio buscaId(int id) throws SQLException{

        Comercio comercio= null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String endereco = res.getString("nome");
            Time horarioInicio = res.getTime("Horario_inicio");
            Time horarioFim = res.getTime("Horario_fim");
            int id_cidade = res.getInt("id_cidade");

            Cidade cidade = getCidade(id_cidade);

            comercio = new Comercio(nome, endereco, horarioInicio, horarioFim, cidade);
        }

        res.close();
        stm.close();
        con.close();

        return comercio;
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
            int id = rs.getInt("id_cidade");
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

            cidade = new Cidade(id, nome_cidade, estado);
        }

        rs.close();
        stm.close();
        con.close();

        return cidade;
    }
}
