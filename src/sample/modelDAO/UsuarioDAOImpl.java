package sample.modelDAO;

import sample.model.Cidade;
import sample.model.Estado;
import sample.model.FabricaConexao;
import sample.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private static String INSERE = "insert into Usuario(nome, email, senha, id_Cidade) values(?, ?, ?, ?)";
    private static String VERIF = "select * from Usuario where email like ? and senha like ?";
    private static String VERIFNOME = "select * from Usuario where nome like ? and senha like ?";
    private static String LISTA = "select * from Usuario";
    private static String BUSCAID = "select * from Usuario where Id_usuario like ?";
    private static String CIDADE = "select * from Cidade where Id_cidade = ?";
    private static String ESTADO = "select * from Estado where Id_estado = ?";

    @Override
    public Usuario insere(String nome, String email, String senha, Cidade cidade) throws SQLException {
        Usuario u = new Usuario(nome, email, senha, cidade);
        Connection con = FabricaConexao.getConnection();


        PreparedStatement stm = con
                .prepareStatement(INSERE);

        System.out.println(u.getNome());
        System.out.println(u.getEmail());
        System.out.println(u.getSenha());
        System.out.println(u.getCidade().getId());

        stm.setString(1,u.getNome());
        stm.setString(2,u.getEmail());
        stm.setString(3,u.getSenha());
        stm.setInt(4,u.getCidade().getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return u;
    }

    @Override
    public Usuario verifEmail(String Email, String Senha) throws SQLException {

        Usuario u = null;
        int id = -1;
        String nome = "";
        String email = "";
        String senha = "";
        int id_cidade = -1;
        Cidade cidade;
        String nome_cidade = "";
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(VERIF);
        stm.setString(1,Email);
        stm.setString(2,Senha);
        ResultSet res = stm.executeQuery();

        while(res.next()){
            id = res.getInt("Id_usuario");
            nome = res.getString("nome");
            email = res.getString("email");
            senha = res.getString("senha");
            id_cidade = res.getInt("id_Cidade");

        }

        res.close();
        stm.close();

        PreparedStatement stm2 = con.prepareStatement(CIDADE);

        stm2.setInt(1, id_cidade);

        ResultSet res2 = stm2.executeQuery();

        while (res2.next()) {
            nome_cidade = res2.getString("nome");
            id_estado = res2.getInt("Id_estado");
        }

        res2.close();
        stm2.close();

        PreparedStatement stm3 = con.prepareStatement(ESTADO);

        stm3.setInt(1, id_estado);

        ResultSet res3 = stm3.executeQuery();

        while (res3.next()) {
            String nome_Estado;

            nome_Estado = res3.getString("nome");

            Estado estado = new Estado(nome_Estado);
            cidade = new Cidade(nome_cidade, estado);
            u = new Usuario(id, nome, email, senha, cidade);
        }

        res3.close();
        stm3.close();

        con.close();

        return u;
    }

    @Override
    public List<Usuario> lista() throws SQLException{
        ArrayList<Usuario> Usuarios = new ArrayList<>();
        String nome_cidade = "";
        Cidade cidade = null;
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            int id_cidade = rs.getInt("id_cidade");

            PreparedStatement stm2 = con.prepareStatement(CIDADE);

            stm2.setInt(1, id_cidade);

            ResultSet res2 = stm.executeQuery();

            while (res2.next()) {
                nome_cidade = res2.getString("nome");
                id_estado = res2.getInt("Id_estado");
            }

            res2.close();
            stm2.close();

            PreparedStatement stm3 = con.prepareStatement(ESTADO);

            stm3.setInt(1, id_estado);

            ResultSet res3 = stm.executeQuery();

            while (res3.next()) {
                String nome_Estado;

                nome_Estado = res3.getString("nome");

                Estado estado = new Estado(nome_Estado);
                cidade = new Cidade(nome_cidade, estado);
            }

            stm3.close();
            res3.close();

            Usuario usuario = new Usuario(nome, email, senha, cidade);
            Usuarios.add(usuario);
        }

        con.close();
        stm.close();
        rs.close();

        return Usuarios;
    }

    @Override
    public Usuario verifNome(String Nome, String Senha) throws SQLException {

        Usuario u = null;
        int id = -1;
        String nome = "";
        String email = "";
        String senha = "";
        int id_cidade = -1;
        Cidade cidade;
        String nome_cidade = "";
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(VERIFNOME);
        stm.setString(1,Nome);
        stm.setString(2,Senha);
        ResultSet res = stm.executeQuery();

        while(res.next()){
            id = res.getInt("Id_usuario");
            nome = res.getString("nome");
            email = res.getString("email");
            senha = res.getString("senha");
            id_cidade = res.getInt("id_Cidade");
        }

        res.close();
        stm.close();

        PreparedStatement stm2 = con.prepareStatement(CIDADE);

        stm2.setInt(1, id_cidade);

        ResultSet res2 = stm2.executeQuery();

        while (res2.next()) {
            nome_cidade = res2.getString("nome");
            id_estado = res2.getInt("Id_estado");
        }

        res2.close();
        stm2.close();

        PreparedStatement stm3 = con.prepareStatement(ESTADO);

        stm3.setInt(1, id_estado);

        ResultSet res3 = stm3.executeQuery();

        while (res3.next()) {
            String nome_Estado;

            nome_Estado = res3.getString("nome");

            Estado estado = new Estado(nome_Estado);
            cidade = new Cidade(nome_cidade, estado);
            u = new Usuario(id, nome, email, senha, cidade);
        }

        res3.close();
        stm3.close();

        con.close();

        return u;
    }

    @Override
    public Usuario buscaId(int id) throws SQLException{

        Usuario u = null;
        String nome_cidade = "";
        Cidade cidade = null;
        int id_estado = -1;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String email = res.getString("email");
            String senha = res.getString("senha");
            int id_cidade = res.getInt("id_cidade");
            PreparedStatement stm2 = con.prepareStatement(CIDADE);

            stm2.setInt(1, id_cidade);

            ResultSet res2 = stm2.executeQuery();

            while (res2.next()) {
                nome_cidade = res2.getString("nome");
                id_estado = res2.getInt("id_estado");
            }

            res2.close();
            stm2.close();

            PreparedStatement stm3 = con.prepareStatement(ESTADO);

            stm3.setInt(1, id_estado);

            ResultSet res3 = stm3.executeQuery();

            while (res3.next()) {
                String nome_Estado;

                nome_Estado = res3.getString("nome");

                Estado estado = new Estado(nome_Estado);
                cidade = new Cidade(nome_cidade, estado);
            }

            res3.close();
            stm3.close();

            u = new Usuario(nome, email, senha, cidade);
        }

        res.close();
        stm.close();
        con.close();

        return u;
    }
}