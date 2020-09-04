package regex.domain;

public class Solmu {
    private int solmu;
    private int seuraava;
    private String siirtyma;
    private boolean hyvaksyva_tila;
    
    public Solmu(int solmu, int seuraava, String siirtyma, boolean hyvaksyva_tila) {
        this.solmu = solmu;
        this.seuraava = solmu;
        this.siirtyma = siirtyma;
        this.hyvaksyva_tila = hyvaksyva_tila;
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
     * @return the seuraava
     */
    public int getSeuraava() {
        return seuraava;
    }

    /**
     * @param seuraava the seuraava to set
     */
    public void setSeuraava(int seuraava) {
        this.seuraava = seuraava;
    }

    /**
     * @return the siirtyma
     */
    public String getSiirtyma() {
        return siirtyma;
    }

    /**
     * @param siirtyma the siirtyma to set
     */
    public void setSiirtyma(String siirtyma) {
        this.siirtyma = siirtyma;
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
}
