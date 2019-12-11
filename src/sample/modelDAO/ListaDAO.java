package sample.modelDAO;

import sample.model.*;

import java.sql.SQLException;
import java.util.List;

public interface ListaDAO {
    Lista insere(Lista lista) throws SQLException;
    Lista verif(String Nome) throws SQLException;
    List<Lista> lista() throws SQLException;
    Lista buscaId(int id) throws SQLException;
    List<Lista> buscaNome(String nome) throws SQLException;
    int retornaMax() throws SQLException;
}
