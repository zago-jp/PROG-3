//@zago-jp
//Codigo para armazenar nome em um ArrayLista tipo String

import java.util.ArrayList;
import java.util.Iterator;

public class AlunosNome {
    public static void main(String[] args) {
        // Criando a ArrayList de nomes de alunos
        ArrayList<String> alunos = new ArrayList<>();

        //Adicionando 5 nomes
        alunos.add("JP");
        alunos.add("ANA");
        alunos.add("Pedro");
        alunos.add("Linderson");
        alunos.add("Lucas");

        // Percorrendo a lista usando Iterator e imprimindo os nomes
        System.out.println("Lista de alunos:");
        Iterator<String> iterator = alunos.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //remove um nome
        alunos.remove("Linderson");

        // exibindo a lista atualizada
        System.out.println("\nLista de alunos após remover Pedro:");
        iterator = alunos.iterator(); // Criando novo iterator

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
