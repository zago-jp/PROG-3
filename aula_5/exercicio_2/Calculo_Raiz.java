import java.util.InputMismatchException;
import java.util.Scanner;
//import java.lang.Math;
//import java.lang.IllegalArgumentExeption;
//estes dois são do pacote padrão do java(lang)


public class Calculo_Raiz {
    public static int calcularRaiz(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Parece que esta tentando fazer coisas erradas com numeros negativos... 🫣");
        }
        int result= (int) Math.sqrt(numero); 
        return result;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try 
            {
                System.out.println("Entre com o numero: ");
                int num = scanner.nextInt();

                int resultado = calcularRaiz(num);
                System.out.printf("%nResultado da raiz de: %d = %d%n", num, resultado);
                break;
            }

            catch (IllegalArgumentException erro) {
                System.err.println("Erro: " + erro.getMessage());  // imprime a mensagem padrão feita dentro da função.
            } 

            catch (InputMismatchException erro) {
                System.err.println("Digite apenas numeros inteiros... 😒");
                scanner.nextLine(); // limpa buffer
            }
        }

        scanner.close();
    }
}



//  Implemente um programa que contenha: Um método calcularRaiz(int numero) que
//  lance uma exceção (IllegalArgumentException) caso o número seja negativo. A cláusula
//  throws na assinatura do método para indicar a possibilidade da exceção. No método
//  main, capture a exceção com try-catch e mostre uma mensagem adequada ao usuário
