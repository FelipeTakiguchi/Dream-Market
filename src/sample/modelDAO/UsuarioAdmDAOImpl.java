package sample.modelDAO;

import sample.model.Cidade;
import sample.model.Estado;
import sample.model.FabricaConexao;
import sample.model.UsuarioAdm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAdmDAOImpl implements UsuarioAdmDAO {

    private static String INSERE = "insert into UsuarioADM(nome, email, senha, id_Cidade, telefone, cpf) values(?, ?, ?, ?, ?, ?)";
    private static String VERIF = "select * from UsuarioADM where nome like ? and senha like ?";
    private static String LISTA = "select * from UsuarioADM";
    private static String BUSCAID = "select * from UsuarioADM where id like ?";
    private static String CIDADE = "select * from Cidade where Id_cidade = ?";
    private static String ESTADO = "select * from Estado where Id_estado = ?";

    @Override
    public UsuarioAdm insere(String nome, String email, String senha, Cidade cidade, String telefone, String cpf) throws SQLException {
        UsuarioAdm u = new UsuarioAdm(nome, email, senha, cidade, telefone, cpf);
        Connection con = FabricaConexao.getConnection();


        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setString(1,u.getNome());
        stm.setString(2,u.getEmail());
        stm.setString(3,u.getSenha());
        stm.setInt(4,u.getCidade().getId());
        stm.setString(5,u.getTelefone());
        stm.setString(6,u.getCpf());

        stm.executeUpdate();

        stm.close();
        con.close();

        return u;
    }

    @Override
    public UsuarioAdm verif(String Nome, String Senha) throws SQLException {

        UsuarioAdm u = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);
        stm.setString(2,Senha);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String email = res.getString("email");
            String senha = res.getString("senha");
            int id_cidade = res.getInt("id_Cidade");
            String telefone = res.getString("telefone");
            String cpf = res.getString("cpf");

            Cidade cidade = getCidade(id_cidade);

            u = new UsuarioAdm(nome, email, senha, cidade, telefone, cpf);
        }

        res.close();
        stm.close();
        con.close();

        return u;
    }

    @Override
    public List<UsuarioAdm> lista() throws SQLException{
        ArrayList<UsuarioAdm> Usuarios = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            int id_cidade = rs.getInt("id_cidade");
            String telefone = rs.getString("telefone");
            String cpf = rs.getString("cpf");

            Cidade cidade = getCidade(id_cidade);

            UsuarioAdm cat = new UsuarioAdm(id, nome, email, senha, cidade, telefone, cpf);

            Usuarios.add(cat);
        }

        con.close();
        stm.close();
        rs.close();

        return Usuarios;
    }

    @Override
    public UsuarioAdm buscaId(int id) throws SQLException{

        UsuarioAdm u = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String email = res.getString("email");
            String senha = res.getString("senha");
            int id_cidade = res.getInt("id_cidade");
            String telefone = res.getString("telefone");
            String cpf = res.getString("cpf");

            Cidade cidade = getCidade(id_cidade);

            u = new UsuarioAdm(id, nome, email, senha, cidade, telefone, cpf);
        }

        res.close();
        stm.close();
        con.close();

        return u;
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
}
