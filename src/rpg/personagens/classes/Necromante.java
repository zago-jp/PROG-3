package rpg.personagens.classes;

import rpg.personagens.raca.Raca;

public class Necromante extends Mago {
    
    // ===== Multiplicadores específicos do Necromante =====
    private static final double HP_MOD_NECRO  = 1.10;  // +10% comparado ao Mago
    private static final double MP_MOD_NECRO  = 1.20;  // +20% MP
    private static final double ATK_MOD_NECRO = 1.15;  // +15% ataque
    private static final double DEF_MOD_NECRO = 0.85;  // -5% defesa
    private static final double SPD_MOD_NECRO = 0.95;  // -5% velocidade
    private static final double LCK_MOD_NECRO = 0.90;  // -10% sorte

    // ===== Atributos específicos do Necromante =====
    private int esqueletosConvocados;
    private final int maxEsqueletos = 3;

    public Necromante(String nome, Raca raca, int idade, String genero) {
        super(nome, raca, idade, genero);
        this.esqueletosConvocados = 0;
        
        // Recalcular atributos com modificadores do Necromante
        recalcularAtributos();
    }

    private void recalcularAtributos() {
        // Recalcula usando os modificadores específicos do Necromante
        this.maxHP = calcularAtributoFinal(baseHP, HP_MOD * HP_MOD_NECRO, raca.getHpMod());
        this.maxMP = calcularAtributoFinal(baseMP, MP_MOD * MP_MOD_NECRO, raca.getMpMod());
        this.atk   = calcularAtributoFinal(baseATK, ATK_MOD * ATK_MOD_NECRO, raca.getAtkMod());
        this.def   = calcularAtributoFinal(baseDEF, DEF_MOD * DEF_MOD_NECRO, raca.getDefMod());
        this.spd   = calcularAtributoFinal(baseSPD, SPD_MOD * SPD_MOD_NECRO, raca.getSpdMod());
        this.lck   = calcularAtributoFinal(baseLCK, LCK_MOD * LCK_MOD_NECRO, raca.getLckMod());

        // Garantir que HP/MP atuais não excedam os máximos
        this.hpAtual = Math.min(this.hpAtual, this.maxHP);
        this.mpAtual = Math.min(this.mpAtual, this.maxMP);
    }

    // ===== HABILIDADES ESPECIAIS DO NECROMANTE =====

    public boolean invocarEsqueleto() {
        if (esqueletosConvocados < maxEsqueletos && mpAtual >= 20) {
            esqueletosConvocados++;
            mpAtual -= 20;
            System.out.println(nome + " invocou um esqueleto! (" + esqueletosConvocados + "/" + maxEsqueletos + ")");
            return true;
        }
        System.out.println("Não foi possível invocar um esqueleto!");
        return false;
    }

    public int drenarVida(int mpGasto) {
        if (mpAtual >= mpGasto) {
            mpAtual -= mpGasto;
            int cura = mpGasto * 2; // Converte MP gasto em cura
            hpAtual = Math.min(hpAtual + cura, maxHP);
            System.out.println(nome + " drenou " + cura + " de vida!");
            return cura;
        }
        System.out.println("MP insuficiente para drenar vida!");
        return 0;
    }

    public int atacarComEsqueletos() {
        int danoBase = atacar();
        int danoExtra = esqueletosConvocados * 5; // Cada esqueleto adiciona 5 de dano
        int danoTotal = danoBase + danoExtra;
        
        System.out.println(nome + " ataca com " + esqueletosConvocados + " esqueletos!");
        return danoTotal;
    }

    @Override
    public int atacar() {
        // Ataque básico do Necromante, mais forte que o Magoos
        int dano = super.atacar();
        // Chance de efeito adicional
        if (Math.random() * 100 < lck) {
            System.out.println("Ataque necrótico causa dano adicional!");
            dano += (int)(dano * 0.3); // +30% de dano(0.3 do dano + danoPadrão)
        }
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        // Defesa com proteção dos esqueletos
        int danoFinal = danoRecebido;
        
        if (esqueletosConvocados > 0) {
            // Esqueletos absorvem parte do dano
            int danoAbsorvido = Math.min(danoRecebido / 2, esqueletosConvocados * 10);
            danoFinal = danoRecebido - danoAbsorvido;
            System.out.println("Esqueletos absorveram " + danoAbsorvido + " de dano!");
        }
        else {
            // Aplica defesa normal
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
        // Necromante tem chance reduzida de fuga
        double chanceFuga = (spd * 0.8 * lck) / 8; 
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso && esqueletosConvocados > 0) {
            System.out.println("Os esqueletos cobriram a retirada do Necromante!");
        }
        
        return sucesso;
    }

    // ===== GETTERS ESPECÍFICOS =====
    public int getEsqueletosConvocados() {
        return esqueletosConvocados;
    }

    public int getMaxEsqueletos() {
        return maxEsqueletos;
    }

    public void dissiparEsqueletos() {
        if (esqueletosConvocados > 0) {
            System.out.println(nome + " dissipou " + esqueletosConvocados + " esqueletos.");
            esqueletosConvocados = 0;
        }
    }

    // Método para mostrar status completo
    public void mostrarStatusNecromante() {
        System.out.println("=== STATUS NECROMANTE ===");
        System.out.println("Nome: " + nome);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Esqueletos: " + esqueletosConvocados + "/" + maxEsqueletos);
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
    }
}
