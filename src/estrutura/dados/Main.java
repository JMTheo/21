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

        while (qtdJogos <= escolha ){
            System.out.println("Distribuindo as cartas...");
            boolean roundFinalizado = false;
            //Cada jogador compra duas cartas
            cartasIA.comprarCarta(baralho);
            cartasIA.comprarCarta(baralho);

            cartasJogador.comprarCarta(baralho);
            cartasJogador.comprarCarta(baralho);

            while (true){
                System.out.println("A sua mão:" + cartasJogador.toString());
                System.out.println("Valor da mão: " + cartasJogador.calcularMao() + "\n");

                imprimirBaralho(cartasIA);

                if(cartasIA.calcularMao() <= 10 && cartasIA.calcularMao() <= 18 ) {
                    cartasIA.comprarCarta(baralho);
                    System.out.println("A IA comprou: " + cartasIA.getCarta(cartasIA.verificarTamanhoBaralho()-1).toString());

                    if(cartasIA.calcularMao() > 21) {
                        System.out.println("IA se fodeu: " + cartasIA.calcularMao());
                        roundFinalizado = true;
                    }
                }

                System.out.println("Ö que deseja fazer, (1)comprar ou (2)parar ?");
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

                //Talvez compre, talvez nao
                if(cartasIA.calcularMao() < 15 && cartasJogador.calcularMao() > 16 && condicao() && !roundFinalizado){
                    cartasIA.comprarCarta(baralho);
                    System.out.println("A IA comprou: " + cartasIA.getCarta(cartasIA.verificarTamanhoBaralho()-1).toString());

                    if(cartasIA.calcularMao() > 21) {
                        System.out.println("IA se fodeu: " + cartasIA.calcularMao());
                        roundFinalizado = true;
                    }
                }

                if(escolhaUsuario == 2)
                    break;
            }

            if(cartasJogador.calcularMao() == cartasIA.calcularMao())
                System.out.println("O jogo EMPATOU");
            else if((cartasJogador.calcularMao() > cartasIA.calcularMao()) && !roundFinalizado)
                System.out.println("O jogador ganhou.");
            else
                System.out.println("A IA GANHOU");

            System.out.println("Cartas IA: " + cartasIA.calcularMao());
            System.out.println("Cartas Jogador: " + cartasJogador.calcularMao());
            qtdJogos++;

            System.out.println("\n");

            //Colocando as cartas de volta ao baralho
            cartasJogador.devolverCartas(baralho);
            cartasIA.devolverCartas(baralho);

            if(qtdJogos == escolha)
                System.out.println("Jogo Finalizado");
            else
                System.out.println("Round finalizado.");

            TimeUnit.SECONDS.sleep(1);

        }

        scanner.close();
    }

    public static boolean condicao(){
        Random random = new Random();
//        return (random.nextInt(10) % 2 ) == 0;
        return random.nextBoolean();
    }

    public static void imprimirBaralho(Baralho baralho){
        String tmp = "";
        for (int i = 1; i < baralho.verificarTamanhoBaralho(); i++){
            tmp += baralho.getCarta(i).toString();
        }
            System.out.println("Mão do oponente: " + tmp + " e a outro está virada ");
    }
}
