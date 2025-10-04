import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private String nome;
    private String cpf;
    private int idade;
    private List<Consulta> historicoConsultas;
    private List<Internacoes> historicoInternacoes;

    //construtor 
    public Paciente(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.historicoConsultas = new ArrayList<>(); 
        this.historicoInternacoes = new ArrayList<>();
    }
    //getters e setters
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public int getIdade(){
        return idade;
    }
    public List<Consulta> getHistoricoConsultas(){
        return historicoConsultas;
    }
    public List<Internacoes> getHistoricoInternacoes(){
        return historicoInternacoes;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }
    public void setHistoricoConsulta(List<Consulta> historicoConsultas){
        this.historicoConsultas = new ArrayList<>(historicoConsultas);
    }
     public void setHistoricoInternacoes(List<Internacoes> historicoInternacoes){
        this.historicoInternacoes = new ArrayList<>(historicoInternacoes);
    }

    //método de adicionar consulta no histórico
public void adicionarConsulta(Consulta c){
    this.historicoConsultas.add(c);
}

    //mét. que add internações no histórico
public void adicionarInternacao(Internacoes i){
    this.historicoInternacoes.add(i);
}

    //mét. que calcula o desconto da consulta
public double calcularDescontoConsulta(){
    if (idade>= 60){
        return 0.30;
    }
    return 0.0;
}
}
