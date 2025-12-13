import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;

public class GravarArquivo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            PrintStream ps = new PrintStream(new FileOutputStream("saida.txt"));

            System.out.println("Digite as linhas (digite FIM para encerrar):");

            while (true) {
                String linha = scanner.nextLine();

                if (linha.equals("FIM")) {
                    break;
                }

                ps.println(linha);
            }

            ps.close();
            System.out.println("Conteúdo gravado em saida.txt");

        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
        }

        scanner.close();
    }
}
