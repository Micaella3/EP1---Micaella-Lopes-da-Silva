public class PlanoSaude {
    
    private double descontoGeral;
    private String descricao; 

    //construtor 
    public PlanoSaude(double  descontoGeral, String descricao){
        this.descontoGeral = descontoGeral;
        this.descricao = descricao;
    }

    //getter e setter
    public double getDescontoGeral() {
        return descontoGeral;
    }

    public void setDescontoGeral(double descontoGeral) {
        this.descontoGeral = descontoGeral;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //método toCsv
public String toCSV(){
    return getDescricao() +";"+
           String.format("%.2f", getDescontoGeral());
}

    //met. desconto por especialidade 
public double calcularDescontoEspecialidade(String especialidade) {
    if (especialidade.equalsIgnoreCase("Cardiologia")) {
        return 0.35; 
    } else if (especialidade.equalsIgnoreCase("Pediatria")) {
        return 0.10;
    } else if (especialidade.equalsIgnoreCase("Ortopedia")) {
        return 0.40; 
    } else if (especialidade.equalsIgnoreCase("Dermatologia")) {
        return 0.15; 
    }else if (especialidade.equalsIgnoreCase("Neurologia")) {
        return 0.05; 
    }else if (especialidade.equalsIgnoreCase("Ginecologia")) {
        return 0.50; 
    }else if (especialidade.equalsIgnoreCase("Oftalmologia")) {
        return 0.16; 
    }else if (especialidade.equalsIgnoreCase("Infectologia")) {
        return 0.25; 
    }else if (especialidade.equalsIgnoreCase("Otorrinolaringologia")) {
        return 0.30; 
    }else if (especialidade.equalsIgnoreCase("Geriatria")) {
        return 0.10; 
    } else {
        return this.descontoGeral; 
    }
}

    //toString 
    @Override
    public String toString(){
        return "\n Desconto aplicado: " + getDescontoGeral() +
               "\n Descrição do Desconto: " + getDescricao();
    }

}
