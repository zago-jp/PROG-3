public class ContaPoupanca extends ContaBancaria {

    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
}
