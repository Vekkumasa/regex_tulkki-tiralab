
package tietorakenteet;

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

import regex.domain.*;
import regex.tietorakenteet.LinkedList;
import regex.tietorakenteet.Solmu;

public class LinkedListTest {
    
    private LinkedList<Integer> lista;
    private Solmu<Integer> solmu;
    
    @Before
    public void setUp() throws Exception {
        this.lista = new LinkedList();
        this.solmu = new Solmu(5);
    }
    
    @Test
    public void ListaanVoiLisataSolmun() {
        lista.add(solmu);
        assertThat(lista.get(solmu).getData(), is(5));
    }
    
    @Test
    public void tyhjaListaPalauttaa() {
        assertThat(lista.get(solmu).getData(), is(-1));
    }
    
    @Test
    public void listastaHakuToimii() {
        Solmu<Integer> s1 = new Solmu(1);
        Solmu<Integer> s2 = new Solmu(2);
        Solmu<Integer> s3 = new Solmu(3);
        Solmu<Integer> s4 = new Solmu(4);
        Solmu<Integer> s5 = new Solmu(5);
        Solmu<Integer> s6 = new Solmu(6);
        Solmu<Integer> s7 = new Solmu(7);
        
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        lista.add(s4);
        lista.add(s5);
        lista.add(s6);
        lista.add(s7);
        
        assertThat(lista.get(s6).getData(), is(6));
        assertThat(lista.get(s3).getData(), is(3));
    }
    
     @Test
    public void listastaHakuDatanMukaanToimii() {
        Solmu<Integer> s1 = new Solmu(1);
        Solmu<Integer> s2 = new Solmu(2);
        Solmu<Integer> s3 = new Solmu(3);
        Solmu<Integer> s4 = new Solmu(4);
        Solmu<Integer> s5 = new Solmu(5);
        Solmu<Integer> s6 = new Solmu(6);
        Solmu<Integer> s7 = new Solmu(7);
        
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        lista.add(s4);
        lista.add(s5);
        lista.add(s6);
        lista.add(s7);
        
        assertThat(lista.getData(3), is(s3));
        assertThat(lista.getData(7), is(s7));
    }
    
    @Test
    public void listastaVoiPoistaa1() {
        Solmu<Integer> s1 = new Solmu(1);
        Solmu<Integer> s2 = new Solmu(2);
        Solmu<Integer> s3 = new Solmu(3);
        Solmu<Integer> s4 = new Solmu(4);
        Solmu<Integer> s5 = new Solmu(5);
        Solmu<Integer> s6 = new Solmu(6);
        Solmu<Integer> s7 = new Solmu(7);
        
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        lista.add(s4);
        lista.add(s5);
        lista.add(s6);
        lista.add(s7);
        
        assertThat(lista.getData(1), is(s1));
        lista.remove(s1);
        assertThat(lista.getData(1), is(nullValue()));
    }
}