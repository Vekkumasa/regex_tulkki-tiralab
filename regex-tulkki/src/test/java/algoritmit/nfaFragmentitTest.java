package algoritmit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import regex.nfa.nfaFragmentit;
import regex.domain.*;

public class nfaFragmentitTest {
    
    private nfaFragmentit nfa;
    
    @Before
    public void setUp() throws Exception {
        this.nfa = new nfaFragmentit();
    }
    
    @Test
    public void kirjainTila() {
        Kaari kaari = nfa.luoKirjainTila('a');
        assertThat(kaari.getAlku().getTila(), is(1));
        assertThat(kaari.getLoppu().getTila(), is(2));
        assertThat(kaari.getAlku().getSiirtyma(), is('a'));
    }
    
    @Test
    public void taiTila() {
        Kaari seuraava = nfa.luoKirjainTila('a');
        Kaari seuraava2 = nfa.luoKirjainTila('b');
        Kaari kaari = nfa.luoTaiTila(seuraava, seuraava2);
        
        assertThat(kaari.getAlku().getSeuraava().getTila(), is(1));
        assertThat(kaari.getAlku().getSeuraava2().getTila(), is(3));
        assertThat(seuraava.getLoppu().getSeuraava().getTila(), is(6));
        assertThat(seuraava2.getLoppu().getSeuraava().getTila(), is(6));
    }
    
    @Test
    public void tahtiTila() {
        Kaari edellinen = nfa.luoKirjainTila('a');
        Kaari kaari = nfa.luoTahtiTila(edellinen);
        
        assertThat(kaari.getAlku().getSeuraava().getTila(), is(1));
        assertThat(edellinen.getLoppu().getSeuraava().getTila(), is(4));
    }
    
    @Test
    public void plusTila() {
        Kaari kaari = nfa.luoPlusTila(nfa.luoKirjainTila('a'));
        
        assertThat(kaari.getAlku().getSeuraava().getTila(), is(1));
        assertThat(kaari.getAlku().getSeuraava2(), is(nullValue()));
        assertThat(kaari.getLoppu().getSeuraava(), is(nullValue()));
        assertThat(kaari.getLoppu().getSeuraava2(), is(nullValue()));
    }
    
    @Test
    public void konkatenaatio() {
        Kaari kaari = nfa.luoKonkatenaatioPisteTila(nfa.luoKirjainTila('a'), nfa.luoKirjainTila('b'));
        
        assertThat(kaari.getAlku().getTila() , is(1));
        assertThat(kaari.getLoppu().getTila(), is(4));
    }
    
}