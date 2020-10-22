package regex.tietorakenteet;

/**
 * Lista tietorakenne korvaa ArrayListin
 * @author ylira
 * @param <T> 
 */

public class Lista<T> {  
    private T[] taulukko;
    private int alkioita;
    
    public Lista() {
        this.taulukko = (T[]) new Object[10];
        this.alkioita = 0;
    }
    
    public void add(T alkio) {
        if(this.alkioita == this.taulukko.length) {
        kasvataListaa();
    }
        
        this.taulukko[this.alkioita] = alkio;
        this.alkioita++;
    }
    
    /**
     * Listan täyttyessä kasvatetaan listan kokoa kaksinkertaiseksi ja kopioidaan taulukko
     */
    public void kasvataListaa() {
        T[] uusi = (T[]) new Object[this.taulukko.length * 2];
        for (int i = 0; i < this.taulukko.length; i++) {
            uusi[i] = this.taulukko[i];
        }
  
        this.taulukko = uusi;
    }
    
    public T get(int indeksi) {   
        if (indeksi < 0 || indeksi >= this.alkioita) {
            throw new ArrayIndexOutOfBoundsException("Indeksi alueen ulkopuolella");
        }
        return this.taulukko[indeksi];
    }
    
    public int size() {
       return this.alkioita;
    }
    
    public T[] getTaulukko() {
        return this.taulukko;
    }
}
