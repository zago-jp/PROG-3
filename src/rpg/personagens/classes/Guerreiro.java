package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Guerreiro extends Personagem {

    // ===== Multiplicadores da classe Guerreiro =====
    protected static final double HP_MOD  = 1.50;
    protected static final double MP_MOD  = 0.50;
    protected static final double ATK_MOD = 1.30;
    protected static final double DEF_MOD = 1.40;
    protected static final double SPD_MOD = 0.80;
    protected static final double LCK_MOD = 0.90;

    // ===== Atributos específicos do Guerreiro =====
    protected int furia; // Acumula para habilidades especiais
    protected final int maxFuria = 100;
    protected boolean defesaElevada;

    public Guerreiro(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Guerreiro =====
        super(nome, raca, idade, genero,
            100,   // baseHP
            50,    // baseMP
            25,    // baseATK
            20,    // baseDEF (percentual)
            12,    // baseSPD
            15     // baseLCK
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
        this.furia = 0;
        this.defesaElevada = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO GUERREIRO =====

    // Golpe devastador que gasta furia
    public int golpePoderoso() {
        if (furia >= 30) {
            furia -= 30;
            int dano = this.atk + (furia / 2);
            System.out.println(nome + " desfere um Golpe Poderoso!");
            return dano;
        }
        System.out.println("Furia insuficiente para Golpe Poderoso!");
        return atacar();
    }

    // Aumenta a defesa temporariamente
    public boolean posturaDefensiva() {
        if (mpAtual >= 20) {
            mpAtual -= 20;
            defesaElevada = true;
            System.out.println(nome + " assume uma Postura Defensiva!");
            return true;
        }
        System.out.println("MP insuficiente para Postura Defensiva!");
        return false;
    }

    // Ataca ignorando parte da defesa do inimigo
    public int investida() {
        if (mpAtual >= 15) {
            mpAtual -= 15;
            int dano = (int)(this.atk * 1.5);
            System.out.println(nome + " realiza uma Investida!");
            return dano;
        }
        System.out.println("MP insuficiente para Investida!");
        return atacar();
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com chance de gerar furia
        int dano = this.atk;
        
        // Chance de crítico baseada na sorte
        if (Math.random() * 100 < lck) {
            System.out.println("Golpe Crítico! Furia aumenta.");
            dano += (int)(dano * 0.3);
            furia = Math.min(furia + 20, maxFuria);
        }
        
        // Gera furia ao atacar
        furia = Math.min(furia + 10, maxFuria);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Defesa elevada reduz dano adicional
        if (defesaElevada) {
            danoFinal = (int)(danoRecebido * 0.5); // 50% de redução
            System.out.println("Postura Defensiva reduz o dano!");
            defesaElevada = false; // Dura apenas um turno
        } else {
            // Defesa normal
            danoFinal = danoRecebido - (danoRecebido * this.def / 100);
        }
        
        // Garantir que danoFinal não seja negativo
        danoFinal = Math.max(danoFinal, 0);
        
        // Subtrair o dano do HP atual
        this.hpAtual -= danoFinal;
        
        // Garantir que HP não fique negativo
        this.hpAtual = Math.max(this.hpAtual, 0);
        
        // Gera furia ao receber dano
        furia = Math.min(furia + 5, maxFuria);
        
        return danoFinal;
    }

    @Override
    public boolean fugir() {
        // Guerreiro tem desvantagem na fuga
        double chanceFuga = spd * 0.7;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " recua taticamente!");
        } else {
            System.out.println(nome + " está muito pesado para fugir!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    public void descansar() {
        int hpRecuperado = (int)(maxHP * 0.2);
        hpAtual = Math.min(hpAtual + hpRecuperado, maxHP);
        System.out.println(nome + " descansa e recupera " + hpRecuperado + " HP!");
    }

    // ===== GETTERS E SETTERS =====
    public int getFuria() {
        return furia;
    }

    public boolean isDefesaElevada() {
        return defesaElevada;
    }

    public void setFuria(int furia) {
        this.furia = Math.min(furia, maxFuria);
    }

    // ===== STATUS DO GUERREIRO =====
    public void mostrarStatus() {
        System.out.println("=== STATUS GUERREIRO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Fúria: " + furia + "/" + maxFuria);
        System.out.println("Postura Defensiva: " + (defesaElevada ? "Ativa" : "Inativa"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("=======================");
    }
}
