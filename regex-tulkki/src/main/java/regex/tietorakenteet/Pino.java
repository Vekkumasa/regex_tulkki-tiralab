package regex.tietorakenteet;

public class Pino<T> {
    private T[] taulukko;
    private int paallimmainen;
    private int koko;
    
    public Pino(int koko) {
        this.koko = koko;
        taulukko = (T[]) new Object[this.koko];
        paallimmainen = -1;
    }
    
    public Pino() {
        this.koko = 20;
        taulukko = (T[]) new Object[this.koko];
        paallimmainen = -1;
    }
    
    public void push(T luku) {
        if (onkoTaynna()) {
            this.koko *= 2;
            T[] uusiTaulukko = (T[]) new Object[this.koko];          
            for (int i = 0; i < taulukko.length; i++) {
                uusiTaulukko[i] = taulukko[i];
                taulukko = uusiTaulukko;
            }
        }
        taulukko[++paallimmainen] = luku;
    }
    
    public boolean onkoTaynna() {
        boolean t = paallimmainen == koko -1 ? true : false;
        return t;
    }
    
    public boolean onkoTyhja() {
        boolean t = paallimmainen == -1 ? true : false;
        return t;
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
}
