public class ListaSimple
{
    Nodo inicio;
    Nodo siguiente;
    int contador = -1;
   
    public ListaSimple()
    {
        inicio = siguiente = null;
    }
    
    public void agregarCelda(){
        Nodo nuevo = new Nodo();
        if (inicio == null){
            inicio = nuevo;
        }
        else {
            Nodo temporalporal = inicio;
            while(temporalporal.getSiguiente() != null){
                temporalporal = temporalporal.getSiguiente();
            }
            
            temporalporal.setSiguiente(nuevo);
            
        }
    }
    
    public void eliminar(Nodo nodo){
        if (inicio == null){
            System.out.println("la lista esta vacia");
        }
        else {
            Nodo temporalporal = inicio;
            while(temporalporal.getSiguiente().getSiguiente() != null){
                temporalporal = temporalporal.getSiguiente().getSiguiente();
            }
            temporalporal.setSiguiente(null);
        }
        
        
        
    }
    
    public Celda getCelda(int columna) {
        Nodo temporal = inicio;
        int contador = 0;

        while (temporal != null) {
            if (contador == columna) {
                return temporal.getCelda();
            }
            temporal = temporal.getSiguiente();
            contador++;
        }

        return null; // columna fuera de rango
    }

    public void imprimirFila() {
        Nodo temporal = inicio;
        int col = 0;

        while (temporal != null) {
            System.out.print("Col " + col + ": ");
            if (temporal.getCelda().esVisitada()) {
                System.out.print("[" + temporal.getCelda().getPuntos() + " puntos] ");
            } else {
                System.out.print("[No visitada]");
            }
            System.out.println();
            temporal = temporal.getSiguiente();
            col++;
        }
    }
    
    
    public Nodo getInicio(){
        return inicio;
    }
    

}