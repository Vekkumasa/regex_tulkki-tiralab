package regex.nfa;

import regex.domain.Tila;
import regex.domain.Kaari;

public class nfaFragmentit {
    
    // Pidetään kirjaa tilan järjestysluvusta
    private int tilat;
    
    public nfaFragmentit() {
        this.tilat = 0;
    }
    
    // Luokan metodit luovat uusia tiloja tai liittävät aiemmin luotuja
    // tiloja toisiin tiloihin
    // Metodien nimet kertovat varmasti riittävästi mitä täällä tapahtuu
    // Eli enempää kommentteja ei ole odotettavissa
    
    public Kaari luoKirjainTila(char c) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        Kaari kaari = new Kaari(alku, loppu);
        alku.setKaari(kaari);
        alku.setSiirtyma(c);
        
        alku.setSeuraava(loppu);
        
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


    public Kaari luoTahtiTila(Kaari edellinen) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        Kaari kaari = new Kaari(alku, loppu);
        
        alku.setSeuraava(edellinen.getAlku());
        alku.setSeuraava2(loppu);
        
        edellinen.getLoppu().setSeuraava(loppu);
        edellinen.getLoppu().setSeuraava2(edellinen.getAlku());
        
        edellinen.setAlku(alku);
        edellinen.setLoppu(loppu);
        
        return kaari;
    }
  
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
  
    public Kaari luoKonkatenaatioPisteTila(Kaari vasen, Kaari oikea) {
        Kaari kaari = new Kaari(vasen.getAlku(), oikea.getLoppu());
        
        vasen.getLoppu().setSeuraava(oikea.getAlku());
        return kaari;
    }

    
}
