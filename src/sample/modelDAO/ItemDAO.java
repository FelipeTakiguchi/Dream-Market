package sample.modelDAO;

import sample.model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    Item insere(Item item) throws SQLException;
    Item verif(String Nome) throws SQLException;
    List<Item> lista() throws SQLException;
    Item buscaId(int id) throws SQLException;
}
