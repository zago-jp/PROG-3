package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Sacerdote extends Personagem {

    // ===== Multiplicadores da classe Sacerdote =====
    protected static final double HP_MOD  = 0.90;
    protected static final double MP_MOD  = 1.50;
    protected static final double ATK_MOD = 0.70;
    protected static final double DEF_MOD = 1.10;
    protected static final double SPD_MOD = 0.90;
    protected static final double LCK_MOD = 1.30;

    // ===== Atributos específicos do Sacerdote =====
    protected int fe; // Acumula para milagres poderosos
    protected final int maxFe = 100;
    protected boolean bencaoAtiva;

    public Sacerdote(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Sacerdote =====
        super(nome, raca, idade, genero,
            85,     // baseHP
            150,    // baseMP
            12,    // baseATK
            15,    // baseDEF (percentual)
            14,    // baseSPD
            28     // baseLCK
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
        this.fe = 50;
        this.bencaoAtiva = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO SACERDOTE =====

    // Cura um aliado ou a si mesmo
    public int curar(Personagem alvo) {
        if (mpAtual >= 30) {
            mpAtual -= 30;
            int cura = 30 * 2 + (fe / 2);
            int vidaAnterior = alvo.getHpAtual();
            alvo.setHpAtual(Math.min(alvo.getHpAtual() + cura, alvo.getMaxHP()));
            int curado = alvo.getHpAtual() - vidaAnterior;
            System.out.println(nome + " invoca a cura divina! Cura: " + curado);
            fe = Math.min(fe + 10, maxFe);
            return curado;
        }
        System.out.println("MP insuficiente para Curar!");
        return 0;
    }

    // Aplica uma bênção de proteção
    public boolean bencaoProtecao() {
        if (mpAtual >= 25 && !bencaoAtiva) {
            mpAtual -= 25;
            bencaoAtiva = true;
            System.out.println(nome + " invoca uma Bênção de Proteção!");
            return true;
        }
        System.out.println("Não foi possível invocar a bênção!");
        return false;
    }

    // Ataque sagrado
    public int golpeSagrado() {
        if (mpAtual >= 20) {
            mpAtual -= 20;
            int dano = this.atk + fe;
            System.out.println(nome + " desfere um Golpe Sagrado!");
            fe = Math.min(fe + 15, maxFe);
            return dano;
        }
        System.out.println("MP insuficiente para Golpe Sagrado!");
        return atacar();
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com chance de bênção
        int dano = this.atk;
        
        if (bencaoAtiva) {
            dano += (int)(dano * 0.3);
            System.out.println("Bênção divina fortalece o ataque!");
        }
        
        // Gera fé ao atacar
        fe = Math.min(fe + 5, maxFe);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Bênção reduz dano adicional
        if (bencaoAtiva) {
            danoFinal = (int)(danoRecebido * 0.7); // 30% de redução
            System.out.println("Bênção divina protege do dano!");
            bencaoAtiva = false;
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
        // Sacerdote tem chance média de fuga
        double chanceFuga = spd * 1.5;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " recua com a proteção divina!");
        } else {
            System.out.println(nome + " não consegue escapar!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    public void orar() {
        int mpRecuperado = (int)(maxMP * 0.2);
        mpAtual = Math.min(mpAtual + mpRecuperado, maxMP);
        fe = Math.min(fe + 20, maxFe);
        System.out.println(nome + " ora e recupera forças divinas!");
    }

    // ===== GETTERS E SETTERS =====
    public int getFe() {
        return fe;
    }

    public boolean isBencaoAtiva() {
        return bencaoAtiva;
    }

    public void setFe(int fe) {
        this.fe = Math.min(fe, maxFe);
    }

    // ===== STATUS DO SACERDOTE =====
    public void mostrarStatus() {
        System.out.println("=== STATUS SACERDOTE ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Fé: " + fe + "/" + maxFe);
        System.out.println("Bênção Ativa: " + (bencaoAtiva ? "Sim" : "Não"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("========================");
    }
}
