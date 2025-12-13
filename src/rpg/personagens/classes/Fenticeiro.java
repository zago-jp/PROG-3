package rpg.personagens.classes;

import rpg.personagens.raca.Raca;

public class Fenticeiro extends Mago {
    
    // ===== Multiplicadores específicos do fenticeiro =====
    private static final double HP_MOD_FENTI  = 1.00;  // 0% a mais
    private static final double MP_MOD_FENTI  = 1.10;  // +10% MP
    private static final double ATK_MOD_FENTI = 1.20;  // +20% ataque
    private static final double DEF_MOD_FENTI = 0.90;  // -10% defesa
    private static final double SPD_MOD_FENTI = 1.20;  // +20% velocidade
    private static final double LCK_MOD_FENTI = 1.00;  // 0% sorte

    public Fenticeiro(String nome, Raca raca, int idade, String genero) {
        super(nome, raca, idade, genero);
        
        // Recalcular atributos com modificadores do FENTICEIRO
        recalcularAtributos();
    }

    private void recalcularAtributos() {
        // Recalcula usando os modificadores específicos do Fenticeiro
        this.maxHP = calcularAtributoFinal(baseHP, HP_MOD * HP_MOD_FENTI, raca.getHpMod());
        this.maxMP = calcularAtributoFinal(baseMP, MP_MOD * MP_MOD_FENTI, raca.getMpMod());
        this.atk   = calcularAtributoFinal(baseATK, ATK_MOD * ATK_MOD_FENTI, raca.getAtkMod());
        this.def   = calcularAtributoFinal(baseDEF, DEF_MOD * DEF_MOD_FENTI, raca.getDefMod());
        this.spd   = calcularAtributoFinal(baseSPD, SPD_MOD * SPD_MOD_FENTI, raca.getSpdMod());
        this.lck   = calcularAtributoFinal(baseLCK, LCK_MOD * LCK_MOD_FENTI, raca.getLckMod());

        // Garantir que HP/MP atuais não excedam os máximos
        this.hpAtual = Math.min(this.hpAtual, this.maxHP);
        this.mpAtual = Math.min(this.mpAtual, this.maxMP);
    }

    @Override
    public int atacar() {
        // Ataque básico do Fenticeiro - mais forte que o Mago normal
        int dano = this.atk;
        
        // Chance de ataque rápido devido à alta velocidade
        if (Math.random() * 100 < this.spd) {
            System.out.println(nome + " ataca com velocidade surpreendente!");
            dano += (int)(dano * 0.2); // +20% de dano
        }
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        // Defesa simples - Fenticeiro tem defesa reduzida nda pra ser feito
        
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
        // Fenticeiro é mais rápido - maior chance de fuga
        double chanceFuga = spd * 1.3; // +30% na fuga comparado ao Mago normal
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " usa sua agilidade para fugir rapidamente!");
        }
        
        return sucesso;
    }

    // Método específico do Fenticeiro - Ataque Rápido
    public int ataqueRapido() {
        if (mpAtual >= 15) {
            mpAtual -= 15;
            int dano = this.atk + (int)(this.atk * 0.3); // +30% de dano
            System.out.println(nome + " executa um ataque rápido!");
            return dano;
        }
        System.out.println("MP insuficiente para Ataque Rápido!");
        return atacar(); // Retorna ataque normal se não tiver MP
    }

    // Método específico do Fenticeiro - Esquiva
    public boolean esquivar() {
        // Chance de esquiva baseada na velocidade
        double chanceEsquiva = this.spd * 1.5;
        boolean sucesso = Math.random() * 100 < chanceEsquiva;
        
        if (sucesso) {
            System.out.println(nome + " esquiva habilmente do ataque!");
        }
        
        return sucesso;
    }

    // Método para mostrar status do Fenticeiro
    public void mostrarStatus() {
        System.out.println("=== STATUS FENTICEIRO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("Idade: " + idade + " | Gênero: " + genero);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Ataque: " + atk + " | Defesa: " + def + "%");
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
    }
}