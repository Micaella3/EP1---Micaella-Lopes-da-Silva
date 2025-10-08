import java.util.ArrayList;
import java.util.List;

public class SistemaHospitalar {
    
    private List<Medico> medicos;
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private List<Internacoes> internacoes;
    private List<Quarto> quartos;
    private List<PlanoSaude> planos;

    //construtor
public SistemaHospitalar() {
    this.medicos = new ArrayList<>();
    this.pacientes = new ArrayList<>();
    this.consultas = new ArrayList<>();
    this.internacoes = new ArrayList<>();
    this.quartos = new ArrayList<>();
    this.planos = new ArrayList<>();
}

    //cadastros
    public void cadastrarMedico(Medico m){
        this.medicos.add(m);
        System.out.println(" - Médico " + m.getNome() + " cadastrado.");
    }

    public void cadastrarPaciente(Paciente p){
        this.pacientes.add(p);
        System.out.println(" - Paciente " + p.getNome() + " cadastrado.");
    }

    public void cadastrarQuarto(Quarto q){
        this.quartos.add(q);
    }

    public void cadastrarPlanoSaude(PlanoSaude ps){
        this.planos.add(ps);
    }

    //**met p/ permitir q a main encontre o plano antes d cadastrar pEspecial
    public PlanoSaude buscarPlanoSaudePorDescricao(String descricao) {
    return this.planos.stream()
               .filter(p -> p.getDescricao().equalsIgnoreCase(descricao))
               .findFirst()
               .orElse(null);
}

    //met. de agendamento d consulta
public boolean agendarConsulta(Consulta novaConsulta){
    Medico medicoDesejado = novaConsulta.getMedico();
    
    String horaConsultaStr = novaConsulta.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    
    for (Consulta c: consultas) {
        if (c.getMedico().equals(medicoDesejado) && c.getDataHora().equals(novaConsulta.getDataHora())){
            System.err.println("*Erro: Médico indisponível no horário.");
            return false;
    }
    
    //se o horário está na agenda do Médico 
     if (!medicoDesejado.getAgendaHorarios().contains(horaConsultaStr)) {
        System.err.println("*Erro: O horário " + horaConsultaStr + " não está na agenda do médico.");
        return false;
        }
    }
    // registro deu bom
    consultas.add(novaConsulta);
    novaConsulta.setStatus("Agendada");
    
    // remove o horario da agdenda do medico
    medicoDesejado.getAgendaHorarios().remove(horaConsultaStr);
    
    // add no histórico
    novaConsulta.getPaciente().adicionarConsulta(novaConsulta);
    
    System.out.println("| Consulta agendada com sucesso! |");
    return true;

}
    //para internações
public boolean registrarInternacoes (Internacoes nova) {

    if(nova.getQuarto().isOcupado()){
        System.err.println("*Erro: O quarto está ocupado.");
        return false;
    }

    //ocupa o quarto e registra ele
    nova.getQuarto().ocupar();
    internacoes.add(nova);

    //add no hist. do paciente
    nova.getPaciente().adicionarInternacao(nova);

    System.out.println("| Internação registrada! |");
    return true;
}

    public boolean liberarInternacao(Internacoes i) {
        if (i.cancelaInternacao()){
            System.out.println("- Paciente liberado da internação.");
            return true; //retorno em caso de sucesso
        } else{
            System.err.println("*Erro: Internação já foi finalizada ou não estava ativa.");
            return false; //retorno em caso de falha
        }
    }

//getters p relatorios
 public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Internacoes> getInternacoes() {
        return internacoes;
    }
     public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<PlanoSaude> getPlanos(){
        return planos;
    }

    //os met. de busca que sao puxados no repositório
    public Medico buscarMedicoPorCrm(String crm){
        return this.medicos.stream()
                            .filter(m -> m.getCrm().equals(crm))
                            .findFirst()
                            .orElse(null);
    }

    public Paciente buscarPacientePorCpf(String cpf) {
    return this.pacientes.stream()
               .filter(p -> p.getCpf().equals(cpf))
               .findFirst()
               .orElse(null);
    }

    public Quarto buscarQuartoPorNumero(int numero) {
    return this.quartos.stream()
               .filter(q -> q.getNumero() == numero)
               .findFirst()
               .orElse(null);

    }

 //buscando uma consulta agendada pelo cpf e data registrando no histórico do médico
    public boolean finalizarConsulta(Consulta consultaParaFinalizar) {
     if (consultaParaFinalizar == null || 
        !consultaParaFinalizar.getStatus().equals("Agendada")) {
        System.err.println("\n *Erro: Consulta não está no status 'Agendada' ou é inválida.");
        return false;
    }

    consultaParaFinalizar.finalizarConsulta();
    System.out.println("\n Consulta finalizada e diagnóstico registrado!");
    return true;
}

}
