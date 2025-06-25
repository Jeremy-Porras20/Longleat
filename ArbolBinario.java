import java.util.ArrayList;

public class ArbolBinario
{
    private Nodo raiz;

    public ArbolBinario()
    {
        raiz = null;       
    }

    public Nodo agregar(int valor){
        return agregar(valor,raiz);
    }

    public Nodo agregar(int valor, Nodo nodo){
        Nodo nuevo = nodo;

        if (nodo != null){

            if ( nodo.valor > valor){
                nodo.hijoIzq =  agregar(valor, nodo.hijoIzq );
            }
            else if(nodo.valor< valor){
                nodo.hijoDer = agregar(valor, nodo.hijoDer);
            }

        }
        else {
            nuevo = new Nodo(valor);
            if (raiz == null)
                raiz = nuevo;
        }

        return nuevo;
    }
    
    public ArrayList<Integer> recorrerArbol(){
        ArrayList<Integer>  valores = new ArrayList<>();
        return recorrerArbol(raiz, valores);
    }
    
    public ArrayList<Integer> recorrerArbol(Nodo nodo, ArrayList<Integer> lista){
        ArrayList<Integer> valores= lista ;
        if (nodo != null){
            recorrerArbol(nodo.hijoIzq, lista);
            valores.add(nodo.valor);
            recorrerArbol(nodo.hijoDer, lista);
        }
        return valores;
    }

    public boolean existe(int elemento){
        boolean existe = false;
        ArrayList<Integer> valores = recorrerArbol();
        for (int valor : valores){
            if (valor == elemento){
                existe = true;
            }
        }
        return existe;
    }

    public Nodo buscarMenor(Nodo nodo){

        while(nodo.hijoIzq != null){
            nodo = nodo.hijoIzq;
        }

        return nodo;
    }

    public int sumarValores(){
        return sumarValores(this.raiz);
    }

    public int sumarValores(Nodo nodo){

        if (nodo != null){
            return nodo.valor + sumarValores(nodo.hijoIzq) + sumarValores(nodo.hijoDer);
        }
        else {
            return 0;
        }

    }

    public void imprimir(){
        imprimir(this.raiz);
    }

    private void imprimir(Nodo nodo){
        if (nodo != null){
            imprimir(nodo.hijoIzq);
            System.out.println(nodo.valor + " ");
            imprimir(nodo.hijoDer);
        }
    }

    private class Nodo{
        private int valor;
        private Nodo hijoIzq;
        private Nodo hijoDer;

        public Nodo(int valor){
            this.valor = valor;
            hijoIzq = hijoDer = null;
        }

        public int getValor(){
            return valor;
        }

    }
}