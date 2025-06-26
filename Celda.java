import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * @class Celda
 * @brief Representa una celda del tablero que contiene un árbol binario de números únicos,
 *        un botón gráfico, y puede ser visitada por un jugador para obtener puntos.
 */
public class Celda {
    
    private ArbolBinario arbol;             /**< Árbol binario que contiene los valores de la celda. */
    private boolean visitada;               /**< Indica si la celda ya ha sido visitada. */
    private int puntos;                     /**< Puntos que contiene la celda. */
    private String jugadorVisitante;        /**< Nombre del jugador que visitó la celda. */
    private JButton boton;                  /**< Botón gráfico asociado a la celda. */

    /**
     * @brief Constructor de la clase Celda.
     *        Inicializa el árbol binario con valores aleatorios únicos y crea el botón.
     */
    public Celda() {
        this.arbol = new ArbolBinario();
        this.visitada = false;
        this.puntos = 0;
        this.jugadorVisitante = "";
        llenarArbol();
        puntos = arbol.sumarValores();
        crearBoton();
    }

    /**
     * @brief Crea e inicializa el botón asociado a la celda.
     */
    public void crearBoton() {
        String puntuacion = Integer.toString(puntos);
        boton = new JButton(puntuacion);
        Color color = new Color(0, 0, 0);
        boton.setBackground(color);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
    }

    /**
     * @brief Llena el árbol binario con una cantidad aleatoria de valores únicos entre -5 y 6.
     */
    private void llenarArbol() {
        Random random = new Random();
        ArrayList<Integer> valores = new ArrayList<>();
        int cantidad = 5 + random.nextInt(6); // entre 5 y 10 valores

        System.out.println("cantidad:" + cantidad);

        while (valores.size() < cantidad) {
            int numero = -5 + random.nextInt(12); // valores entre -5 y 6
            if (!existe(numero, valores)) {
                valores.add(numero);
            }
        }

        for (int valor : valores) {
            System.out.println(valor);
            arbol.agregar(valor);
        }
    }

    /**
     * @brief Verifica si un valor ya existe en la lista.
     * @param elemento Valor a verificar.
     * @param valores Lista de valores existentes.
     * @return true si el valor existe, false en caso contrario.
     */
    public boolean existe(int elemento, ArrayList<Integer> valores) {
        boolean existe = false;
        for (int valor : valores) {
            if (valor == elemento) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * @brief Activa la celda para un jugador si aún no ha sido visitada.
     * @param nombreJugador Nombre del jugador que activa la celda.
     * @return Los puntos contenidos en la celda.
     */
    public int activar(String nombreJugador) {
        if (!visitada) {
            visitada = true;
            jugadorVisitante = nombreJugador;
            puntos = arbol.sumarValores();
        }
        return puntos;
    }

    /**
     * @brief Muestra en una ventana los valores almacenados en el árbol de la celda.
     */
    public void mostrarValoresDelArbol() {
        ArrayList<Integer> lista = arbol.recorrerArbol();
        String elementos = String.join("\n", "[" + lista + "]");
        JOptionPane.showMessageDialog(null, elementos, "Elementos del árbol", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @brief Verifica si la celda ya fue visitada.
     * @return true si fue visitada, false en caso contrario.
     */
    public boolean esVisitada() {
        return visitada;
    }

    /**
     * @brief Obtiene el nombre del jugador que visitó la celda.
     * @return Nombre del jugador visitante.
     */
    public String getJugadorVisitante() {
        return jugadorVisitante;
    }

    /**
     * @brief Obtiene los puntos actuales de la celda.
     * @return Puntos de la celda.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @brief Obtiene el árbol binario de la celda.
     * @return Objeto ArbolBinario asociado.
     */
    public ArbolBinario getArbol() {
        return arbol;
    }

    /**
     * @brief Obtiene el botón gráfico de la celda.
     * @return Botón Swing asociado a la celda.
     */
    public JButton getBoton() {
        return boton;
    }
}
