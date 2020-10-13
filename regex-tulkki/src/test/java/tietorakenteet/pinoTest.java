package tietorakenteet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import regex.tietorakenteet.Pino;

public class pinoTest {
    
    private Pino<Integer> pino;
    
    public pinoTest() {
        this.pino = new Pino();
    }
    
    @Test
    public void pinoonVoiLisata() {
        pino.push(1);
        pino.push(2);
        assertThat(pino.size(), is(2));
        pino.push(4);
        assertThat(pino.size(), is(3));
    }
    
    @Test
    public void pinonKokoKasvaa() {
        for (int i = 0; i < 12; i++) {
            pino.push(i);
        }
        assertThat(pino.getKoko(), is(20));
    }
    
    @Test
    public void pinostaVoiPoistaaPaallimmaisen() {
        pino.push(6);
        assertThat(pino.size(), is(1));
        int luku = pino.pop();
        assertThat(luku, is(6));
        assertThat(pino.size(), is(0));
    }
    
    @Test
    public void pinoOnTyhja() {
        assertThat(pino.onkoTyhja(), is(true));
        pino.push(4);
        assertThat(pino.onkoTyhja(), is(false));
    }
    
    @Test
    public void pinoOnTaynna() {
        
    }
}
