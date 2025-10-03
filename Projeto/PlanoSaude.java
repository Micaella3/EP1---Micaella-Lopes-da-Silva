public class PlanoSaude {
    
    private double aplicarDesconto;
    private String descricao; 

    //construtor 
    public PlanoSaude(double  aplicarDesconto, String descricao){
        this.aplicarDesconto = aplicarDesconto;
        this.descricao = descricao;
    }

    //getter e setter
    public double getAplicarDesconto() {
        return aplicarDesconto;
    }

    public void setAplicarDesconto(double aplicarDesconto) {
        this.aplicarDesconto = aplicarDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //toString 
    @Override
    public String toString(){
        return "\n Desconto aplicado: " + getAplicarDesconto() +
               "\n Descrição do Desconto: " + getDescricao();
    }

}
