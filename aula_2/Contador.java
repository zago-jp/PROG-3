// @zago-jp
// Classe Contador demonstra o uso de membros estaticos em ava.

public class Contador {
    static int totalObjetos = 0;

    // Contrutor
    public Contador() {
        totalObjetos++; //para fazer a contagem
    }

    // Exibe o total de objetos criados até o momento
    public static void mostrarTotal() {
        System.out.println("Total de objetos criados: " + totalObjetos);
    }

    public static void main(String[] args) {

        // Criação de alguns objetos da classe Contador
        Contador c1 = new Contador();
        Contador c2 = new Contador();
        Contador c3 = new Contador();

        Contador.mostrarTotal(); // Exibe o total de objetos criados
    }
}
