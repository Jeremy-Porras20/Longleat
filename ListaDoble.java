/**
 * @class ListaDoble
 * @brief Representa una lista doblemente enlazada donde cada nodo contiene una fila (ListaSimple).
 *        Utilizada para modelar estructuras como un tablero bidimensional.
 */
public class ListaDoble {
    private Nodo inicio;  /**< Primer nodo de la lista. */
    private Nodo ultimo;  /**< Último nodo de la lista. */

    /**
     * @brief Constructor de la clase ListaDoble.
     *        Inicializa la lista vacía.
     */
    public ListaDoble() {
        inicio = ultimo = null;
    }

    /**
     * @brief Agrega una nueva fila (ListaSimple) al final de la lista.
     * @param elemento La fila a agregar.
     */
    public void agregarFila(ListaSimple elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (inicio != null) {
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        } else {
            inicio = ultimo = nuevo;
        }
    }

    /**
     * @brief Elimina la última fila de la lista.
     *        Si la lista está vacía, muestra un mensaje en consola.
     */
    public void eliminar() {
        if (ultimo != null) {
            Nodo penultimo = ultimo.ant;
            if (penultimo != null) {
                ultimo.ant = null;
                penultimo.sig = null;
                ultimo = penultimo;
            } else {
                // Si solo hay un elemento
                inicio = ultimo = null;
            }
        } else {
            System.out.println("La lista está vacía");
        }
    }

    /**
     * @brief Retorna la fila en la posición indicada.
     * @param buscada Índice de la fila (0-based).
     * @return La fila como ListaSimple, o null si no existe.
     */
    public ListaSimple getFila(int buscada) {
        Nodo fila = inicio;
        int contador = 0;

        while (fila != null) {
            if (contador == buscada) {
                return fila.contenido;
            }
            fila = fila.sig;
            contador++;
        }

        return null;
    }

    /**
     * @brief Retorna una celda específica según fila y columna.
     * @param fil Índice de la fila.
     * @param col Índice de la columna.
     * @return La Celda correspondiente, o null si no existe.
     */
    public Celda getCelda(int fil, int col) {
        ListaSimple fila = getFila(fil);
        if (fila != null) {
            return fila.getCelda(col);
        }
        return null;
    }

    /**
     * @brief Imprime en consola todas las filas del tablero.
     */
    public void imprimirTablero() {
        Nodo temporal = inicio;
        int fila = 0;
        while (temporal != null) {
            System.out.println("Fila " + fila + ":");
            temporal.contenido.imprimirFila();
            temporal = temporal.sig;
            fila++;
        }
    }

    /**
     * @class Nodo
     * @brief Clase interna que representa un nodo doblemente enlazado en la lista.
     */
    private class Nodo {
        private ListaSimple contenido; /**< Contenido del nodo (una fila). */
        private Nodo ant;              /**< Nodo anterior. */
        private Nodo sig;              /**< Nodo siguiente. */

        /**
         * @brief Constructor del nodo.
         * @param contenido La fila almacenada en el nodo.
         */
        public Nodo(ListaSimple contenido) {
            this.contenido = contenido;
            ant = sig = null;
        }
    }
}
