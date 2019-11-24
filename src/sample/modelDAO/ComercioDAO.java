package sample.modelDAO;

import sample.model.Comercio;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface ComercioDAO {
    Comercio insere(Comercio comercio) throws SQLException;
    Comercio verif(String Nome) throws SQLException;
    List<Comercio> lista() throws SQLException;
    Comercio buscaId(int id) throws SQLException;
}
