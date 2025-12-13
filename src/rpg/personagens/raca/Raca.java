package rpg.personagens.raca;

public enum Raca {
    // MODIFICADORES PARA RAÇAS ALIADAS/ OU NÃO...
    HUMANO    (1.00, 1.00, 1.00, 1.00, 1.00, 1.00),
    ELFO      (0.90, 1.20, 1.00, 0.80, 1.30, 1.10),
    ANAO      (1.20, 0.80, 1.20, 1.30, 0.70, 0.90),
    ORC       (1.40, 0.70, 1.30, 1.10, 0.70, 0.80),
    DEMONIO   (1.10, 1.30, 1.20, 1.00, 1.10, 1.20),
    ELFONEGRO (0.85, 1.40, 1.10, 0.90, 1.30, 1.30),

    // RAÇAS DE INIMIGOS NATURAIS
    GOBLIN      (1.20, 1.50, 1.00, 1.00, 2.00, 0.70),
    LOBO        (0.90, 0.20, 1.00, 0.60, 1.30, 0.40),
    ESQUELETO   (0.70, 0.30, 0.80, 0.90, 0.80, 0.30),
    ZUMBI       (1.20, 0.10, 0.70, 0.80, 0.50, 0.10),

    // MELHORADA PARA TESTES
    DRAGAO      (41.50, 11.80, 11.50, 11.40, 10.90, 1.00),

    // BOSS
    REI_DEMONIO (5.00, 5.00, 5.00, 5.00, 5.00, 5000.00);

    // multiplicadores das racas
    private final double hpMod;
    private final double mpMod;
    private final double atkMod;
    private final double defMod;
    private final double spdMod;
    private final double lckMod;

    // contrutor
    Raca(double hpMod, double mpMod, double atkMod, double defMod, double spdMod, double lckMod) {
        this.hpMod = hpMod;
        this.mpMod = mpMod;
        this.atkMod = atkMod;
        this.defMod = defMod;
        this.spdMod = spdMod;
        this.lckMod = lckMod;
    }

    public double getHpMod()  { return hpMod; }
    public double getMpMod()  { return mpMod; }
    public double getAtkMod() { return atkMod; }
    public double getDefMod() { return defMod; }
    public double getSpdMod() { return spdMod; }
    public double getLckMod() { return lckMod; }
}

