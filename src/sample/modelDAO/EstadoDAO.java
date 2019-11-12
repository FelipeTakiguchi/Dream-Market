package sample.modelDAO;

import sample.model.Estado;

import java.sql.SQLException;
import java.util.List;

public interface EstadoDAO {
    Estado insere(String nome) throws SQLException;
    Estado verif(String Nome) throws SQLException;
    List<Estado> lista() throws SQLException;
    Estado buscaId(int id) throws SQLException;
}
