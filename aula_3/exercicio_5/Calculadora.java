// @zago-jp

public class Calculadora {

    // Construtor
    public Calculadora(){}

    // Soma de dois inteiros
    public void somar(int A, int B) {
        int resultado = A + B;
        System.out.println("Resultado da soma inteira de dois números: " + resultado + "\n");
    }

    // Soma de três inteiros
    public void somar(int A, int B, int C) {
        int resultado = A + B + C;
        System.out.println("Resultado da soma inteira de três números: " + resultado + "\n");
    }

    // Soma de dois doubles
    public void somar(double A, double B) {
        double resultado = A + B;
        System.out.println("Resultado da soma de dois doubles: " + resultado + "\n");
    }

    public static void main(String[] args) {
        // Criando um objeto da classe Calculadora para poder chmar
        Calculadora calc = new Calculadora();

        calc.somar(10, 5);            // dois inteiros
        calc.somar(3, 7, 2);      // três inteiros
        calc.somar(4.5, 2.3);      // dois doubles
    }
}

