

public class PacienteComum extends Paciente {

    private String metodoPagamento;
    
    //construtor 
    public PacienteComum(String metodoPagamento, String nome, String cpf, int idade) {
        super(nome, cpf, idade);
        this.metodoPagamento = metodoPagamento;
    }

    //gettters e setters
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    //método csv
@Override
public String toCSV(){
    return "COMUM;" +
            super.toCSV() + ";" +
            getMetodoPagamento();
}
    
    //método d calcular o custo da consulta
public double calcularCustoConsulta(double custoInicial){

    //pega da super
    double desconto = super.calcularDescontoConsulta();
    return custoInicial*(1.0 - desconto);
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