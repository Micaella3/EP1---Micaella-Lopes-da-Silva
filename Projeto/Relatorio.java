import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Relatorio{

    private List<Medico> medicos;
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private List<Internacoes> internacoes;

    //const.
    public Relatorio(List<Consulta> consultas, List<Internacoes> internacoes, List<Medico> medicos, List<Paciente> pacientes) {
        this.consultas = consultas;
        this.internacoes = internacoes;
        this.medicos = medicos;
        this.pacientes = pacientes;
    }

    //metodos de lista
    public void listarPaciente(){
        System.out.println("\n Relatório de Pacientes Cadastrados: ");
        pacientes.forEach(p -> System.out.println(p.toString()));
    }

    public void listarMedicos(){
        System.out.println("Relatório de Médicos Cadastrados e Produtividade Deles: ");
        medicos.forEach(m -> System.out.println(m.toString()+ "\n Consultas Realizadas: " + m.consultasRealizadas()));
    }

    public void listarInternados(){
        //.stream é sequência de elementos que suporta várias operações de processamento
        this.internacoes.stream()
        .filter(Internacoes::isInternado)
        .forEach(i -> System.out.println("Paciente Internado: " + i.getPaciente().getNome() + " Quarto: " + i.getQuarto().getNumero()));
    }

    public void listarConsultasFuturas() { 
        System.out.println("\n Consultas Futuras: ");
    }
    
    public void listarConsultasPassadas() {
        System.out.println("\n Consultas Passadas: ");
    }
    
    //met. de estatistica
    public void estatistica() {
        System.out.println("\n---| Estatisticas Gerais do Hospital |---");

        medicos.stream()
        .max(Comparator.comparingInt(Medico::consultasRealizadas))
        .ifPresentOrElse(m -> System.out.println("Médico(a) Mais Produtivo: " + m.getNome()),
        () -> System.out.println("Nenhum médico encontrado."));

    //especialidade mais procurada
        consultas.stream()
            .map(c -> c.getMedico().getEspecialidade())
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(e -> System.out.println("Especialidade Mais Procurada: " + e.getKey()));
            
    }

}
