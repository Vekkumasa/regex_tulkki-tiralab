package regex.dfa;

import regex.domain.*;
import regex.nfa.nfa;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import regex.tietorakenteet.Pino;

public class kopio {
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
    
    public kopio(nfa nfa, String syote) {
        this.nfa = nfa;
        // Liitetään toisiinsa kaikki NFA tilat (hashset) ja dfa tilat
        this.dfa_tilat_avaimina = new HashMap<>();
        this.dfa_setit_avaimina = new HashMap<>();
        this.pino = new Pino();
        
        // Numeroi dfa tilat
        this.tila = 1;
        // Lisätään syötteen eteen 1 mikä tahansa merkki tarkistus metodin indeksoinnin helpottamiseksi
        this.syote = "#" + syote;
        this.kirjaimet = new Character[nfa.getKirjaimet().size()];
        this.alkutilat = new HashSet();
        this.ekaDfaTila = new dfaTila();
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
        System.out.print("dfa tila " + tila + " = ");
        
        for (Tila t : alkutilat) {
            System.out.print(t.getTila() + " ");
        }   
        System.out.println("");
        
        pino.push(tila);
        
        while (!pino.onkoTyhja()) {
            HashSet<Tila> temp = dfa_tilat_avaimina.get(pino.pop());
            dfaTila currentDfa = dfaLista[tila];
            
            //System.out.println("Tilat: " + currentDfa + " setti: " + temp.toString());
            // Tarkistetaan jos tilojen joukosta löytyy nfa kaaren lopputila
            // Jos löytyy niin kyseinen DFA tila on hyväksyvä
                
            for (Tila tila : currentDfa.getNfaTilat()) {
                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                    currentDfa.setHyvaksyvaTila(true);
                }
            }
            
            tila++;
            System.out.println("Uusi tila: " + tila);
            
            // käydään kaikki mahdolliset siirtymät läpi jokaiselta joukkoon kuuluvalta tilalta
            for (Tila t : temp) {
                for (char c : kirjaimet) {
                    if (t.getSiirtyma() == c) {
                        
                        HashSet<Tila> seuraava = new HashSet();
                        dfaTila seurDfa = new dfaTila();
                        
                        System.out.println("Ennen epsiloneja: " + t.getTila() + " kaari.loppu: " + t.getKaari().getLoppu().getTila());
                        
                        etsiJaLiitaEpsilonit(t.getKaari().getLoppu(), seuraava, seurDfa);
                        nollaaVierailut(t.getKaari().getLoppu());
                        
                        if (dfa_setit_avaimina.containsKey(seuraava)) {
                            // Ikuinen looppi löytynyt
                            // Etsitään aikaisemmin käyty tila ja liitetään se dfa:n seuraavien listaan
                            int seuraava_tila = dfa_setit_avaimina.get(seuraava);
                            currentDfa.lisaaSiirtyma(c, seuraava_tila);
                            
                            System.out.println("Dfa tiloja loopissa: ");
                            currentDfa.getNfaTilat().forEach((t1) -> {
                                System.out.print(t1.getTila() + ", ");
                            });
                            System.out.println("");
                            

                            continue;
                        }
                        
                        // Lisätään siirtymä currentDfa:sta seuraavaan
                        dfaLista[tila] = seurDfa;
                        currentDfa.lisaaSiirtyma(c, tila);
                        dfa_tilat_avaimina.put(tila, seuraava); 
                        dfa_setit_avaimina.put(seuraava, tila);
                        System.out.println("DFA tiloja: ");
                        for (Tila t1 : seurDfa.getNfaTilat()) {
                                System.out.print(t1.getTila() + ", ");
                            }
                        System.out.println("");
                        
                        pino.push(tila);
                    }
                }
                
            }
        }
    }
    
    public dfaTila luoDfaTila(Tila tila, boolean vanha) {
        dfaTila uusi = new dfaTila();
        HashSet<Tila> seuraava = new HashSet();
        
        etsiJaLiitaEpsilonit(tila, seuraava, uusi);
        nollaaVierailut(tila);
        
        if (dfa_setit_avaimina.containsKey(seuraava)) {
            int tilaNro = dfa_setit_avaimina.get(seuraava);
            vanha = true;
            return dfaLista[tilaNro];
            
        } else {
            for (Tila t1 : uusi.getNfaTilat()) {
                if (t1.getTila() == nfa.getKaari().getLoppu().getTila()) {
                    uusi.setHyvaksyvaTila(true);
                }
            }
            this.tila++;
            dfaLista[this.tila] = uusi;
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
