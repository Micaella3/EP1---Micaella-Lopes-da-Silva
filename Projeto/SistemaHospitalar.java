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
        System.out.println("Médico " + m.getNome() + " cadastrado.");
    }

    public void cadastrarPaciente(Paciente p){
        this.pacientes.add(p);
        System.out.println("Paciente " + p.getNome() + " cadastrado.");
    }

    public void cadastrarQuarto(Quarto q){
        this.quartos.add(q);
    }

    public void cadastrarPlanoSaude(PlanoSaude ps){
        this.planos.add(ps);
    }

    //met. de agendamento d consulta
public boolean agendarConsulta(Consulta novaConsulta){
    Medico medicoDesejado = novaConsulta.getMedico();

    //validação de conflito d horario e local
    for (Consulta c: consultas) {
        if (c.getMedico().equals(medicoDesejado) && c.getDataHora().equals(novaConsulta.getDataHora())){
            System.err.println("Erro: Médico indisponível no horário.");
            return false;
        }
        if (c.getLocal().equals(novaConsulta.getLocal()) && c.getDataHora().equals(novaConsulta.getDataHora())) {
            System.err.println("Erro: Local Ocupado.");
            return false;
        }
    }
    
    //se o horário está na agenda do Médico 
    if (!medicoDesejado.getAgendaHorarios().contains(novaConsulta.getDataHora())) {
        System.err.println("Erro: O horário " + novaConsulta.getDataHora() + " não está na agenda do médico.");
        return false;
    }

    // registro deu bom
    consultas.add(novaConsulta);
    novaConsulta.setStatus("Consulta Agendada.");
    
    // remove o horario da agdenda do medico
    medicoDesejado.getAgendaHorarios().remove(novaConsulta.getDataHora());
    
    // add no histórico
    novaConsulta.getPaciente().adicionarConsulta(novaConsulta);
    
    System.out.println("Consulta agendada com sucesso!");
    return true;
}

    //para internações
public boolean registrarInternacoes (Internacoes nova) {

    if(nova.getQuarto().isOcupado()){
        System.err.println("Erro: O quarto está ocupado.");
        return false;
    }

    //ocupa o quarto e registra ele
    nova.getQuarto().ocupar();
    internacoes.add(nova);

    //add no hist. do paciente
    nova.getPaciente().adicionarInternacao(nova);

    System.out.println("Internação registrada!");
    return true;
}

public void liberarInternacao(Internacoes i) {
    if (i.cancelaInternacao()){
        System.out.println("Paciente liberado da internação.");
    } else{
        System.err.println("Erro: Internação já foi finalizada ou não estava ativa.");
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
}
