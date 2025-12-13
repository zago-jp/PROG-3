package rpg.personagens.status;

import java.util.HashMap;
import java.util.Map;

public class StatusHandler {

    // Cada status pode ter duração. -1 = duração indefinida (ex: paralisia permanente).
    private final Map<StatusAilment, Integer> statusAtivos = new HashMap<>();

    
    // Aplica um status ao personagem.
    // @param status tipo de status
    // @param duracao quantos turnos dura (-1 = permanente)

    public void aplicarStatus(StatusAilment status, int duracao) {
        statusAtivos.put(status, duracao);
    }

    // Remove completamente um status.

    public void removerStatus(StatusAilment status) {
        statusAtivos.remove(status);
    }
    
    // Verifica se o personagem está com determinado status.

    public boolean possuiStatus(StatusAilment status) {
        return statusAtivos.containsKey(status);
    }

    // Processa efeitos por turno.
    // Retorna um objeto com informações do turno (dano por veneno, impedimento de ação, etc)

    public StatusTurnResult processarTurno() {
        int dano = 0;
        boolean impedeAcao = false;

        // Criamos uma cópia para evitar alteração durante a iteração(facxilitar)
        Map<StatusAilment, Integer> copia = new HashMap<>(statusAtivos);

        for (Map.Entry<StatusAilment, Integer> entry : copia.entrySet()) {
            StatusAilment status = entry.getKey();
            int duracao = entry.getValue();

            switch (status) {

                case ENVENENADO:
                    dano += 1;     // Dano efetivo será calculado pelo DanoCalculator
                    break;

                case SANGRANDO:
                    dano += 1;     // Mesmo esquema, quem decide o valor final é o sistema de dano
                    break;

                case ATORDOADO:
                case SONO:
                    impedeAcao = true;
                    break;

                case PARALISADO:
                    // Aqui tem chance de impedir ação
                    impedeAcao = impedeAcao || true;
                    break;

                case CONFUSO:
                    // A lógica de atacar aliado ficará no módulo de combate
                    break;
            }

            // Reduz a duração (se não for permanente)
            if (duracao > 0) {
                duracao -= 1;
                if (duracao == 0) {
                    statusAtivos.remove(status);
                } else {
                    statusAtivos.put(status, duracao);
                }
            }
        }

        return new StatusTurnResult(dano, impedeAcao);
    }

    // Representa os efeitos de status que precisam ser tratados no turno.

    public static class StatusTurnResult {
        public final int dano;          // Dano simbólico, convertido depois
        public final boolean impedeAcao;

        public StatusTurnResult(int dano, boolean impedeAcao) {
            this.dano = dano;
            this.impedeAcao = impedeAcao;
        }
    }
}

