package regex.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class dfaTila {
    private ArrayList<Tila> nfaTilat;
    private HashMap<Integer, Integer> siirtymat;
    private boolean hyvaksyvaTila;
    private boolean kasitelty;
    private int tila;
    
    public dfaTila(int tila) {
        this.nfaTilat = new ArrayList();
        this.siirtymat = new HashMap();
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
    
    public String printtaa() {
        for (int i = 0; i < this.getNfaTilat().size(); i++) {
            System.out.println(this.getNfaTilat().get(i).getTila());
        }
        return "";
    }

    /**
     * @return the nfaTilat
     */
    public ArrayList<Tila> getNfaTilat() {
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