package regex.tietorakenteet;

import java.util.Arrays;

public class ArrayList<T> {
    private int koko = 10;    
    private T[] lista;
    private int osoittaja;
    
    public ArrayList() {
        this.lista = (T[]) new Object[this.koko];
        this.osoittaja = 0;
    }
    
    public void add(T alkio) {
        if (onkoTaynna()) {
            tuplaaListanKoko();
        }
        this.lista[osoittaja++] = alkio;
    }
    
    public void tuplaaListanKoko() {
        this.koko = this.getKoko() * 2;
        T[] uusiLista = (T[]) new Object[this.getKoko()];
        for (int i = 0; i < this.lista.length; i++) {
            uusiLista[i] = lista[i];
        }
        this.lista = uusiLista;
    }
    
    public boolean onkoTaynna() {
        return this.osoittaja == this.getKoko();
    }
    
    public T get(int i) {
        return lista[i];
    }

    /**
     * @return the koko
     */
    public int getKoko() {
        return koko;
    }
    
    public int size() {
        return osoittaja;
    }
}
