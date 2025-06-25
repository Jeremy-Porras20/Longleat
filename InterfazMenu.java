import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class InterfazMenu
{
    private JFrame ventana;

       
    public InterfazMenu()
    {
        ventana = new JFrame("Menu");
        ventana.setLayout(null);
        ventana.setBounds(730,240,400,400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setResizable(false);
        
        Color color = new Color(64,64,64);
        ventana.getContentPane().setBackground(color);
        
        
        JButton botonHistorial = new JButton("Historial"); 
        JButton botonJugar = new JButton("Jugar");
        
        Color colorBorde = new Color(128,0,128);
        
        botonHistorial.setBounds(90,150,100,50);
        botonHistorial.setBackground(Color.WHITE);
        botonHistorial.setBorder(new LineBorder(colorBorde, 2, false));
        
        botonJugar.setBounds(210,150,100,50);
        botonJugar.setBackground(Color.WHITE);
        botonJugar.setBorder(new LineBorder(colorBorde, 2, false));
        
        ventana.add(botonHistorial);
        ventana.add(botonJugar);
    }

    
    public void ventanaInicioJuego(){
        JFrame ventana = new JFrame("Inicio Juego");
        
        
    }
   
}