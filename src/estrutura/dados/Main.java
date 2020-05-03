package estrutura.dados;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Blackjack ===");

        //Baralho do jogo
        Baralho baralho = new Baralho();

        //Mão do jogador
        Baralho cartasJogador = new Baralho();

        //Mão da i.a
        Baralho cartasIA = new Baralho();

        Lista listaBaralhos = new Lista();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a quantidade de partidas, 3 ou 5");
        int escolha = Validador.validarInt(scanner.nextLine());

        for (int i = 0; i < escolha; i++) {
            baralho.criarBaralho();
            baralho.embaralhar();

            listaBaralhos.enfileira(baralho);
        }



        int qtdJogos = 0;
        boolean[][] score = new boolean[3][escolha];
        int[][] scoreDetalhado = new int[2][escolha];

        while (qtdJogos < escolha ){
            baralho = listaBaralhos.pegarEl(qtdJogos).getDado();

            System.out.println("Distribuindo as cartas...");
            boolean roundFinalizado = false;

            //Cada jogador compra duas cartas
            cartasIA.comprarCarta(baralho);
            cartasIA.comprarCarta(baralho);

            cartasJogador.comprarCarta(baralho);
            cartasJogador.comprarCarta(baralho);

            while (true){
                System.out.println("\nA sua mão:" + cartasJogador.toString());
                System.out.println("Valor da mão: " + cartasJogador.calcularMao() + "\n");

                TimeUnit.SECONDS.sleep(1);
                imprimirBaralho(cartasIA);

                System.out.println("O que deseja fazer, (1)comprar ou (2)parar ?");
                //Validador dps
                int escolhaUsuario = Validador.validarInt(scanner.nextLine());

                if(escolhaUsuario == 1){
                    cartasJogador.comprarCarta(baralho);

                    System.out.println("Você tem certeza ? (1)Sim (2)Voltar");

                    if(Validador.validarInt(scanner.nextLine()) == 1){
                        System.out.println("Você comprou um:" + cartasJogador.getCarta(cartasJogador.verificarTamanhoBaralho()-1).toString());

                        if(cartasJogador.calcularMao() > 21){
                            System.out.println("Você estourou: " + cartasJogador.calcularMao());
                            roundFinalizado = true;
                            break;
                        }
                    } else {
                      cartasJogador.removerCarta(cartasJogador.verificarTamanhoBaralho()-1);
                    }

                }

                if(escolhaUsuario == 2)
                    break;
            }
            TimeUnit.SECONDS.sleep(1);

            if((cartasIA.calcularMao() < 15) && !roundFinalizado) {
                cartasIA.comprarCarta(baralho);
                System.out.println("O banqueiro comprou: " + cartasIA.getCarta(cartasIA.verificarTamanhoBaralho()-1).toString());

                if(cartasIA.calcularMao() > 21) {
                    System.out.println("O banqueiro estourou: " + cartasIA.calcularMao());
                    roundFinalizado = true;
                }
            }

            //Talvez compre, talvez nao
            if(cartasIA.calcularMao() < 15 && condicao() && !roundFinalizado){
                cartasIA.comprarCarta(baralho);
                System.out.println("O banqueiro comprou: " + cartasIA.getCarta(cartasIA.verificarTamanhoBaralho()-1).toString());

                if(cartasIA.calcularMao() > 21) {
                    System.out.println("O banqueiro estourou: " + cartasIA.calcularMao());
                    roundFinalizado = true;
                }
            }

            scoreDetalhado[0][qtdJogos] = cartasJogador.calcularMao();
            scoreDetalhado[1][qtdJogos] = cartasIA.calcularMao();

            if(cartasJogador.calcularMao() == cartasIA.calcularMao()){
                System.out.println("O jogo EMPATOU");
                score[2][qtdJogos] = true;
            }
            else if((cartasJogador.calcularMao() > cartasIA.calcularMao()) && !roundFinalizado){
                System.out.println("O jogador GANHOU.");
                score[0][qtdJogos] = true;
                score[1][qtdJogos] = false;
            }else{
                System.out.println("O banqueiro GANHOU");
                score[1][qtdJogos] = true;
                score[0][qtdJogos] = false;
            }

            System.out.println("Pontos banqueiro: " + cartasIA.calcularMao());
            System.out.println("Pontos jogador: " + cartasJogador.calcularMao());
            qtdJogos++;

            //Colocando as cartas de volta ao baralho
            cartasJogador.devolverCartas();
            cartasIA.devolverCartas();


            if(qtdJogos == escolha){
                contarPlacar(score, escolha);
                System.out.println();

                TimeUnit.SECONDS.sleep(1);

                System.out.println("Pontuação Detalhada");
                for (int i = 0; i < qtdJogos; i++) {
                    System.out.println("Jogo " + (i+1));
                    System.out.println("Pontuação Jogador: "+ scoreDetalhado[0][i]);
                    System.out.println("Pontuação Banqueiro: " + scoreDetalhado[1][i] + "\n");

                }
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
            tmp += baralho.getCarta(i).toString() + ", ";
        }
            System.out.println("Mão do oponente: " + tmp + "e a outra está virada ");
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
        System.out.println("Vitorias do Jogador: " + contadorJ);
        System.out.println("Vitorias do Banqueiro: " + contadorIA);
        System.out.println("Empates: " + contadorEmpates);

    }
}
