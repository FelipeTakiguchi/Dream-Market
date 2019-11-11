package sample.modelDAO;

import sample.model.UsuarioAdm;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioAdmDAO {
    UsuarioAdm insere(String nome, String email, String senha, int id_Cidade, String telefone, String cpf) throws SQLException;
    UsuarioAdm verif(String Nome, String Senha) throws SQLException;
    List<UsuarioAdm> lista() throws SQLException;
    UsuarioAdm buscaId(int id) throws SQLException;
}
