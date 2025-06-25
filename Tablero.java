
public class Tablero
{
    private ListaDoble tablero;
    private int tamanio;

    public Tablero(int tamanio)
    {
        this.tamanio = tamanio;
        this.tablero = new ListaDoble();
        inicializartablero();
    }

    public void inicializartablero(){
        for (int i = 0; i < tamanio ; i++){
            ListaSimple fila = new ListaSimple();
            for (int j = 0; j < tamanio; j++){
                fila.agregarCelda();
            }
            tablero.agregarFila(fila);
        }
    }
    
    public Celda getCelda(int fila, int columna){
        return tablero.getCelda(fila,columna);
    }
    
    public int getTamanio(){
        return this.tamanio;
    }
    
    
    public void imprimirTablero(){
        tablero.imprimirTablero();
    }
}