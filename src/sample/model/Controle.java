package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.modelDAO.*;

import java.sql.SQLException;
import java.util.List;

public class Controle {

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    CidadeDAO cidadeDAO = new CidadeDAOImpl();

    private ObservableList<Usuario> usuarios;
    private ObservableList<Produto> produtos;
    private static Usuario usuario;
    private static UsuarioAdm usuarioAdm;

    private Controle(){
        usuarios = FXCollections.observableArrayList();
        produtos = FXCollections.observableArrayList();
    }

    private static Controle instance = new Controle();

    public static UsuarioAdm getUsuarioAdm() {
        return usuarioAdm;
    }

    public static void setUsuarioAdm(UsuarioAdm usuarioAdm) {
        Controle.usuarioAdm = usuarioAdm;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Controle.usuario = usuario;
    }

    public static Controle getInstance(){
        return instance;
    }

    public boolean verificaUsuario(String usuario, String senha) throws SQLException {
        if(usuarioDAO.verif(usuario, senha) == null){
            return false;
        }

        return true;
    }

    public List<Cidade> carregaCidades() throws SQLException {
        return cidadeDAO.lista();
    }
}
