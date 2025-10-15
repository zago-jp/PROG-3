/*
 * @zago-j.p  -------  Fibonacci da primeira aula.
 */

public class Fibonacci {

    // Funcao recursiva para calcular o n-esimo termo da sequencia
    public static int fibo(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }
    }

    public static void main(String[] args) {
        int quantidade = 30;

        System.out.println("Os " + quantidade + " primeiros termos da sequencia de Fibonacci:");

        for (int i = 1; i < quantidade + 1; i++) {
            System.out.print(fibo(i) + ", ");
        }

        System.out.println(); // pula linha no final
    }
}
