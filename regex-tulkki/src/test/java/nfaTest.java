import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import regex.nfa.nfa;
import regex.domain.*;

public class nfaTest {
    
    private nfa nfa;
    
    @Before
    public void setUp() throws Exception {
        this.nfa = new nfa("a(bb)+a");
    }
    
    @Test
    public void nfaLuodaanOikein() {
        nfa.luoNfa();
        Kaari kaari = nfa.getKaari();
        assertThat(kaari.getAlku().getTila(), is(1));
        assertThat(kaari.getLoppu().getTila(), is(10));
        assertThat(kaari.getAlku().getSeuraava().getTila(), is(2));
        assertThat(kaari.getLoppu().getSeuraava(), is(nullValue()));
    }
    
}