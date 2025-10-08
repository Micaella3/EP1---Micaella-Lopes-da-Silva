public class Quarto {
    
    //atributos
    private int numero;
    private boolean  ocupado;

    //construtor
    public Quarto(int numero, boolean ocupado){
        this.numero = numero;
        this.ocupado = ocupado;
    }

    //getters e setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    //método toCvs 
public String toCSV(){
    return getNumero() + ";" +
           isOcupado() + ";";
}
    //métodos
public boolean ocupar(){
    if (this.ocupado){
        return false;
    }
    this.ocupado = true;
    return true;
}

public void liberar(){
    this.ocupado = false;
}

    //toString
    @Override
    public String toString(){
        return "\n Informações do Quarto: " +
                "\n Número: " + getNumero() +
                "\n Disponível: " + isOcupado();
    }
}
