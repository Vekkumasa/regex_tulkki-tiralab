package regex.domain;

public class Kaari {
    private Tila alku;
    private Tila loppu;
    
    public Kaari(Tila alku, Tila loppu) {
        this.alku = alku;
        this.loppu = loppu;
    }
    
    public Kaari() {
        
    }

    /**
     * @return the alku
     */
    public Tila getAlku() {
        return alku;
    }

    /**
     * @param alku the alku to set
     */
    public void setAlku(Tila alku) {
        this.alku = alku;
    }

    /**
     * @return the loppu
     */
    public Tila getLoppu() {
        return loppu;
    }

    /**
     * @param loppu the loppu to set
     */
    public void setLoppu(Tila loppu) {
        this.loppu = loppu;
    }
    
}
