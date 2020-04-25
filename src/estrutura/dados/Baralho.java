package estrutura.dados;

import java.util.ArrayList;
import java.util.Random;

public class Baralho {
    private ArrayList<Carta> cartas;

    public Baralho(){
        //Cria a estrtutra para armazenar o deck
        this.cartas = new ArrayList<Carta>();
    }

    public void criarBaralho(){
        //Primeiro loop para percorrer os naipes
        for(Naipe naipeCarta: Naipe.values()){
            //Segundo For é para percorrer os valores
            for(Valores valorCarta: Valores.values()){
                this.cartas.add(new Carta(naipeCarta, valorCarta));
            }
        }
    }

    public void removerCarta(int i){
        this.cartas.remove(i);
    }

    public Carta getCarta(int i){
        return this.cartas.get(i);
    }

    //Compra a primeira carta e a remove
    public void comprarCarta(Baralho baralho){
        this.cartas.add(baralho.getCarta(0));
        baralho.removerCarta(0);
    }

    public int verificarTamanhoBaralho(){
        return this.cartas.size();
    }

    //Printar baralho
    public String toString(){
        String listaCartas = "";
        for(Carta umaCarta : this.cartas){
            listaCartas += "\n" + umaCarta.toString();
        }
        return listaCartas;
    }

    // Calcular o valor das cartas
    public int calcularMao() {
        int valorTotal = 0;
        for (Carta umaCarta : this.cartas) {
            switch (umaCarta.getValue()) {
                case DOIS:
                    valorTotal += 2;
                    break;
                case TRES:
                    valorTotal += 3;
                    break;
                case QUATRO:
                    valorTotal += 4;
                    break;
                case CINCO:
                    valorTotal += 5;
                    break;
                case SEIS:
                    valorTotal += 6;
                    break;
                case SETE:
                    valorTotal += 7;
                    break;
                case OITO:
                    valorTotal += 8;
                    break;
                case NOVE:
                    valorTotal += 9;
                    break;
                case DEZ:
                case VALETE:
                case RAINHA:
                case REI:
                    valorTotal += 10;
                    break;
                case AS:
                    valorTotal += 1;
                    break;
            }
        }
        return valorTotal;
    }

    public void embaralhar(){
        ArrayList<Carta> tmpDeck = new ArrayList<Carta>();
        Random random = new Random();
        int indexAleatorio = 0;
        int tamanhoOriginal = this.cartas.size();
        for(int i = 0; i< tamanhoOriginal;i++){
            //Pegando um numero aleatorio
            indexAleatorio = random.nextInt((this.cartas.size() - 1) + 1);
            tmpDeck.add(this.cartas.get(indexAleatorio));
            //removevendo a carta já adicionada para não ter duplicação
            this.cartas.remove(indexAleatorio);
        }
        //Substituindo o deck antigo pelo embaralhado
        this.cartas = tmpDeck;
    }



    public void devolverCartas(Baralho destino){
        int tamanhoDoBaralho = this.cartas.size();
        //Colocando de volta
        for(int i = 0; i < tamanhoDoBaralho; i++){
            destino.adicionarCarta(this.getCarta(i));
        }
        //Esvaziando o deck
        for(int i = 0; i < tamanhoDoBaralho; i++){
            this.removerCarta(0);
        }
    }

    public void adicionarCarta(Carta cartaNova){
        this.cartas.add(cartaNova);
    }

}
