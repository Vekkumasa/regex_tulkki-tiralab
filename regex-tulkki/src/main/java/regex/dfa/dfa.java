package regex.dfa;

import regex.domain.*;
import regex.nfa.nfa;

import java.util.HashSet;

import regex.tietorakenteet.Pino;
import regex.tietorakenteet.Lista;
import regex.tietorakenteet.HajautusTaulu;

public class dfa {
    private nfa nfa;
    private HajautusTaulu<Integer, HashSet<Tila>> dfa_tilat_avaimina;
    private HajautusTaulu<HashSet<Tila>, Integer> dfa_setit_avaimina;
    private int tila;
    private HashSet<Tila> alkutilat;
    private String syote;
    private Character[] kirjaimet;
    private int indeksi = 0;
    private Pino<Integer> pino;
    private dfaTila ekaDfaTila;
    private dfaTila[] dfaLista;
    private Lista<Tila> array;
    
    public dfa(nfa nfa, String syote) {
        this.nfa = nfa;
        // Liitetään toisiinsa kaikki NFA tilat (hashset) ja dfa tilat
        this.dfa_tilat_avaimina = new HajautusTaulu<>();
        this.dfa_setit_avaimina = new HajautusTaulu<>();
        this.pino = new Pino();
        this.array = new Lista();
        // Numeroi dfa tilat
        this.tila = 1;
        // Lisätään syötteen eteen 1 mikä tahansa merkki tarkistus metodin indeksoinnin helpottamiseksi
        this.syote = "#" + syote;
        this.kirjaimet = new Character[nfa.getKirjaimet().size()];
        this.alkutilat = new HashSet();
        this.ekaDfaTila = new dfaTila(this.tila);
        this.dfaLista = new dfaTila[nfa.getKaari().getLoppu().getTila() + 1];
    
        nollaaVierailut(nfa.getKaari().getAlku());
        etsiJaLiitaEpsilonit(nfa.getKaari().getAlku(), alkutilat, ekaDfaTila);
        Lista<Tila> tilat = ekaDfaTila.getNfaTilat();
        for (int i = 0; i < tilat.size(); i++) {
            Tila tila = tilat.get(i);
                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                    ekaDfaTila.setHyvaksyvaTila(true);
                }
        }
        nollaaVierailut(nfa.getKaari().getAlku());
        
        // Nfa:ssa on tallennettu kaikki käytetyt kirjaimet hashsettiin eli ei ole duplikaatteja
        // ja tässä ne kopioidaan kirjain taulukkoon
        for(Character c : nfa.getKirjaimet()) {
            kirjaimet[indeksi++] = c;
        }
        
        dfa_tilat_avaimina.put(tila, alkutilat);
        dfa_setit_avaimina.put(alkutilat, tila);
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
                for (char c : kirjaimet) {
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
        HashSet<Tila> seuraava = new HashSet();
        
        etsiJaLiitaEpsilonit(t, seuraava, uusi);
        nollaaVierailut(t);
        
        Lista<Tila> tilat = uusi.getNfaTilat();
        
        if (dfa_setit_avaimina.containsKey(seuraava) != null) {
            uusi = dfaLista[dfa_setit_avaimina.get(seuraava)];
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
            dfa_setit_avaimina.put(seuraava, this.tila);
            dfa_tilat_avaimina.put(this.tila, seuraava);
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
    
    private void etsiJaLiitaEpsilonit(Tila tila, HashSet<Tila> tilat, dfaTila dfaTila) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        
        tila.setVierailtu(true);
        
        tilat.add(tila);
        dfaTila.lisaaTila(tila);
        
        if (tila.getSiirtyma() == ' ') {
            etsiJaLiitaEpsilonit(tila.getSeuraava(), tilat, dfaTila);
            etsiJaLiitaEpsilonit(tila.getSeuraava2(), tilat, dfaTila);
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
