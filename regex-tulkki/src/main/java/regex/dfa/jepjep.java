package regex.dfa;

import regex.domain.*;
import regex.nfa.nfa;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class dfa {
    private nfa nfa;
    private HashMap<Integer, HashSet<Tila>> dfa_tilat_avaimina;
    private HashMap<HashSet<Tila>, Integer> dfa_setit_avaimina;
    private int tila;
    private HashSet<Tila> alkutilat;
    private String syote;
    private Character[] kirjaimet;
    private int indeksi = 0;
    private Stack<Integer> pino;
    private int[][] siirtymat;
    private dfaTila ekaDfaTila;
    private dfaTila[] dfaLista;
    
    public dfa(nfa nfa, String syote) {
        this.nfa = nfa;
        this.dfa_tilat_avaimina = new HashMap<>();
        this.dfa_setit_avaimina = new HashMap<>();
        this.pino = new Stack();
        this.tila = 1;
        this.syote = "#" + syote;
        this.kirjaimet = new Character[nfa.getKirjaimet().size()];
        this.siirtymat = new int[6][6];
        this.siirtymat[0][0] = -1;
        this.alkutilat = new HashSet();
        this.ekaDfaTila = new dfaTila();
        this.dfaLista = new dfaTila[nfa.getKaari().getLoppu().getTila()];
        
        nollaaVierailut(nfa.getKaari().getAlku());
        etsiJaLiitaEpsilonit(nfa.getKaari().getAlku(), ' ', alkutilat, ekaDfaTila);
        nollaaVierailut(nfa.getKaari().getAlku());
        for(Character c : nfa.getKirjaimet()) {
        //    this.siirtymat[0][(indeksi + 1)] = c;
            kirjaimet[indeksi++] = c;
        }
        dfa_tilat_avaimina.put(tila, alkutilat);
        dfa_setit_avaimina.put(alkutilat, tila);
        dfaLista[tila] = ekaDfaTila;
    }
    
    public void lisaaSiirtymaTauluun(char c, int alku, int loppu) {
    //    System.out.println("------");
    //    System.out.println("Taulussa:" + c + " lähtötila: " + alku + " lopputila: " + loppu);
    //    System.out.println("------");
        siirtymat[alku][loppu] = (int)c;
    }
    
    public void luoDfa() {
        System.out.println("");
        System.out.println("LuoDFA" + "\n" + "------");        
        System.out.print("dfa tila " + tila + " = ");
        for (Tila t : alkutilat) {
            System.out.print(t.getTila() + " ");
            if (t.getTila() == nfa.getKaari().getLoppu().getTila()) {
                ekaDfaTila.setHyvaksyvaTila(true);
            }
        }   
        pino.push(tila++);
        System.out.println("");
        while (!pino.isEmpty()) {
    //        System.out.println("---- tilaan " + (pino.peek() + 1) + " menevät ---");
            HashSet<Tila> temp = dfa_tilat_avaimina.get(pino.pop());
            
            for (char c : kirjaimet) {
                // käydään kaikki mahdolliset siirtymät läpi jokaiselta joukkoon kuuluvalta tilalta
                for (Tila t : temp) {
                    if (t.getSiirtyma() == c) {
              //          System.out.println("Tilan " + t.getTila() + " siirtymä on: " + c);
                        HashSet<Tila> seuraava = new HashSet();
                        dfaTila seurDfa = new dfaTila();
                        etsiJaLiitaEpsilonit(t.getKaari().getLoppu(), ' ', seuraava, seurDfa);
                        if (dfa_setit_avaimina.containsKey(seuraava)) {
                            // ikuinen looppi löydetty
            //                tila--;
                            int seuraava_tila = dfa_setit_avaimina.get(seuraava);
                            seurDfa = dfaLista[tila -2];
                            System.out.println("DFA TILA LISTASTA -2 " + (tila -2));
                            for (Tila tila : seurDfa.getNfaTilat()) {
                                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                                    seurDfa.setHyvaksyvaTila(true);
                                }
                            }
                            seurDfa.lisaaSiirtyma(c, seuraava_tila +1);
                            System.out.println("Lisätään siirtymä: " + c + " tilaan: " + seuraava_tila );
                            lisaaSiirtymaTauluun(c, tila -1, seuraava_tila);
                            continue;
                        }
                        
                        dfa_tilat_avaimina.put(tila, seuraava); 
                        dfa_setit_avaimina.put(seuraava, tila);
                        dfaLista[tila] = seurDfa;
                        
                        dfaTila edeltavaDfa = dfaLista[tila -1];
                        edeltavaDfa.lisaaSiirtyma(c, tila);
                        for (Tila tila : seurDfa.getNfaTilat()) {
                                if (tila.getTila() == nfa.getKaari().getLoppu().getTila()) {
                                    seurDfa.setHyvaksyvaTila(true);
                                }
                            }
                        System.out.println("Lisätään siirtymä: " + c + " tilaan: " + tila);
                        System.out.print("Dfa tila " + tila + " = ");
                        for (Tila x : seuraava) {
                            System.out.print(x.getTila() + " ");
                        }                       
                        System.out.println("");
                        
                        lisaaSiirtymaTauluun(c, tila-1, tila);
                        pino.push(tila++);
                        nollaaVierailut(t.getKaari().getLoppu());                     
                    }
                }
            }
        }
        
        System.out.println("tila: " + tila + " " + (char)tila);
        System.out.println("loppu");
    }
    
    public void etsiJaLiitaEpsilonit(Tila tila, char siirtyma, Set<Tila> tilat, dfaTila dfaTila) {
        if (tila == null || tila.isVierailtu()) {
            return;
        }
        /*
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
            etsiJaLiitaEpsilonit(tila.getSeuraava(), siirtyma, tilat, dfaTila);
            etsiJaLiitaEpsilonit(tila.getSeuraava2(), siirtyma, tilat, dfaTila);
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
    
    public void testi() {
        /*
        for (int i = 1; i < siirtymat.length; i++) {
            for (int k = 0; k < siirtymat[i].length; k++) {
                System.out.println("Siirtymat[" + i + "][" + k + "] = " + siirtymat[i][k]);
            }
        }
*/
        System.out.println("-------");
        System.out.println("testi: ");
        System.out.println("-------");
        for (int i = 0; i < dfaLista.length; i++) {

            if (dfaLista[i] != null) {
                System.out.println("indeksi: " + i);
                System.out.println(dfaLista[i].isHyvaksyvaTila());
                for (Tila tila : dfaLista[i].getNfaTilat()) {
                    System.out.print(tila.getTila() + ",");
                }
                System.out.println("");
            }
        }
    }
    
    public boolean tarkista() {
      //  testi();
        System.out.println("---Tarkistus---");
        System.out.println("Syöte: " + syote);
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
                
                if (dfaLista[currentState].isHyvaksyvaTila()) {
                    System.out.println("Hyväksytään: " + currentState);
                    return true;
                }
                
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
}
