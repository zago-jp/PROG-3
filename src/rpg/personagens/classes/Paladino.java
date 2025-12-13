package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Paladino extends Personagem {

    // ===== Multiplicadores da classe Paladino =====
    protected static final double HP_MOD  = 1.30;
    protected static final double MP_MOD  = 1.20;
    protected static final double ATK_MOD = 1.20;
    protected static final double DEF_MOD = 1.30;
    protected static final double SPD_MOD = 0.95;
    protected static final double LCK_MOD = 1.15;

    // ===== Atributos específicos do Paladino =====
    protected int devocao; // Poder sagrado para julgamentos
    protected final int maxDevocao = 100;
    protected boolean auraSagrada;

    public Paladino(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Paladino =====
        super(nome, raca, idade, genero,
            120,    // baseHP
            120,    // baseMP
            24,    // baseATK
            22,    // baseDEF (percentual)
            15,    // baseSPD
            24     // baseLCK
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
        this.devocao = 75;
        this.auraSagrada = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO PALADINO =====

    // Golpe sagrado poderoso
    public int julgamentoDivino() {
        if (mpAtual >= 35) {
            mpAtual -= 35;
            int dano = this.atk + devocao;
            System.out.println(nome + " invoca o Julgamento Divino!");
            return dano;
        }
        System.out.println("MP insuficiente para Julgamento Divino!");
        return atacar();
    }

    // Ativa aura protetora
    public boolean ativarAuraSagrada() {
        if (mpAtual >= 40 && !auraSagrada) {
            mpAtual -= 40;
            auraSagrada = true;
            def += 10;
            System.out.println(nome + " ativa uma Aura Sagrada protetora!");
            return true;
        }
        System.out.println("Não foi possível ativar a Aura Sagrada!");
        return false;
    }

    // Cura usando luz divina
    public int luzCura() {
        if (mpAtual >= 40) {
            mpAtual -= 40;
            int cura = 40 + (devocao / 2);
            hpAtual = Math.min(hpAtual + cura, maxHP);
            System.out.println(nome + " invoca a Luz Curativa! +" + cura + " HP");
            return cura;
        }
        System.out.println("MP insuficiente para Luz Curativa!");
        return 0;
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com bônus de devoção
        int dano = this.atk;
        
        if (auraSagrada) {
            dano += (int)(dano * 0.25);
            System.out.println("A Aura Sagrada fortalece o ataque!");
        }
        System.out.println("Dano: " + dano);
        // Gera devoção ao atacar
        devocao = Math.min(devocao + 6, maxDevocao);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Aura sagrada reduz dano adicional
        if (auraSagrada) {
            danoFinal = (int)(danoRecebido * 0.6); // 40% de redução
            System.out.println("A Aura Sagrada protege do dano!");
            // Aura se dissipa após defender
            auraSagrada = false;
            def -= 10;
        } else {
            // Defesa normal (já alta)
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
        // Paladino prefere lutar, chance baixa de fuga
        double chanceFuga = spd * 0.6;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " recua taticamente para proteger os inocentes!");
        } else {
            System.out.println(nome + " se recusa a fugir da batalha!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    public void oracao() {
        devocao = maxDevocao;
        int hpRecuperado = (int)(maxHP * 0.2);
        hpAtual = Math.min(hpAtual + hpRecuperado, maxHP);
        System.out.println(nome + " ora e fortalece sua devoção!");
    }

    // ===== GETTERS E SETTERS =====
    public int getDevocao() {
        return devocao;
    }

    public boolean isAuraSagrada() {
        return auraSagrada;
    }

    public void setDevocao(int devocao) {
        this.devocao = Math.min(devocao, maxDevocao);
    }

    // ===== STATUS DO PALADINO =====
    public void mostrarStatus() {
        System.out.println("=== STATUS PALADINO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Devoção: " + devocao + "/" + maxDevocao);
        System.out.println("Aura Sagrada: " + (auraSagrada ? "Ativa" : "Inativa"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("=======================");
    }
}