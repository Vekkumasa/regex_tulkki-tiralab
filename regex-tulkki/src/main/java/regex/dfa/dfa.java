package regex.dfa;

import regex.domain.*;
import regex.nfa.nfa;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

// Vielä täysin vaiheessa ja suunnittelun alla toteutus

public class dfa {
    private nfa nfa;
    private HashMap<Set<Tila>, Character> siirtymat;
    private int tila;
    private HashSet<Tila> alkutilat;
    private String syote;
    private Character[] kirjaimet;
    private int indeksi = 0;
    
    public dfa(nfa nfa, String syote) {
        this.nfa = nfa;
        this.siirtymat = new HashMap<>();
        this.tila = 'A';
        this.syote = syote;
        this.kirjaimet = new Character[nfa.getKirjaimet().size()];
        nollaaVierailut(nfa.getKaari().getAlku());
        alkutilat = new HashSet();
        etsiJaLiitaEpsilonit(nfa.getKaari().getAlku(), ' ', alkutilat);
        nollaaVierailut(nfa.getKaari().getAlku()); 
        for(Character c : nfa.getKirjaimet()) {
            kirjaimet[indeksi++] = c;
        }
    }
    
    public void luoDfa() {
        for (int i = 0; i < kirjaimet.length; i++) {
            HashSet<Tila> tilat = kayTilatLapi(alkutilat, kirjaimet[i]);
            siirtymat.put(tilat, kirjaimet[i]);
        }
    }
    
    public void etsiJaLiitaEpsilonit(Tila tila, char siirtyma, Set<Tila> tilat) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        
        tila.setVierailtu(true);
        tilat.add(tila);
        
        if (tila.getSiirtyma() == ' ') {
            etsiJaLiitaEpsilonit(tila.getSeuraava(), siirtyma, tilat);
            etsiJaLiitaEpsilonit(tila.getSeuraava2(), siirtyma, tilat);
        }
    }
    
    public void etsiJaLiitaKirjainSiirtymat(Tila tila, char siirtyma, Set<Tila> tilat) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        
        tila.setVierailtu(true);
        tilat.add(tila);
        
        if (tila.getSiirtyma() == siirtyma) {
            etsiJaLiitaEpsilonit(tila.getSeuraava(), siirtyma, tilat);
            etsiJaLiitaEpsilonit(tila.getSeuraava2(), siirtyma, tilat);
        }
    }
    
    public void nollaaVierailut(Tila tila) {
        if (tila == null || tila.isVierailtu() == false) {
            return;
        }
        
        tila.setVierailtu(false);
        
        nollaaVierailut(tila.getSeuraava());
        nollaaVierailut(tila.getSeuraava2());
    }
    
    public HashSet<Tila> kayTilatLapi(Set<Tila> setti, char c) {
        HashSet<Tila> temp = new HashSet();
        for(Tila tila : setti){
            etsiJaLiitaKirjainSiirtymat(tila, c , temp);
        }
        System.out.println("testi " + temp.size());
        return temp;
    }
}
