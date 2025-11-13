public class Main {
    public static void main(String[] args) {
        ContaBancaria cc = new ContaCorrente(100);
        ContaBancaria cp = new ContaPoupanca(200);

        cc.sacar(50);
        cp.depositar(100);

        System.out.println("Saldo Conta Corrente: R$ " + cc.getSaldo());
        System.out.println("Saldo Conta Poupança: R$ " + cp.getSaldo());
    }
}

//Javac ContaBancaria.java ContaPoupanca.java ContaCorrente.java Main.java
//java Main