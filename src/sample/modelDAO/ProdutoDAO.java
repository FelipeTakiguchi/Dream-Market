package sample.modelDAO;

import sample.model.Produto;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoDAO {
    Produto insere(String nome, String marca, String descricao, int id_responsavel) throws SQLException;
    Produto verif(String Nome) throws SQLException;
    List<Produto> lista() throws SQLException;
    List<Produto> listaAdm(int id_usuario) throws SQLException;
    Produto buscaId(int id) throws SQLException;
}
