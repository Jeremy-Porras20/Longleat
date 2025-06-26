import java.util.ArrayList;

/**
 * @class ArbolBinario
 * @brief Implementa un árbol binario de búsqueda para almacenar enteros.
 */
public class ArbolBinario {
    private Nodo raiz; /**< Nodo raíz del árbol. */

    /**
     * @brief Constructor que inicializa un árbol vacío.
     */
    public ArbolBinario() {
        raiz = null;
    }

    /**
     * @brief Agrega un valor al árbol binario.
     * @param valor Valor a insertar.
     * @return Nodo insertado o modificado.
     */
    public Nodo agregar(int valor) {
        return agregar(valor, raiz);
    }

    /**
     * @brief Método recursivo auxiliar para agregar un valor.
     * @param valor Valor a insertar.
     * @param nodo Nodo actual.
     * @return Nodo actualizado.
     */
    public Nodo agregar(int valor, Nodo nodo) {
        Nodo nuevo = nodo;

        if (nodo != null) {
            if (nodo.valor > valor) {
                nodo.hijoIzq = agregar(valor, nodo.hijoIzq);
            } else if (nodo.valor < valor) {
                nodo.hijoDer = agregar(valor, nodo.hijoDer);
            }
        } else {
            nuevo = new Nodo(valor);
            if (raiz == null)
                raiz = nuevo;
        }
        return nuevo;
    }

    /**
     * @brief Obtiene una lista con los valores del árbol en orden.
     * @return Lista con valores en orden.
     */
    public ArrayList<Integer> recorrerArbol() {
        ArrayList<Integer> valores = new ArrayList<>();
        return recorrerArbol(raiz, valores);
    }

    /**
     * @brief Método recursivo que agrega valores en orden a una lista.
     * @param nodo Nodo actual.
     * @param lista Lista donde se agregan valores.
     * @return Lista con valores agregados.
     */
    public ArrayList<Integer> recorrerArbol(Nodo nodo, ArrayList<Integer> lista) {
        if (nodo != null) {
            recorrerArbol(nodo.hijoIzq, lista);
            lista.add(nodo.valor);
            recorrerArbol(nodo.hijoDer, lista);
        }
        return lista;
    }

    /**
     * @brief Verifica si un elemento existe en el árbol.
     * @param elemento Valor a buscar.
     * @return true si existe, false si no.
     */
    public boolean existe(int elemento) {
        ArrayList<Integer> valores = recorrerArbol();
        for (int valor : valores) {
            if (valor == elemento) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Busca el nodo con el menor valor a partir de un nodo dado.
     * @param nodo Nodo inicial para la búsqueda.
     * @return Nodo con el valor mínimo.
     */
    public Nodo buscarMenor(Nodo nodo) {
        while (nodo.hijoIzq != null) {
            nodo = nodo.hijoIzq;
        }
        return nodo;
    }

    /**
     * @brief Suma todos los valores almacenados en el árbol.
     * @return Suma total de los valores.
     */
    public int sumarValores() {
        return sumarValores(this.raiz);
    }

    /**
     * @brief Método recursivo para sumar valores a partir de un nodo.
     * @param nodo Nodo actual.
     * @return Suma de valores.
     */
    public int sumarValores(Nodo nodo) {
        if (nodo != null) {
            return nodo.valor + sumarValores(nodo.hijoIzq) + sumarValores(nodo.hijoDer);
        } else {
            return 0;
        }
    }

    /**
     * @brief Imprime los valores del árbol en orden.
     */
    public void imprimir() {
        imprimir(this.raiz);
    }

    /**
     * @brief Método recursivo auxiliar para imprimir los valores en orden.
     * @param nodo Nodo actual.
     */
    private void imprimir(Nodo nodo) {
        if (nodo != null) {
            imprimir(nodo.hijoIzq);
            System.out.println(nodo.valor + " ");
            imprimir(nodo.hijoDer);
        }
    }

    /**
     * @class Nodo
     * @brief Nodo que compone el árbol binario.
     */
    private class Nodo {
        private int valor;       /**< Valor almacenado en el nodo. */
        private Nodo hijoIzq;    /**< Hijo izquierdo. */
        private Nodo hijoDer;    /**< Hijo derecho. */

        /**
         * @brief Constructor que inicializa un nodo con un valor.
         * @param valor Valor a almacenar en el nodo.
         */
        public Nodo(int valor) {
            this.valor = valor;
            hijoIzq = hijoDer = null;
        }

        /**
         * @brief Obtiene el valor almacenado en el nodo.
         * @return Valor del nodo.
         */
        public int getValor() {
            return valor;
        }
    }
}
