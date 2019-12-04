package sample.modelDAO;

import sample.model.*;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    Item insere(Item item) throws SQLException;
    Item verif(String Nome) throws SQLException;
    List<Item> lista() throws SQLException;
    Item buscaId(int id) throws SQLException;
    List<Item> buscaNome(String nome) throws SQLException;
    void atualiza(Item item, Float valor) throws SQLException;
    List<Item> listaAdm(Usuario usuario) throws SQLException;
    List<Item> pesquisaComercio(Comercio c) throws SQLException;
    List<Item> pesquisaCidade(Cidade cidade) throws SQLException;
}
