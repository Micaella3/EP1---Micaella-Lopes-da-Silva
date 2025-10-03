
import java.util.List;

public class Medico {
   
    private String nome;
    private String especialidade;
    private String crm;
    private int custoConsulta;
    private List<String> agendaHorarios;

    //construtor 
    public Medico(String nome, String especialidade, String crm, int custoConsulta, List<String> agendaHorarios){
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.agendaHorarios = agendaHorarios;
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

    public int getCustoConsulta() {
        return custoConsulta;
    }

    public void setCustoConsulta(int custoConsulta) {
        this.custoConsulta = custoConsulta;
    }

    public List<String> getAgendaHorarios() {
        return agendaHorarios;
    }

    public void setAgendaHorarios(List<String> agendaHorarios) {
        this.agendaHorarios = agendaHorarios;
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

