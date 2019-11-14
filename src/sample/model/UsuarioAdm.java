package sample.model;

public class UsuarioAdm extends Usuario{

    private String telefone;
    private String cpf;

    public UsuarioAdm(String nome, String email, String senha, Cidade cidade, String telefone, String cpf) {
        super(nome, email, senha, cidade);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public UsuarioAdm(int id, String nome, String email, String senha, Cidade cidade, String telefone, String cpf) {
        super(id, nome, email, senha, cidade);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}