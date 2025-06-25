import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Juego
{
    Tablero tablero;
    Jugador jugador1, jugador2;
    Jugador jugadorActual;
    int tamanio;
    int filaActual;
    int columnaActual;
    boolean juegoTerminado;

    public Juego(int tamanio, Jugador jugador1, Jugador jugador2)
    {

        tablero = new Tablero(tamanio);
        this.tamanio = tamanio;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        this.filaActual = this.columnaActual = 0;
        juegoTerminado = false;
    }

    public boolean hacerMovimiento(int fila, int col){
        if (juegoTerminado){
            return false;}

        if (!validarMovimiento(fila,col)){
            return false;}

        activarCelda(fila, col);

        if (fila == tamanio - 1 && col == tamanio - 1){
            juegoTerminado = true;
            return true;
        }
        aplicarBonificacion(jugadorActual);    
        filaActual = fila;  
        columnaActual = col;

        cambiarTurno();

        return true;
    }

    public boolean validarMovimiento(int fil, int col){
        boolean valido = false;
        int nuevaFila = fil - filaActual;
        int nuevaColumna = col - columnaActual;
        if (nuevaFila == 1 && nuevaColumna == 0 ||
        nuevaFila == 0 && nuevaColumna == 1 ||
        nuevaFila == 1 && nuevaColumna == 1 ){
            valido = true;
        }

        return valido;
    }

    public void activarCelda(int fila, int col){
        Celda celda = tablero.getCelda(fila, col);
        int puntos = celda.activar(jugadorActual.getNombre());

        jugadorActual.sumarPuntos(puntos);
        jugadorActual.agregarCoordenada(fila, col);
    }

    private void cambiarTurno(){
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }

    public boolean hayGanador(){
        boolean hayGanador = false;
        if (juegoTerminado){
            hayGanador = true;
        }
        return hayGanador;
    }

    public void aplicarBonificacion(Jugador jugador){
        int[] todosPositivos = {0, 1, 2, 3 , 4, 5, 6};
        int[] todosNegativos = {-5, -4, -3, -2, -1};
        int[] todosLosValores = {-5, -4, -3, -2, -1, 0, 1, 2, 3 , 4, 5, 6};

        List<Integer> valores = new ArrayList<>();
        List<int []> recorrido = jugador.getRecorridoBonificacion();

        for (int[] coord : recorrido){
            Celda celda = tablero.getCelda(coord[0],coord[1]);
            valores.addAll(celda.getArbol().recorrerArbol());
        }

        if (contieneLosValores(todosPositivos, valores)){
            jugador.sumarPuntos(-15);
            recorrido.clear();
        }

        if (contieneLosValores(todosNegativos, valores)){
            jugador.sumarPuntos(100);
            recorrido.clear();
        }

        if (contieneLosValores(todosLosValores, valores)){
            jugador.sumarPuntos(10);
            recorrido.clear();
        }
    }

    private boolean contieneLosValores(int[] lista, List<Integer> valores){
        Set<Integer> valoresUnicos = new HashSet<>(valores); // hacemos que no hayan valores duplicados
        for (int numero : lista){
            if (!valoresUnicos.contains(numero)){
                return false;
            }
        }
        return true;
    }

    public Jugador obtenerGanador() {
        
        if (juegoTerminado == true){
            aplicarBonificacion(jugador1);
            aplicarBonificacion(jugador2);

            if (jugador1.getPuntos() > jugador2.getPuntos()) {
                jugador1.incrementarGanadas();
                jugador2.incrementarPerdidas();
                return jugador1;
            } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
                jugador2.incrementarGanadas();
                jugador1.incrementarPerdidas();
                return jugador2;
            }

            return null;// empate
        }
        return null;
    }
    
    public Tablero getTablero(){
        return this.tablero;
    }
    
    public Jugador getJugadorActual(){
        return this.jugadorActual;
    }
    
    public Jugador getJugador1(){
        return this.jugador1;
    }
    
    public Jugador getJugador2(){
        return this.jugador2;
    }
    
    public boolean getJuegoTerminado(){
        return this.juegoTerminado;
    }

}