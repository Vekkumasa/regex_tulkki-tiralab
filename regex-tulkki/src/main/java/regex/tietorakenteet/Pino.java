package regex.tietorakenteet;

/**
 * Pino tietorakenne
 * @author ylira
 * @param <T> 
 */

public class Pino<T> {
    private T[] taulukko;
    private int paallimmainen;
    private int koko;
    
    public Pino() {
        this.koko = 10;
        taulukko = (T[]) new Object[this.koko];
        paallimmainen = -1;
    }
    
    /**
     * Tallenetaan alkio pinon päällimmäiseksi
     * Mikäli pino on täynnä, kasvatetaan pinon kokoa.
     * @param luku 
     */
    public void push(T luku) {
        if (onkoTaynna()) {
            this.koko = this.koko * 2;
            T[] uusiTaulukko = (T[]) new Object[this.koko];          
            for (int i = 0; i < taulukko.length; i++) {
                uusiTaulukko[i] = taulukko[i];
                this.taulukko = uusiTaulukko;
            }
        }
        taulukko[++paallimmainen] = luku;
    }
    
    public boolean onkoTaynna() {
        if (this.paallimmainen == this.koko -1) {
            return true;
        } else {   
            return false;
        }
    }
    
    public boolean onkoTyhja() {
        if (this.paallimmainen == -1) {
            return true;
        } else {   
            return false;
        }
    }
    
    public T pop() {
        if (onkoTyhja()) {
            return null;
        }
        return taulukko[paallimmainen--];
    }
    
    public T peek() {
        if (onkoTyhja()) {
            return null;
        }
        return taulukko[paallimmainen];
    }
    
    public int size() {
        return this.paallimmainen + 1;
    }
    
    public int getKoko() {
        return this.koko;
    }
}
