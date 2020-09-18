package regex.nfa;

import regex.domain.Tila;
import regex.domain.Kaari;

public class nfaFragmentit {
    
    private int tilat;
    
    public nfaFragmentit() {
        this.tilat = 0;
    }
    
    public Kaari luoKirjainTila(char c) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        alku.setSeuraava(loppu);
        
        Kaari kaari = new Kaari(alku, loppu);
        kaari.setSiirtyma(c);
        
        return kaari;
    }
    
    public Kaari luoTaiTila(Kaari seuraava, Kaari seuraava2) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        Kaari kaari = new Kaari(alku, loppu);
        
        kaari.getAlku().setSeuraava(seuraava.getAlku());
        kaari.getAlku().setSeuraava2(seuraava2.getAlku());
        
        seuraava.getLoppu().setSeuraava(loppu);
        seuraava2.getLoppu().setSeuraava(loppu);
        
        return kaari;
    }
/*    
    public Kaari luoTaiTila(Kaari seuraava, Kaari seuraava2) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
    
        Kaari kaari = new Kaari(alku, loppu);
        
        kaari.getAlku().setSeuraava(seuraava.getAlku());
        kaari.getAlku().setSeuraava2(seuraava2.getAlku());
        
        seuraava.getLoppu().setSeuraava(loppu);
        seuraava2.getLoppu().setSeuraava(loppu);
        
        return kaari;
    }
*/    
    public Kaari luoTahtiTila(Kaari edellinen) {
        edellinen.getAlku().setSeuraava(edellinen.getAlku());
        edellinen.getAlku().setSeuraava2(edellinen.getLoppu());  
        
        edellinen.getLoppu().setSeuraava(edellinen.getAlku());
        
        return edellinen;
    }
/*    
    public Kaari luoPlusTila(Kaari edellinen) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        Kaari kaari = new Kaari(alku, loppu);
        
        alku.setSeuraava(edellinen.getAlku());
        
        edellinen.getLoppu().setSeuraava(alku);
        edellinen.getLoppu().setSeuraava2(loppu);
        
        edellinen.setAlku(alku);
        edellinen.setLoppu(loppu);
        
        return kaari;
    }
*/ 
    public Kaari luoPlusTila(Kaari edellinen) {
        edellinen.getAlku().setSeuraava(edellinen.getAlku());
        edellinen.getAlku().setSeuraava2(null);
        
        edellinen.getLoppu().setSeuraava(edellinen.getAlku());
        edellinen.getLoppu().setSeuraava2(edellinen.getLoppu());
        
        return edellinen;
    }
    
    public Kaari luoKonkatenaatioPisteTila(Kaari vasen, Kaari oikea) {
        Kaari kaari = new Kaari(vasen.getLoppu(), oikea.getAlku());
        
        vasen.getLoppu().setSeuraava(oikea.getAlku());
        return kaari;
    }

    
}
