import java.util.ArrayList;
import java.util.Scanner;

public class ListaNumeros {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();

        System.out.println("Digite 10 numeros inteiros:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Numero " + (i + 1) + ": "); // (i + 1) indice do número atual
            numeros.add(scanner.nextInt()); // lê um inteiro
        }

        // Exibir todos os números
        System.out.println("\nNumeros da lista");
        for (Integer n : numeros) {
            System.out.print(n + " ");
        }
        System.out.println();

        // Calcular soma e média
        int soma = 0;
        for (Integer n : numeros) {
            soma += n;                  // incerementar o indice
        }
        double media = soma / 10.0;     // calcular a mediaa

        System.out.println("\nSoma total: " + soma);
        System.out.println("Media: " + media);

        // Remover números pares
        numeros.removeIf(n -> n % 2 == 0); // se "n / 2" não tiver resto remove, já que é par

        // Exibir lista atualizada
        System.out.println("\nLista apos remover os numeros pares:");
        for (Integer n : numeros) {
            System.out.print(n + " ");
        }

        scanner.close();
    }
}



// Crie um programa em Java que: Utilize um ArrayList<Integer> para armazenar 10
// números inteiros inseridos pelo usuário. Exiba todos os números. Calcule e mostre a
// soma total e a média dos números inseridos. Remova os números pares e exiba a lista
// atualizada.