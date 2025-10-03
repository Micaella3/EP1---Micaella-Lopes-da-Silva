
import java.util.List;

public class PacienteEspecial extends Paciente {
    
    private PlanoSaude planoSaude;
    private int grauPrioridade;

    //construtor
    public PacienteEspecial(PlanoSaude planoSaude, String nome, String cpf, int idade, List<String> historicoConsultas, List<String> historicoInternacoes,  int grauPrioridade) {
        super(nome, cpf, idade, historicoConsultas, historicoInternacoes);
        this.planoSaude = planoSaude;
        this.grauPrioridade = grauPrioridade;
    }

    //getters e setters  
    public PlanoSaude getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaude planoSaude) {
        this.planoSaude = planoSaude;
    }

    public int getGrauPrioridade() {
        return grauPrioridade;
    }

    public void setGrauPrioridade(int grauPrioridade) {
        this.grauPrioridade = grauPrioridade;
    }

    //metodoDescontoInternacao
    //metodocustoconsulta

    //toString
    @Override
    public String toString(){
        return "\n --- Paciente Especial ---" +
                "\n Nome: " + getNome() +
                "\n CPF: " + getCpf() +
                "\n Idade: " + getIdade()+
                "\n Histórico de Consultas: " + getHistoricoConsultas()+
                "\n Histórico de Internações: " + getHistoricoInternacoes() +
                "\n Informações sobre o plano de saúde: " + getPlanoSaude()+
                "\n Grau de Prioridade: " + getGrauPrioridade();
    }
}
