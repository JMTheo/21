package estrutura.dados;
import java.util.Random;

public class Carta{
    private Naipe naipe;
    private Valores valor;

    public Carta(Naipe naipe, Valores valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public String toString(){
        return  this.valor.toString() + " de " +  this.naipe.toString();
    }

    public Valores getValue(){
        return this.valor;
    }
}