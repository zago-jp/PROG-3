package rpg.testes;

import rpg.personagens.Personagem;
import rpg.personagens.classes.*;
import rpg.personagens.raca.Raca;
import rpg.inimigos.Goblin;
import java.util.Scanner;

public class TesteClasseEspecifica {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== TESTE DE CLASSE ESPECÍFICA ===");
        System.out.println("Escolha qual classe testar:\n");
        
        // Menu de classes
        String[] classes = {
            "1. Guerreiro", "2. Mago", "3. Arqueiro", "4. Ladino",
            "5. Sacerdote", "6. Druida", "7. Monge", "8. Bardo",
            "9. Paladino", "10. Bárbaro", "11. Fenticeiro", "12. Bruxo"
        };
        
        for (String classe : classes) {
            System.out.println(classe);
        }
        
        System.out.print("\nDigite o número da classe (1-12): ");
        int escolha = scanner.nextInt();
        
        // Criar herói
        Personagem heroi = criarHeroi(escolha);
        
        // Mostrar status
        System.out.println("\n" + "=".repeat(50));
        System.out.println("HERÓI: " + heroi.getClass().getSimpleName());
        System.out.println("HP: " + heroi.getHpAtual() + "/" + heroi.getMaxHP());
        System.out.println("MP: " + heroi.getMpAtual() + "/" + heroi.getMaxMP());
        System.out.println("ATK: " + heroi.getAtk() + " | DEF: " + heroi.getDef() + "%");
        System.out.println("SPD: " + heroi.getSpd() + " | LCK: " + heroi.getLck());
        
        // Criar Goblin
        Personagem goblin = new Goblin();
        System.out.println("\nINIMIGO: Goblin");
        System.out.println("HP: " + goblin.getHpAtual() + "/" + goblin.getMaxHP() + "\nAtk: " + goblin.getAtk());
        
        // Testar ações
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TESTANDO AÇÕES BÁSICAS:");
        
        // 1. Ataque
        System.out.println("\n1. ATAQUE:");
        int dano = heroi.atacar();
        int danoRecebido = goblin.defender(dano);
        System.out.println("   " + heroi.getClass().getSimpleName() + " ataca!");
        System.out.println("   Dano causado: " + danoRecebido);
        System.out.println("   Goblin HP restante: " + goblin.getHpAtual());
        
        // 2. Defesa
        System.out.println("\n2. DEFESA:");
        int danoGoblin = goblin.atacar();
        int danoHeroi = heroi.defender(danoGoblin);
        System.out.println("   Dano recebido: " + danoHeroi);
        System.out.println("   Herói HP restante: " + heroi.getHpAtual());
        
        // 3. Fuga
        System.out.println("\n3. FUGA:");
        boolean fugiu = heroi.fugir();
        System.out.println("   Tentativa de fuga: " + (fugiu ? "✅ Sucesso" : "❌ Falhou"));
        
        // Resultado
        System.out.println("\n" + "=".repeat(50));
        System.out.println("RESULTADO FINAL:");
        System.out.println("Herói HP: " + heroi.getHpAtual());
        System.out.println("Goblin HP: " + goblin.getHpAtual());
        
        if (!heroi.estaVivo()) {
            System.out.println("STATUS: ❌ Herói foi derrotado!");
        } else if (!goblin.estaVivo()) {
            System.out.println("STATUS: ✅ Herói venceu!");
        } else {
            System.out.println("STATUS: ⚔️ Empate");
        }
        
        scanner.close();
    }
    
    private static Personagem criarHeroi(int escolha) {
        switch(escolha) {
            case 1: 
                return new Guerreiro("Teste", Raca.HUMANO, 30, "M");
            case 2: 
                return new Mago("Teste", Raca.HUMANO, 65, "M");
            case 3: 
                return new Arqueiro("Teste", Raca.ELFO, 120, "M");
            case 4: 
                return new Ladino("Teste", Raca.HUMANO, 28, "M");
            case 5: 
                return new Sacerdote("Teste", Raca.HUMANO, 42, "M");
            case 6: 
                return new Druida("Teste", Raca.ELFO, 120, "F");
            case 7: 
                return new Monge("Teste", Raca.HUMANO, 50, "M");
            case 8: 
                return new Bardo("Teste", Raca.HUMANO, 30, "M");
            case 9: 
                return new Paladino("Teste", Raca.HUMANO, 38, "M");
            case 10: 
                return new Barbaro("Teste", Raca.ANAO, 45, "M");
            case 11: 
                return new Fenticeiro("Teste", Raca.ELFO, 85, "M");
            case 12: 
                return new Bruxo("Teste", Raca.DEMONIO, 200, "M");

            default: 
                return new Guerreiro("Teste", Raca.HUMANO, 30, "M");
        }
    }
}
