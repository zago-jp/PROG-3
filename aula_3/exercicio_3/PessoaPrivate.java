// @zago-jp

// Classe base Pessoa
public class PessoaPrivate {
    private String nome;
    private int idade;

    // Construtor
    PessoaPrivate(String nome, int idade){
        this.nome = nome;      
        this.idade = idade;
    }

    // Getters e Setters
    public void setNome(String nome) {  
        this.nome = nome;
    }

    public String getNome() {           
        return nome;
    }

    public void setIdade(int idade) { 
        this.idade = idade;
    }

    public int getIdade() {  
        return idade;
    }

    public void exibirInfo(){ // sem usar get e setters
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }
}
