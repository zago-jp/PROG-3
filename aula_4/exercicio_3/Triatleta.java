public class Triatleta implements Corredor, Nadador, Ciclista {

    @Override
    public void correr() {
        System.out.println("O triatleta está correndo.");
    }

    @Override
    public void nadar() {
        System.out.println("O triatleta está nadando.");
    }

    @Override
    public void pedalar() {
        System.out.println("O triatleta está pedalando.");
    }
}

