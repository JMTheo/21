package estrutura.dados;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 21 ===");

        //Baralho do jogo
        Baralho baralho = new Baralho();
        baralho.criarBaralho();
        baralho.embaralhar();

        //Mão do jogador
        Baralho cartasJogador = new Baralho();

        //Mão da i.a
        Baralho cartasIA = new Baralho();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a quantidade de partidas, 3 ou 5");
        //Adicionar o validador dps
        int escolha = Integer.parseInt(scanner.nextLine());
        int qtdJogos = 0;
        boolean[][] score = new boolean[3][escolha];

        while (qtdJogos < escolha ){ 
            System.out.println("Distribuindo as cartas...");
            boolean roundFinalizado = false;
            System.out.println(baralho.calcularMao());
            //Cada jogador compra duas cartas
            cartasIA.comprarCarta(baralho);
            cartasIA.comprarCarta(baralho);

            cartasJogador.comprarCarta(baralho);
            cartasJogador.comprarCarta(baralho);

            while (true){
                System.out.println("A sua mão:" + cartasJogador.toString());
                System.out.println("Valor da mão: " + cartasJogador.calcularMao() + "\n");

                imprimirBaralho(cartasIA);

                if(roundFinalizado)
                    break;



                System.out.println("O que deseja fazer, (1)comprar ou (2)parar ?");
                //Validador dps
                int escolhaUsuario = Integer.parseInt(scanner.nextLine());

                if(escolhaUsuario == 1){
                    cartasJogador.comprarCarta(baralho);
                    System.out.println("Você comprou um:" + cartasJogador.getCarta(cartasJogador.verificarTamanhoBaralho()-1).toString());

                    if(cartasJogador.calcularMao() > 21){
                        System.out.println("SE FODEU: " + cartasJogador.calcularMao());
                        roundFinalizado = true;
                        break;
                    }
                }

                if(escolhaUsuario == 2)
                    break;
            }

            if((cartasIA.calcularMao() <= 10) && (cartasIA.calcularMao() <= 18)) {
                cartasIA.comprarCarta(baralho);
                System.out.println("A IA comprou: " + cartasIA.getCarta(cartasIA.verificarTamanhoBaralho()-1).toString());

                if(cartasIA.calcularMao() > 21) {
                    System.out.println("IA se fodeu: " + cartasIA.calcularMao());
                    roundFinalizado = true;
                }
            }

            //Talvez compre, talvez nao
            if(cartasIA.calcularMao() < 15 && condicao() && !roundFinalizado){
                cartasIA.comprarCarta(baralho);
                System.out.println("A IA comprou: " + cartasIA.getCarta(cartasIA.verificarTamanhoBaralho()-1).toString());

                if(cartasIA.calcularMao() > 21) {
                    System.out.println("IA se fodeu: " + cartasIA.calcularMao());
                    roundFinalizado = true;
                }
            }

            if(cartasJogador.calcularMao() == cartasIA.calcularMao())
                System.out.println("O jogo EMPATOU");
            else if((cartasJogador.calcularMao() > cartasIA.calcularMao()) && !roundFinalizado){
                System.out.println("O jogador ganhou.");
                score[0][qtdJogos] = true;
                score[1][qtdJogos] = false;
            }else{
                System.out.println("A IA GANHOU");
                score[1][qtdJogos] = true;
                score[0][qtdJogos] = false;
            }

            System.out.println("Cartas IA: " + cartasIA.calcularMao());
            System.out.println("Cartas Jogador: " + cartasJogador.calcularMao());
            qtdJogos++;

            System.out.println("\n");

            //Colocando as cartas de volta ao baralho
            cartasJogador.devolverCartas(baralho);
            cartasIA.devolverCartas(baralho);

            if(qtdJogos == escolha){
                contarPlacar(score, escolha);
            }
            else
                System.out.println("Round finalizado.");

            TimeUnit.SECONDS.sleep(1);

        }

        scanner.close();
    }



    public static boolean condicao(){
        Random random = new Random();
        return random.nextBoolean();
    }

    public static void imprimirBaralho(Baralho baralho){
        String tmp = "";
        for (int i = 1; i < baralho.verificarTamanhoBaralho(); i++){
            tmp += baralho.getCarta(i).toString() + ",   ";
        }
            System.out.println("Mão do oponente: " + tmp + " e a outro está virada ");
    }

    public static void contarPlacar(boolean[][] placar, int qtdJogos){
        int contadorJ = 0;
        int contadorIA = 0;
        int contadorEmpates = 0;

        System.out.println("Jogo Finalizado");

        for (int i = 0; i < qtdJogos; i++) {
            if (placar[0][i])
                contadorJ++;

            if (placar[1][i])
                contadorIA++;

            if (placar[2][i])
                contadorEmpates++;
        }
        System.out.println("=== PLACAR FINAL ===");
        System.out.println("Vitorias Jogador: " + contadorJ);
        System.out.println("Vitorias IA: " + contadorIA);
        System.out.println("Empates: " + contadorEmpates);

    }
}
