import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class LerArquivo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }

            br.close();
            isr.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        scanner.close();
    }
}
