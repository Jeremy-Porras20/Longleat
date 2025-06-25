import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;



public class Celda
{
    private ArbolBinario arbol;
    private boolean visitada;
    private int puntos;
    private String jugadorVisitante;
    private JButton boton;
    
    public Celda()
    {
        this.arbol = new ArbolBinario();
        this.visitada = false;
        this.puntos = 0;
        this.jugadorVisitante = "";
        llenarArbol();
        puntos = arbol.sumarValores();
        crearBoton();
    }
    
    public void crearBoton(){
        String puntuacion = Integer.toString(puntos);
        boton = new JButton(puntuacion);
        Color color = new Color(0,0,0);
        boton.setBackground(color);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
    }

    private void llenarArbol(){
        Random random = new Random();
        ArrayList<Integer> valores = new ArrayList<>();
        int cantidad = 5 + random.nextInt(6);
        System.out.println("cantidad:" + cantidad);
        
        while (valores.size() < cantidad){
            int numero = -5 + random.nextInt(12);
            if (!existe(numero, valores)){
                valores.add(numero);
            }
            
            
        }
        
        for (int valor : valores){
            
            System.out.println(valor);
            arbol.agregar(valor);
        }
    
    }
    
    public boolean existe(int elemento, ArrayList<Integer> valores ){
        for (int valor : valores){
            if (valor == elemento){
                return true;
            }
        }
        
        return false;
    }
    
    public int activar(String nombreJugador){
        if (!visitada){
            visitada = true;
            jugadorVisitante = nombreJugador;
            puntos = arbol.sumarValores();
        }
        return puntos;
    }
    
    public void mostrarValoresDelArbol() {
        ArrayList<Integer> lista = arbol.recorrerArbol();
        String elementos = String.join("\n", "[" + lista + "]");
        JOptionPane.showMessageDialog(null, elementos, "Elementos del arbol", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
     public boolean esVisitada() {
        return visitada;
    }

    public String getJugadorVisitante() {
        return jugadorVisitante;
    }

    public int getPuntos() {
        return puntos;
    }

    public ArbolBinario getArbol() {
        return arbol;
    }
    
    public JButton getBoton(){
        return boton;
    }
    
}