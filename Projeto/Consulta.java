import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {

    //formatando data e hora
    private static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    //atributos
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataHora;
    private String local;
    private String status;
    private String prescricao; 
    private String registro;
    private String diagnostico;

    public static final String STATUS_AGENDADA = "Agendada";
    public static final String STATUS_CONCLUIDA = "Concluída";
    public static final String STATUS_CANCELADA = "Cancelada";

    //cosntrutor 
public Consulta(Medico medico,Paciente paciente, LocalDateTime dataHora, String local, String status, String prescricao, String registro, String diagnostico){
    this.medico = medico;
    this.paciente = paciente;
    this.dataHora = dataHora;
    this.local = local;
    this.status = status;
    this.prescricao = prescricao;
    this.registro = registro;
    this.diagnostico =diagnostico;
}

//getters e setters
public Medico getMedico(){
    return medico;
}

public void setMedico(Medico medico){
    this.medico = medico;
}

public Paciente getPaciente(){
    return paciente;
}

public void setPaciente(Paciente paciente){
    this.paciente = paciente;
}

public LocalDateTime getDataHora(){
    return dataHora;
}

public void setDataHora(LocalDateTime dataHora){
    this.dataHora = dataHora;
}

public String getLocal(){
    return local;
}

public void setLocal(String local){
    this.local = local;
}

public String getStatus(){
    return status;
}

public void setStatus(String status){
    this.status = status;
}

public String getPrescricao(){
    return prescricao;
}

public void setPrescricao(String prescricao){
    this.prescricao = prescricao;
}

public String getRegistro(){
    return registro;
}

public void setRegistro(String registro){
    this.registro = registro;
}

public String getDiagnostico(){
    return diagnostico;
}

public void setDiagnostico(String diagnostico){
    this.diagnostico = diagnostico;
}

//met. SCV
    public String toCSV(){
        String dataHoraFormatada = this.dataHora.format(FORMATTER);

        return this.medico.getCrm() + ";" +
               this.paciente.getCpf() + ";" +
               dataHoraFormatada + ";" +
               getLocal() + ";" +
               getStatus() + ";" +
               getPrescricao() + ";" +
               getRegistro() + ";" +
               getDiagnostico();
    }

//met. de registrar diagnostico
public void registrarDiagnostico(String diagnostico, String prescricao, String registro){
    this.setDiagnostico(diagnostico);
    this.setPrescricao(prescricao);
    this.setRegistro(registro);
}

//met. finalizar consulta
public void finalizarConsulta(){
    if(this.diagnostico == null){
        System.out.println("Erro: É preciso do diagnóstico.");
    return;
    }

    //status alterado
    this.setStatus("Consulta Concluída.");

    //p registrar essa consulta no hist. do paciente
    if (this.paciente != null){
        this.paciente.adicionarConsulta(this);
        this.medico.adicionarConsultaRealizada(this);
    
        System.out.println("Consulta finalizada.");
    }
}
    //met. calcular o custo final usando o custo d consulta
public double calcularCustoFinal(){
    double custoInicial = this.medico.getCustoConsulta();
    
    //desconto por idade
    double descontoIdade = this.paciente.calcularDescontoConsulta(); 
    
    //por especialidade
    double descontoPlanoAplicavel = 0.0;
    
    //verifica se o paciente é especial ou n
    if (this.paciente instanceof PacienteEspecial) {
        PacienteEspecial pe = (PacienteEspecial) this.paciente;
        
        //pega a especialidade
        String especialidade = this.medico.getEspecialidade(); 
        
        //chama o met. q retorna o desconto
        descontoPlanoAplicavel = pe.getDescontoPlanoAplicavel(especialidade);
    }
    
   double custoAtual = custoInicial;
    custoAtual *= (1.0 - descontoIdade); //desconto idade
    custoAtual *= (1.0 - descontoPlanoAplicavel); //desconto do plano

return custoAtual;
}

//toString 
public String toString(){
    return "\n --- Informações da Consulta ---" +
            "\n Médico Responsável: " + getMedico() +
            "\n Paciente: " + getPaciente() +
            "\n Data e Hora da Consulta: " + getDataHora()+
            "\n Local: " + getLocal() +
            "\n Status Atual: " + getStatus() +
            "\n Prescrição: " + getPrescricao() +
            "\n Registro: " + getRegistro() +
            "\n Diagnóstico: " + getDiagnostico();
    }
}
