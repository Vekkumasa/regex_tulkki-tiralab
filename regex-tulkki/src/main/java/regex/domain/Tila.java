package regex.domain;

// Tilat ovat NFA tiloja.
// Tiloilla voi olla korkeintaan 2 seuraavaa tilaa mihin sieltä päästään
// Tiloista voi päästä seuraavaan käyttämällä yhden siirtymä merkin
// Tai epsilon siirtymällä käyttämättä yhtään merkkiä syötteestä.

public class Tila {
    private int tila;
    private Tila seuraava;
    private Tila seuraava2;
    private Kaari kaari;
    private boolean hyvaksyva_tila;
    private boolean vierailtu;
    private char siirtyma;
    
    public Tila(int tila) {
        this.tila = tila;
        this.hyvaksyva_tila = false;
        this.kaari = new Kaari();
        this.vierailtu = false;
        this.siirtyma = ' ';
    }

    /**
     * @return the tila
     */
    public int getTila() {
        return tila;
    }

    /**
     * @param tila the tila to set
     */
    public void setTila(int tila) {
        this.tila = tila;
    }

    /**
     * @return the seuraava
     */
    public Tila getSeuraava() {
        return seuraava;
    }

    /**
     * @param seuraava the seuraava to set
     */
    public void setSeuraava(Tila seuraava) {
        this.seuraava = seuraava;
    }
    
    public Tila getSeuraava2() {
        return seuraava2;
    }

    /**
     * @param seuraava the seuraava to set
     */
    public void setSeuraava2(Tila seuraava2) {
        this.seuraava2 = seuraava2;
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
     * @return the vierailtu
     */
    public boolean isVierailtu() {
        return vierailtu;
    }

    /**
     * @param vierailtu the vierailtu to set
     */
    public void setVierailtu(boolean vierailtu) {
        this.vierailtu = vierailtu;
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

    @Override
    public boolean equals(Object verrattava) {
        if (this == verrattava) {
            return true;
        }
        
        if (!(verrattava instanceof Tila)) {
            return false;
        }
        
        Tila uusi = (Tila) verrattava;
        
        if (uusi.getTila() == this.tila) {
            return true;
        }
        
        return false;
    }

}
