package tietorakenteet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import regex.tietorakenteet.HajautusTaulu;
import regex.tietorakenteet.Pari;

public class hajautusTauluTest {
    private HajautusTaulu<Object, Object> taulu;
    
    public hajautusTauluTest() {
        this.taulu = new HajautusTaulu();
    }
    
    @Test
    public void hakuToimii() {
        taulu.put(3, "testi");
        assertThat(taulu.get(3), is("testi"));
    }
    
    @Test
    public void hakuToimii1() {
        assertThat(taulu.get(3), is(nullValue()));
    }
    
    @Test
    public void defaultHakuToimii() {
        taulu.put(2, "kyllä on");
        assertThat(taulu.getOrDefault(3, "ei ole"), is("ei ole"));
        assertThat(taulu.getOrDefault(2, "ei ole"), is("kyllä on"));
    }
    
    @Test
    public void containsKey() {
        taulu.put("Avain", "Arvo");
        assertThat(taulu.containsKey("Avain"), is("Avain"));
        assertThat(taulu.containsKey("Arvo"), is(nullValue()));
    }
    
    @Test
    public void kopio() {
        for (Integer i = 0; i < 300; i++) {
            taulu.put(i, 300-i);
        }
        
        for (Integer i = 0; i < 300; i++) {
            assertThat(taulu.get(i), is(300-i));
        }
        
    }
}
