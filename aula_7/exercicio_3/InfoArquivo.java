import java.io.File;
import java.util.Scanner;

public class InfoArquivo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o caminho do arquivo ou diretório: ");
        String caminho = scanner.nextLine();

        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            System.out.println("O caminho informado não existe.");
        } else {
            System.out.println("Caminho absoluto: " + arquivo.getAbsolutePath());
            System.out.println("Tamanho (bytes): " + arquivo.length());

            if (arquivo.isFile()) {
                System.out.println("É um arquivo.");
            } else if (arquivo.isDirectory()) {
                System.out.println("É um diretório.");

                // Só para os pro: listar arquivos do diretório
                File[] arquivos = arquivo.listFiles();
                if (arquivos != null) {
                    System.out.println("Conteúdo do diretório:");
                    for (File f : arquivos) {
                        System.out.println("- " + f.getName());
                    }
                }
            }
        }

        scanner.close();
    }
}
