/**
 * @class Tablero
 * @brief Representa el tablero del juego como una matriz de celdas utilizando una lista doble de listas simples.
 */
public class Tablero {
    private ListaDoble tablero;  /**< Lista doble que representa las filas del tablero. */
    private int tamanio;         /**< Tamaño del tablero (n x n). */

    /**
     * @brief Constructor del tablero.
     * @param tamanio Tamaño del tablero cuadrado.
     */
    public Tablero(int tamanio) {
        this.tamanio = tamanio;
        this.tablero = new ListaDoble();
        inicializartablero();
    }

    /**
     * @brief Inicializa el tablero agregando listas simples (filas) y celdas.
     */
    public void inicializartablero() {
        for (int i = 0; i < tamanio; i++) {
            ListaSimple fila = new ListaSimple();
            for (int j = 0; j < tamanio; j++) {
                fila.agregarCelda();
            }
            tablero.agregarFila(fila);
        }
    }

    /**
     * @brief Obtiene una celda específica del tablero.
     * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @return La celda en la posición indicada.
     */
    public Celda getCelda(int fila, int columna) {
        return tablero.getCelda(fila, columna);
    }

    /**
     * @brief Obtiene el tamaño del tablero.
     * @return Tamaño del tablero.
     */
    public int getTamanio() {
        return this.tamanio;
    }

    /**
     * @brief Imprime el contenido del tablero en consola.
     */
    public void imprimirTablero() {
        tablero.imprimirTablero();
    }
}
