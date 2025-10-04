public class Consulta {
    
    private Medico medico;
    private Paciente paciente;
    private String dataHora;
    private String local;
    private String status;
    private String prescricao; 
    private String registro;
    private String diagnostico;

    //cosntrutor 
public Consulta(Medico medico,Paciente paciente, String dataHora, String local, String status, String prescricao, String registro, String diagnostico){
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

public String getDataHora(){
    return dataHora;
}

public void setDataHora(String dataHora){
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
