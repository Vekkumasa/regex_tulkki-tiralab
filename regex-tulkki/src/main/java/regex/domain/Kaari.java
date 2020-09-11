package regex.domain;

public class Kaari {
    private Solmu alku;
    private Solmu loppu;
    private char siirtyma;
    
    public Kaari(Solmu alku, Solmu loppu, char siirtyma) {
        this.alku = alku;
        this.loppu = loppu;
        this.siirtyma = siirtyma;
    }
    
    public Kaari() {
        
    }

    /**
     * @return the alku
     */
    public Solmu getAlku() {
        return alku;
    }

    /**
     * @param alku the alku to set
     */
    public void setAlku(Solmu alku) {
        this.alku = alku;
    }

    /**
     * @return the loppu
     */
    public Solmu getLoppu() {
        return loppu;
    }

    /**
     * @param loppu the loppu to set
     */
    public void setLoppu(Solmu loppu) {
        this.loppu = loppu;
    }

    /**
     * @return the siirtyma
     */
    public char getSiirtyma() {
        return siirtyma;
    }

    /**
     * @param siirtyma the siirtyma to set
     */
    public void setSiirtyma(char siirtyma) {
        this.siirtyma = siirtyma;
    }
    
    
}
