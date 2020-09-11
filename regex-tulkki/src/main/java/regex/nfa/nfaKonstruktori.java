package regex.nfa;

import regex.domain.Solmu;
import regex.domain.Kaari;
import java.util.Stack;

public class nfaKonstruktori {
    
    private String lauseke;
    private Solmu[] tilat;
    private Stack pino;
    
    private int indeksi;
    private Solmu aloitusSolmu;
    private Solmu current;
    
    public nfaKonstruktori(String lauseke) {
        this.indeksi = 0;
        this.aloitusSolmu = new Solmu(indeksi, false);
        this.tilat = new Solmu[5];
        tilat[indeksi] = aloitusSolmu;
        indeksi++; 
        Kaari kaari = new Kaari();
        kaari.setAlku(aloitusSolmu);
        aloitusSolmu.setKaari(kaari);
        current = aloitusSolmu;
        this.lauseke = lauseke;
    }
    
    public void luoNFA() {
        for (int i = 0; i < lauseke.length(); i++) {
            char merkki = lauseke.charAt(i);
            if (merkki >= 97 && merkki <= 122) {
                luoKirjainTila(merkki);
            } else if (merkki == 124) {
                luoTaiTila(merkki);
            } else if (merkki == 40) {
                vasenSulku(merkki);
            } else if (merkki == 41) {
                oikeaSulku(merkki);
            } else if (merkki == 42) {
                luoTahtiTila(merkki);
            } else if (merkki == 43) {
                luoPlusTila(merkki);
            }
        }
        print();
    }
    
    public void luoKirjainTila(char c) {
        System.out.println("Kirjaintila " + c);
        
    }
    
    public void luoTaiTila(char c) {
        System.out.println("Taitila: " + c);
        
    }
    
    public void luoTahtiTila(char c) {
        System.out.println("Tähtitila " + c);
    }
    
    public void luoPlusTila(char c) {
        System.out.println("Plustila " + c);
    }
    
    public void vasenSulku(char c) {
        System.out.println("Vasensulku " + c);
        
    }
    
    public void oikeaSulku(char c) {
        System.out.println("Oikeasulku " + c);
    }
    
    public void print() {
        for (int i = 0; i < tilat.length; i++) {
       //     System.out.println(tilat[i].getKaari().getAlku().getSolmu());
        }
    }
}
