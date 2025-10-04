public class PacienteEspecial extends Paciente {
    
    private PlanoSaude planoSaude;
    private int grauPrioridade;

    //construtor
    public PacienteEspecial(PlanoSaude planoSaude, String nome, String cpf, int idade,  int grauPrioridade) {
        super(nome, cpf, idade);
        this.planoSaude = planoSaude;
        this.grauPrioridade = grauPrioridade;
    }

    //getters e setters  
    public PlanoSaude getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaude planoSaude) {
        this.planoSaude = planoSaude;
    }

    public int getGrauPrioridade() {
        return grauPrioridade;
    }

    public void setGrauPrioridade(int grauPrioridade) {
        this.grauPrioridade = grauPrioridade;
    }

    //met. q tem o custo total base e a duração em dias
    public double calcularDescontoInternacao(double custoBase, int diasInternacao) { 
        
        //se o paciente templano msm
        if (this.planoSaude == null) {
            return custoBase;
        }

        if (this.planoSaude.getDescricao().toLowerCase().contains("especial") && 
            diasInternacao < 7) {
            
            return 0.0; 
        }
        
        //se o desconto nao aplicou, esse é o desconto geral
        double descontoPadrao = this.planoSaude.getDescontoGeral();
        
        return custoBase * (1.0 - descontoPadrao);
    }
     
    //desconto do plano
public double getDescontoPlanoAplicavel(String especialidade) {
        if (this.planoSaude == null) {
            return 0.0;
        }
        return this.planoSaude.calcularDescontoEspecialidade(especialidade);
    }
    

    //toString
    @Override
    public String toString(){
        return "\n --- Paciente Especial ---" +
                "\n Nome: " + getNome() +
                "\n CPF: " + getCpf() +
                "\n Idade: " + getIdade()+
                "\n Histórico de Consultas: " + getHistoricoConsultas()+
                "\n Histórico de Internações: " + getHistoricoInternacoes() +
                "\n Informações sobre o plano de saúde: " + getPlanoSaude()+
                "\n Grau de Prioridade: " + getGrauPrioridade();
    }
}
