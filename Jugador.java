
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int puntos;
    private List<int[]> recorrido; 
    private List<int[]> recorridoBonificacion;
    private int ganadas;
    private int perdidas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.ganadas = 0;
        this.perdidas = 0;
        this.recorrido = new ArrayList<>();
        this.recorridoBonificacion = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void restarPuntos(int puntos) {
        this.puntos -= puntos;
    }

    public void agregarCoordenada(int fila, int columna) {
        recorrido.add(new int[]{fila, columna});
        recorridoBonificacion.add(new int[]{fila, columna});
    }

    public List<int[]> getRecorrido() {
        return recorrido;
    }
    
    public List<int[]> getRecorridoBonificacion() {
        return recorridoBonificacion;
    }

    public void incrementarGanadas() {
        ganadas++;
    }

    public void incrementarPerdidas() {
        perdidas++;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void resetearPartida() {
        puntos = 0;
        recorrido.clear();
    }
}
