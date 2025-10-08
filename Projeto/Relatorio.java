import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
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
        System.out.println("\n Pacientes Atualmente Internados:");
        
         //.stream é sequência de elementos que suporta várias operações de processamento
        this.internacoes.stream()
        .filter(Internacoes::isInternado)
        .forEach(i -> {
            //calculo d tempo q diferencia a data d entrada e hoje
            long dias = ChronoUnit.DAYS.between(i.getDataEntrada(), LocalDate.now());
            
            System.out.printf("Paciente: %s | Quarto: %d | Dias Internado: %d\n", 
                                i.getPaciente().getNome(), i.getQuarto().getNumero(), dias);
        });
    }

public void listarConsultasFuturas() {
    System.out.println("\n--- Consultas Futuras ---");

    List<Consulta> futuras = consultas.stream()
        .filter(c -> c.getDataHora() != null && c.getDataHora().isAfter(LocalDateTime.now()))
        .collect(java.util.stream.Collectors.toList());

    if (futuras.isEmpty()) {
        System.out.println("Nenhuma consulta futura agendada.");
    } else {
        futuras.forEach(System.out::println);
    }
}

public void listarConsultasPassadas() {
    System.out.println("\n--- Consultas Passadas ---");
    
    List<Consulta> passadas = consultas.stream()
        .filter(c -> c.getDataHora() != null && c.getDataHora().isBefore(LocalDateTime.now()))
        .collect(java.util.stream.Collectors.toList());

    if (passadas.isEmpty()) {
        System.out.println("Nenhuma consulta passada registrada.");
    } else {
        passadas.forEach(System.out::println);
    }
}
    
    //met. de estatistica
    public void estatistica() {
        System.out.println("\n---| Estatisticas Gerais do Hospital |---");

        medicos.stream()
        .max(Comparator.comparingInt(Medico::consultasRealizadas))
        .ifPresentOrElse(m -> System.out.println("\n *** Médico(a) Mais Produtivo: ***" + m.getNome()),
        () -> System.out.println("Nenhum médico encontrado."));

    //especialidade mais procurada
        consultas.stream()
            .map(c -> c.getMedico().getEspecialidade())
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresent(e -> System.out.println("\n*** Especialidade Mais Procurada: ***" + e.getKey()));
            
            this.calcularEconomiaPlano();
    }

    public void calcularEconomiaPlano(){
        //mapiando paciente especial
        Map<String, Double> economiaPorPlano = this.pacientes.stream()
            .filter(p -> p instanceof PacienteEspecial)
            .map(p -> (PacienteEspecial) p)
            .collect(Collectors.groupingBy(p -> p.getPlanoSaude() != null ? p.getPlanoSaude().getDescricao() : "SEM PLANO" ,
            
        //somando economia das consultas e internções
        Collectors.summingDouble(pe -> {
                    double economiaConsulta = 0.0;
                    double economiaInternacao = 0.0; 
                
                //custo base - custo calculado = economia
                economiaConsulta = pe.getHistoricoConsultas().stream()
                                         .mapToDouble(c -> c.getMedico().getCustoConsulta() - c.calcularCustoFinal())
                                         .sum();

                return economiaConsulta + economiaInternacao;
                })
            ));
    
        System.out.println("\n--- Economia por Plano de Saúde ---");
             economiaPorPlano.forEach((plano, economia) -> {
            // Filtra o "SEM PLANO"
            if (!plano.equals("SEM PLANO")) {

                 long qtdPessoas = this.pacientes.stream()
                                       .filter(p -> p instanceof PacienteEspecial && 
                                            ((PacienteEspecial) p).getPlanoSaude() != null &&
                                            ((PacienteEspecial) p).getPlanoSaude().getDescricao().equals(plano))
                                        .count();
                                        
                 System.out.printf("Plano: %s | Usuários: %d | Total Economizado: R$ %.2f\n",plano, qtdPessoas, economia);
            }
        });
    }

}
