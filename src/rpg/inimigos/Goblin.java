package rpg.inimigos;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Goblin extends Personagem {
    
    public Goblin() {
        super("Goblin", Raca.GOBLIN, 7, "Masculino",
              40,   // baseHP
              10,   // baseMP  
              12,   // baseATK
              8,    // baseDEF (10% muito para goblin)
              18,   // baseSPD
              6     // baseLCK
        );
        
        calcularAtributosInimigo();
    }
    
    private void calcularAtributosInimigo() {
        this.maxHP = (int)(baseHP * raca.getHpMod());
        this.maxMP = (int)(baseMP * raca.getMpMod());
        this.atk = (int)(baseATK * raca.getAtkMod());
        this.def = (int)(baseDEF * raca.getDefMod());
        this.spd = (int)(baseSPD * raca.getSpdMod());
        this.lck = (int)(baseLCK * raca.getLckMod());
        
        this.hpAtual = maxHP;
        this.mpAtual = maxMP;
    }
    
    @Override
    public int atacar() {
        // 20% chance de ataque sujo
        if (Math.random() < 0.2) {
            System.out.println("Goblin ataca com uma faca enferrujada! Critico");
            return (int)(atk * 1.3); //30%+
        }
        System.out.println("Goblin ataca desajeitadamente");
        return atk;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Goblin ágil tem chance de esquiva
        if (Math.random() < (spd / 100.0)) {
            danoFinal = (int)(danoRecebido * 0.3); // 70% de esquiva
            System.out.println("Goblin esquiva habilmente!");
        } else {
            // Defesa normal (baixa)
            danoFinal = danoRecebido - (danoRecebido * this.def / 100);
        }
        
        // Garantir que danoFinal não seja negativo
        danoFinal = Math.max(danoFinal, 0);
        
        // Subtrair o dano do HP atual
        this.hpAtual -= danoFinal;
        
        // Garantir que HP não fique negativo
        this.hpAtual = Math.max(this.hpAtual, 0);
        
        return danoFinal;
    }

    @Override
    public boolean fugir() {
        // Goblin é covarde, alta chance de fuga
        double chanceFuga = spd * 0.8; // 80% do valor de velocidade
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println("Goblin corre!");
        } else {
            System.out.println("Goblin tenta fugir mas tropeça no próprio pé...");
        }
        
        return sucesso;
    }

    // UTILS
    public int getExperiencia() {
        return 30; // XP moderado
    }
    
}