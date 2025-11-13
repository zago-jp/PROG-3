public class Main {
    public static void main(String[] args) {
        Usuario u1 = new Usuario("João", NivelAcesso.BASICO);
        Usuario u2 = new Usuario("Maria", NivelAcesso.INTERMEDIARIO);
        Usuario u3 = new Usuario("Carlos", NivelAcesso.ADMIN);

        u1.exibirMensagem();
        u2.exibirMensagem();
        u3.exibirMensagem();
    }
}
