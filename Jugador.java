import java.util.ArrayList;
import java.util.List;

/**
 * @class Jugador
 * @brief Representa a un jugador en el juego, incluyendo su nombre, puntaje,
 *        historial de recorrido, partidas ganadas y perdidas.
 */
public class Jugador {
    private String nombre;                     /**< Nombre del jugador. */
    private int puntos;                        /**< Puntos actuales del jugador. */
    private List<int[]> recorrido;             /**< Lista de coordenadas recorridas. */
    private List<int[]> recorridoBonificacion; /**< Lista de coordenadas para bonificación. */
    private int ganadas;                       /**< Cantidad de partidas ganadas. */
    private int perdidas;                      /**< Cantidad de partidas perdidas. */

    /**
     * @brief Constructor del jugador.
     * @param nombre Nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.ganadas = 0;
        this.perdidas = 0;
        this.recorrido = new ArrayList<>();
        this.recorridoBonificacion = new ArrayList<>();
    }

    /** @return El nombre del jugador. */
    public String getNombre() {
        return nombre;
    }

    /** @return Los puntos acumulados del jugador. */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @brief Suma puntos al jugador.
     * @param puntos Cantidad de puntos a sumar.
     */
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    /**
     * @brief Resta puntos al jugador.
     * @param puntos Cantidad de puntos a restar.
     */
    public void restarPuntos(int puntos) {
        this.puntos -= puntos;
    }

    /**
     * @brief Agrega una coordenada al recorrido del jugador.
     * @param fila Fila recorrida.
     * @param columna Columna recorrida.
     */
    public void agregarCoordenada(int fila, int columna) {
        recorrido.add(new int[]{fila, columna});
        recorridoBonificacion.add(new int[]{fila, columna});
    }

    /** @return Lista de coordenadas recorridas por el jugador. */
    public List<int[]> getRecorrido() {
        return recorrido;
    }

    /** @return Lista de coordenadas para aplicar bonificación. */
    public List<int[]> getRecorridoBonificacion() {
        return recorridoBonificacion;
    }

    /** @brief Incrementa el contador de partidas ganadas. */
    public void incrementarGanadas() {
        ganadas++;
    }

    /** @brief Incrementa el contador de partidas perdidas. */
    public void incrementarPerdidas() {
        perdidas++;
    }

    /** @return Número de partidas ganadas. */
    public int getGanadas() {
        return ganadas;
    }

    /** @return Número de partidas perdidas. */
    public int getPerdidas() {
        return perdidas;
    }

    /**
     * @brief Reinicia el estado del jugador para una nueva partida.
     */
    public void resetearPartida() {
        puntos = 0;
        recorrido.clear();
    }
}
