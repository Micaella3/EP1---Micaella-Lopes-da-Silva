public class Internacoes{

    private boolean internado;
    private String dataEntrada;
    private String dataSaida;
    private int custo;
    private Quarto quarto;
    private Paciente paciente;
    private Medico medico;
    
    //construtor
    public Internacoes(Medico medico, Paciente paciente, Quarto quarto, boolean internado, String dataEntrada, String dataSaida, int custo){
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

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
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

    //metodo cancelar internacao

    //toString
    @Override
    public String toString(){
        return "\n Informações sobre Internação: " +
                "\n Internado: " + isInternado() +
                "\n Data de Entrada: " + getDataEntrada() +
                "\n Data de Saída: " + getDataSaida() +
                "\n Quarto: " + getQuarto() +
                "\n Paciente internado: " + getPaciente() +
                "\n Médico responsável: " + getPaciente();
    }

}