package algoritmit;

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
        String testi = regex.luoPostfix("(ab|c)*c+a");
        assertThat(testi, is("ab.c|*c+.a."));
    }
    
    @Test
    public void merkkijono3() {
        String testi = regex.luoPostfix("a*b*c*d*e*");
        assertThat(testi, is("a*b*.c*.d*.e*."));
    }
    
    @Test
    public void merkkijono4() {
        String testi = regex.luoPostfix("a(b|d)+ca*(dada)*c");
        assertThat(testi, is("abd|+.c.a*.da.d.a.*.c."));
    }
    
    @Test
    public void merkkijono5() {
        String testi = regex.luoPostfix("a(bb)+a");
        assertThat(testi, is("abb.+.a."));
    }
    
    // Tästä eteenpäin konkatenaatio testejä
    
    @Test
    public void merkkijono6() {
        String testi = regex.konkatenaatiot("a|s|d|f|g");
        assertThat(testi, is("a|s|d|f|g"));
    }
    
    @Test
    public void merkkijono7() {
        String testi = regex.konkatenaatiot("(ab|c)*c+a");
        assertThat(testi, is("(a.b|c)*.c+.a"));
    }
    
    @Test
    public void merkkijono8() {
        String testi = regex.konkatenaatiot("a*b*c*d*e*");
        assertThat(testi, is("a*.b*.c*.d*.e*"));
    }
    
    @Test
    public void merkkijono9() {
        String testi = regex.konkatenaatiot("a(b|d)+ca*(dada)*c");
        assertThat(testi, is("a.(b|d)+.c.a*.(d.a.d.a)*.c"));
    }
    
    @Test
    public void merkkijono10() {
        String testi = regex.konkatenaatiot("a(bb)+a");
        assertThat(testi, is("a.(b.b)+.a"));
    }
}