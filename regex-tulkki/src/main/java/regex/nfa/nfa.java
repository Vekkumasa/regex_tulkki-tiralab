package regex.nfa;

import regex.domain.Kaari;
import regex.tietorakenteet.Pino;
import regex.tietorakenteet.Lista;

public class nfa {
    private Pino<Kaari> pino;
    private String lauseke;
    private regexToPostfix rtp;
    private nfaFragmentit fragmentit;
    private Kaari kaari;
    private Lista<Character> kirjaimet;
    
    public nfa(String lauseke) {
        this.rtp = new regexToPostfix();
        lauseke = rtp.luoPostfix(lauseke);
        this.lauseke = lauseke;
        this.fragmentit = new nfaFragmentit();
        this.pino = new Pino();
        this.kirjaimet = new Lista();
    }
    
    /**
     * Metodissa käydään lauseke läpi merkki kerrallaan ja luodaan
     * merkkiä vastaava nfafragmentti. 
     * Lopputuloksena saadaan yksi Kaari olio minkä alkutilasta
     * on reitti lopputilaan, joko epsilon siirtymiä tai "normaaleja" siirtymiä
     * käyttämällä.
     */
    
    public void luoNfa() {
        Kaari seuraava, seuraava2;
        
        for (int i = 0; i < lauseke.length(); i++) {
            char c = lauseke.charAt(i);
            switch (c) {
                case '|':
                    seuraava2 = pino.pop();
                    seuraava = pino.pop();
                    pino.push(fragmentit.luoTaiTila(seuraava, seuraava2)); 
                    break;
                    
                case '*':
                    seuraava = pino.pop();
                    pino.push(fragmentit.luoTahtiTila(seuraava));
                    break;
                    
                case '+':
                    seuraava = pino.pop();
                    pino.push(fragmentit.luoPlusTila(seuraava));
                    break;
                    
                case '.':
                    seuraava2 = pino.pop();
                    seuraava = pino.pop();
                    pino.push(fragmentit.luoKonkatenaatioPisteTila(seuraava, seuraava2));
                    break;
                    
                default:
                    boolean onkoKirjainListassa = false;
                    for (int k = 0; k < this.kirjaimet.size(); k++) {
                        if (this.kirjaimet.get(k).equals(c)) {
                            onkoKirjainListassa = true;
                            break;
                        }
                    }
                    if (onkoKirjainListassa == false) {
                        kirjaimet.add(c);
                    }
                    pino.push(fragmentit.luoKirjainTila(c));
                    onkoKirjainListassa = false;
            }
        }
        this.kaari = pino.pop();
        this.kaari.getLoppu().setHyvaksyva_tila(true);
    }
    
    public Kaari getKaari() {
        return this.kaari;
    }
    
    /**
     * Dfa:lle tarvitaan mahdolliset kirjaimet mitä käytetään siirtymissä
     * ilman duplikaatteja.
     * @return 
     */
    public Lista<Character> getKirjaimet() {
        return this.kirjaimet;
    }
    
    public String getLauseke() {
        return this.lauseke;
    }
}
