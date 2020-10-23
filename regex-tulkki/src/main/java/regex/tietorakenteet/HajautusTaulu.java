package regex.tietorakenteet;

/**
 * HajautusTaulu korvaa HashMap tietorakenteen
 * Yhteentörmäykset ketjutetaan Lista rakenteeseen.
 * @author ylira
 * @param <K>
 * @param <V> 
 */
public class HajautusTaulu<K, V> {

    private Lista<Pari<K, V>>[] taulu;
    private int alkioita;

    public HajautusTaulu() {
        this.taulu = new Lista[32];
        this.alkioita = 0;
    }
    
    /**
     * Etsitään taulusta avaimella löytyvää arvoa, jos ei löydy
     * palautetaan defaultValue
     * @param avain
     * @param defaultValue
     * @return 
     */
    public V getOrDefault(Object avain, V defaultValue) {
        int hash = Math.abs(avain.hashCode() % this.taulu.length);
        if (this.taulu[hash] == null) {
            return defaultValue;
        }
        
        Lista<Pari<K, V>> indeksi = this.taulu[hash];

        for (int i = 0; i < indeksi.size(); i++) {
            if (indeksi.get(i).getAvain().equals(avain)) {
                return indeksi.get(i).getArvo();
            }
        }
        return defaultValue;
    }
    
    /**
     * Etsitään taulusta avaimella arvoa ja palautetaan se.
     * Null palautetaan jos ei arvoa löydy avaimella
     * @param avain
     * @return 
     */
    public V get(K avain) {
        int hash = Math.abs(avain.hashCode() % this.taulu.length);
        if (this.taulu[hash] == null) {
            return null;
        }

        Lista<Pari<K, V>> indeksi = this.taulu[hash];

        for (int i = 0; i < indeksi.size(); i++) {
            if (indeksi.get(i).getAvain().equals(avain)) {
                return indeksi.get(i).getArvo();
            }
        }
        return null;
    }
    
    /**
     * Tarkisteaan löytyykö kyseistä avainta listasta
     * @param avain
     * @return 
     */
    public K containsKey(K avain) {
        int hash = Math.abs(avain.hashCode() % this.taulu.length);
        if (this.taulu[hash] == null) {
            return null;
        } else {
            Lista<Pari<K, V>> indeksi = this.taulu[hash];
            for (int i = 0; i < indeksi.size(); i++) {
                if (indeksi.get(i).getAvain().equals(avain)) {
                    return avain;
                }
            }
        }
        return null;
    }
    
    /**
     * Lisätään tauluun Avain-Arvo pari
     * Jos täyttösuhde ylittää 0.75, tuplataan taulukon koko
     * @param avain
     * @param arvo 
     */
    public boolean put(K avain, V arvo) {
        Lista<Pari<K, V>> lista = haeLista(avain);
        int i = haeAvaimenIndeksi(lista, avain);
        
        if (i < 0) {
            Pari<K, V> uusiPari = new Pari<>(avain, arvo);
            lista.add(uusiPari);
            this.alkioita++;
            
            if (this.alkioita / this.taulu.length > 0.75) {
            kasvataTaulunKokoa();
        }
            return true;
        } else {
            lista.get(i).setArvo(arvo);
            return false;
        }   
    }
    
    /**
     * Etsitään avainta vastaava lista
     * @param avain
     * @return 
     */
    
    private Lista<Pari<K, V>> haeLista(K avain) {
        int hash = Math.abs(avain.hashCode() % taulu.length);
        if (taulu[hash] == null) {
            taulu[hash] = new Lista<>();
        }
        return taulu[hash];
    }
    
    /**
     * Etsitään listasta avaimeen liittyvä indeksi
     * Jos ei listassa ole kyseistä avainta, palautetaan -1
     * @param lista
     * @param avain
     * @return 
     */
  
    private int haeAvaimenIndeksi(Lista<Pari<K, V>> lista, K avain) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getAvain().equals(avain)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Kasvatetaan taulun kokoa ja kopioidaan sen sisältö
     */
    private void kasvataTaulunKokoa() {
        Lista<Pari<K, V>>[] uusi = new Lista[this.taulu.length * 2];

        for (int i = 0; i < this.taulu.length; i++) {
            kopioi(uusi, i);
        }
        
        this.taulu = uusi;
    }
    
    /**
     * Kopioidaan taulun sisältö. 
     * Tarkistetaan ensin että taulun indeksiin on lisätty lista.
     * Lista käydään läpi kokonaisuudessaan ja kopioidaan arvot uuteen listaan
     * @param uusi
     * @param indeksista 
     */
    
    private void kopioi(Lista<Pari<K, V>>[] uusi, int indeksista) {
        if (this.taulu[indeksista] != null) {
            for (int i = 0; i < this.taulu[indeksista].size(); i++) {
                Pari<K, V> arvo = this.taulu[indeksista].get(i);

                int hash = Math.abs(arvo.getAvain().hashCode() % uusi.length);
                if (uusi[hash] == null) {
                    uusi[hash] = new Lista<>();
                }
                uusi[hash].add(arvo);
            }
        } 
    }
}