package estrutura.dados;

public class Lista {
    private No primeiro;
    private No ultimo;
    private int qtdBaralhos = 0;

    public Lista(){
        this.primeiro = this.ultimo = null;
    }

    public void enfileira(Baralho item ){
        if(vazia()){
            this.primeiro = this.ultimo = new No(item);
        }else{
            this.ultimo.setProx(new No(item));
            this.ultimo = this.ultimo.getProx();
        }
        this.qtdBaralhos++;
    }


    public void removeEl(int n){
        No anterior  = pegarEl(n - 1);
        No atual = anterior.getProx();
        No prox = atual.getProx();

        anterior.setProx(prox);
        this.qtdBaralhos--;

    }

    public  No pegarEl(int pos){
        No atual = this.primeiro;

        for (int i = 0; i < pos; i++) {
            atual = atual.getProx();
        }
        return atual;

    }

    public void removerTodosEl(){
        this.primeiro = null;
        this.ultimo = null;

        this.qtdBaralhos = 0;
    }

    public boolean vazia(){
        return this.primeiro == null;
    }

}
