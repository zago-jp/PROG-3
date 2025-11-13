public class Usuario {
    private String nome;
    private NivelAcesso nivel;

    public Usuario(String nome, NivelAcesso nivel) {
        this.nome = nome;
        this.nivel = nivel;
    }

    public void exibirMensagem() {
        switch (nivel) {
            case BASICO:
                System.out.println(nome + " - Acesso básico: funções limitadas.");
                break;
            case INTERMEDIARIO:
                System.out.println(nome + " - Acesso intermediário: mais recursos disponíveis.");
                break;
            case ADMIN:
                System.out.println(nome + " - Acesso total de administrador!");
                break;
        }
    }
}

