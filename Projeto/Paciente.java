import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private String nome;
    private String cpf;
    private int idade;
    private List<String> historicoConsultas;
    private List<String> historicoInternacoes;

    //construtor 
    public Paciente(String nome, String cpf, int idade, List<String> historicoConsultas, List<String> historicoInternacoes){
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
    public List<String> getHistoricoConsultas(){
        return historicoConsultas;
    }
    public List<String> getHistoricoInternacoes(){
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
    public void setHistoricoConsulta(List<String> historicoConsultas){
        this.historicoConsultas = new ArrayList<>(historicoConsultas);
    }
     public void setHistoricoInternacoes(List<String> historicoInternacoes){
        this.historicoInternacoes = new ArrayList<>(historicoInternacoes);
    }

    //tostring
    public String tostring(){
        return "\n *** Paciente ***" +
                "\n Nome: " + getNome() +
                "\n CPF: " + getCpf() +
                "\n Idade: " + getIdade()+
                "\n Histórico de Consultas: " + getHistoricoConsultas()+
                "\n Histórico de Internações: " + getHistoricoInternacoes();

    }
    

}
