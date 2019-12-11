package sample.modelDAO;

import sample.model.*;

import java.sql.SQLException;
import java.util.List;

public interface ListaItemDAO {
    ListaItem insere(ListaItem listaItem) throws SQLException;
    List<ListaItem> lista() throws SQLException;
    List<ListaItem> buscaId(int id) throws SQLException;
}
