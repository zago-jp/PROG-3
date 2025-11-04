// @zago-jp

// Subclasse Aluno herdando de Pessoa
class AlunoPrivate extends PessoaPrivate {
    private String matricula;

    // Construtor com super()
    AlunoPrivate(String nome, int idade, String matricula) {
        super(nome, idade); 
        this.matricula = matricula;
    }

    // Getter
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    //Setter
    public String getMatricula() {
        return matricula;
    }

    // Método atualizados usando getters e setters para os atributos private
    public void exibirInfo(){
        System.out.println("Nome: " + getNome() + ", Idade: " + getIdade() + ", Matrícula: " + matricula);
    }

    public static void main(String[] args) {
        // Antes:
        Aluno a1 = new Aluno("João Pedro", 18, "2025001");
        a1.exibirInfo();

        // Testando métodos set
        a1.setNome("Paulo");
        a1.setIdade(20);
        a1.setMatricula("2025002");

        // Exibindo informações atualizadas usando getters
        System.out.println("\nApós alterações:");
        System.out.println("Nome: " + a1.getNome());
        System.out.println("Idade: " + a1.getIdade());
        System.out.println("Matrícula: " + a1.getMatricula());
    }
}
