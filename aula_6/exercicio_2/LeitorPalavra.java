import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LeitorPalavra {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Set<String> palavras = new HashSet<>();

        System.out.println("Digite palavras (digite 'fim' para encerrar):");

        while (true) {
            String entrada = scanner.next();

            // condição de parada, digitar "fim"
            if (entrada.equalsIgnoreCase("fim")) { 
                break;
            }

            // adiciona ao conjunto
            palavras.add(entrada);
        }

        // exibe todas as palavras únicas digitadas, so usar um for no conjunto
        System.out.println("\nPalavras únicas digitadas:");
        for (String p : palavras) {
            System.out.println(p);
        }

        // verifica se "Java" foi digitada, maiusculo
        if (palavras.contains("Java")) {
            System.out.println("\nA palavra 'Java' foi digitada!");
        } else {
            System.out.println("\nA palavra 'Java' NÃO foi digitada.");
        }

        scanner.close();
    }
}


// Crie um programa que: Leia diversas palavras do teclado (até o usuário digitar “fim”).
// Armazene essas palavras em um HashSet<String>. Exiba todas as palavras únicas
// digitadas (sem repetições). Verifique se a palavra “Java” foi digitada.
// Dica: use contains() e loops para percorrer o conjunto

