// @zago-jp

public class Livro {
    String autor;
    String titulo;


    // Construtor
    Livro(String titulo, String autor){
        this.titulo = titulo;      
        this.autor = autor;
    }

    Livro(){
        titulo = "Desconhecido";
        autor = "Desconhecido";
    }

    public void setMarca(String autor) {  
        this.autor = autor;
    }

    public String getMarca() {           
        return autor;
    }

    public void setModelo(String titulo) { 
        this.titulo = titulo;
    }

    public String getModelo() {  
        return titulo;
    }

    
    public void exibirInfo(){
        System.out.println("Autor: " + autor + ", Titulo: " + titulo);
    }

    public static void main(String[] args) {
        // Criação de dois objetos da classe Carro com valores diferentes
        Livro c1 = new Livro("O livro1" ,"Carlor");
        Livro c2 = new Livro();

        // Exibição das informações de cada carro
        c1.exibirInfo();
        c2.exibirInfo();
    }
}

