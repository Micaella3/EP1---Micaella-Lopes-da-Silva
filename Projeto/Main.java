import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);

    private static final DateTimeFormatter FORMATTER_DATAHORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATTER_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        
        //começando pelo repositorio
        SistemaHospitalar sistema = new SistemaHospitalar();
        RepositorioHospital repositorio = new RepositorioHospital(sistema);
        
        System.out.println("\n**********************************************");
        System.out.println("\n      SISTEMA DE GERENCIAMENTO HOSPITALAR     ");
        System.out.println("\n**********************************************");
     
        repositorio.carregarTudo(); 
        
        Relatorio relatorio = new Relatorio(
            sistema.getConsultas(), 
            sistema.getInternacoes(), 
            sistema.getMedicos(), 
            sistema.getPacientes()
        );

        //hoook p quando tiver desligamento salvar tudo
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n\n--- Salvando dados finais antes de encerrar... ---");
            repositorio.salvarTudo();
            scanner.close(); 
        }));
        
        exibirMenuPrincipal(sistema, relatorio); 
    }
//começando o menu 
    private static void exibirMenuPrincipal(SistemaHospitalar sistema, Relatorio relatorio) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n==============================================");
            System.out.println("\n||              MENU PRINCIPAL              ||");
            System.out.println("\n==============================================");
            System.out.println("\n 1. CADASTROS (Médico, Paciente, Plano, Quarto)");
            System.out.println("\n 2. AGENDAMENTOS E TRANSAÇÕES"); 
            System.out.println("\n 3. RELATÓRIOS E ESTATÍSTICAS");
            System.out.println("\n 0. SAIR E SALVAR DADOS");
            System.out.print("\n Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                
            switch (opcao) {
                case 1: 
                menuCadastros(sistema);
                    break;
                case 2: 
                menuTransacoes(sistema); 
                    break;
                case 3:
                 menuRelatorios(relatorio);
                     break;
                case 0:
                 System.out.println("\n --- Encerrando o sistema... ---");
                 break; 
                    default: System.err.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite o número 0, 1, 2 ou 3.");
                scanner.nextLine(); 
                opcao = -1;
            }
        }
    }
   
    private static void menuCadastros(SistemaHospitalar sistema) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n-----------------------------------------------");
            System.out.println("\n-              Menu de Cadastro               -");
            System.out.println("\n-----------------------------------------------");
            System.out.println("\n 1. Cadastrar Médico");
            System.out.println("\n 2. Cadastrar Paciente");
            System.out.println("\n 3. Cadastrar Plano de Saúde");
            System.out.println("\n 4. cadastrar Quarto");
            System.out.println("\n 0. Voltar ao Menu Principal");
            System.out.print("\n Escolha uma Opção: ");
            
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                
            switch (opcao) {
                case 1: 
                    cadastrarNovoMedico(sistema); 
                     break;
                case 2: 
                    cadastrarNovoPaciente(sistema);
                     break;
                case 3:
                    cadastrarNovoPlano(sistema);
                    break;
                case 4:
                    cadastrarNovoQuarto(sistema);
                    break;
                case 0:
                    break;
                default: 
                    System.err.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("Entrada inválida. Tente novamente (1, 2 ou 0): ");
                 scanner.nextLine();
                 opcao = -1;
            }
        }
    }

    private static void menuTransacoes(SistemaHospitalar sistema) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n-----------------------------------------------");
            System.out.println("\n-       Menu de Agendamento e Transações      -");
            System.out.println("\n-----------------------------------------------");
            System.out.println("\n 1. Agendar Nova Consulta");
            System.out.println("\n 2. Registrar Nova Internação");
            System.out.println("\n 3. Liberar Paciente da Internação");
            System.out.println("\n 4. Finalizar Consulta e Registrar Diagnóstico do Paciente");
            System.out.println("\n 0. Voltar ao Menu Principal");
            System.out.print("\n Escolha uma Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1:
                        agendarNovaConsulta(sistema); 
                         break;
                    case 2:
                         registrarNovaInternacao(sistema);
                        break;
                    case 3:
                         liberarPacienteInternacao(sistema);
                         break;
                    case 4:
                         finalizarConsultaMenu(sistema);
                         break;
                    case 0:
                         break;
                    default: System.err.println("\n Opção inválida.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("Entrada inválida. Tente novamente(1, 2, 3, 4 ou 0).");
                 scanner.nextLine();
                 opcao = -1;
            }
        }
    }
    private static void menuRelatorios(Relatorio relatorio) {
         int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n-----------------------------------------------");
            System.out.println("\n-              Menu de Relatórios             -");
            System.out.println("\n-----------------------------------------------");
            System.out.println("\n 1. Listar Pacientes e Histórico");
            System.out.println("\n 2. Listar Médicos e Produtividade");
            System.out.println("\n 3. Pacientes Internados (com tempo)");
            System.out.println("\n 4. Consultas Futuras e Passadas");
            System.out.println("\n 5. Estatísticas Gerais (Produtividade/Economia)");
            System.out.println("\n 0. Voltar ao Menu Principal");
            System.out.print("\n Escolha uma Opção: ");
             try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1: 
                    relatorio.listarPaciente();
                     break;
                    case 2:
                     relatorio.listarMedicos();
                      break;
                    case 3: 
                    relatorio.listarInternados();
                     break;
                    case 4: 
                        relatorio.listarConsultasFuturas();
                        relatorio.listarConsultasPassadas();
                        break;
                    case 5: 
                        relatorio.estatistica(); 
                        relatorio.calcularEconomiaPlano(); 
                        break;
                    case 0:
                     break;
                    default: System.err.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("Entrada inválida. Tente novamente (1, 2, 3, 4, 5 ou 0).");
                 scanner.nextLine();
                 opcao = -1;
            }
        }
    }

    //metodos p entrada d dados
     private static void cadastrarNovoPlano(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n|          Cadastro de Plano de Saúde         |");
        System.out.println("\n|_____________________________________________|");
    try {
        System.out.print("\n Descrição do Plano (Ex: Diamante, Ouro): ");
        String descricao = scanner.nextLine();
        
        System.out.print("\n Desconto Geral (Ex: 0.15 para 15%): ");
        double descontoGeral = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("\n Este Plano oferece internação GRÁTIS por 7 dias? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        boolean especialParaInternacao = resposta.equals("S"); 
        
        PlanoSaude novoPlano = new PlanoSaude(descontoGeral, descricao, especialParaInternacao);
        sistema.cadastrarPlanoSaude(novoPlano);
        System.out.println("Plano " + descricao + " cadastrado com sucesso!");
        
    } catch (InputMismatchException e) {
        System.err.println("Entrada numérica inválida. Cadastro falhou.");
        scanner.nextLine();
    }
}
    private static void cadastrarNovoMedico(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n|          Cadastro de Um Novo Médico         |");
        System.out.println("\n|_____________________________________________|");
            try {
            System.out.print("\n Nome: ");
            String nome = scanner.nextLine();
            System.out.print("\n Especialidade: ");
            String especialidade = scanner.nextLine();
            System.out.print("\n CRM: ");
            String crm = scanner.nextLine();
            System.out.print("\n Custo da Consulta (Ex: 250,00): R$ ");
            double custo = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("\n Definir Agenda (*Horários separados por vírgula: 10:00,14:30*): ");
            String agendaInput = scanner.nextLine();
            List<String> agenda = Arrays.stream(agendaInput.split(","))
                                                 .map(String::trim) //remove espaços em branco!!!
                                                .collect(Collectors.toList());

            Medico novoMedico = new Medico(nome, especialidade, crm, custo, agenda);
            sistema.cadastrarMedico(novoMedico);

            RepositorioHospital repositorio = new RepositorioHospital(sistema);
            repositorio.salvarTudo();

        } catch (InputMismatchException e) {
            System.err.println("Entrada numérica inválida (Custo). Cadastro falhou.");
            scanner.nextLine();
        }
       
    }
    private static void cadastrarNovoPaciente(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n|        Cadastro de Um Novo Paciente         |");
        System.out.println("\n|_____________________________________________|");
        try {
            System.out.print("\n Nome: ");
            String nome = scanner.nextLine();
            System.out.print("\n CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("\n Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("\n *** Tipo de Paciente: ***");
            System.out.println("\n 1. Paciente Comum");
            System.out.println("\n 2. Paciente Especial");
            System.out.print("\n Escolha o tipo (1 ou 2): ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            Paciente novoPaciente = null;

            if (tipo == 1) {
                System.out.print("Método de Pagamento: ");
                String metodoPagamento = scanner.nextLine();
                novoPaciente = new PacienteComum(metodoPagamento, nome, cpf, idade);
            } else if (tipo == 2) {
                System.out.print("Descrição do Plano de Saúde: ");
                String descPlano = scanner.nextLine();
                PlanoSaude plano = sistema.buscarPlanoSaudePorDescricao(descPlano); 
                
                if (plano == null) {
                    System.err.println("Plano de Saúde não encontrado. Cadastro como Especial cancelado.");
                    return;
                }
                
                System.out.print("Grau de Prioridade (1 a 5): ");
                int prioridade = scanner.nextInt();
                scanner.nextLine();

                novoPaciente = new PacienteEspecial(plano, nome, cpf, idade, prioridade);
            }

            if (novoPaciente != null) {
                sistema.cadastrarPaciente(novoPaciente);
            } else {
                System.err.println("Opção inválida. Cadastro cancelado.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Cadastro falhou.");
            scanner.nextLine();
        }
    }

    private static void cadastrarNovoQuarto(SistemaHospitalar sistema) {
        System.out.println("\n--- Cadastro de Novo Quarto ---");
        try {
            System.out.print("Número do Quarto: ");
            int numero = scanner.nextInt();
            scanner.nextLine();
            
            Quarto novoQuarto = new Quarto(numero, false); 
            sistema.cadastrarQuarto(novoQuarto);
            System.out.println("\n - Quarto " + numero + " cadastrado e disponível.");
            
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Cadastro falhou.");
            scanner.nextLine();
        }
    }
    
    private static void agendarNovaConsulta(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n|           Agendamento de Consulta           |");
        System.out.println("\n|_____________________________________________|");
        
        System.out.print("\n CRM do Médico: ");
        Medico medico = sistema.buscarMedicoPorCrm(scanner.nextLine());
        if (medico == null) { System.err.println("\n Médico não encontrado."); return; }

        System.out.print("\n CPF do Paciente: ");
        Paciente paciente = sistema.buscarPacientePorCpf(scanner.nextLine());
        if (paciente == null) { System.err.println("\n Paciente não encontrado."); return; }

        System.out.print("Data e Hora (dd/MM/yyyy HH:mm, Ex: 25/12/2025 15:30): ");
        String dataHoraStr = scanner.nextLine().trim();
        
        try {
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, FORMATTER_DATAHORA);
            System.out.print("\n Sala da Consulta (Ex: Consultório 3): ");
            String sala = scanner.nextLine();
            Consulta novaConsulta = new Consulta(medico, paciente, dataHora, sala, "Agendada", "", "", "");

        if (sistema.agendarConsulta(novaConsulta)) {
     medico.adicionarConsultaRealizada(novaConsulta);
    paciente.adicionarConsulta(novaConsulta);

    System.out.println("\n Consulta agendada e registrada com sucesso!");
}
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Formato de data e hora inválido. Use dd/MM/yyyy HH:mm.");
        } catch (Exception e) {
            System.err.println("Erro ao agendar a consulta: " + e.getMessage());
        }
    }
    private static void registrarNovaInternacao(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n|          Registro de Nova Internação        |");
        System.out.println("\n|_____________________________________________|");

        try {
            System.out.print("\n CRM do Médico Responsável: ");
            Medico medico = sistema.buscarMedicoPorCrm(scanner.nextLine());
            if (medico == null) { System.err.println("\n Médico não encontrado."); return; }

            System.out.print("\n CPF do Paciente: ");
            Paciente paciente = sistema.buscarPacientePorCpf(scanner.nextLine());
            if (paciente == null) { System.err.println("\n Paciente não encontrado."); return; }

            System.out.print("\n Número do Quarto: ");
            int numQuarto = scanner.nextInt();
            scanner.nextLine();
            Quarto quarto = sistema.buscarQuartoPorNumero(numQuarto);
            if (quarto == null) { System.err.println("\n Quarto não encontrado/inexistente."); return; }

            System.out.print("\n Custo diário da Internação (Ex: 100.00): R$ ");
            double custoDiario = scanner.nextDouble();
            scanner.nextLine(); 

            LocalDate dataEntrada = LocalDate.now();
            
            Internacoes novaInternacao = new Internacoes(medico, paciente, quarto, true, dataEntrada, null,custoDiario);

            if (sistema.registrarInternacoes(novaInternacao)) {
                System.out.println("\n Internação registrada com sucesso!");
            } else {
                System.err.println("\n Falha ao registrar a internação.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada numérica inválida. Registro falhou.");
            scanner.nextLine();
        }
    }
    
    //met q permite encerrar uma internação ativa
    private static void liberarPacienteInternacao(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n|            Liberação do Paciente:           |");
        System.out.println("\n|_____________________________________________|");
        System.out.print("\n CPF do Paciente a ser liberado: ");
        String cpf = scanner.nextLine();
        
        Paciente paciente = sistema.buscarPacientePorCpf(cpf);
        if (paciente == null) {
            System.err.println("\n Paciente não encontrado.");
            return;
        }
    //buscando internação ativa
        Internacoes internacaoAtiva = sistema.getInternacoes().stream()
            .filter(i -> i.getPaciente().getCpf().equals(cpf) && i.isInternado())
            .findFirst()
            .orElse(null);

        if (internacaoAtiva == null) {
            System.err.println("Não há internação ativa para o CPF " + cpf + ".");
            return;
        }
        
        if (sistema.liberarInternacao(internacaoAtiva)) {
            System.out.println("\n --- Paciente liberado com sucesso. Quarto desocupado. ---");
        } else {
            System.err.println("\n --- Erro ao liberar internação. Ela pode já ter sido finalizada. ---");
        }
    }

    //met auxiliar p registro d consul.
    private static void finalizarConsultaMenu(SistemaHospitalar sistema) {
        System.out.println("\n_______________________________________________");
        System.out.println("\n| Finalizar Consulta e Registrar Diagnóstico  |");
        System.out.println("\n|_____________________________________________|");
    
    System.out.print("\n CPF do Paciente: ");
    String cpf = scanner.nextLine();
    
    System.out.print("Data e Hora da Consulta (dd/MM/yyyy HH:mm): ");
    String dataHoraStr = scanner.nextLine().trim();

    try {
       LocalDateTime dataHoraEntrada = LocalDateTime.parse(dataHoraStr, FORMATTER_DATAHORA);

       String chaveDataHoraUsuario = dataHoraEntrada.format(FORMATTER_DATAHORA);

    Consulta consulta = sistema.getConsultas().stream()
        .filter(c -> c.getPaciente().getCpf().equals(cpf) && 
                     c.getDataHora().format(FORMATTER_DATAHORA).equals(chaveDataHoraUsuario) && 
                     c.getStatus().equals("Agendada"))
        .findFirst()
        .orElse(null);

        if (consulta == null) {
            System.err.println("Nenhuma consulta agendada encontrada para este CPF e data/hora.");
            return;
        }
        System.out.println("\nConsulta encontrada para o Dr(a). " + consulta.getMedico().getNome());
        System.out.print("\n Digite o Diagnóstico: ");
        String diagnostico = scanner.nextLine();
        
        System.out.print("\n Digite a Prescrição (ou nenhuma): ");
        String prescricao = scanner.nextLine();
        
        System.out.print("\n Digite o Registro da Consulta (Ex: 001/2025): ");
        String registro = scanner.nextLine();
        
        //atualiz. o objeto
        consulta.registrarDiagnostico(diagnostico, prescricao, registro);
        sistema.finalizarConsulta(consulta); 

    } catch (java.time.format.DateTimeParseException e) {
        System.err.println("*Formato de data e hora inválido. Use dd/MM/yyyy HH:mm.");
    }
}
}