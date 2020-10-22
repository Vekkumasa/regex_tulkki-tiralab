package tietorakenteet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import regex.tietorakenteet.Lista;

public class listaTest<T> {
    
    private Lista<Object> lista;
    
    public listaTest() {
        this.lista = new Lista();
    }
    
    @Test
    public void listaanVoiLisata() {
        assertThat(lista.size(), is(0));
        lista.add(4);
        assertThat(lista.size(), is(1));
    }
    
    @Test
    public void listanKokoKasvaa() {
        assertThat(lista.getTaulukko().length, is(10));
        for (int i = 0; i < 11; i++) {
            lista.add(i);
        }
        assertThat(lista.getTaulukko().length, is(20));
    }
    
    @Test
    public void getKoko() {
        assertThat(lista.size(), is(0));
        for (int i = 0; i < 11; i++) {
            lista.add(i);
        }
        assertThat(lista.size(), is(11));
    }
}
