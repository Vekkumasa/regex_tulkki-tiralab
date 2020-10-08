package regex.nfa;

import java.util.HashSet;

import regex.domain.Kaari;
import regex.domain.Tila;
import regex.tietorakenteet.Pino;

public class nfa {
    private Pino<Kaari> pino;
    private String lauseke;
    private regexToPostfix rtp;
    private nfaFragmentit fragmentit;
    private Kaari kaari;
    private HashSet<Character> kirjaimet;
    
    public nfa(String lauseke) {
        this.rtp = new regexToPostfix();
        lauseke = rtp.luoPostfix(lauseke);
        this.lauseke = lauseke;
        this.fragmentit = new nfaFragmentit();
        this.pino = new Pino();
        this.kirjaimet = new HashSet();
    }
    
    public void luoNfa() {
        Kaari seuraava, seuraava2;
        
        // Luetaan kaikki merkit lausekkeesta ja toimitaan sen mukaisesti
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
                    pino.push(fragmentit.luoKirjainTila(c));
                    kirjaimet.add(c);
            }
        }
        this.kaari = pino.pop();
        this.kaari.getLoppu().setHyvaksyva_tila(true);
    }
    
    public Kaari getKaari() {
        return this.kaari;
    }
    
    public HashSet<Character> getKirjaimet() {
        return this.kirjaimet;
    }
    
    /**
     * Pelkkää printtailua tiloista ja siirtymistä ym
     * @param tila 
     */
    public void faktatTiskiin(Tila tila) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        tila.setVierailtu(true);
        
        if (tila.getSiirtyma() != ' ') {
            System.out.println("Tilan " + tila.getTila() + " siirtyma seuraavaan tilaan " + (tila.getTila() +1) + " on: " + tila.getSiirtyma());
            faktatTiskiin(tila.getKaari().getLoppu());
        }
        if (tila.getSeuraava() != null) {
            System.out.println("Tilan " + tila.getTila() + " seuraava tila on: " + tila.getSeuraava().getTila());
            faktatTiskiin(tila.getSeuraava());
        } else {
     //       System.out.println("Tilan " + tila.getTila() + " seuraava on tyhjä");
        }
        if (tila.getSeuraava2() != null) {
            System.out.println("Tilan " + tila.getTila() + " seuraava2 tila on: " + tila.getSeuraava2().getTila());
            faktatTiskiin(tila.getSeuraava2());
        } else {
     //       System.out.println("Tilan " + tila.getTila() + " seuraava KAKSI on tyhjä");
        }        
    }
    
    public String getLauseke() {
        return this.lauseke;
    }
}
