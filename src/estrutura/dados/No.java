package estrutura.dados;

public class No {
    private Baralho baralho;
    private No prox;

    public No(Baralho Baralho){
        this.baralho = Baralho;
        this.prox = null;
    }

    public No(Baralho dado, No prox) {
        this.baralho = dado;
        this.prox = prox;
    }

    public No getProx() {
        return this.prox;
    }

    public void setProx(No prox){
        this.prox = prox;
    }

    public Baralho getDado(){

        return this.baralho;
    }
}
