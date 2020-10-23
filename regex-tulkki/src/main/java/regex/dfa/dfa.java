package regex.dfa;

import regex.domain.*;
import regex.nfa.nfa;

import regex.tietorakenteet.Pino;
import regex.tietorakenteet.Lista;
import regex.tietorakenteet.HajautusTaulu;

public class dfa {
    private nfa nfa;
    private HajautusTaulu<Integer, dfaTila> dfa_tilat_avaimina;
    private HajautusTaulu<dfaTila, Integer> dfa_setit_avaimina;
    private int tila;
    private String syote;
    private Lista<Character> kirjaimet;
    private int indeksi = 0;
    private Pino<Integer> pino;
    private dfaTila ekaDfaTila;
    private dfaTila[] dfaLista;
    private Lista<Tila> array;
    
    public dfa(nfa nfa, String syote) {
        this.nfa = nfa;
        this.dfa_tilat_avaimina = new HajautusTaulu<>();
        this.dfa_setit_avaimina = new HajautusTaulu<>();
        this.pino = new Pino();
        this.array = new Lista();
        // Numeroi dfa tilat
        this.tila = 1;
        // Lisätään syötteen eteen 1 mikä tahansa merkki tarkistus metodin indeksoinnin helpottamiseksi
        this.syote = "#" + syote;
        this.kirjaimet = nfa.getKirjaimet();
        this.ekaDfaTila = new dfaTila(this.tila);
        this.dfaLista = new dfaTila[nfa.getKaari().getLoppu().getTila() + 1];
    
        etsiJaLiitaEpsilonit(nfa.getKaari().getAlku(), ekaDfaTila);
        nollaaVierailut(nfa.getKaari().getAlku());
        
        Lista<Tila> tilat = ekaDfaTila.getNfaTilat();
        for (int i = 0; i < tilat.size(); i++) {
            Tila tila = tilat.get(i);
                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                    ekaDfaTila.setHyvaksyvaTila(true);
                }
        }
        
        dfa_tilat_avaimina.put(tila, ekaDfaTila);
        dfa_setit_avaimina.put(ekaDfaTila, tila);
        dfaLista[tila] = ekaDfaTila;
    }
    
    public void luoDfa() {
        pino.push(tila);
        
        while (!pino.onkoTyhja()) {
            int luku = pino.pop();
            dfaTila currentDfa = dfaLista[luku];
            
            array = currentDfa.getNfaTilat();
           
            if (currentDfa.getKasitelty()) {
                continue;
            }
            
            for (int i = 0; i < array.size(); i++) {
                Tila t = array.get(i);
                for (int k = 0; k < this.kirjaimet.size(); k++) {
                    Character c = this.kirjaimet.get(k);
                    if (t.getSiirtyma() == c) {
                        dfaTila uusi = luoDfaTila(t.getKaari().getLoppu());
                        currentDfa.lisaaSiirtyma(c, uusi.getTila());
                    }
                }
            }
            currentDfa.setKasitelty();
        }
    }
    
    /**
     * Etsitään vanha dfatila hashmapista, mikäli sellaista ei löydy tehdään uusi
     * dfa tila ja tarkistetaan onko se hyväksyvä tila
     * @param t
     * @return 
     */
    private dfaTila luoDfaTila(Tila t) {
        this.tila++;
        dfaTila uusi = new dfaTila(this.tila);
        
        etsiJaLiitaEpsilonit(t, uusi);
        nollaaVierailut(t);
        
        Lista<Tila> tilat = uusi.getNfaTilat();
        
        if (dfa_setit_avaimina.containsKey(uusi) != null) {
            uusi = dfaLista[dfa_setit_avaimina.get(uusi)];
            this.tila--;
            return uusi;     
        } else {
            for (int i = 0; i < tilat.size(); i++) {
                Tila tila = tilat.get(i);
                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                    uusi.setHyvaksyvaTila(true);
                }
            }
            
            dfaLista[this.tila] = uusi;
            this.pino.push(this.tila);
            dfa_setit_avaimina.put(uusi, this.tila);
            dfa_tilat_avaimina.put(this.tila, uusi);
        }
        
        return uusi;
    }
    
    /**
     * Metodi etsii rekursiivisesti kaikki seuraavat tilat mihin päästään käyttämättä yhtään merkkiä,
     * lisätään ne tilat hashsettiin ja dfaTila luokkaan
     * @param tila
     * @param siirtyma
     * @param tilat
     * @param dfaTila 
     */
    
    private void etsiJaLiitaEpsilonit(Tila tila, dfaTila dfaTila) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        
        tila.setVierailtu(true);
        dfaTila.lisaaTila(tila);
        
        if (tila.getSiirtyma() == ' ') {
            etsiJaLiitaEpsilonit(tila.getSeuraava(), dfaTila);
            etsiJaLiitaEpsilonit(tila.getSeuraava2(), dfaTila);
        }
    }
    
    /**
     * etsiJaLiitaEpsilonit metodin jäljiltä pitää vierailut asettaa takaisin falseksi
     * @param tila 
     */

    private void nollaaVierailut(Tila tila) {
        if (tila == null || tila.isVierailtu() == false) {
            return;
        }
        
        tila.setVierailtu(false);
        
        nollaaVierailut(tila.getSeuraava());
        nollaaVierailut(tila.getSeuraava2());
    }
    
    /**
     * Tarkistetaan täsmääkö syöte annettuun lausekkeeseen
     * DFA tilassa talletetaan siirtymät HashMappiin ja jos ei halutulla merkillä löydy
     * siirtymää niin palautetaan arvo -1. Jos saadaan arvo -1 ollaan päädytty tilaan mistä ei ole siirtymää
     * enää seuraavaan tilaan joten palautetaan suoraan false
     * @return 
     */
    
    public boolean tarkista() {       
        //Pidetään kirjaa missä DFA tilassa ollaan
        int currentState = 1;
        
        if (syote.length() == 1) {
            if (dfaLista[currentState].isHyvaksyvaTila()) {
                return true;
            }
        } 
        for (int i = 1; i < syote.length(); i++) {          
            if (i == syote.length() -1) {
                currentState = dfaLista[currentState].getSiirtyma((int)syote.charAt(i));
                
                if (currentState == -1) {
                    return false;
                }
                
                // Syöte on luettu loppuun ja currentstate arvo muutettu viimeiseen tilaan
                // mikäli tila on hyväksyvä, hyväksytään syöte
                
                if (dfaLista[currentState].isHyvaksyvaTila()) {
                    return true;
                }
                
                // Syötettä ei ole luettu loppuun joten täytyy päivittää currentstate ja tarkistaa
                // että tilasta löytyy siirtymä
            } else {
                currentState = dfaLista[currentState].getSiirtyma((int)syote.charAt(i));
                if (currentState == -1) {
                    return false;
                }     
            }
        }
        
        return false;
    }

    public dfaTila[] getDfaLista() {
        return dfaLista;
    }
    
    public void setSyote(String syote) {
        this.syote = "#" + syote;
    }
}
