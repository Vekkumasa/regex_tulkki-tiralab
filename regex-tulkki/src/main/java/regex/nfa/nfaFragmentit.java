package regex.nfa;

import regex.domain.Tila;
import regex.domain.Kaari;

public class nfaFragmentit {
    
    // Pidetään kirjaa tilan järjestysluvusta
    private int tilat;
    
    public nfaFragmentit() {
        this.tilat = 1;
    }
    
    // Luokan metodit luovat uusia tiloja tai liittävät aiemmin luotuja
    // tiloja toisiin tiloihin
    
    // Luodaan "perus" fragmentti missä on 2 tilaa ja niiden välillä on siirtymä kirjaimella
    // tai numerolla
    
    public Kaari luoKirjainTila(char c) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        Kaari kaari = new Kaari(alku, loppu);
        alku.setKaari(kaari);
        alku.setSiirtyma(c);
        
        alku.setSeuraava(loppu);
        
        return kaari;
    }

    /**
     * Luodaan kaksi uutta tilaa ja asetetaan kaari näiden väliin
     * Pinosta otetaan kaksi päällimäistä ja yhdistetään ne uuteen luotuun tilaan
     * @param seuraava
     * @param seuraava2
     * @return 
     */
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
    
    /**
     * Tehdään uusi kaari.
     * Kaaresta voi siirtyä edeltävän alkuun tai loppuun
     * Edeltävän kaaren lopusta voi palata takaisin alkuun tai uuden kaaren loppuun
     * @param edellinen
     * @return 
     */
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
  
    // Sama kuin edellinen mutta edellisen alku tilasta ei ole muuta mahdollisuutta kuin käyttää siirtymä mennäkseen
    // seuraavaan (eli edellinen loppu tilaan) ja sieltä on taas mahdollisuus mennä alkuun jos haluaa
    
    /**
     * Tehdään uusi kaari.
     * Kaaresta voi siirtyä edeltävän alkuun.
     * Edeltävän kaaren lopusta voi siirtyä takaisin alkuun tai uuden luodun kaaren loppuun
     * @param edellinen
     * @return 
     */
    public Kaari luoPlusTila(Kaari edellinen) {
        Tila alku = new Tila(tilat++);
        Tila loppu = new Tila(tilat++);
        
        Kaari kaari = new Kaari(alku, loppu);
        
        alku.setSeuraava(edellinen.getAlku());
        
        edellinen.getLoppu().setSeuraava(edellinen.getAlku());
        edellinen.getLoppu().setSeuraava2(loppu);
        
        return kaari;
    }
  
    /**
     * Yhdistetään kaksi pinon päällimäistä kaarta toisiinsa
     * @param vasen
     * @param oikea
     * @return 
     */
    public Kaari luoKonkatenaatioPisteTila(Kaari vasen, Kaari oikea) {
        Kaari kaari = new Kaari(vasen.getAlku(), oikea.getLoppu());
        
        vasen.getLoppu().setSeuraava(oikea.getAlku());
        return kaari;
    }    
}
