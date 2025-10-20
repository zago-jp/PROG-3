// @zago-jp
// Classe Carro demonstra o uso de atributos, construtor, encapsulamento e constante final.

public class Carro {
    String marca;
    String modelo;
    final int anoCarro; 

    // Construtor
    Carro(String modelo, String marca, int anoCarro){
        this.marca = marca;      
        this.modelo = modelo;
        this.anoCarro = anoCarro; 
    }



    public void setMarca(String marca) {  
        this.marca = marca;
    }

    public String getMarca() {           
        return marca;
    }

    public void setModelo(String modelo) { 
        this.modelo = modelo;
    }

    public String getModelo() {  
        return modelo;
    }

    public int getAnoCarro(){
        return anoCarro;
    }

    public void exibirInfo(){
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + ", Ano: " + anoCarro + "\n");
    }

    public static void main(String[] args) {
        // Criação de dois objetos da classe Carro com valores diferentes
        Carro c1 = new Carro("Corolla", "Toyotha", 2020);
        Carro c2 = new Carro("Camaro", "Chrevolet", 2016);

        // Exibição das informações de cada carro
        c1.exibirInfo();
        c2.exibirInfo();
    }
}
