

public class Nodo
{   
    private Celda celda;
    private Nodo siguiente;
    
    
    public Nodo()
    {
       celda = new Celda();
       siguiente = null;
    }
    
    public Celda getCelda(){
        return this.celda;
    }
   
    public Nodo getSiguiente(){
        return this.siguiente;
    }
    
    public void setSiguiente(Nodo nuevo){
        siguiente = nuevo;
    }
    
}