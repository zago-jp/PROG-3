// @zago-jp
// Classe Carro demonstra o uso de atributos, construtor, encapsulamento e constante final.

public class Carro {
    // Atributos da classe (características de um carro)
    String marca;
    String modelo;
    final int anoCarro; // atributo final — o ano não pode ser alterado após definido

    // Construtor: inicializa os atributos ao criar o objeto
    Carro(String modelo, String marca, int anoCarro){
        this.marca = marca;         // 'this' referencia o atributo da classe
        this.modelo = modelo;
        this.anoCarro = anoCarro;   // 'final' → valor só pode ser definido aqui
    }

    // Métodos SET e GET permitem acessar e modificar atributos de forma segura

    public void setMarca(String marca) {   // altera a marca do carro
        this.marca = marca;
    }

    public String getMarca() {             // retorna a marca atual
        return marca;
    }

    public void setModelo(String modelo) { // altera o modelo do carro
        this.modelo = modelo;
    }

    public String getModelo() {            // retorna o modelo atual
        return modelo;
    }

    // Não existe setAnoCarro() pois o ano é final (não pode mudar)
    public int getAnoCarro(){
        return anoCarro;
    }

    // Método que exibe as informações completas do carro
    public void exibirInfo(){
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + ", Ano: " + anoCarro + "\n");
    }

    // Método principal para testar a classe
    public static void main(String[] args) {
        // Criação de dois objetos da classe Carro com valores diferentes
        Carro c1 = new Carro("Corolla", "Toyotha", 2020);
        Carro c2 = new Carro("Camaro", "Chrevolet", 2016);

        // Exibição das informações de cada carro
        c1.exibirInfo();
        c2.exibirInfo();
    }
}
