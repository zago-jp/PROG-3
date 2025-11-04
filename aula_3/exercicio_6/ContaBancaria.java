// @zago-jp

public class ContaBancaria {
    private int numero;

    // Construtor
    public ContaBancaria(int numero) {
        this.numero = numero;
    }

    // Getter
    public int getNumero() {
        return numero;
    }

    // Sobrescrevendo o método toString()
    @Override
    public String toString() {
        return "ContaBancaria [número=" + numero + "]";
    }

    // Sobrescrevendo o método equals()
    @Override
    public boolean equals(Object o) {
        // Se for nulo é diferente
        if (o == null) {
            return false;
        }

        // se for de diferentes classe tambem é diferente
        if (o.getClass() != this.getClass()) {
            return false;
        }

        // Se os dois primeiros passarem verifica se o atributo é igual
        ContaBancaria outra = (ContaBancaria) o;
        if (outra.getNumero() != this.getNumero()) {
            return false;
        }

        // Se passou é True
        return true;
    }

    // Método principal para teste
    public static void main(String[] args) {
        ContaBancaria c1 = new ContaBancaria(41);
        ContaBancaria c2 = new ContaBancaria(41);


        // Testes
        System.out.println("c1.equals(c2): " + c1.equals(c2));   // true (mesmo número)
        System.out.println("c1.toString(): " + c1);             // usa o toString() modificado
        System.out.println(c1 + "é igual a " + c2 + "? " + c1.equals(c2));             // usa os dois
    }
}
