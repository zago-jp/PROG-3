package rpg.testes;

import rpg.combate.Combate;
import rpg.personagens.Personagem;
import rpg.personagens.classes.*;
import rpg.personagens.raca.Raca;
import rpg.inimigos.ReiDemonio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteReiDemonio1v1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("   TESTE DO BOSS FINAL  -  REI DEMONIO  ");
        System.out.println("========================================\n");
        
        // Escolher a classe do desafiante
        System.out.println("Escolha sua classe para enfrentar o Rei Demônio:");
        System.out.println("1.  Guerreiro  - Tanque resistente");
        System.out.println("2.  Mago       - Poder arcano");
        System.out.println("3.  Arqueiro   - Precisão à distância");
        System.out.println("4.  Ladino     - Furtividade e esquiva");
        System.out.println("5.  Sacerdote  - Cura e proteção divina");
        System.out.println("6.  Druida     - Transformação animal");
        System.out.println("7.  Monge      - Artes marciais e ki");
        System.out.println("8.  Bardo      - Inspiração e encantamento");
        System.out.println("9.  Paladino   - Defesa sagrada");
        System.out.println("10. Bárbaro    - Fúria e força bruta");
        System.out.println("11. Fenticeiro - Velocidade mágica");
        System.out.println("12. Bruxo      - Poder puro (vidro canhão)");
        System.out.print("\nEscolha (1-12): ");
        
        int escolha = scanner.nextInt();
        Personagem heroi = criarHeroi(escolha);
        
        // Criar o Rei Demônio
        Personagem reiDemonio = new ReiDemonio();
        
        // Mostrar matchup
        mostrarMatchup(heroi, reiDemonio);
        
        // Confirmar início
        System.out.println("\n" + "=".repeat(50));
        System.out.print("Pressione Enter para começar o combate épico...");
        scanner.nextLine(); // Limpar buffer
        scanner.nextLine();
        
        // Criar lista com apenas o herói
        List<Personagem> aliados = new ArrayList<>();
        aliados.add(heroi);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("O COMBATE COMEÇA!");
        System.out.println("=".repeat(50));
        
        // Iniciar combate 1v1
        Combate combate = new Combate(aliados, reiDemonio);
        combate.iniciarCombate();
        
        scanner.close();
    }
    
    // Raça predefinidas(geradas por IA)
    private static Personagem criarHeroi(int escolha) {
        switch(escolha) {
            case 1:
                return new Guerreiro("Thor", Raca.HUMANO, 35, "Masculino");
            case 2:
                return new Mago("Merlin", Raca.HUMANO, 65, "Masculino");
            case 3:
                return new Arqueiro("Hawkeye", Raca.ELFO, 150, "Masculino");
            case 4:
                return new Ladino("Shadow", Raca.HUMANO, 28, "Masculino");
            case 5:
                return new Sacerdote("Cleric", Raca.HUMANO, 42, "Masculino");
            case 6:
                return new Druida("Dru", Raca.ELFO, 120, "Feminino");
            case 7:
                return new Monge("Lee", Raca.HUMANO, 50, "Masculino");
            case 8:
                return new Bardo("Bard", Raca.HUMANO, 30, "Masculino");
            case 9:
                return new Paladino("Arthur", Raca.HUMANO, 38, "Masculino");
            case 10:
                return new Barbaro("Conan", Raca.ANAO, 45, "Masculino");
            case 11:
                return new Fenticeiro("Fenti", Raca.ELFO, 85, "Masculino");
            case 12:
                return new Bruxo("Warlock", Raca.DEMONIO, 200, "Masculino");
            default:
                return new Guerreiro("Herói", Raca.HUMANO, 30, "Masculino");
        }
    }
    
    private static void mostrarMatchup(Personagem heroi, Personagem reiDemonio) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MATCHUP: " + heroi.getNome() + " vs " + reiDemonio.getNome());
        System.out.println("=".repeat(50));
        
        // Estatísticas do Herói
        System.out.println("\n[ SEU CAMPEÃO ]");
        System.out.println("Nome: " + heroi.getNome());
        System.out.println("Classe: " + heroi.getClass().getSimpleName());
        System.out.println("HP: " + heroi.getHpAtual() + "/" + heroi.getMaxHP());
        System.out.println("MP: " + heroi.getMpAtual() + "/" + heroi.getMaxMP());
        System.out.println("Ataque: " + heroi.getAtk());
        System.out.println("Defesa: " + heroi.getDef() + "%");
        System.out.println("Velocidade: " + heroi.getSpd());
        System.out.println("Sorte: " + heroi.getLck());
        
        // Estatísticas do Rei Demônio
        System.out.println("\n[ REI DEMÔNIO ]");
        System.out.println("HP: " + reiDemonio.getHpAtual() + "/" + reiDemonio.getMaxHP());
        System.out.println("MP: " + reiDemonio.getMpAtual() + "/" + reiDemonio.getMaxMP());
        System.out.println("Ataque: " + reiDemonio.getAtk());
        System.out.println("Defesa: " + reiDemonio.getDef() + "%");
        System.out.println("Velocidade: " + reiDemonio.getSpd());
        System.out.println("Sorte: " + reiDemonio.getLck());
        
        // Análise de chances de vitoria kk
        System.out.println("\n[ ANALISE de cnances ]");
        double proporcaoHP = (double) heroi.getMaxHP() / reiDemonio.getMaxHP() * 100;
        double proporcaoATK = (double) heroi.getAtk() / reiDemonio.getAtk() * 100;
        
        System.out.printf("Seu HP e %.1f%% do HP do Rei Demonio\n", proporcaoHP);
        System.out.printf("Seu ataque e %.1f%% do ataque do Rei Demonio\n", proporcaoATK);
    }
}
