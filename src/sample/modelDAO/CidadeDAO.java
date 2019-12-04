package sample.modelDAO;

import sample.model.Cidade;
import sample.model.Estado;

import java.sql.SQLException;
import java.util.List;

public interface CidadeDAO {
    Cidade insere(String nome, Estado estado) throws SQLException;
    Cidade verif(String Nome) throws SQLException;
    List<Cidade> lista() throws SQLException;
    Cidade buscaId(int id) throws SQLException;
}