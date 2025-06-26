/**
 * @class ListaSimple
 * @brief Representa una lista simplemente enlazada de celdas. 
 *        Cada nodo contiene una instancia de la clase Celda.
 */
public class ListaSimple {
    Nodo inicio;        /**< Primer nodo de la lista. */
    Nodo siguiente;     /**< (No usado directamente, se mantiene para compatibilidad). */
    int contador = -1;  /**< Contador general (no utilizado activamente). */

    /**
     * @brief Constructor que inicializa la lista vacía.
     */
    public ListaSimple() {
        inicio = siguiente = null;
    }

    /**
     * @brief Agrega una nueva celda al final de la lista.
     */
    public void agregarCelda() {
        Nodo nuevo = new Nodo();
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo temporal = inicio;
            while (temporal.getSiguiente() != null) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevo);
        }
    }

    /**
     * @brief Elimina la última celda de la lista.
     * @param nodo (No utilizado en el método, puede ser eliminado o ajustado).
     */
    public void eliminar(Nodo nodo) {
        if (inicio == null) {
            System.out.println("La lista está vacía");
        } else {
            Nodo temporal = inicio;
            while (temporal.getSiguiente().getSiguiente() != null) {
                temporal = temporal.getSiguiente().getSiguiente(); // Avanza de 2 en 2 (debe corregirse si es un bug)
            }
            temporal.setSiguiente(null);
        }
    }

    /**
     * @brief Obtiene una celda en una posición específica.
     * @param columna Índice de la columna deseada.
     * @return Celda en la posición indicada, o null si está fuera de rango.
     */
    public Celda getCelda(int columna) {
        Nodo temporal = inicio;
        int contador = 0;

        while (temporal != null) {
            if (contador == columna) {
                return temporal.getCelda();
            }
            temporal = temporal.getSiguiente();
            contador++;
        }

        return null; // columna fuera de rango
    }

    /**
     * @brief Imprime en consola el estado de todas las celdas en la fila.
     */
    public void imprimirFila() {
        Nodo temporal = inicio;
        int col = 0;

        while (temporal != null) {
            System.out.print("Col " + col + ": ");
            if (temporal.getCelda().esVisitada()) {
                System.out.print("[" + temporal.getCelda().getPuntos() + " puntos] ");
            } else {
                System.out.print("[No visitada]");
            }
            System.out.println();
            temporal = temporal.getSiguiente();
            col++;
        }
    }

    /**
     * @brief Retorna el primer nodo de la lista.
     * @return Nodo inicial.
     */
    public Nodo getInicio() {
        return inicio;
    }
}
