package sample.modelDAO;

import sample.model.Produto;
import sample.model.FabricaConexao;
import sample.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private static String INSERE = "insert into usuario(nome, email, senha, id_Cidade) values(?, ?, ?, ?)";
    private static String VERIF = "select nome from usuario where nome like ?";
    private static String LISTA = "select * from usuario";
    private static String BUSCAID = "select * from usuario where id like ?";

    @Override
    public Usuario insere(String nome, String email, String senha, int id_Cidade) throws SQLException {
        Usuario u = new Usuario(nome, email, senha, id_Cidade);
        Connection con = FabricaConexao.getConnection();


        PreparedStatement stm = con
                .prepareStatement(INSERE);

        stm.setString(1,u.getNome());
        stm.setString(1,u.getEmail());
        stm.setString(1,u.getSenha());
        stm.setInt(1,u.getId_Cidade());

        stm.executeUpdate();

        stm.close();
        con.close();

        return u;
    }

    @Override
    public Usuario verif(String Nome) throws SQLException {

        Usuario u = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(VERIF);

        stm.setString(1,Nome);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String email = res.getString("email");
            String senha = res.getString("senha");
            int id_cidade = res.getInt("id_Cidade");

            u = new Usuario(nome, email, senha, id_cidade);
        }

        res.close();
        stm.close();
        con.close();

        return u;
    }

    @Override
    public List<Usuario> lista() throws SQLException{
        ArrayList<Usuario> Usuarios = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();
        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            int id_cidade = rs.getInt("id_cidade");
            Usuario cat = new Usuario(id, nome, email, senha, id_cidade);

            Usuarios.add(cat);
        }

        con.close();
        stm.close();
        rs.close();

        return Usuarios;
    }

    @Override
    public Usuario buscaId(int id) throws SQLException{

        Usuario u = null;

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(BUSCAID);

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String email = res.getString("email");
            String senha = res.getString("senha");
            int id_cidade = res.getInt("id_cidade");
            u = new Usuario(id, nome, email, senha, id_cidade);
        }

        res.close();
        stm.close();
        con.close();

        return u;
    }
}
