
public class ListaDoble
{
    private Nodo inicio;
    private Nodo ultimo;

    public ListaDoble()
    {
        inicio = ultimo = null;
    }

    public void agregarFila(ListaSimple elemento){
        Nodo nuevo = new Nodo(elemento);
        if (inicio != null){
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        }
        else {
            inicio = ultimo = nuevo;
        }
    }

    public void eliminar(){

        if (ultimo != null){
            Nodo penultimo = ultimo.ant;
            ultimo.ant = null;
            penultimo.sig = null; 
            ultimo = penultimo;
        }

        else {
            System.out.println("la lista esta vacia");
        }
    }
    
    public ListaSimple getFila(int buscada){
        Nodo fila = inicio;
        int contador = 0;
        
        while(fila != null){
            if (contador == buscada){
                return fila.contenido;
            }
            fila = fila.sig;
            contador++;
        }
        
        return null;
    }
    
    public Celda getCelda(int fil, int col){
        ListaSimple fila = getFila(fil);
        if (fila != null){
            return fila.getCelda(col);
        }
        return null;
    }

     public void imprimirTablero() {
        Nodo temporal = inicio;
        int fila = 0;
        while (temporal != null) {
            System.out.println("Fila " + fila + ":");
            temporal.contenido.imprimirFila();
            temporal = temporal.sig;
            fila++;
        }
    }

    private class Nodo{
        private ListaSimple contenido;
        private Nodo ant;
        private Nodo sig;

        public Nodo(ListaSimple contenido){
            this.contenido = contenido;
            ant = sig = null;
        }
        
        

    }
}