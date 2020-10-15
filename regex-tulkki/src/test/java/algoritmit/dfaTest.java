package algoritmit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

import regex.dfa.dfa;
import regex.nfa.nfa;
import regex.domain.*;
import java.util.HashSet;
import regex.tietorakenteet.ArrayList;

public class dfaTest {
    
    private nfa nfa;
    private dfa dfa;
    
    @Before
    public void setUp() throws Exception {
        this.nfa = new nfa("a(bb)+a");
        nfa.luoNfa();
        this.dfa = new dfa(nfa, "abba");
    }
    
    // Kyseisessä DFA:ssa tulee olla 5 tilaa ja jokainen dfa tila pitää sisällään X määrän nfatiloja
    // Ensimmäisessä tilassa on vain tila 1
    // Toisessa tilassa on tilat 2 3 ja 7
    // Kolmannessa tilassa on tilat 4 ja 5
    // Neljännessä tilassa on tilat 3,6,7,8 ja 9
    // Viidennessä tilassa on tila 10
    
    @Test
    public void dfaLuodaanOikein1() {
        dfa.luoDfa();
        dfaTila[] dfaLista = dfa.getDfaLista();
        ArrayList<Tila> nfaTila = dfaLista[1].getNfaTilat();
        assertThat(nfaTila.get(0).getTila(), is(1));
    }
    
    @Test
    public void dfaLuodaanOikein2() {
        dfa.luoDfa();
        dfaTila[] dfaLista = dfa.getDfaLista();
        int[] testiTilat = new int[3];
        testiTilat[0] = 2;
        testiTilat[1] = 7;
        testiTilat[2] = 3;
        ArrayList<Tila> tilat = dfaLista[2].getNfaTilat();
        
        int[] dfaTilat = new int[3];
        for (int i = 0; i < tilat.size(); i++) {
            dfaTilat[i] = tilat.get(i).getTila();
        }
        
        assertArrayEquals(testiTilat, dfaTilat);
    }
    
    @Test
    public void dfaLuodaanOikein3() {
        dfa.luoDfa();
        dfaTila[] dfaLista = dfa.getDfaLista();
        int[] testiTilat = new int[2];
        testiTilat[0] = 4;
        testiTilat[1] = 5;
        ArrayList<Tila> tilat = dfaLista[3].getNfaTilat();
        
        int[] dfaTilat = new int[2];
        for (int i = 0; i < dfaTilat.length; i++) {
            dfaTilat[i] = tilat.get(i).getTila();
        }
        
        assertArrayEquals(testiTilat, dfaTilat);
        
    }
    
    @Test
    public void dfaLuodaanOikein4() {
        dfa.luoDfa();
        dfaTila[] dfaLista = dfa.getDfaLista();
        int[] testiTilat = new int[5];
        testiTilat[0] = 6;
        testiTilat[1] = 7;
        testiTilat[2] = 3;
        testiTilat[3] = 8;
        testiTilat[4] = 9;
        ArrayList<Tila> tilat = dfaLista[4].getNfaTilat();
        
        int[] dfaTilat = new int[5];
        for (int i = 0; i < dfaTilat.length; i++) {
            dfaTilat[i] = tilat.get(i).getTila();
        }
        
        assertArrayEquals(testiTilat, dfaTilat);
        
    }
    
    @Test
    public void dfaLuodaanOikein5() {
        dfa.luoDfa();
        dfaTila[] dfaLista = dfa.getDfaLista();
        ArrayList<Tila> nfaTila = dfaLista[5].getNfaTilat();
        assertThat(nfaTila.get(0).getTila(), is(10));
    }
    
}