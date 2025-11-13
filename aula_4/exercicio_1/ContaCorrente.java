public class ContaCorrente extends ContaBancaria {

    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public boolean sacar(double valor) {
        double valorTotal = valor + 1.0;
        if (saldo >= valorTotal) {
            saldo -= valorTotal;
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
}

