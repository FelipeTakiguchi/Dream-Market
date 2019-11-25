package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.modelDAO.*;

import java.sql.SQLException;
import java.util.List;

public class Controle {

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
    CidadeDAO cidadeDAO = new CidadeDAOImpl();
    EstadoDAO estadoDAO = new EstadoDAOImpl();

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
        System.out.println(usuarioAdm);
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

    public Usuario addUsuario(Usuario usuario) throws SQLException{
        return usuarioDAO.insere(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCidade());
    }

    public Usuario verificaUsuarioNome(String nome, String senha) throws SQLException {
        return usuarioDAO.verifNome(nome, senha);
    }

    public Usuario verificaUsuarioEmail(String email, String senha) throws SQLException {
        return usuarioDAO.verifEmail(email, senha);
    }

    public List<Cidade> carregaCidades() throws SQLException {
        return cidadeDAO.lista();
    }

    public Boolean addCidade(String cidade, Estado estado) throws SQLException {
        try {
            cidadeDAO.insere(cidade, estado);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Estado verificaEstado(String nome) throws SQLException {
        return estadoDAO.verif(nome);
    }

    public Estado registraNovoEstado(String nome) throws  SQLException{
        return estadoDAO.insere(nome);
    }

    public List<Estado> listaEstados() throws SQLException{
        return estadoDAO.lista();
    }

    public Cidade verificaCidade(String nomeCidade) throws SQLException {
        return cidadeDAO.verif(nomeCidade);
    }

    public UsuarioAdm addUsuarioAdm(UsuarioAdm usuarioAdm) throws SQLException {
        return usuarioAdmDAO.insere(usuarioAdm.getNome(), usuarioAdm.getEmail(), usuarioAdm.getSenha(), usuarioAdm.getCidade(), usuarioAdm.getTelefone(), usuarioAdm.getCpf());
    }

    public UsuarioAdm mudaUsuario(UsuarioAdm usuarioAdm) throws SQLException {
        return usuarioAdmDAO.insereadm(usuarioAdm.getNome(), usuarioAdm.getEmail(), usuarioAdm.getSenha(), usuarioAdm.getCidade(), usuarioAdm.getTelefone(), usuarioAdm.getCpf());
    }

    public Boolean isAdm(String credencial, String senha) throws SQLException{
        if(usuarioAdmDAO.verif(credencial, senha) != null || usuarioAdmDAO.verifEmail(credencial, senha) != null){
            return true;
        }

        return false;
    }
}
