package regex.domain;

import regex.tietorakenteet.Lista;
import regex.tietorakenteet.HajautusTaulu;

public class dfaTila {
    private Lista<Tila> nfaTilat;
    private HajautusTaulu<Integer, Integer> siirtymat;
    private boolean hyvaksyvaTila;
    private boolean kasitelty;
    private int tila;
    
    public dfaTila(int tila) {
        this.nfaTilat = new Lista();
        this.siirtymat = new HajautusTaulu();
        this.hyvaksyvaTila = false;
        this.kasitelty = false;
        this.tila = tila;
    }
    
    public void lisaaTila(Tila tila) {
        getNfaTilat().add(tila);
    }
    
    public int getTila() {
        return this.tila;
    }
    
    public void setKasitelty() {
        this.kasitelty = true;
    }
    
    public boolean getKasitelty() {
        return this.kasitelty;
    }
    
    public int getSiirtyma(int siirtyma) {
        return siirtymat.getOrDefault(siirtyma, -1);
    }
    
    public void lisaaSiirtyma(int siirtyma, int dfaTila) {
        this.siirtymat.put(siirtyma, dfaTila);
    }

    /**
     * @return the nfaTilat
     */
    public Lista<Tila> getNfaTilat() {
        return nfaTilat;
    }
    
    /**
     * @return the hyvaksyvaTila
     */
    public boolean isHyvaksyvaTila() {
        return hyvaksyvaTila;
    }

    /**
     * @param hyvaksyvaTila the hyvaksyvaTila to set
     */
    public void setHyvaksyvaTila(boolean hyvaksyvaTila) {
        this.hyvaksyvaTila = hyvaksyvaTila;
    }
    
}