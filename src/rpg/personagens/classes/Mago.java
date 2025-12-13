package rpg.personagens.classes;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class Mago extends Personagem {

    // ===== Multiplicadores da classe Mago =====
    protected static final double HP_MOD  = 0.70;
    protected static final double MP_MOD  = 1.40;
    protected static final double ATK_MOD = 0.80;
    protected static final double DEF_MOD = 0.90;
    protected static final double SPD_MOD = 1.10;
    protected static final double LCK_MOD = 1.10;

    // ===== Atributos específicos do Mago =====
    protected int cargaMagica; // Acumula para magias mais poderosas
    protected final int maxCargaMagica = 100;
    protected boolean escudoAtivo;
    protected int turnosEscudo;

    public Mago(String nome, Raca raca, int idade, String genero) {

        // ===== Valores base da classe Mago =====
        super(nome, raca, idade, genero,
            70,    // baseHP
            120,   // baseMP
            15,   // baseATK
            10,   // baseDEF (percentual)
            18,   // baseSPD
            22    // baseLCK
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
        this.cargaMagica = 0;
        this.escudoAtivo = false;
        this.turnosEscudo = 0;
    }

    // Método auxiliar para cálculo consistente
    protected int calcularAtributoFinal(int base, double modClasse, double modRaca) {
        return (int) (base * modClasse * modRaca);
    }

    // ===== HABILIDADES ESPECIAIS DO MAGO =====

    
    //Conjura um poderoso feitiço de fogo
    public int bolaDeFogo(int mpGasto) {
        if (mpAtual >= mpGasto) {
            mpAtual -= mpGasto;
            int dano = this.atk + (mpGasto * 2) + cargaMagica;
            cargaMagica = 0; // Reseta a carga
            System.out.println(nome + " lança uma Bola de Fogo! Dano: " + dano);
            return dano;
        }
        System.out.println("MP insuficiente para Bola de Fogo!");
        return 0;
    }

    //Cria um escudo mágico que reduz dano por alguns turnos
    public boolean conjurarEscudo(int mpGasto) {
        if (mpAtual >= mpGasto && !escudoAtivo) {
            mpAtual -= mpGasto;
            escudoAtivo = true;
            turnosEscudo = 3; // Dura 3 turnos
            System.out.println(nome + " conjurou um Escudo Mágico!");
            return true;
        }
        System.out.println("Não foi possível conjurar escudo!");
        return false;
    }

    //Rouba MP do inimigo
    public int drenarMP(Personagem alvo, int quantidade) {
        if (mpAtual >= 10) { // Custo fixo para drenar
            mpAtual -= 10;
            int mpDrenado = Math.min(quantidade, ((Mago) alvo).getMpAtual());
            ((Mago) alvo).setMpAtual(((Mago) alvo).getMpAtual() - mpDrenado);
            mpAtual = Math.min(mpAtual + mpDrenado, maxMP);
            System.out.println(nome + " drenou " + mpDrenado + " MP do alvo!");
            return mpDrenado;
        }
        System.out.println("MP insuficiente para drenar!");
        return 0;
    }

    //Acumula energia mágica para próximo feitiço
    public int carregarMagia() {
        int cargaGanha = 15 + (lck / 5);
        cargaMagica = Math.min(cargaMagica + cargaGanha, maxCargaMagica);
        System.out.println(nome + " carrega energia mágica! Carga: " + cargaMagica + "/" + maxCargaMagica);
        return cargaGanha;
    }

    //Teleporta para evitar dano completamente
    public boolean teleportar(int mpGasto) {
        if (mpAtual >= mpGasto) {
            mpAtual -= mpGasto;
            // Chance de sucesso baseada em velocidade e sorte
            double chanceSucesso = (spd + lck) / 2.0;
            boolean sucesso = Math.random() * 100 < chanceSucesso;
            if (sucesso) {
                System.out.println(nome + " teleporta para evitar o dano!");
            } else {
                System.out.println(nome + " falha ao teleportar!");
            }
            return sucesso;
        }
        System.out.println("MP insuficiente para teleportar!");
        return false;
    }

    // ===== SOBRESCRITA DOS MÉTODOS BASE =====

    @Override
    public int atacar() {
        // Ataque básico com chance de crítico mágico
        int dano = this.atk;
        
        // Chance de crítico baseada na sorte
        if (Math.random() * 100 < lck) {
            System.out.println("Crítico Mágico! Poder arcano aumenta o dano.");
            dano += (int)(dano * 0.5); // +50% de dano
        }
        
        // Acumula carga mágica ao atacar
        cargaMagica = Math.min(cargaMagica + 5, maxCargaMagica);
        
        return dano;
    }

    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        // Escudo mágico reduz dano adicional
        if (escudoAtivo) {
            danoFinal = (int)(danoRecebido * 0.6); // 40% de redução
            System.out.println("Escudo Mágico absorve parte do dano!");
            turnosEscudo--;
            if (turnosEscudo <= 0) {
                escudoAtivo = false;
                System.out.println("Escudo Mágico se dissipa!");
            }
        }
        else {
            // Aplica defesa normal
            danoFinal = (int)(danoRecebido - (danoRecebido * this.def / 100));
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
        // Mago tem vantagem na fuga devido à velocidade
        double chanceFuga = spd * 1.2; // 
        boolean sucesso = Math.random() * 100 < chanceFuga;
        
        if (sucesso) {
            System.out.println(nome + " usa magia de teleporte para fugir!");
        } else {
            System.out.println(nome + " falha em fugir!");
        }
        
        return sucesso;
    }

    // ===== MÉTODOS DE UTILIDADE =====

    // VERIFICA SE HÁ MANA
    public boolean podeLancarMagia(int custoMP) {
        return mpAtual >= custoMP;
    }

    // RECAREGA O MP
    public void meditar() {
        int mpRecuperado = (int)(maxMP * 0.3); // Recupera 30% do MP máximo
        mpAtual = Math.min(mpAtual + mpRecuperado, maxMP);
        System.out.println(nome + " medita e recupera " + mpRecuperado + " MP!");
    }

    // ===== GETTERS E SETTERS PARA SHILD E CARGA(MAGICA) =====
    public int getCargaMagica() {
        return cargaMagica;
    }

    public boolean isEscudoAtivo() {
        return escudoAtivo;
    }

    public int getTurnosEscudo() {
        return turnosEscudo;
    }

    public void setCargaMagica(int cargaMagica) {
        this.cargaMagica = Math.min(cargaMagica, maxCargaMagica);
    }

    // ===== STATUS DO MAGO =====
    public void mostrarStatusMago() {
        System.out.println("=== STATUS MAGO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("HP: " + hpAtual + "/" + maxHP);
        System.out.println("MP: " + mpAtual + "/" + maxMP);
        System.out.println("Carga Mágica: " + cargaMagica + "/" + maxCargaMagica);
        System.out.println("Escudo Ativo: " + (escudoAtivo ? "Sim (" + turnosEscudo + " turnos)" : "Não"));
        System.out.println("Ataque: " + atk + " | Defesa: " + def);
        System.out.println("Velocidade: " + spd + " | Sorte: " + lck);
    }
}


