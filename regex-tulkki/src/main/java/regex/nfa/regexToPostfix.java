package regex.nfa;

import regex.tietorakenteet.Pino;

public class regexToPostfix {
    
    /**
     * Luokka muuttaa annetun lausekkeen vastaavaksi postfix muodossa esim:
     * a(bb)+a = abb.+.a.
     */
    public regexToPostfix() {        
    }
    
    /**
     * Lisätään "." merkit konkatenoitavien merkkien väliin
     * @param lauseke
     * @return 
     */
    public String konkatenaatiot(String lauseke) {
        if (lauseke.length() == 1) {
            return lauseke;
        }
        
        char eka, toka;
        String palautus = "";
        
        for (int i = 0; i < lauseke.length() -1; i++) {
            eka = lauseke.charAt(i);
            toka = lauseke.charAt(i + 1);
            
            palautus += eka;
            if (eka != '(' && eka != '|' && kirjain(toka)) {
                palautus += ".";
            } else if (toka == '(' && eka != '(' && eka != '|') {
                palautus += ".";
            }
            
            if (i == lauseke.length() -2) {
                palautus += lauseke.charAt(lauseke.length() -1);
            }
        }
        return palautus;
    }
    
    
    /**
     * https://medium.com/@gregorycernera/converting-regular-expressions-to-postfix-notation-with-the-shunting-yard-algorithm-63d22ea1cf88
     * Sivulta löytyy tarkka seloste kuinka seuraava algoritmi toimii
     * Lyhyesti sanottuna merkeille annetaan jokin arvo ja sen mukaan ne joko jäävät pinoon tai siirretään pinosta lopulliseen palautukseen
     * Kirjaimet siirretään suoraan lopulliseen palautukseen
     * @param lauseke
     * @return 
     */ 
    public String luoPostfix(String lauseke) {
        String postfix = "";
        char c, c1;
        int i = 0;
        lauseke = konkatenaatiot(lauseke);
        lauseke += " ";
        Pino<Character> pino = new Pino();
        pino.push(' ');
        
        c = lauseke.charAt(i);
        i++;
        while (!pino.onkoTyhja()) {
            if (kirjain(c)) {
                postfix += c;
                c = lauseke.charAt(i);
                i++;
            } else {
                c1 = pino.peek();
                if (pinossa(c1) < pinoon(c)) {
                    pino.push(c);
                    c = lauseke.charAt(i);
                    i++;
                } else if (pinossa(c1) > pinoon(c)) {
                    postfix += pino.pop();
                } else {
                    // Vastakkaiset sulkumerkit tuli pinossa vastaan
                    // molemmat roskiin
                    if (pino.pop() == '(') {
                        c = lauseke.charAt(i);
                        i++;
                    }
                }
            }
        }
        return postfix;
    }
    
    /**
     * Tarkistetaan onko kirjain a-z tai A-Z tai 0-9
     * @param c
     * @return 
     */
    public boolean kirjain(char c) {
        if (c >= 97 && c <= 122 || c >= 65 && c <= 90 || c >= 48 && c <=  57) {
            return true;
        }
        return false;
    }
    
    // Jo pinossa olevien merkkien arvot
    public int pinossa(char c) {
        
        if (c == '(') {
            return 1;
            
        } else if (c == '*') {
            return 7;
            
        } else if (c == '+') {
            return 7;
            
        } else if(c == '.') {
            return 5;
            
        } else if (c == '|') {
            return 3;
            
        } else if (c == ')') {
            return 8;
            
        } else {
            return -1;
        }
    }
    
    // Pinoon menevien merkkien arvot
    public int pinoon(char c) {
        
        if (c == '(') {
            return 8;
            
        } else if (c == '*') {
            return 6;
            
        } else if (c == '+') {
            return 6;
            
        } else if(c == '.') {
            return 4;
            
        } else if (c == '|') {
            return 2;
            
        } else if (c == ')') {
            return 1;
            
        } else {
            return -1;
        }
    }

}
