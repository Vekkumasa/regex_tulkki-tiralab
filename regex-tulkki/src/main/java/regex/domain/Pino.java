package regex.domain;

public class Pino {
    private int taulukko[];
    private int paallimmainen;
    private int koko;
    
    public Pino(int koko) {
        this.koko = koko;
        taulukko = new int[koko];
        paallimmainen = -1;
    }
    
    public void push(int luku) {
        if (onkoTaynna()) {
            int uusiTaulukko[] = new int[taulukko.length * 2];
            koko *= 2;
            for (int i = 0; i < taulukko.length; i++) {
                uusiTaulukko[i] = taulukko[i];
                taulukko = uusiTaulukko;
            }
        }
        
        taulukko[++paallimmainen] = luku;
    }
    
    public boolean onkoTaynna() {
        boolean t = paallimmainen == koko ? true : false;
        return t;
    }
    
    public boolean onkoTyhja() {
        boolean t = paallimmainen == -1 ? true : false;
        return t;
    }
}
