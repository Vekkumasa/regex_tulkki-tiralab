package regex.dfa;

import regex.domain.*;
import regex.nfa.nfa;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import regex.tietorakenteet.Pino;
import regex.tietorakenteet.ArrayList;

public class dfa {
    private nfa nfa;
    private HashMap<Integer, HashSet<Tila>> dfa_tilat_avaimina;
    private HashMap<HashSet<Tila>, Integer> dfa_setit_avaimina;
    private int tila;
    private HashSet<Tila> alkutilat;
    private String syote;
    private Character[] kirjaimet;
    private int indeksi = 0;
    private Pino<Integer> pino;
    private dfaTila ekaDfaTila;
    private dfaTila[] dfaLista;
    private ArrayList<Tila> array;
    
    public dfa(nfa nfa, String syote) {
        this.nfa = nfa;
        // Liitetään toisiinsa kaikki NFA tilat (hashset) ja dfa tilat
        this.dfa_tilat_avaimina = new HashMap<>();
        this.dfa_setit_avaimina = new HashMap<>();
        this.pino = new Pino();
        this.array = new ArrayList();
        // Numeroi dfa tilat
        this.tila = 1;
        // Lisätään syötteen eteen 1 mikä tahansa merkki tarkistus metodin indeksoinnin helpottamiseksi
        this.syote = "#" + syote;
        this.kirjaimet = new Character[nfa.getKirjaimet().size()];
        this.alkutilat = new HashSet();
        this.ekaDfaTila = new dfaTila(this.tila);
        this.dfaLista = new dfaTila[nfa.getKaari().getLoppu().getTila() + 1];
        
    //  nollaaVierailut metodia tulee käyttää jos UI:ssa käytetään ennen dfa:n luomista
    //  nfa:n faktatTiskiin metodia, koska siellä vieraillaan tiloissa
    
        nollaaVierailut(nfa.getKaari().getAlku());
        etsiJaLiitaEpsilonit(nfa.getKaari().getAlku(), alkutilat, ekaDfaTila);
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
        System.out.println("");
        System.out.println("LuoDFA" + "\n" + "------");
        System.out.println("Alku: " + nfa.getKaari().getAlku().getTila() + " loppu: " + nfa.getKaari().getLoppu().getTila());
        
        pino.push(tila);
        
        while (!pino.onkoTyhja()) {
            int luku = pino.pop();
            HashSet<Tila> temp = dfa_tilat_avaimina.get(luku);
            dfaTila currentDfa = dfaLista[luku];
            System.out.println("DfaTila: " + this.tila + " Käsittelyssä");
            
            array = currentDfa.getNfaTilat();
            for (int i = 0; i < array.size(); i++) {
                System.out.print(array.get(i).getTila() + " ");
            } 
            System.out.println("");
            if (currentDfa.getKasitelty()) {
                continue;
            }
            
            for (int i = 0; i < array.size(); i++) {
                Tila t = array.get(i);
                for (char c : kirjaimet) {
                    if (t.getSiirtyma() == c) {
                        dfaTila uusi = luoDfaTila(t.getKaari().getLoppu());
                        currentDfa.lisaaSiirtyma(c, uusi.getTila());
                        System.out.println("Currentdfa lisätty siirtymä: " + c + " tilaan: " + uusi.getTila());
                    }
                }
            }
            System.out.println(currentDfa.getTila() + " on käsitelty");
            currentDfa.setKasitelty();
        }
    }
    
    public dfaTila luoDfaTila(Tila t) {
        this.tila++;
        dfaTila uusi = new dfaTila(this.tila);
        HashSet<Tila> seuraava = new HashSet();
        
        etsiJaLiitaEpsilonit(t, seuraava, uusi);
        nollaaVierailut(t);
        
        ArrayList<Tila> tilat = uusi.getNfaTilat();
        
        if (dfa_setit_avaimina.containsKey(seuraava)) {
            System.out.println("Ifissä");
            uusi = dfaLista[dfa_setit_avaimina.get(seuraava)];
            this.tila--;
            return uusi;
            
        } else {
            System.out.println("Elsessä");
            for (int i = 0; i < tilat.size(); i++) {
                Tila tila = tilat.get(i);
                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                    uusi.setHyvaksyvaTila(true);
                }
            }
            
            dfaLista[this.tila] = uusi;
            this.pino.push(this.tila);
            System.out.println("Pushataan pinoon: " + this.tila);
            dfa_setit_avaimina.put(seuraava, this.tila);
            dfa_tilat_avaimina.put(this.tila, seuraava);
        }
        
        return uusi;
    }
    
    /**
     * Metodi etsii rekursiivisesti kaikki seuraavat tilat mihin päästään käyttämättä yhtään merkkiä,
     * lisää ne tilat hashsettiin ja dfaTila luokan dfaTilaan
     * @param tila
     * @param siirtyma
     * @param tilat
     * @param dfaTila 
     */
    
    public void etsiJaLiitaEpsilonit(Tila tila, Set<Tila> tilat, dfaTila dfaTila) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        
        /*
        System.out.println("");
        System.out.println("Liitä metodi" +
                "\n" + "tila: " + tila.getTila() + 
                "\n" + "Seuraava: " + tila.getSeuraava() + 
                "\n" + "Seuraava2: " + tila.getSeuraava2() +
                "\n" + "Siirtymä: " + tila.getSiirtyma() +
                "\n" + "------"); 
        */
        
        tila.setVierailtu(true);
        
        tilat.add(tila);
        dfaTila.lisaaTila(tila);
        
        if (tila.getSiirtyma() == ' ') {
            etsiJaLiitaEpsilonit(tila.getSeuraava(), tilat, dfaTila);
            etsiJaLiitaEpsilonit(tila.getSeuraava2(), tilat, dfaTila);
        }
    }
    
    /**
     * Edeltävän metodin jäljiltä pitää vierailut asettaa takaisin falseksi
     * @param tila 
     */

    public void nollaaVierailut(Tila tila) {
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
        //printtaaDfaTilat();
        System.out.println("---Tarkistus---");
        System.out.println("Syöte: " + syote);
        
        //Pidetään kirjaa missä DFA tilassa ollaan
        int currentState = 1;
        
        for (int i = 1; i < syote.length(); i++) {
            System.out.println("Indeksi: " + i);
            
            if (i == syote.length() -1) {
                System.out.println("Indeksi = syote.length eli: " +(syote.length()-1));
                System.out.println("currentState ennen muutosta: " + currentState);
                currentState = dfaLista[currentState].getSiirtyma((int)syote.charAt(i));
                System.out.println("finalState: " + currentState);
                
                if (currentState == -1) {
                    return false;
                }
                
                // Syöte on luettu loppuun ja currentstate arvo muutettu viimeiseen tilaan
                // mikäli tila on hyväksyvä, hyväksytään syöte
                
                if (dfaLista[currentState].isHyvaksyvaTila()) {
                    System.out.println("Hyväksytään: " + currentState);
                    return true;
                }
                
                // Syötettä ei ole luettu loppuun joten täytyy päivittää currentstate ja tarkistaa
                // että tilasta löytyy siirtymä
            } else {
                System.out.println("Currentstate: " + currentState);
                currentState = dfaLista[currentState].getSiirtyma((int)syote.charAt(i));
                System.out.println("CurrentState vol 2: " + currentState);
                if (currentState == -1) {
                    return false;
                }     
            }
        }
        
        return false;
    }

    /**
     * Tehty dfatestejä varten
     * @return the dfaLista
     */
    public dfaTila[] getDfaLista() {
        return dfaLista;
    }
    
    public void printtaaDfaTilat() {
        System.out.println("PRINTTAUS");
        for (int i = 1; i < dfaLista.length; i++) {
            System.out.println("Tila: " + i);
            System.out.println("Nfa tilat: ");
            for (int k = 0; k < dfaLista[i].getNfaTilat().size(); k++) {
                System.out.print(dfaLista[i].getNfaTilat().get(k).getTila() + ", ");
            }
            System.out.println("");
        }
    }
}
