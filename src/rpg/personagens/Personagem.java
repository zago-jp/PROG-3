package rpg.personagens;

import rpg.personagens.raca.Raca;

public abstract class Personagem {

    // ===== IDENTIDADE =====
    protected String nome;
    protected Raca raca;
    protected int idade;
    protected String genero;

    // ===== ATRIBUTOS BASE =====
    protected int baseHP;
    protected int baseMP;
    protected int baseATK;
    protected int baseDEF;
    protected int baseSPD;
    protected int baseLCK;

    // ===== ATRIBUTOS FINAIS (MODIFICADOS POR CLASSE + RAÇA) =====
    protected int maxHP;
    protected int maxMP;
    protected int atk;
    protected int def;
    protected int spd;
    protected int lck;

    // ===== ATRIBUTOS ATUAIS =====
    protected int hpAtual;
    protected int mpAtual;

    // =====================================================
    // CONSTRUTOR
    // =====================================================
    public Personagem(String nome, Raca raca, int idade, String genero,
                      int baseHP, int baseMP, int baseATK, int baseDEF, int baseSPD, int baseLCK) {

        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.genero = genero;

        this.baseHP  = baseHP;
        this.baseMP  = baseMP;
        this.baseATK = baseATK;
        this.baseDEF = baseDEF;
        this.baseSPD = baseSPD;
        this.baseLCK = baseLCK;

        // atributos finais serão calculados pela classe (Mago, Guerreiro, etc.)
        this.maxHP = 0;
        this.maxMP = 0;
        this.atk   = 0;
        this.def   = 0;
        this.spd   = 0;
        this.lck   = 0;

        this.hpAtual = 0; //inicializa com os atributos base
        this.mpAtual = 0;
    }

    // =====================================================
    // MÉTODOS POLIMÓRFICOS
    // =====================================================
    public abstract int atacar();
    public abstract int defender(int danoRecebido);
    public abstract boolean fugir();

    // =====================================================
    // GETTERS
    // =====================================================
    public String getNome() { return nome; }
    public Raca getRaca() { return raca; }
    public int getIdade() { return idade; }
    public String getGenero() { return genero; }

    public int getMaxHP() { return maxHP; }
    public int getMaxMP() { return maxMP; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getSpd() { return spd; }
    public int getLck() { return lck; }

    public int getHpAtual() { return hpAtual; }
    public int getMpAtual() { return mpAtual; }

    public void setHpAtual(int hp) { this.hpAtual = hp; }
    public void setMpAtual(int mp) { this.mpAtual = mp; }

    // metodo auxiliar
    public boolean estaVivo() { return hpAtual > 0; }
}



