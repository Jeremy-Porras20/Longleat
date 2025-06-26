import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @class InterfazMenu
 * @brief Representa la interfaz gráfica del menú principal del juego.
 *        Incluye botones para acceder al historial y para iniciar el juego.
 */
public class InterfazMenu {

    private JFrame ventana; /**< Ventana principal del menú */

    /**
     * @brief Constructor de la clase InterfazMenu.
     *        Crea y configura la ventana del menú con dos botones: "Historial" y "Jugar".
     */
    public InterfazMenu() {
        ventana = new JFrame("Menu");
        ventana.setLayout(null);
        ventana.setBounds(730, 240, 400, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setResizable(false);

        Color color = new Color(64, 64, 64);
        ventana.getContentPane().setBackground(color);

        JButton botonHistorial = new JButton("Historial");
        JButton botonJugar = new JButton("Jugar");

        Color colorBorde = new Color(128, 0, 128);

        botonHistorial.setBounds(90, 150, 100, 50);
        botonHistorial.setBackground(Color.WHITE);
        botonHistorial.setBorder(new LineBorder(colorBorde, 2, false));

        botonJugar.setBounds(210, 150, 100, 50);
        botonJugar.setBackground(Color.WHITE);
        botonJugar.setBorder(new LineBorder(colorBorde, 2, false));

        botonJugar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String nombre1 = pedirNombreJugador(1);
                    String nombre2 = pedirNombreJugador(2);
                    int tamanio = pedirTamanio();
                    Jugador jugador1 = new Jugador(nombre1);
                    Jugador jugador2 = new Jugador(nombre2);

                    new InterfazDeJuego(jugador1, jugador2, tamanio);
                }
            });

        ventana.add(botonHistorial);
        ventana.add(botonJugar);
    }

    /**
     * @brief Solicita al usuario el nombre de un jugador.
     * @param i Número de jugador (1 o 2).
     * @return Nombre ingresado.
     */
    public static String pedirNombreJugador(int i) {
        String nombre = "";
        while (nombre == null || nombre.trim().isEmpty()) {
            nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del jugador:", "Nombre del Jugador " + i, JOptionPane.QUESTION_MESSAGE);
        }
        return nombre.trim();
    }

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
     * @brief Crea una nueva ventana para iniciar el juego.
     *        (Método aún no implementado completamente).
     */
    public void ventanaInicioJuego() {
        JFrame ventana = new JFrame("Inicio Juego");
        // Aquí se puede continuar el diseño de la ventana de inicio del juego
    }

}
