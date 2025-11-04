

class Aluno extends Pessoa {
    String matricula;

    // Construtor com super()
    Aluno(String nome, int idade, String matricula) {
        super(nome, idade); // chama o contrutor da classe pai
        this.matricula = matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    // Método exibir modificado
    public void exibirInfo(){
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Matrícula: " + matricula);
    }

    public static void main(String[] args) {
        // Instanciando um objeto da classe Aluno
        Aluno a1 = new Aluno("João Pedro", 18, "2025001");

        // Exibindo as informações do aluno
        a1.exibirInfo();
    }
}

// javac Pessoa.java Aluno.java
//java Aluno
