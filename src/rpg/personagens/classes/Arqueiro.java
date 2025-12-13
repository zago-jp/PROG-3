package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Arqueiro extends Personagem {

    // ===== Multiplicadores da classe Arqueiro =====
    protected static final double HP_MOD  = 0.90;
    protected static final double MP_MOD  = 0.80;
    protected static final double ATK_MOD = 1.20;
    protected static final double DEF_MOD = 0.80;
    protected static final double SPD_MOD = 1.30;
    protected static final double LCK_MOD = 1.20;

    // ===== Atributos específicos do Arqueiro =====
    protected int precisao; // Aumenta chance de acerto crítico
    protected final int maxPrecisao = 100;
    protected boolean miraConcentrada;

    public Arqueiro(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Arqueiro =====
        super(nome, raca, idade, genero,
            80,     // baseHP
            70,     // baseMP
            30,    // baseATK
            12,    // baseDEF (percentual)
            25,    // baseSPD
            20     // baseLCK
        );

        // Cálculos dos atributos finais
        this.maxHP = calcularAtributoFinal(baseHP, HP_MOD, raca.getHpMod());
        this.maxMP = calcularAtributoFinal(baseMP, MP_MOD, raca.getMpMod());
        this.atk   = calcularAtributoFinal(baseATK, ATK_MOD, raca.getAtkMod());
        this.def   = calcularAtributoFinal(baseDEF, DEF_MOD, raca.getDefMod());
        this.spd   = calcularAtributoFinal(baseSPD, SPD_MOD, raca.getSpdMod());
        this.lck   = calcularAtributoFinal(baseLCK, LCK_MOD, raca.getLckMod());

        // Inicializar HP e MP
        this.hpAtual = this.maxHP;
        this.mpAtual = this.maxMP;
        
        // Inicializar atributos específicos
        this.precisao = 50;
        this.miraConcentrada = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO ARQUEIRO =====

    // Dispara uma flecha precisa com dano aumentado
    public int tiroPreciso() {
        if (mpAtual >= 25) {
            mpAtual -= 25;
            int dano = this.atk + (precisao / 2);
            System.out.println(nome + " dispara um Tiro Preciso!");
            precisao = Math.max(precisao - 10, 0);
            return dano;
        }
        System.out.println("MP insuficiente para Tiro Preciso!");
        return atacar();
    }

    // Aumenta a precisão temporariamente
    public boolean mirar() {
        if (mpAtual >= 15) {
            mpAtual -= 15;
            miraConcentrada = true;
            precisao = Math.min(precisao + 30, maxPrecisao);
            System.out.println(nome + " concentra sua mira!");
            return true;
        }
        System.out.println("MP insuficiente para Mirar!");
        return false;
    }

    // Dispara múltiplas flechas
    public int flechaMultipla() {
        if (mpAtual >= 35) {
            mpAtual -= 35;
            int dano = (int)(this.atk * 0.7) * 3; // 3 flechas com 70% do dano
            System.out.println(nome + " dispara Flechas Múltiplas!");
            return dano;
        }
        System.out.println("MP insuficiente para Flecha Múltipla!");
        return atacar();
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com bônus de precisão
        int dano = this.atk;
        
        // Chance de crítico aumentada pela precisão
        double chanceCritico = lck + (precisao / 2);
        if (Math.random() * 100 < chanceCritico) {
            System.out.println("Tiro Certeiro! Dano crítico.");
            dano += (int)(dano * 0.6);
        }
        
        // Aumenta precisão ao atacar
        if (miraConcentrada) {
            precisao = Math.min(precisao + 15, maxPrecisao);
            miraConcentrada = false;
        } else {
            precisao = Math.min(precisao + 5, maxPrecisao);
        }
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        // Arqueiro papel
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
        // Arqueiro tem vantagem na fuga
        double chanceFuga = spd * 1.4;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " usa sua agilidade para escapar!");
        } else {
            System.out.println(nome + " não consegue encontrar uma rota de fuga!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    public void prepararFlechas() {
        precisao = Math.min(precisao + 25, maxPrecisao);
        System.out.println(nome + " prepara suas flechas! Precisão aumentada.");
    }

    // ===== GETTERS E SETTERS =====
    public int getPrecisao() {
        return precisao;
    }

    public boolean isMiraConcentrada() {
        return miraConcentrada;
    }

    public void setPrecisao(int precisao) {
        this.precisao = Math.min(precisao, maxPrecisao);
    }

    // ===== STATUS DO ARQUEIRO =====
    public void mostrarStatus() {
        System.out.println("=== STATUS ARQUEIRO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Precisão: " + precisao + "/" + maxPrecisao);
        System.out.println("Mira Concentrada: " + (miraConcentrada ? "Ativa" : "Inativa"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("==========");
    }
}