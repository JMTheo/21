package estrutura.dados;

import java.util.Scanner;

public class Validador {

    public static int validarInt(String valor) {
        Scanner scanner = new Scanner(System.in);
        int vlr;
        try {
            vlr = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido !");
            return validarInt(scanner.nextLine());
        }
        return vlr;
    }
}
