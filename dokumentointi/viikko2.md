- Monta epäonnistunutta yritystä luoda nfa annetusta lausekkeesta (En keksinyt kuinka tehdä se ilman muunnosta postfix muotoon)
- Luotu algoritmi postfix muunnokselle (https://medium.com/@gregorycernera/converting-regular-expressions-to-postfix-notation-with-the-shunting-yard-algorithm-63d22ea1cf88)
- testit tehty algoritmille

- Seuraavaksi:
1. Algoritmi mikä lisää pisteet lausekkeeseen ennen postfix muunnoksen suorittamista, toistaiseksi lisännyt ne itse. (Following Thompson's paper,
the compiler builds an NFA from a regular expression in postfix notation with dot (.) added as an explicit concatenation operator. 
A separate function re2post rewrites infix regular expressions like “a(bb)+a” into equivalent postfix expressions like “abb.+.a.”. )
Lähde: https://swtch.com/~rsc/regexp/regexp1.html
2. uusi yritys nfa:sta

Aikaa käytetty noin 15h
