import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InterfazDeJuego 
{
    private JFrame ventana;    
    private JPanel tablero;
    private Juego juego;
    private JLabel dJ1;
    private JLabel datosJugador2;
    
    public InterfazDeJuego(Jugador jugador1, Jugador jugador2 , int tamanio)
    {
        juego = new Juego(tamanio, jugador1, jugador2);
        ventana = new JFrame("Juego");
        ventana.setLayout(null);
        ventana.setBounds(550,90,800,800);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        
        JLabel dJ1 = new JLabel(juego.getJugador1().getNombre());;
        dJ1.setBounds(0,0,200,100);
        dJ1.setBackground(Color.GREEN);
        ventana.add(dJ1);

        Celda celda = juego.getTablero().getCelda(0,0);
        System.out.println(celda.getPuntos());

        tablero = new JPanel();
        tablero.setBounds(450,0,1000,1000);
        ventana.add(tablero);

        ventana.add(tablero, BorderLayout.CENTER);
        tablero.setLayout(new GridLayout(tamanio, tamanio));
        for (int i = 0 ; i < tamanio ; i++ ){
            for (int j = 0; j < tamanio; j++){
                JButton boton = juego.getTablero().getCelda(i,j).getBoton();
                boton.addActionListener(new CeldaListener(i,j,juego,boton,this));
                tablero.add(boton);
            }
        }

        ventana.add(tablero);

    }

    private class CeldaListener implements ActionListener{
        private int fila;
        private int columna;
        private Juego juego;
        private JButton boton;
        private InterfazDeJuego interfaz;

        public CeldaListener(int fila, int columna, Juego juego, JButton boton, InterfazDeJuego interfaz) {
            this.fila = fila;
            this.columna = columna;
            this.juego = juego;
            this.boton = boton;
            this.interfaz = interfaz;
        }

        public void actionPerformed(ActionEvent e){
            boolean valido = juego.hacerMovimiento(fila, columna);

            if (valido) {
                Jugador actual = juego.getJugadorActual();
                Celda celda = juego.getTablero().getCelda(fila, columna);

                // Actualizar botón visualmente
                if (juego.getJugadorActual().getNombre().equals(juego.jugador1.getNombre())){
                    boton.setBackground(Color.GREEN);
                }
                else{
                    boton.setBackground(Color.RED);
                }

                celda.mostrarValoresDelArbol();

                boton.setText(String.valueOf(celda.getPuntos()));
                boton.setEnabled(false); // para que no se vuelva a presionar

                // Verificar si el juego terminó
                if (juego.getJuegoTerminado()) {
                    Jugador ganador = juego.obtenerGanador();
                    JOptionPane.showMessageDialog(null,
                        "¡Juego terminado!\nGanador: " + ganador.getNombre() +
                        "\nPuntos: " + ganador.getPuntos());
                }

                // Actualizar GUI si fuera necesario
                interfaz.actualizar(); // si tenés un método así
            } else {
                JOptionPane.showMessageDialog(null, "Movimiento inválido.");
            }
        }
    }

    public void actualizar(){
        ventana.repaint();
    }

    public static void main(String [] args){
        Jugador jugador1 = new Jugador("jeremy");
        Jugador jugador2 = new Jugador("emily");
        InterfazDeJuego interfaz = new InterfazDeJuego(jugador1, jugador2, 10);

    }
}