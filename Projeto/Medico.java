
import java.util.ArrayList;
import java.util.List;

public class Medico {
   
    private String nome;
    private String especialidade;
    private String crm;
    private double custoConsulta;
    private List<String> agendaHorarios;
    private List<Consulta> historicoConsultas;

    //construtor 
    public Medico(String nome, String especialidade, String crm, double custoConsulta, List<String> agendaHorarios){
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.agendaHorarios = agendaHorarios;
        this.historicoConsultas = new ArrayList<>();
}

    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public double  getCustoConsulta() {
        return custoConsulta;
    }

    public void setCustoConsulta(double custoConsulta) {
        this.custoConsulta = custoConsulta;
    }

    public List<String> getAgendaHorarios() {
        return agendaHorarios;
    }

    public void setAgendaHorarios(List<String> agendaHorarios) {
        this.agendaHorarios = agendaHorarios;
    }

    //método de toCvs q converte as informações em uma linha única de texto p ser mais fácil d salvar
public String toCSV(){
    //esse método d join é uma coleção de strings
    String agendaFormatada = String.join(",", this.agendaHorarios);

    return getNome() + ";" +
           getEspecialidade() + ";" +
           getCrm() + ";" +
           String.format("%.2f", getCustoConsulta()) + ";" +
           agendaFormatada;
}

    //método p saber a disponibilidade 
public boolean disponibilidade(){
    return !this.agendaHorarios.isEmpty();
}

    //método p saber a quant. de consultas realizadas
public int consultasRealizadas(){
    return this.historicoConsultas.size();
}

    //método p hist. de consultas
public void adicionarConsultaRealizada(Consulta c){
    this.historicoConsultas.add(c);
}

    //toString
    @Override
    public String toString() {
        return "\n *** Médico ***" +
                "\n Nome: " +getNome() +
                "\n Especialidade: " + getEspecialidade() +
                "\n CRM: " + getCrm() +
                "\n Custo da Consulta: " + getCustoConsulta() +
                "\n Agenda de Horários: " + getAgendaHorarios();
    }

}

