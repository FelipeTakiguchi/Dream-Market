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
    ComercioDAO comercioDAO = new ComercioDAOImpl();
    CidadeDAO cidadeDAO = new CidadeDAOImpl();
    EstadoDAO estadoDAO = new EstadoDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();

    private ObservableList<Usuario> usuarios;
    private ObservableList<Produto> produtos;
    private static Usuario usuario;
    private static UsuarioAdm usuarioAdm;
    private static Boolean ok;

    public static Boolean getOk() {
        return ok;
    }

    public static void setOk(Boolean ok) {
        Controle.ok = ok;
    }

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

    public Lista salvaLista(Lista lista) throws SQLException{
        ListaDAO listaDAO = new ListaDAOImpl();
        ListaItemDAO listaItemDAO = new ListaItemDAOImpl();

        Lista list = listaDAO.insere(lista);
        int max = listaDAO.retornaMax();
        lista.setId(max);

        int i = 0;

        while(i < lista.getListaItems().size()){
            listaItemDAO.insere(lista.getListaItems().get(i));
            i++;
        }

        return list;
    }

    public List<Lista> carregaListas() throws SQLException {
        ListaDAO listaDAO = new ListaDAOImpl();
        return listaDAO.lista();
    }

    public Usuario addUsuario(Usuario usuario) throws SQLException{
        return usuarioDAO.insere(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCidade());
    }

    public Usuario mudaUsuarioNormal(Usuario usuario) throws SQLException {
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

    public UsuarioAdm getAdm(Usuario usuario) throws SQLException{
        UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
        return usuarioAdmDAO.buscaId(usuario.getId());
    }

    public UsuarioAdm verificaAdm(String credencial, String senha) throws SQLException{
        if(usuarioAdmDAO.verif(credencial, senha) != null){
            return usuarioAdmDAO.verif(credencial, senha);
        }
        if(usuarioAdmDAO.verifEmail(credencial, senha) != null){
            return usuarioAdmDAO.verifEmail(credencial, senha);
        }

        return null;
    }

    public ObservableList<Item> pesquisaItem(String nome) throws SQLException {
        ObservableList<Item> items = (ObservableList<Item>) itemDAO.buscaNome(nome);
        return items;
    }

    public List<Item> listaItems() throws SQLException{
        List<Item> items = itemDAO.lista();
        return items;
    }

    public List<Comercio> listaComercios() throws  SQLException{
        List<Comercio> comercios = comercioDAO.lista();
        return comercios;
    }

    public List<Produto> listaProdutos() throws SQLException{
        List<Produto> produtos = produtoDAO.lista();
        return produtos;
    }

    public Comercio addComercio(Comercio comercio) throws SQLException {
        return comercioDAO.insere(comercio);
    }

    public List<Produto> listaProdutosAdm() throws SQLException{
        List<Produto> produtos = produtoDAO.listaAdm(usuario.getId());
        return produtos;
    }

    public void atualizaItem(Item item, Float valor) throws SQLException{
        itemDAO.atualiza(item, valor);
    }

    public void addProduto(Produto produto) throws SQLException{
        produtoDAO.insere(produto.getNome(), produto.getMarcca(), produto.getDescricao(), produto.getId_responsavel());
    }

    public void addItem(Item item) throws SQLException {
        itemDAO.insere(item);
    }

    public List<Item> listaItemsAdm() throws SQLException {
        return itemDAO.listaAdm(usuario);
    }

    public List<Item> pesquisaComercio(Comercio comercio) throws SQLException {
        return itemDAO.pesquisaComercio(comercio);
    }

    public List<Item> pesquisaCidade(Cidade cidade) throws SQLException{
        return itemDAO.pesquisaCidade(cidade);
    }
}
