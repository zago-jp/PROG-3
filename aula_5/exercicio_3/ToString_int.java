import java.util.Scanner;

public class ToString_int {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Entre com o numero: ");   
            String numString = scanner.nextLine();

            int numero = Integer.parseInt(numString);
            System.out.println("Número convertido: " + numero);
        }

        catch (NumberFormatException erro) {
            System.err.println("Entrada inválida! Por favor, digite apenas números inteiros.");
        }

        finally {
            System.out.println("Encerrando programa...");
        }

        scanner.close();
    }
}



//    Crie um programa que: Solicite ao usuário um número inteiro e receba-o como uma
//    String e converta para int. Utilize try-catch para tratar NumberFormatException (ex.: se o usuário
//    digitar letras). Utilize um bloco finally para exibir uma mensagem do tipo: "Encerrando
//    programa...", garantindo que essa linha sempre seja executada, independentemente de erros