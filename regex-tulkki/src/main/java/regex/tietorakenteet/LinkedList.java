package regex.tietorakenteet;

public class LinkedList<T> {
    private Solmu alku;
    
    public LinkedList() {
        this.alku = null;
    }
    
    public void add(Solmu seuraava) {
        if (alku == null) {
            alku = seuraava;
        } else if (alku.getSeuraava() == null) {
            alku.setSeuraava(seuraava);
        } else {
            Solmu s = alku.getSeuraava();
            while (s.getSeuraava() != null) {
                s = s.getSeuraava();
            }
            s.setSeuraava(seuraava);
        }
    }
    
    public Solmu get(Solmu solmu) {      
        if (alku == null) {
            return new Solmu(-1);
        } else if (alku == solmu) {
            return alku;
        } else if (alku.getSeuraava() == solmu) {
            return alku.getSeuraava();
        } else {
            Solmu
            s = alku.getSeuraava();
            while (s.getSeuraava() != solmu) {
                s = s.getSeuraava();
            }
            if (s.getSeuraava() == solmu) {
                return s.getSeuraava();
            } else {
                return new Solmu(-1);
            }
            
        }
    }
    
    public Solmu getData(T data) {
        if (alku == null) {
            return null;
        } else if (alku.getData() == data) {
            return alku;
        } else if (alku.getSeuraava().getData() == data) {
            return alku.getSeuraava();
        } else {
            Solmu s = alku.getSeuraava();            
            while (s.getSeuraava() != null) {
                s = s.getSeuraava();
                if (s.getData() == data) {
                    return s;
                } 
            }
        }
        return null;
    }
    
    public void remove(Solmu solmu) {
        if (alku == solmu) {
            alku = alku.getSeuraava();
        } else if (alku.getSeuraava() == solmu) {
            Solmu s = alku.getSeuraava();
            alku.setSeuraava(s.getSeuraava());
        } else {
            Solmu s = alku.getSeuraava();
            while (s.getSeuraava() != solmu && s.getSeuraava() != null) {
                s = s.getSeuraava();
            }
            if (s.getSeuraava() == solmu) {
                Solmu poistettava = s.getSeuraava();
                s.setSeuraava(poistettava.getSeuraava());
            }
        }
    }
}
