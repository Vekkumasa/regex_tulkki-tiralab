package regex.nfa;

import java.util.Stack;

public class regexToPostfix {
    
    public regexToPostfix() {        
    }
    
    public String luoPostfix(String regex) {
        String postfix = "";
        char c, c1;
        int i = 0;
        regex += " ";
        Stack<Character> pino = new Stack();
        pino.push(' ');
        
        c = regex.charAt(i);
        i++;
        while (!pino.isEmpty()) {
            if (kirjain(c)) {
                postfix += c;
                c = regex.charAt(i);
                i++;
            } else {
                c1 = pino.peek();
                if (pinossa(c1) < pinoon(c)) {
                    pino.push(c);
                    c = regex.charAt(i);
                    i++;
                } else if (pinossa(c1) > pinoon(c)) {
                    postfix += pino.pop();
                } else {
                    // Vastakkaiset sulkumerkit tuli pinossa vastaan
                    // molemmat roskiin
                    if (pino.pop() == '(') {
                        c = regex.charAt(i);
                        i++;
                    }
                }
            }
        }
        System.out.println("Regex: " + regex + " Postfix: " + postfix);
        return postfix;
    }
    
    public boolean kirjain(char c) {
        if (c >= 97 && c <= 122) {
            return true;
        }
        return false;
    }
    
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