package regex.tietorakenteet;

/**
 * Omatekoiseen HashMap (HajautusTaulu) tietorakenteeseen talletetaan tiedot
 * Avain-Arvo pareina
 * @author ylira
 * @param <K>
 * @param <V> 
 */
public class Pari<K, V> {
    private K avain;
    private V arvo;
    
    public Pari(K avain, V arvo) {
        this.avain = avain;
        this.arvo = arvo;
    }

    /**
     * @return the avain
     */
    public K getAvain() {
        return avain;
    }

    /**
     * @param avain the avain to set
     */
    public void setAvain(K avain) {
        this.avain = avain;
    }

    /**
     * @return the arvo
     */
    public V getArvo() {
        return arvo;
    }

    /**
     * @param arvo the arvo to set
     */
    public void setArvo(V arvo) {
        this.arvo = arvo;
    }
}
