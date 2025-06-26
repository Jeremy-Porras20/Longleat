import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @class InterfazDeJuego
 * @brief Clase que representa la interfaz gráfica del juego.
 *        Muestra el tablero, la información de los jugadores y gestiona la interacción del usuario.
 */
public class InterfazDeJuego {

    private JFrame ventana;               /**< Ventana principal del juego */
    private JPanel tablero;              /**< Panel que contiene la cuadrícula del tablero */
    private Juego juego;                 /**< Lógica del juego */
    private JPanel dJ;                   /**< Panel lateral con información del jugador */
    private JLabel ptsJugador1;          /**< Etiqueta con los puntos del jugador 1 */
    private JLabel ptsJugador2;          /**< Etiqueta con los puntos del jugador 2 */
    private JButton botonTurnoActual;    /**< Botón indicador del turno actual */

    /**
     * @brief Constructor de la interfaz gráfica.
     * @param jugador1 Jugador 1.
     * @param jugador2 Jugador 2.
     * @param tamanio Tamaño del tablero (NxN).
     */
    public InterfazDeJuego(Jugador jugador1, Jugador jugador2 , int tamanio) {
        juego = new Juego(tamanio, jugador1, jugador2);
        ventana = new JFrame("Juego");
        ventana.setLayout(null);
        ventana.setBounds(550, 90, 800, 800);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        Color color = new Color(240, 240, 235);
        dJ = new JPanel();
        dJ.setBounds(0, 0, 450, 1000);
        dJ.setLayout(null);
        dJ.setBackground(color);
        ventana.add(dJ);

        panelInformacion();

        tablero = new JPanel();
        tablero.setBounds(450, 0, 1000, 1000);
        tablero.setLayout(new GridLayout(tamanio, tamanio));

        for (int i = 0 ; i < tamanio ; i++) {
            for (int j = 0; j < tamanio; j++) {
                JButton boton = juego.getTablero().getCelda(i,j).getBoton();
                boton.addActionListener(new CeldaListener(i, j, juego, boton, this));

                // Marcar esquinas especiales
                if (juego.getTablero().getCelda(i,j) == juego.getTablero().getCelda(0,0)
                    || juego.getTablero().getCelda(i,j) == juego.getTablero().getCelda(tamanio-1,tamanio-1)) {
                    boton.setText("");
                    boton.setBackground(Color.YELLOW);
                }

                tablero.add(boton);
            }
        }

        ventana.add(tablero);
    }

    /**
     * @brief Crea y configura el panel con la información de los jugadores.
     */
    public void panelInformacion() {
        JLabel titulo = new JLabel("Jugadores");
        titulo.setBounds(150, 50, 250, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 21));
        dJ.add(titulo);

        JLabel nombreJ1 = new JLabel("Jugador 1: " + juego.getJugador1().getNombre().toUpperCase());
        nombreJ1.setBounds(120, 150, 250, 40);
        nombreJ1.setFont(new Font("Arial", Font.BOLD, 18));
        dJ.add(nombreJ1);

        JLabel nombreJ2 = new JLabel("Jugador 2: " + juego.getJugador2().getNombre().toUpperCase());
        nombreJ2.setBounds(120, 210, 250, 40);
        nombreJ2.setFont(new Font("Arial", Font.BOLD, 18));
        dJ.add(nombreJ2);

        JButton botonJ1 = new JButton();
        botonJ1.setBounds(330, 150, 40, 40);
        botonJ1.setBackground(Color.RED);
        dJ.add(botonJ1);

        JButton botonJ2 = new JButton();
        botonJ2.setBounds(330, 210, 40, 40);
        botonJ2.setBackground(Color.GREEN);
        dJ.add(botonJ2);

        JLabel turnoActual = new JLabel("Turno Actual:");
        turnoActual.setBounds(125, 420, 250, 50);
        turnoActual.setFont(new Font("Arial", Font.BOLD, 22));
        dJ.add(turnoActual);

        this.botonTurnoActual = new JButton();
        botonTurnoActual.setBounds(300, 420, 40, 40);
        botonTurnoActual.setBackground(Color.RED);
        dJ.add(botonTurnoActual);

        String ptsJ1 = Integer.toString(juego.getJugador1().getPuntos());
        ptsJugador1 = new JLabel(juego.getJugador1().getNombre().toUpperCase() + ": " + ptsJ1 + " puntos");
        ptsJugador1.setBounds(120, 560, 300, 40);
        ptsJugador1.setFont(new Font("Arial", Font.BOLD, 20));
        dJ.add(ptsJugador1);

        String ptsJ2 = Integer.toString(juego.getJugador2().getPuntos());
        ptsJugador2 = new JLabel(juego.getJugador2().getNombre().toUpperCase() + ": " + ptsJ2 + " puntos");
        ptsJugador2.setBounds(120, 620, 300, 40);
        ptsJugador2.setFont(new Font("Arial", Font.BOLD, 20));
        dJ.add(ptsJugador2);
    }

    /**
     * @brief Actualiza visualmente el botón que indica el turno actual.
     */
    public void actualizarBotonTurnoActual() {
        if (juego.getJugadorActual().getNombre().equals(juego.jugador1.getNombre())) {
            botonTurnoActual.setBackground(Color.RED);
        } else {
            botonTurnoActual.setBackground(Color.GREEN);
        }
        botonTurnoActual.repaint();
    }

    /**
     * @brief Actualiza las etiquetas con los puntos actuales de los jugadores.
     */
    public void actualizarPuntos() {
        String ptsJ1 = Integer.toString(juego.getJugador1().getPuntos());
        ptsJugador1.setText(juego.getJugador1().getNombre().toUpperCase() + ": " + ptsJ1 + " puntos");

        String ptsJ2 = Integer.toString(juego.getJugador2().getPuntos());
        ptsJugador2.setText(juego.getJugador2().getNombre().toUpperCase() + ": " + ptsJ2 + " puntos");

        ptsJugador1.repaint();
        ptsJugador2.repaint();
    }

    /**
     * @brief Actualiza la interfaz gráfica completa.
     */
    public void actualizar() {
        ventana.repaint();
        actualizarBotonTurnoActual();
        actualizarPuntos();
    }

    

    /**
     * @brief Solicita el tamaño del tablero al usuario.
     * @return Tamaño del tablero entre 10 y 35.
     */
    public static int pedirTamanio() {
        String input = "";
        int tamanio = 0;

        while (tamanio <= 0 || (tamanio > 35 || tamanio < 10)) {
            input = JOptionPane.showInputDialog(null, "Ingrese el tamaño del tablero: (Debe ser entre 10 y 35)", "Tamaño de Tablero", JOptionPane.QUESTION_MESSAGE);
            try {
                tamanio = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                tamanio = 0;
            }
        }
        return tamanio;
    }


    /**
     * @class CeldaListener
     * @brief Clase interna que maneja los eventos al hacer clic en una celda del tablero.
     */
    private class CeldaListener implements ActionListener {
        private int fila;                      /**< Fila de la celda */
        private int columna;                   /**< Columna de la celda */
        private Juego juego;                   /**< Lógica del juego */
        private JButton boton;                 /**< Botón correspondiente a la celda */
        private InterfazDeJuego interfaz;      /**< Referencia a la interfaz gráfica */

        /**
         * @brief Constructor del listener.
         */
        public CeldaListener(int fila, int columna, Juego juego, JButton boton, InterfazDeJuego interfaz) {
            this.fila = fila;
            this.columna = columna;
            this.juego = juego;
            this.boton = boton;
            this.interfaz = interfaz;
        }

        /**
         * @brief Maneja el evento de clic en una celda.
         */
        public void actionPerformed(ActionEvent e) {
            boolean valido = juego.hacerMovimiento(fila, columna);

            if (valido) {
                Jugador actual = juego.getJugadorActual();
                Celda celda = juego.getTablero().getCelda(fila, columna);

                if (actual.getNombre().equals(juego.jugador1.getNombre())) {
                    boton.setBackground(Color.GREEN);
                } else {
                    boton.setBackground(Color.RED);
                }

                celda.mostrarValoresDelArbol();
                boton.setText(String.valueOf(celda.getPuntos()));
                boton.setEnabled(false);

                if (juego.getJuegoTerminado()) {
                    Jugador ganador = juego.obtenerGanador();

                    int opcion = JOptionPane.showConfirmDialog(null,
                        "¡Juego terminado!\nGanador: " + ganador.getNombre() +
                        "\nPuntos: " + ganador.getPuntos() +
                        "\n\n¿Jugar de nuevo?",
                        "Juego terminado",
                        JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                        int tamanio = pedirTamanio();
                        new InterfazDeJuego(juego.getJugador1(), juego.getJugador2(), tamanio);
                    }

                    ventana.dispose();
                }

                interfaz.actualizar();
            } else {
                JOptionPane.showMessageDialog(null, "Movimiento inválido.");
            }
        }
    }
}
