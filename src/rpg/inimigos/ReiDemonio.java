package rpg.inimigos;

import rpg.personagens.Personagem;
import rpg.personagens.raca.Raca;

public class ReiDemonio extends Personagem {
    
    private int fase = 1;
    
    public ReiDemonio() {
        super("Rei Demônio", Raca.REI_DEMONIO, 500, "Andrôgeno",
            500,   // baseHP
            200,   // baseMP
            40,   // baseATK
            20,   // baseDEF
            25,   // baseSPD
            30    // baseLCK
        );
        
        calcularAtributosInimigo();
    }
    
    private void calcularAtributosInimigo() {
        // Boss tem bônus extra
        this.maxHP = (int)(baseHP * raca.getHpMod() * 1.5);
        this.maxMP = (int)(baseMP * raca.getMpMod() * 1.3);
        this.atk = (int)(baseATK * raca.getAtkMod() * 1.4);
        this.def = (int)(baseDEF * raca.getDefMod() * 1.3);
        this.spd = (int)(baseSPD * raca.getSpdMod() * 1.0);
        this.lck = (int)(baseLCK * raca.getLckMod() * 1.2);
        
        this.hpAtual = maxHP;
        this.mpAtual = maxMP;
    }
    
    @Override
    public int atacar() {
        if (fase == 1 && hpAtual < maxHP * 0.5) {
            fase = 2;
            System.out.println("O Rei Demônio libera seu poder demoníaco!");
            atk += 15;
            def += 10;
        }
        
        if (fase == 1) {
            System.out.println("Rei Demônio usa Lâmina das Sombras!");
            return atk;
        } else {
            System.out.println("Rei Demônio usa Inferno Eterno!");
            return (int)(atk * 1.5);
        }
    }
    
    @Override
    public int defender(int danoRecebido) {
        int danoFinal = danoRecebido;
        
        if (fase == 2) {
            // Na fase 2, tem resistência aumentada
            System.out.println("Aura demoníaca absorve parte do dano!");
            danoFinal = (int)(danoRecebido * 0.3); // 70% de redução
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
        // Boss nunca foge (a não ser que você queira um easter egg)
        System.out.println("O Rei Demônio ri da sua tentativa de fuga!");
        return false;
    }
    
    // Habilidade especial do boss
    public int BlackRole() {
        // dano massivo hómicida a todos. 10% de chance
        int dmg = 0;
        if (Math.random() < 0.1){
            System.out.println("Rei Demônio invoca um buraco negro massivo e desintegra tudo!");
            dmg = (int)(atk * 100); 
        }
        return dmg;
    }
    
    public int getExperiencia() {
        return 1000; // XP generoso para um boss final
    }
}