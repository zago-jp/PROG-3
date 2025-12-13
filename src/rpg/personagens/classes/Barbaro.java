package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Barbaro extends Personagem {

    // ===== Multiplicadores da classe Bárbaro =====
    protected static final double HP_MOD  = 1.60;
    protected static final double MP_MOD  = 0.40;
    protected static final double ATK_MOD = 1.50;
    protected static final double DEF_MOD = 1.10;
    protected static final double SPD_MOD = 1.00;
    protected static final double LCK_MOD = 0.80;

    // ===== Atributos específicos do Bárbaro =====
    protected int furia; // Estado de fúria incontrolável
    protected final int maxFuria = 100;
    protected boolean furiaAtiva;

    public Barbaro(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Bárbaro =====
        super(nome, raca, idade, genero,
            140,   // baseHP
            40,    // baseMP
            28,    // baseATK
            16,    // baseDEF (percentual)
            16,    // baseSPD
            14     // baseLCK
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
        this.furia = 40;
        this.furiaAtiva = false;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO BÁRBARO =====

    // Entra em estado de fúria incontrolável
    public boolean entrarEmFuria() {
        if (furia >= 50 && !furiaAtiva) {
            furia -= 50;
            furiaAtiva = true;
            atk += 15;
            def -= 5; // Fúria reduz a defesa
            System.out.println(nome + " entra em FÚRIA INCONTROLÁVEL!");
            return true;
        }
        System.out.println("Fúria insuficiente para entrar em Fúria!");
        return false;
    }

    // Ataque devastador que consome toda a fúria
    public int golpeDevastador() {
        if (furia >= 30) {
            int danoExtra = furia;
            furia = 0;
            int dano = this.atk + danoExtra;
            System.out.println(nome + " desfere um Golpe Devastador!");
            return dano;
        }
        System.out.println("Fúria insuficiente para Golpe Devastador!");
        return atacar();
    }

    // Grito que intimida inimigos
    public boolean gritoDeGuerra() {
        if (mpAtual >= 25) {
            mpAtual -= 25;
            furia = Math.min(furia + 30, maxFuria);
            System.out.println(nome + " solta um Grito de Guerra aterrorizante!"); // atordoa
            return true;
        }
        System.out.println("MP insuficiente para Grito de Guerra!");
        return false;
    }

    public void descansoFeroz() {
        int hpRecuperado = (int)(maxHP * 0.35);
        hpAtual = Math.min(hpAtual + hpRecuperado, maxHP);
        furia = Math.min(furia + 25, maxFuria);
        System.out.println(nome + " descansa ferozmente e recupera forças!");
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com bônus de fúria
        int dano = this.atk;
        
        if (furiaAtiva) {
            dano += 20;
            System.out.println("A fúria aumenta brutalmente o dano!");
        }
        
        // Gera fúria ao atacar
        furia = Math.min(furia + 12, maxFuria);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Em fúria, o bárbaro ignora parte da dor(dano)
        if (furiaAtiva) {
            danoFinal = (int)(danoRecebido * 0.7); // 30% de redução
            System.out.println("A fúria faz " + nome + " ignorar a dor!");
            // Fúria se dissipa após defender
            furiaAtiva = false;
            atk -= 15;
            def += 5;
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
        
        // Receber dano gera fúria
        furia = Math.min(furia + 8, maxFuria);
        
        return danoFinal;
    }

    @Override
    public boolean fugir() {
        // Bárbaro raramente foge, chance muito baixa
        double chanceFuga = spd * 0.4;
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " recua para lutar outro dia!");
        } else {
            System.out.println(nome + " NUNCA FUGIRÁ DA BATALHA!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    public boolean podeUsarHabilidade(int custoMP) {
        return mpAtual >= custoMP;
    }

    // ===== GETTERS E SETTERS =====
    public int getFuria() {
        return furia;
    }

    public boolean isFuriaAtiva() {
        return furiaAtiva;
    }

    public void setFuria(int furia) {
        this.furia = Math.min(furia, maxFuria);
    }

    // ===== STATUS DO BÁRBARO =====
    public void mostrarStatus() {
        System.out.println("=== STATUS BÁRBARO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP + " 💪 MÁXIMO");
        System.out.println("MP: " + mpAtual + "/" + maxMP + " ⚠️ BAIXO");
        System.out.println("Fúria: " + furia + "/" + maxFuria);
        System.out.println("Fúria Ativa: " + (furiaAtiva ? "SIM! 💥" : "Não"));
        System.out.println("Ataque: " + atk + " 💀 BRUTAL | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
        System.out.println("======================");
    }
}
