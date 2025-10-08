
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Internacoes{

    //formatando data
    private static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //atributos
    private boolean internado;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private double custo;
    private Quarto quarto;
    private Paciente paciente;
    private Medico medico;
    
    //construtor
    public Internacoes(Medico medico, Paciente paciente, Quarto quarto, boolean internado, LocalDate dataEntrada, LocalDate dataSaida, double  custo){
        this.medico = medico;
        this.paciente = paciente;
        this.quarto = quarto;
        this.custo = custo;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.internado = internado;
    }

    //getters e setters
    public boolean isInternado() {
        return internado;
    }

    public void setInternado(boolean internado) {
        this.internado = internado;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double  getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    //método do toCSV
public String toCSV(){
    String dataEntradaFormatada = this.dataEntrada.format(FORMATTER);
    String dataSaidaFormatada = (this.dataSaida != null) ?
    this.dataSaida.format(FORMATTER) : "NULO";

    return this.medico.getCrm() + ";" +
           this.paciente.getCpf()  + ";" +
           this.quarto.getNumero()  + ";" +
           isInternado()  + ";" +
           dataEntradaFormatada  + ";" +
           dataSaidaFormatada  + ";" +
           String.format("%.2f", getCusto());
}

    //met. de cancelar internação
public boolean cancelaInternacao(){
    if(!this.internado){
        return false;
    }

    //grava data e status
    this.setDataSaida(LocalDate.now());
    this.setInternado(false);

    //p liberar o quarto
    this.liberarQuarto();
    return true;
}

    //met. p calcular o custo da internacao
public double calcularCusto(int diasInternacao){
    double custoInicial = this.custo * diasInternacao;

    if (this.paciente instanceof PacienteEspecial){
        PacienteEspecial pe = (PacienteEspecial) this.paciente;
        return pe.calcularDescontoInternacao(custoInicial, diasInternacao);
    }
    return custoInicial;
}

    //met. p liberar o quarto
public void liberarQuarto(){
    if (this.quarto != null){
        this.quarto.setOcupado(false);
    }
}

    //toString
    @Override
    public String toString(){
        return "\n Informações sobre Internação: " +
                "\n Internado: " + isInternado() +
                "\n Data de Entrada: " + getDataEntrada() +
                "\n Data de Saída: " + getDataSaida() +
                "\n Quarto: " + getQuarto() +
                "\n Paciente internado: " + getPaciente() +
                "\n Médico responsável: " + getMedico();
    }

}