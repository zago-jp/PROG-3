package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Ladino extends Personagem {

    // ===== Multiplicadores da classe Ladino =====
    protected static final double HP_MOD  = 0.80;
    protected static final double MP_MOD  = 0.70;
    protected static final double ATK_MOD = 1.10;
    protected static final double DEF_MOD = 0.70;
    protected static final double SPD_MOD = 1.50;
    protected static final double LCK_MOD = 1.40;

    // ===== Atributos específicos do Ladino =====
    protected int furtividade; // Chance de ataque furtivo
    protected final int maxFurtividade = 100;
    protected boolean invisivel;

    public Ladino(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Ladino =====
        super(nome, raca, idade, genero,
            75,    // baseHP
            60,    // baseMP
            18,    // baseATK
            8,     // baseDEF (percentual)
            30,    // baseSPD
            25     // baseLCK
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
        this.furtividade = 60;
        this.invisivel = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO LADINO =====

    // Ataque furtivo com dano massivo
    public int ataqueFurtivo() {
        if (furtividade >= 40 && mpAtual >= 20) {
            mpAtual -= 20;
            furtividade -= 40;
            int dano = this.atk * 2; // Dano dobrado
            System.out.println(nome + " executa um Ataque Furtivo!");
            return dano;
        }
        System.out.println("Não é possível executar Ataque Furtivo!");
        return atacar();
    }

    // Fica invisível temporariamente
    public boolean esconder() {
        if (mpAtual >= 30) {
            mpAtual -= 30;
            invisivel = true;
            furtividade = maxFurtividade;
            System.out.println(nome + " desaparece nas sombras!");
            return true;
        }
        System.out.println("MP insuficiente para Esconder!");
        return false;
    }

    // Rouba itens ou recursos (conceitual)
    public boolean roubar() {
        if (mpAtual >= 15) {
            mpAtual -= 15;
            double chanceSucesso = (furtividade + lck) / 2.0;
            boolean sucesso = Math.random() * 100 < chanceSucesso;
            if (sucesso) {
                System.out.println(nome + " rouba com sucesso!");
                // Aqui poderia adicionar lógica de itens roubados
            } else {
                System.out.println(nome + " falha ao roubar!");
            }
            return sucesso;
        }
        System.out.println("MP insuficiente para Roubar!");
        return false;
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com chance de ataque furtivo
        int dano = this.atk;
        
        // Chance de ataque furtivo automático
        if (invisivel || Math.random() * 100 < furtividade) {
            System.out.println("Ataque Furtivo Surpresa!");
            dano += (int)(dano * 0.8);
            invisivel = false;
        }
        
        // Aumenta furtividade ao atacar
        furtividade = Math.min(furtividade + 8, maxFurtividade);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        // Ladino pode esquiva parcialmente
        int danoFinal = danoRecebido;
        
        if (Math.random() * 100 < spd) {
            danoFinal = (int)(danoRecebido * 0.3); // 70% de esquiva
            System.out.println(nome + " esquiva habilmente do ataque!");
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
        // Ladino é mestre em fugir
        double chanceFuga = (spd + furtividade) / 2.0;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " desaparece nas sombras!");
        } else {
            System.out.println(nome + " é detectado ao tentar fugir!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    public void observar() {
        furtividade = Math.min(furtividade + 20, maxFurtividade);
        System.out.println(nome + " observa o ambiente atentamente!");
    }

    // ===== GETTERS E SETTERS =====
    public int getFurtividade() {
        return furtividade;
    }

    public boolean isInvisivel() {
        return invisivel;
    }

    public void setFurtividade(int furtividade) {
        this.furtividade = Math.min(furtividade, maxFurtividade);
    }

    // ===== STATUS DO LADINO =====
    public void mostrarStatus() {
        System.out.println("=== STATUS LADINO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Furtividade: " + furtividade + "/" + maxFurtividade);
        System.out.println("Invisível: " + (invisivel ? "Sim" : "Não"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("=====================");
    }
}
