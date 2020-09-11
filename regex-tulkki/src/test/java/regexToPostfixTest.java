import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import regex.nfa.regexToPostfix;

public class regexToPostfixTest {
    
    private regexToPostfix regex;
    
    @Before
    public void setUp() throws Exception {
        this.regex = new regexToPostfix();
    }
    
    @Test
    public void merkkijono1() {
        String testi = regex.luoPostfix("a|s|d|f|g");
        assertThat(testi, is("as|d|f|g|"));
    }
    
    @Test
    public void merkkijono2() {
        String testi = regex.luoPostfix("(a.b|c)*.c+.a");
        assertThat(testi, is("ab.c|*c+.a."));
    }
    
    @Test
    public void merkkijono3() {
        String testi = regex.luoPostfix("a*.b*.c*.d*.e*");
        assertThat(testi, is("a*b*.c*.d*.e*."));
    }
    
    @Test
    public void merkkijono4() {
        String testi = regex.luoPostfix("a.(b|d)+.c.a*.(d.a.d.a)*.c");
        assertThat(testi, is("abd|+.c.a*.da.d.a.*.c."));
    }
}