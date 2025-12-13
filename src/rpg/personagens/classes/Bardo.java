package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Bardo extends Personagem {

    // ===== Multiplicadores da classe Bardo =====
    protected static final double HP_MOD  = 0.85;
    protected static final double MP_MOD  = 1.20;
    protected static final double ATK_MOD = 0.90;
    protected static final double DEF_MOD = 0.85;
    protected static final double SPD_MOD = 1.15;
    protected static final double LCK_MOD = 1.50;

    // ===== Atributos específicos do Bardo =====
    protected int inspiracao; // Afeta aliados e habilidades
    protected final int maxInspiracao = 100;
    protected boolean performanceAtiva;

    public Bardo(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Bardo =====
        super(nome, raca, idade, genero,
            80,     // baseHP
            200,    // baseMP
            14,    // baseATK
            11,    // baseDEF (percentual)
            22,    // baseSPD
            30     // baseLCK
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
        this.inspiracao = 60;
        this.performanceAtiva = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO BARDO =====

    // Canção que inspira aliados
    public boolean cancaoInspiradora() {
        if (mpAtual >= 25) {
            mpAtual -= 25;
            performanceAtiva = true;
            inspiracao = Math.min(inspiracao + 25, maxInspiracao);
            System.out.println(nome + " canta uma Canção Inspiradora!");
            return true;
        }
        System.out.println("MP insuficiente para Canção Inspiradora!");
        return false;
    }

    // Ataque sônico que causa dano e confunde inimigos
    public int notaCortante() {
        if (mpAtual >= 20) {
            mpAtual -= 20;
            int dano = this.atk + (inspiracao / 3);
            System.out.println(nome + " emite uma Nota Cortante!");
            return dano;
        }
        System.out.println("MP insuficiente para Nota Cortante!");
        return atacar();
    }

    // Encantamento que distrai inimigos
    public boolean encantar() { // para usar efeitos
        if (mpAtual >= 30) {
            mpAtual -= 30;
            double chanceSucesso = (lck + inspiracao) / 2.0;
            boolean sucesso = Math.random() * 100 < chanceSucesso;
            if (sucesso) {
                System.out.println(nome + " encanta os inimigos com sua música de ninar!");
            } else {
                System.out.println("Os inimigos resistem ao encantamento!");
            }
            return sucesso;
        }
        System.out.println("MP insuficiente para Encantar!");
        return false;
    }

    // Metodo para carregar a inspiraçãoi
    public void improvisar() {
        inspiracao = maxInspiracao;
        int mpRecuperado = (int)(maxMP * 0.3);
        mpAtual = Math.min(mpAtual + mpRecuperado, maxMP);
        System.out.println(nome + " improvisa uma melodia e recupera energias!");
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com bônus de performance
        int dano = this.atk;
        
        if (performanceAtiva) {
            dano += (int)(dano * 0.4);
            System.out.println("A performance aumenta o poder do ataque!");
        }
        
        // Gera inspiração ao atacar
        inspiracao = Math.min(inspiracao + 8, maxInspiracao);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Performance pode reduzir dano
        if (performanceAtiva) {
            danoFinal = (int)(danoRecebido * 0.8); // 20% de redução
            System.out.println("A performance distrai os inimigos!");
            performanceAtiva = false;
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
        // Bardo tem excelente chance de fuga
        double chanceFuga = (spd + lck) / 1.8;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " distrai os inimigos e escapa cantando!");
        } else {
            System.out.println(nome + " não consegue encontrar a melodia da fuga!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    // ===== GETTERS E SETTERS =====
    public int getInspiracao() {
        return inspiracao;
    }

    public boolean isPerformanceAtiva() {
        return performanceAtiva;
    }

    public void setInspiracao(int inspiracao) {
        this.inspiracao = Math.min(inspiracao, maxInspiracao);
    }

    // ===== STATUS DO BARDO =====
    public void mostrarStatus() {
        System.out.println("=== STATUS BARDO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Inspiração: " + inspiracao + "/" + maxInspiracao);
        System.out.println("Performance: " + (performanceAtiva ? "Ativa" : "Inativa"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("====================");
    }
}