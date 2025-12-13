package rpg.personagens.status;

public enum StatusAilment {

    ENVENENADO,   // perde HP por turno e reduz dano
    ATORDOADO,    // perde 1 turno
    CONFUSO,      // chance de atacar a si mesmo/aliado
    SONO,         // não age até ser atingido
    PARALISADO,   // 50% de chance de não agir (ou permanente até cura)
    SANGRANDO;    // perde HP por turno
}
