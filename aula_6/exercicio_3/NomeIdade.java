import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NomeIdade {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> pessoas = new HashMap<>();

        // Cadastro de pelo menos 5 pessoas
        for (int i = 1; i <= 5; i++) {
            System.out.print("Digite o nome da pessoa " + i + ": ");
            String nome = scanner.nextLine();

            System.out.print("Digite a idade de " + nome + ": ");
            int idade = Integer.parseInt(scanner.nextLine());

            pessoas.put(nome, idade);
        }

        // Consulta de idade pelo nome
        System.out.print("\nDigite um nome para buscar a idade: ");
        String nomeBusca = scanner.nextLine();

        if (pessoas.containsKey(nomeBusca)) {
            System.out.println("Idade de " + nomeBusca + ": " + pessoas.get(nomeBusca));
        } else {
            System.out.println("Nome não encontrado.");
        }

        // Remoção de uma pessoa
        System.out.print("\nDigite o nome da pessoa a ser removida: ");
        String nomeRemover = scanner.nextLine();

        if (pessoas.containsKey(nomeRemover)) {
            pessoas.remove(nomeRemover);
            System.out.println("Pessoa removida com sucesso.");
        } else {
            System.out.println("Nome não encontrado para remoção.");
        }

        // Exibição do mapa atualizado
        System.out.println("\nMapa atualizado:");
        for (Map.Entry<String, Integer> entry : pessoas.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        scanner.close();
    }
}


// Implemente um programa que: Utilize um HashMap<String, Integer> para armazenar
// pares nome → idade. Permita cadastrar pelo menos 5 pessoas. Peça um nome ao
// usuário e exiba a idade correspondente, se existir. Remova uma pessoa pelo nome e
// mostre o mapa atualizado.
// Dica: use métodos put(), get(), remove() e containsKey().