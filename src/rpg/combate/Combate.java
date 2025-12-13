package rpg.combate;

import rpg.personagens.Personagem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combate {
    
    private List<Personagem> aliados;
    private Personagem inimigo;
    private Scanner scanner;
    private boolean combateAtivo;
    
    public Combate(List<Personagem> aliados, Personagem inimigo) {
        this.aliados = new ArrayList<>(aliados);
        this.inimigo = inimigo;
        this.scanner = new Scanner(System.in);
        this.combateAtivo = true;
    }
    
    public void iniciarCombate() {
        System.out.println("=== COMBATE INICIADO ===");
        System.out.println("Inimigo: " + inimigo.getNome());
        System.out.println("HP: " + inimigo.getHpAtual() + "/" + inimigo.getMaxHP());
        System.out.println("========================\n");
        
        while (combateAtivo && !combateAcabou()) {
            turnoAliados();
            if (combateAtivo && inimigo.estaVivo()) {
                turnoInimigo();
            }
            verificarStatusCombate();
        }
        
        finalizarCombate();
    }
    
    private void turnoAliados() {
        System.out.println("\n=== TURNO DOS ALIADOS ===");
        
        for (int i = 0; i < aliados.size(); i++) {
            Personagem aliado = aliados.get(i);
            
            if (!aliado.estaVivo()) {
                System.out.println(aliado.getNome() + " está incapacitado!");
                continue;
            }
            
            System.out.println("\n--- " + aliado.getNome() + " ---");
            System.out.println("HP: " + aliado.getHpAtual() + "/" + aliado.getMaxHP());
            System.out.println("MP: " + aliado.getMpAtual() + "/" + aliado.getMaxMP());
            
            mostrarOpcoesAcao(aliado, i);
        }
    }
    
    private void mostrarOpcoesAcao(Personagem aliado, int index) {
        System.out.println("\nEscolha ação:");
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Fugir");
        System.out.println("4. Ver Status do Inimigo");
        
        System.out.print("Escolha: ");
        int escolha = scanner.nextInt();
        
        executarAcao(aliado, escolha, index);
    }
    
    private void executarAcao(Personagem aliado, int escolha, int index) {
        switch (escolha) {
            case 1: // Atacar
                int dano = aliado.atacar();
                int danoRecebido = inimigo.defender(dano);
                System.out.println(aliado.getNome() + " ataca e causa " + danoRecebido + " de dano!");
                break;
                
            case 2: // Defender
                aliado.defender(0); // Apenas prepara defesa
                System.out.println(aliado.getNome() + " assume postura defensiva!");
                break;
                
            case 3: // Fugir
                boolean fugiu = aliado.fugir();
                if (fugiu) {
                    System.out.println(aliado.getNome() + " fugiu do combate!");
                    aliados.remove(index);
                } else {
                    System.out.println(aliado.getNome() + " falhou em fugir!");
                }
                break;
                
            case 4: // Ver Status
                mostrarStatusInimigo();
                // Volta ao menu de ações
                mostrarOpcoesAcao(aliado, index);
                break;

            default:
                System.out.println("Ação inválida! Perdeu o turno.");
        }
    }
    
    private void turnoInimigo() {
        System.out.println("\n=== TURNO DO INIMIGO ===");
        
        // IA simples: ataca o aliado com menos HP
        Personagem alvo = encontrarAlvo();
        
        if (alvo != null) {
            int dano = inimigo.atacar();
            int danoRecebido = alvo.defender(dano);
            System.out.println(inimigo.getNome() + " ataca " + alvo.getNome() + " e causa " + danoRecebido + " de dano!");
            
            if (!alvo.estaVivo()) {
                System.out.println(alvo.getNome() + " foi derrotado!");
            }
        }
    }
    
    private Personagem encontrarAlvo() {
        Personagem alvo = null;
        int menorHP = Integer.MAX_VALUE;
        
        for (Personagem aliado : aliados) {
            if (aliado.estaVivo() && aliado.getHpAtual() < menorHP) {
                menorHP = aliado.getHpAtual();
                alvo = aliado;
            }
        }
        
        return alvo;
    }
    
    private void mostrarStatusInimigo() {
        System.out.println("\n=== STATUS DO INIMIGO ===");
        System.out.println("Nome: " + inimigo.getNome());
        System.out.println("HP: " + inimigo.getHpAtual() + "/" + inimigo.getMaxHP());
        System.out.println("==========================\n");
    }
    
    private boolean combateAcabou() {
        // Verifica se todos os aliados estão mortos
        boolean aliadosMortos = true;
        for (Personagem aliado : aliados) {
            if (aliado.estaVivo()) {
                aliadosMortos = false;
                break;
            }
        }
        
        return aliadosMortos || !inimigo.estaVivo();
    }
    
    private void verificarStatusCombate() {
        if (!inimigo.estaVivo()) {
            System.out.println("\n" + inimigo.getNome() + " foi derrotado!");
            combateAtivo = false;
        } else if (aliados.isEmpty()) {
            System.out.println("\nTodos os aliados fugiram!");
            combateAtivo = false;
        }
    }
    
    private void finalizarCombate() {
        if (!inimigo.estaVivo()) {
            System.out.println("\n=== VITÓRIA! ===");
            // Distribuir experiência
            for (Personagem aliado : aliados) {
                if (aliado.estaVivo()) {
                    System.out.println(aliado.getNome() + " sobreviveu ao combate!");
                }
            }
        } else {
            System.out.println("\n=== DERROTA ===");
            System.out.println("O inimigo prevaleceu...");
        }
        
        scanner.close();
    }
}