// @zago-jp

public class Main {
    public static void main(String[] args) {
        // Array para colocar os objetos
        Animal[] animais = new Animal[2]; // Array de tamanhu 2
        animais[0] = new Cachorro();
        animais[1] = new Gato();

        // Percorrendo o array e chamando emitirSom() para cada um
        for (Animal a : animais) {
            a.emitirSom(); // polimorfismo aqui--
        }
    }
}

// javac Animal.java Cachorro.java Gato.java Main.java
// java Main
