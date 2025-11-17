import java.util.InputMismatchException;
import java.util.Scanner;

public class div_exp {
    public static int divisao(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("Parece que esta tentando fazer coisas erradas com esse zero... 🫣");
        }
        return numerador / denominador;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try 
            {
                System.out.println("Entre com o numerador: ");
                int num = scanner.nextInt();

                System.out.println("Entre com o denominador: ");
                int den = scanner.nextInt();

                int resultado = divisao(num, den);
                System.out.printf("%nResultado: %d / %d = %d%n", num, den, resultado);
                break;
            }

            catch (ArithmeticException erro) {
                System.err.println("Erro: " + erro.getMessage());
            } 

            catch (InputMismatchException erro) {
                System.err.println("Digite apenas numeros inteiros... 😒");
                scanner.nextLine(); // limpa buffer
            }
        }

        scanner.close();
    }
}




//  Escreva um programa que leia dois números inteiros e divida o primeiro pelo segundo.
//  Use try-catch para tratar ArithmeticException (divisão por zero). Exiba uma mensagem
//  amigável caso ocorra erro.
