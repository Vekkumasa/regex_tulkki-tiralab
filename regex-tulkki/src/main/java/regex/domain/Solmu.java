package regex.domain;

public class Solmu {
    private int solmu;
    private Kaari kaari;
    private boolean hyvaksyva_tila;
    
    public Solmu(int solmu, boolean hyvaksyva_tila) {
        this.solmu = solmu;
        this.hyvaksyva_tila = hyvaksyva_tila;
        this.kaari = new Kaari();
    }

    /**
     * @return the solmu
     */
    public int getSolmu() {
        return solmu;
    }

    /**
     * @param solmu the solmu to set
     */
    public void setSolmu(int solmu) {
        this.solmu = solmu;
    }

    /**
     * @return the hyvaksyva_tila
     */
    public boolean isHyvaksyva_tila() {
        return hyvaksyva_tila;
    }

    /**
     * @param hyvaksyva_tila the hyvaksyva_tila to set
     */
    public void setHyvaksyva_tila(boolean hyvaksyva_tila) {
        this.hyvaksyva_tila = hyvaksyva_tila;
    }

    /**
     * @return the kaari
     */
    public Kaari getKaari() {
        return kaari;
    }

    /**
     * @param kaari the kaari to set
     */
    public void setKaari(Kaari kaari) {
        this.kaari = kaari;
    }
}
