package sample.modelDAO;

import sample.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    Usuario insere(String nome, String email, String senha, int id_Cidade) throws SQLException;
    Usuario verif(String Nome) throws SQLException;
    List<Usuario> lista() throws SQLException;
    Usuario buscaId(int id) throws SQLException;
}
