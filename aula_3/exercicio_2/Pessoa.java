// @zago-jp

// Classe base Pessoa
public class Pessoa {
    protected String nome;
    protected int idade;

    // Construtores
    Pessoa(String nome, int idade){
        this.nome = nome;      
        this.idade = idade;
    }

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

    public void exibirInfo(){
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }
}


