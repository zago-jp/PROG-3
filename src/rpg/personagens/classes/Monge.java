package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Monge extends Personagem {

    // ===== Multiplicadores da classe Monge =====
    protected static final double HP_MOD  = 1.20;
    protected static final double MP_MOD  = 0.90;
    protected static final double ATK_MOD = 1.10;
    protected static final double DEF_MOD = 1.20;
    protected static final double SPD_MOD = 1.10;
    protected static final double LCK_MOD = 1.00;

    // ===== Atributos específicos do Monge =====
    protected int ki; // Energia espiritual para técnicas
    protected final int maxKi = 1000;
    protected boolean meditacaoAtiva;

    public Monge(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Monge =====
        super(nome, raca, idade, genero,
            110,   // baseHP
            80,    // baseMP
            22,    // baseATK
            18,    // baseDEF (percentual)
            20,    // baseSPD
            18     // baseLCK
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
        this.ki = 70;
        this.meditacaoAtiva = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO MONGE =====

    // Golpe rápido que consome ki
    public int golpeRapido() {
        if (ki >= 25) {
            ki -= 25;
            int dano = this.atk + (spd / 2);
            System.out.println(nome + " executa um Golpe Rápido!");
            return dano;
        }
        System.out.println("Ki insuficiente para Golpe Rápido!");
        return atacar();
    }

    // Entra em estado de meditação
    public boolean meditar() {
        if (mpAtual >= 20 && !meditacaoAtiva) {
            mpAtual -= 20;
            meditacaoAtiva = true;
            ki = Math.min(ki + 50, maxKi);
            System.out.println(nome + " entra em estado de meditação!");
            return true;
        }
        System.out.println("Não foi possível meditar!");
        return false;
    }

    // Defesa perfeita usando ki
    public int superAtaque() {
        if (ki >= 50 && mpAtual >= 50) {
            ki -= 50;
            mpAtual -= 50;
            int dano = this.atk * 2 + ki;
            System.out.println(nome + " executa um Ataque perfeito!");
            return dano;
        }
        System.out.println("Ki ou mana insuficiente para Ataque especial!");
        return atacar();
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com bônus de meditação
        int dano = this.atk;
        
        if (meditacaoAtiva) {
            dano += (int)(dano * 0.4);
            System.out.println("Meditação aumenta a precisão do golpe!");
            meditacaoAtiva = false;
        }
        System.out.println("Dano: " + dano);
        // Gera ki ao atacar
        ki = Math.min(ki + 10, maxKi);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Monge pode usar ki para reduzir dano
        if (ki >= 20) {
            ki -= 20;
            danoFinal = (int)(danoRecebido * 0.5); // 50% de redução
            System.out.println(nome + " usa ki para se defender!");
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
        
        return danoFinal;
    }

    @Override
    public boolean fugir() {
        // Monge tem boa chance de fuga devido ao treinamento
        double chanceFuga = (spd + ki / 2) / 1.5;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " escapa com agilidade sobre-humana!");
        } else {
            System.out.println(nome + " decide ficar e lutar!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    public void concentrar() {
        ki = maxKi;
        atk += 5;
        System.out.println(nome + " concentra seu ki e aumenta o poder de ataque!");
    }

    // ===== GETTERS E SETTERS =====
    public int getKi() {
        return ki;
    }

    public boolean isMeditacaoAtiva() {
        return meditacaoAtiva;
    }

    public void setKi(int ki) {
        this.ki = Math.min(ki, maxKi);
    }

    // ===== STATUS DO MONGE =====
    public void mostrarStatus() {
        System.out.println("=== STATUS MONGE ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Ki: " + ki + "/" + maxKi);
        System.out.println("Meditação: " + (meditacaoAtiva ? "Ativa" : "Inativa"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("====================");
    }
}
