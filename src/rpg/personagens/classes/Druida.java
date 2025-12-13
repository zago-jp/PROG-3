package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Druida extends Personagem {

    // ===== Multiplicadores da classe Druida =====
    protected static final double HP_MOD  = 1.10;
    protected static final double MP_MOD  = 1.30;
    protected static final double ATK_MOD = 0.90;
    protected static final double DEF_MOD = 1.00;
    protected static final double SPD_MOD = 1.00;
    protected static final double LCK_MOD = 1.10;

    // ===== Atributos específicos do Druida =====
    protected int energiaNatural; // Controle sobre a natureza
    protected final int maxEnergiaNatural = 100;
    protected boolean formaAnimal;

    public Druida(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Druida =====
        super(nome, raca, idade, genero,
            95,    // baseHP
            130,   // baseMP
            16,   // baseATK
            14,   // baseDEF (percentual)
            16,   // baseSPD
            20    // baseLCK
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
        this.energiaNatural = 50;
        this.formaAnimal = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO DRUIDA =====

    // Invoca os poderes da natureza para atacar
    public int invocarNatureza() {
        if (mpAtual >= 30) {
            mpAtual -= 30;
            int dano = this.atk + (energiaNatural * 2);
            System.out.println(nome + " invoca a fúria da natureza!");
            energiaNatural = Math.max(energiaNatural - 20, 0);
            return dano;
        }
        System.out.println("MP insuficiente...");
        return atacar();
    }

    // Transforma-se em animal temporariamente
    public boolean transformarAnimal() {
        if (mpAtual >= 50 && !formaAnimal) {
            mpAtual -= 50;
            formaAnimal = true;
            atk += 10;
            def += 5;
            System.out.println(nome + " transforma-se em animal selvagem!");
            return true;
        }
        System.out.println("Não foi possível se transformar!");
        return false;
    }

    // Cura usando energias naturais
    public int curaNatural() {
        if (mpAtual >= 25) {
            mpAtual -= 25;
            int cura = 30 + (energiaNatural / 2);
            hpAtual = Math.min(hpAtual + cura, maxHP);
            System.out.println(nome + " invoca a cura da natureza! +" + cura + " HP");
            return cura;
        }
        System.out.println("MP insuficiente para Cura Natural!");
        return 0;
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com bônus em forma animal
        int dano = this.atk;
        
        if (formaAnimal) {
            dano += 15;
            System.out.println("Garras e presas causam dano extra!");
        }

        // Recupera energia natural ao atacar
        energiaNatural = Math.min(energiaNatural + 8, maxEnergiaNatural);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        if (formaAnimal) {
            // Forma animal tem defesa melhorada
            danoFinal = (int)(danoRecebido * 0.8);
            System.out.println("Pele grossa reduz o dano!");
            // Volta à forma normal após defender
            formaAnimal = false;
            atk -= 10;
            def -= 5;
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
        // Druida tem boa chance de fuga, especialmente em forma animal
        double chanceFuga = formaAnimal ? spd * 1.5 : spd;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " desaparece na natureza!");
        } else {
            System.out.println(nome + " não consegue escapar!");
        }
        
        return sucesso;
    }

    public void conectarNatureza() {
        energiaNatural = maxEnergiaNatural;
        int mpRecuperado = (int)(maxMP * 0.25);
        mpAtual = Math.min(mpAtual + mpRecuperado, maxMP);
        System.out.println(nome + " conecta-se com a natureza e recupera energias!");
    }

    // ===== MÉTODOS DE UTILIDADE =====
    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    // ===== GETTERS E SETTERS =====
    public int getEnergiaNatural() {
        return energiaNatural;
    }

    public boolean isFormaAnimal() {
        return formaAnimal;
    }

    public void setEnergiaNatural(int energiaNatural) {
        this.energiaNatural = Math.min(energiaNatural, maxEnergiaNatural);
    }

    // ===== STATUS DO DRUIDA =====
    public void mostrarStatus() {
        System.out.println("=== STATUS DRUIDA ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Energia Natural: " + energiaNatural + "/" + maxEnergiaNatural);
        System.out.println("Forma Animal: " + (formaAnimal ? "Ativa" : "Inativa"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("=====================");
    }
}
