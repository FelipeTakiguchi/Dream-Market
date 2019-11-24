package sample.model;

import java.sql.Time;

public class Comercio {
    private int Id;
    private String nome;
    private String Endereco;
    private Time horarioInicio;
    private Time horarioFim;
    private Cidade cidade;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public Time getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Time horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Time getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Time horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Comercio(String nome, String endereco, Time horarioInicio, Time horarioFim, Cidade cidade) {
        this.nome = nome;
        Endereco = endereco;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.cidade = cidade;
    }

    public Comercio(int id, String nome, String endereco, Time horarioInicio, Time horarioFim, Cidade cidade) {
        Id = id;
        this.nome = nome;
        Endereco = endereco;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.cidade = cidade;
    }
}
