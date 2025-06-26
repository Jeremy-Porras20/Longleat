/**
 * @class Nodo
 * @brief Representa un nodo de una lista simplemente enlazada que contiene una celda del juego.
 */
public class Nodo {
    private Celda celda;        /**< Celda almacenada en este nodo. */
    private Nodo siguiente;     /**< Referencia al siguiente nodo en la lista. */

    /**
     * @brief Constructor por defecto. 
     *        Crea una nueva celda y apunta a null como siguiente nodo.
     */
    public Nodo() {
        celda = new Celda();
        siguiente = null;
    }

    /**
     * @brief Obtiene la celda contenida en este nodo.
     * @return La celda.
     */
    public Celda getCelda() {
        return this.celda;
    }

    /**
     * @brief Obtiene el nodo siguiente en la lista.
     * @return El siguiente nodo.
     */
    public Nodo getSiguiente() {
        return this.siguiente;
    }

    /**
     * @brief Asigna un nuevo nodo como siguiente.
     * @param nuevo El nodo que será el siguiente de este nodo.
     */
    public void setSiguiente(Nodo nuevo) {
        siguiente = nuevo;
    }
}
