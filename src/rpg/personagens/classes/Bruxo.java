package rpg.personagens.classes;

import rpg.personagens.raca.Raca;
import rpg.personagens.Personagem;

public class Bruxo extends Mago {
    
    // ===== Multiplicadores específicos do Bruxo =====
    private static final double HP_MOD_BRUXO  = 0.50;  // -50% HP (muito frágil)
    private static final double MP_MOD_BRUXO  = 1.30;  // +30% MP
    private static final double ATK_MOD_BRUXO = 1.40;  // +40% ataque (muito forte)
    private static final double DEF_MOD_BRUXO = 0.60;  // -40% defesa (muito frágil)
    private static final double SPD_MOD_BRUXO = 0.90;  // -10% velocidade
    private static final double LCK_MOD_BRUXO = 0.80;  // -20% sorte

    public Bruxo(String nome, Raca raca, int idade, String genero) {
        super(nome, raca, idade, genero);
        
        // Recalcular atributos com modificadores do Bruxo
        recalcularAtributos();
    }

    private void recalcularAtributos() {
        // Recalcula usando os modificadores específicos do Bruxo
        this.maxHP = calcularAtributoFinal(baseHP, HP_MOD * HP_MOD_BRUXO, raca.getHpMod());
        this.maxMP = calcularAtributoFinal(baseMP, MP_MOD * MP_MOD_BRUXO, raca.getMpMod());
        this.atk   = calcularAtributoFinal(baseATK, ATK_MOD * ATK_MOD_BRUXO, raca.getAtkMod());
        this.def   = calcularAtributoFinal(baseDEF, DEF_MOD * DEF_MOD_BRUXO, raca.getDefMod());
        this.spd   = calcularAtributoFinal(baseSPD, SPD_MOD * SPD_MOD_BRUXO, raca.getSpdMod());
        this.lck   = calcularAtributoFinal(baseLCK, LCK_MOD * LCK_MOD_BRUXO, raca.getLckMod());

        // Garantir que HP/MP atuais não excedam os máximos
        this.hpAtual = Math.min(this.hpAtual, this.maxHP);
        this.mpAtual = Math.min(this.mpAtual, this.maxMP);
    }

    @Override
    public int atacar() {
        // Ataque básico do Bruxo - muito forte
        int dano = this.atk;
        
        // Bruxo tem dano puro alto, sem efeitos especiais
        System.out.println(nome + " lança um feitiço obscuro!");
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        // Defesa reduzida - Bruxo é muito frágil
        int danoFinal = danoRecebido - (danoRecebido * this.def / 100);
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
        // Bruxo é mais lento - menor chance de fuga
        double chanceFuga = spd * 0.8; // -20% na fuga comparado ao Mago normal 
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " consegue escapar com dificuldade!");
        } else {
            System.out.println(nome + " é muito lento para fugir!");
        }
        
        return sucesso;
    }

    // Método específico do Bruxo - Explosão Arcana
    public int explosaoArcana(int mpGasto) {
        if (mpAtual >= mpGasto) {
            mpAtual -= mpGasto;
            int dano = this.atk + (mpGasto * 3); // Dano muito alto baseado no MP gasto
            System.out.println(nome + " libera uma Explosão Arcana!");
            return dano;
        }
        System.out.println("MP insuficiente para Explosão Arcana!");
        return atacar();
    }

    // Método específico do Bruxo - Drenagem Vital
    public int drenagemVital(Personagem alvo) {
        if (mpAtual >= 25) {
            mpAtual -= 25;
            int dano = this.atk + 10;
            // Bruxo se cura com parte do dano causado
            int cura = dano / 3;
            this.hpAtual = Math.min(this.hpAtual + cura, this.maxHP);
            System.out.println(nome + " drena a vida do alvo! Dano: " + dano + ", Cura: " + cura);
            return dano;
        }
        System.out.println("MP insuficiente para Drenagem Vital!");
        return atacar();
    }

    // Método para mostrar status do Bruxo
    public void mostrarStatus() {
        System.out.println("=== STATUS BRUXO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("Idade: " + idade + " | Gênero: " + genero);
        System.out.println("HP: " + hpAtual + "/" + maxHP + " ⚠️ FRÁGIL");
        System.out.println("MP: " + mpAtual + "/" + maxMP + " ✨ ALTO");
        System.out.println("Ataque: " + atk + " 💀 DEVASTADOR | Defesa: " + def + "% ⚠️ BAIXA");
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
    }
}