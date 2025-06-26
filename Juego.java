import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * @class Juego
 * @brief Controla la lógica principal del juego, incluyendo el tablero, los jugadores,
 *        los movimientos, las bonificaciones y la determinación del ganador.
 */
public class Juego {
    Tablero tablero;                  /**< Tablero del juego. */
    Jugador jugador1, jugador2;       /**< Jugadores del juego. */
    Jugador jugadorActual;            /**< Jugador que tiene el turno actual. */
    int tamanio;                      /**< Tamaño del tablero (NxN). */
    int filaActual;                   /**< Fila actual del jugador. */
    int columnaActual;                /**< Columna actual del jugador. */
    boolean juegoTerminado;           /**< Indica si el juego ha finalizado. */

    /**
     * @brief Constructor de la clase Juego.
     * @param tamanio Tamaño del tablero.
     * @param jugador1 Primer jugador.
     * @param jugador2 Segundo jugador.
     */
    public Juego(int tamanio, Jugador jugador1, Jugador jugador2) {
        tablero = new Tablero(tamanio);
        this.tamanio = tamanio;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        this.filaActual = this.columnaActual = 0;
        juegoTerminado = false;
    }

    /**
     * @brief Intenta realizar un movimiento en la celda indicada.
     * @param fila Fila del movimiento.
     * @param col Columna del movimiento.
     * @return true si el movimiento fue válido, false si no lo fue.
     */
    public boolean hacerMovimiento(int fila, int col) {
        boolean valido = false;
        if (!juegoTerminado) {
            if (validarMovimiento(fila, col)) {
                activarCelda(fila, col);

                if (fila == tamanio - 1 && col == tamanio - 1) {
                    juegoTerminado = true;
                }

                aplicarBonificacion(jugadorActual);
                filaActual = fila;
                columnaActual = col;

                cambiarTurno();
                valido = true;
            }
        }
        return valido;
    }

    /**
     * @brief Valida si un movimiento es legal según la posición actual.
     * @param fil Fila destino.
     * @param col Columna destino.
     * @return true si el movimiento es válido.
     */
    public boolean validarMovimiento(int fil, int col) {
        int nuevaFila = fil - filaActual;
        int nuevaColumna = col - columnaActual;
        return (nuevaFila == 1 && nuevaColumna == 0) ||
               (nuevaFila == 0 && nuevaColumna == 1) ||
               (nuevaFila == 1 && nuevaColumna == 1);
    }

    /**
     * @brief Activa la celda seleccionada y suma los puntos al jugador actual.
     * @param fila Fila de la celda.
     * @param col Columna de la celda.
     */
    public void activarCelda(int fila, int col) {
        Celda celda = tablero.getCelda(fila, col);
        int puntos = celda.activar(jugadorActual.getNombre());

        jugadorActual.sumarPuntos(puntos);
        jugadorActual.agregarCoordenada(fila, col);
    }

    /**
     * @brief Cambia el turno al otro jugador.
     */
    private void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

    /**
     * @brief Verifica si ya hay un ganador (es decir, si el juego terminó).
     * @return true si hay un ganador.
     */
    public boolean hayGanador() {
        return juegoTerminado;
    }

    /**
     * @brief Aplica bonificaciones al jugador según combinaciones de valores recorridos.
     * @param jugador Jugador al que se le aplicará la bonificación.
     */
    public void aplicarBonificacion(Jugador jugador) {
        int[] todosPositivos = {0, 1, 2, 3 , 4, 5, 6};
        int[] todosNegativos = {-5, -4, -3, -2, -1};
        int[] todosLosValores = {-5, -4, -3, -2, -1, 0, 1, 2, 3 , 4, 5, 6};

        List<Integer> valores = new ArrayList<>();
        List<int[]> recorrido = jugador.getRecorrido();

        for (int[] coord : recorrido) {
            Celda celda = tablero.getCelda(coord[0], coord[1]);
            valores.addAll(celda.getArbol().recorrerArbol());
        }

        if (contieneLosValores(todosPositivos, valores)) {
            jugador.sumarPuntos(-15);
            recorrido.clear();
        }

        if (contieneLosValores(todosNegativos, valores)) {
            jugador.sumarPuntos(100);
            recorrido.clear();
        }

        if (contieneLosValores(todosLosValores, valores)) {
            jugador.sumarPuntos(10);
            recorrido.clear();
        }
    }

    /**
     * @brief Verifica si la lista contiene todos los valores del conjunto especificado.
     * @param lista Lista de valores requeridos.
     * @param valores Lista de valores a evaluar.
     * @return true si los contiene todos.
     */
    private boolean contieneLosValores(int[] lista, List<Integer> valores) {
        Set<Integer> valoresUnicos = new HashSet<>(valores);
        for (int numero : lista) {
            if (!valoresUnicos.contains(numero)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @brief Obtiene el jugador ganador del juego, si lo hay.
     * @return El jugador ganador, o null en caso de empate o si el juego no ha terminado.
     */
    public Jugador obtenerGanador() {
        if (juegoTerminado) {
            aplicarBonificacion(jugador1);
            aplicarBonificacion(jugador2);

            if (jugador1.getPuntos() > jugador2.getPuntos()) {
                jugador1.incrementarGanadas();
                jugador2.incrementarPerdidas();
                return jugador1;
            } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
                jugador2.incrementarGanadas();
                jugador1.incrementarPerdidas();
                return jugador2;
            }
            return null; // Empate
        }
        return null;
    }

    /** @return Tablero actual del juego. */
    public Tablero getTablero() {
        return this.tablero;
    }

    /** @return Jugador que tiene el turno actual. */
    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }

    /** @return Jugador 1. */
    public Jugador getJugador1() {
        return this.jugador1;
    }

    /** @return Jugador 2. */
    public Jugador getJugador2() {
        return this.jugador2;
    }

    /** @return true si el juego ha terminado. */
    public boolean getJuegoTerminado() {
        return this.juegoTerminado;
    }

    /** @return Tamaño del tablero. */
    public int getTamanio() {
        return this.tamanio;
    }
}
