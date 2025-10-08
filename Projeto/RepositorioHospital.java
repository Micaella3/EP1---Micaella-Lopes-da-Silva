
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioHospital {
    
    //definindo o caminho onde os arquivos vao ser salvos 
    private static final String DATA_PATH = "data/";
    private static String DELIMITER = ";";

    private static final DateTimeFormatter FORMATO_DATAHORA = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATO_DATA = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    //referencia o sistema p puxar as listas
    private final SistemaHospitalar sistema; 

    public RepositorioHospital(SistemaHospitalar sistema){
        this.sistema= sistema;

        //verificando se a pasta data existe antes d enviar dado
        File dataDir = new File(DATA_PATH);

        //se nao existe: .mkdir cria 
        if (!dataDir.exists()){
            dataDir.mkdir();
        }
    }

    //métodos p salvar as listas d sistemaHosp
    public void salvarTudo(){
        salvarEntidades(sistema.getMedicos(), "medicos.csv");
        salvarEntidades(sistema.getPlanos(), "planos.csv");
        salvarEntidades(sistema.getPacientes(), "pacientes.csv");
        salvarEntidades(sistema.getQuartos(), "quartos.csv");
        salvarEntidades(sistema.getConsultas(), "consultas.csv");
        salvarEntidades(sistema.getInternacoes(), "internacoes.csv");

        System.out.println("Dados Salvos!");
    }

    //usando reflection 
    private <T> void salvarEntidades(List<T> lista, String nomeArquivo){
        File file = new File(DATA_PATH + nomeArquivo);
        //file file cria um objeto file q apresenta o caminho do arquivo
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))){
            //filewriter: fluxo que conecta o código ao arquivo no disco
            //printwriter: escreve texto e cria novas linhas 
            
        for (T objeto: lista) {
        if (objeto != null){
                    String linhaCSV = (String) objeto.getClass()
                                                    .getMethod("toCSV")
                                                    .invoke(objeto);
                    pw.println(linhaCSV);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar " + nomeArquivo + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    //método de carregamento
    public void carregarTudo(){
        carregarMedicos();
        carregarPlanos();
        carregarQuartos();
        carregarPacientes();
        carregarConsultas();
        carregarInternacoes();

        System.out.println("Dados carregados!");
    }

    //transformando em objetos agora
    private void carregarMedicos(){
        File file = new File(DATA_PATH + "medicos.csv");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            //variável p armazenar as linhas 
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(DELIMITER);
                if (dados.length < 5) continue;

                String nome = dados[0];
                String especialidade = dados[1];
                String crm = dados[2];

                double custoConsulta = Double.parseDouble(dados[3].replace(",", "."));

                List<String> agenda = new ArrayList<>();
                if (dados.length > 4 && !dados[4].isEmpty()){
                    agenda = Arrays.asList(dados[4].split(","));
                }

                Medico novoMedico = new Medico(nome, especialidade, crm, custoConsulta, agenda);
                sistema.getMedicos().add(novoMedico);
            }
        } catch(IOException | NumberFormatException e){
            System.err.println("Erro ao carregar médicos: " + e.getMessage());
        }
    }

    private void carregarPlanos() {
        File file = new File(DATA_PATH + "planos.csv");
        if (!file.exists()) return; 

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(DELIMITER);
                if (dados.length < 2) continue; 
                
                String descricao = dados[0];
                double descontoGeral = Double.parseDouble(dados[1].replace(",", ".")); 
                
                PlanoSaude novoPlano = new PlanoSaude(descontoGeral, descricao);
                sistema.getPlanos().add(novoPlano); 
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar planos de saúde: " + e.getMessage());
        }
    }
    
    private void carregarQuartos() {
        File file = new File(DATA_PATH + "quartos.csv");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(DELIMITER);
                if (dados.length < 2) continue;
                
                int numero = Integer.parseInt(dados[0]);
                boolean ocupado = Boolean.parseBoolean(dados[1]); 
                
                Quarto novoQuarto = new Quarto(numero, ocupado);
                sistema.getQuartos().add(novoQuarto);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar quartos: " + e.getMessage());
        }
    }

    private void carregarPacientes() {
         File file = new File(DATA_PATH + "pacientes.csv");
         if (!file.exists()) return;
        
             try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
             while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(DELIMITER);
             if (dados.length < 4) continue;
                
                String tipo = dados[0]; 
                String nome = dados[1];
                String cpf = dados[2];
                int idade = Integer.parseInt(dados[3]);
                
                Paciente novoPaciente = null;

                if (tipo.equals("COMUM") && dados.length >= 5) {
                   
                    String metodoPagamento = dados[4];
                    novoPaciente = new PacienteComum(metodoPagamento, nome, cpf, idade);
                    
                } else if (tipo.equals("ESPECIAL") && dados.length >= 6) {
                    
                    String planoDescricaoChave = dados[4];
                    int grauPrioridade = Integer.parseInt(dados[5]);
                    
                    PlanoSaude planoAssociado = null;
                    
                    //busca o objeto PlanoSaude na lista carregada (ligação da chave)
                    if (!planoDescricaoChave.equals("NULO")) {
                        planoAssociado = sistema.getPlanos().stream()
                            .filter(p -> p.getDescricao().equals(planoDescricaoChave))
                            .findFirst()
                            .orElse(null);
                             // Retorna null se não encontrar
                    }
                    novoPaciente = new PacienteEspecial(planoAssociado, nome, cpf, idade, grauPrioridade);
                }
                
                if (novoPaciente != null) 
                    sistema.getPacientes().add(novoPaciente);
                }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
        }
    }

        private  void carregarConsultas(){
            File file = new File(DATA_PATH + "consultas.csv");
            if (!file.exists()) return;

            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String linha;
                while ((linha = br.readLine()) != null){
                    String[] dados = linha.split(DELIMITER);
                    if (dados.length<8) continue;

            //buscando médico e paciente
            Medico medico = sistema.buscarMedicoPorCrm(dados[0]);
            Paciente paciente = sistema.buscarPacientePorCpf(dados[1]);

            if (medico == null || paciente == null) continue;

            LocalDateTime dataHora = LocalDateTime.parse(dados[2], FORMATO_DATAHORA);

            //recriando o objeto consulta
            Consulta novConsulta = new Consulta(medico, paciente, dataHora, dados[3], dados[4], dados[5], dados[6], dados[7]);

            sistema.getConsultas().add(novConsulta);
                }
            } catch (IOException | NumberFormatException e) {
        System.err.println("Erro ao carregar consultas: " + e.getMessage());
            }
        }


     private void carregarInternacoes() {
        File file = new File(DATA_PATH + "internacoes.csv");
        if (!file.exists()) return;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(DELIMITER);
            if (dados.length < 7) continue; 

            //buscando os objetos referenciados 
            Medico medico = sistema.buscarMedicoPorCrm(dados[0]);
            Paciente paciente = sistema.buscarPacientePorCpf(dados[1]);
            Quarto quarto = sistema.buscarQuartoPorNumero(Integer.parseInt(dados[2]));

            if (medico == null || paciente == null || quarto == null) continue;
            
            boolean internado = Boolean.parseBoolean(dados[3]); 
            LocalDate dataEntrada = LocalDate.parse(dados[4], FORMATO_DATA); 
            
            // data de saida
            LocalDate dataSaida = null;
            if (!dados[5].equals("NULO")) {
                dataSaida = LocalDate.parse(dados[5], FORMATO_DATA); 
            }
            
            double custo = Double.parseDouble(dados[6].replace(",", "."));

            // recriando objetos
            Internacoes novaInternacao = new Internacoes(medico, paciente, quarto, internado, dataEntrada, dataSaida, custo);
            
            sistema.getInternacoes().add(novaInternacao);
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Erro ao carregar internações: " + e.getMessage());
        }
    }

}