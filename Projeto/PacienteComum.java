
import java.util.List;

public class PacienteComum extends Paciente {

    private String metodoPagamento;
    
    //construtor 
    public PacienteComum(String metodoPagamento, String nome, String cpf, int idade, List<String> historicoConsultas, List<String> historicoInternacoes) {
        super(nome, cpf, idade, historicoConsultas, historicoInternacoes);
        this.metodoPagamento = metodoPagamento;
    }

    //gettters e setters
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
    
    //string
    @Override
    public String toString(){
        return "\n --- Paciente Comum ---" +
        "\n Nome: " + getNome() +
                "\n CPF: " + getCpf() +
                "\n Idade: " + getIdade()+
                "\n Histórico de Consultas: " + getHistoricoConsultas()+
                "\n Histórico de Internações: " + getHistoricoInternacoes() +
                "\n Método de Pagamento: " + getMetodoPagamento();
    }
}