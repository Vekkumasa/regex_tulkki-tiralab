![Image of jacoco-regex-domain](https://github.com/Vekkumasa/regex_tulkki-tiralab/blob/master/dokumentointi/kuvat/jacoco-regex-domain.png)

![Image of jacoco-regex-nfa](https://github.com/Vekkumasa/regex_tulkki-tiralab/blob/master/dokumentointi/kuvat/jacoco-regex-nfa.png)

![Image of jacoco-regex-tulkki](https://github.com/Vekkumasa/regex_tulkki-tiralab/blob/master/dokumentointi/kuvat/jacoco-regex-tulkki.png)

![Image of jacoco-regex-dfa](https://github.com/Vekkumasa/regex_tulkki-tiralab/blob/master/dokumentointi/kuvat/jacoco-regex-dfa.png)

![Image of jacoco-regex-tietorakenteet](https://github.com/Vekkumasa/regex_tulkki-tiralab/blob/master/dokumentointi/kuvat/jacoco-regex-tietorakenteet.png)
## Testaaminen

Testaamiseen käytetään JUnit:ia ja testit voidaan suorittaa komentoriviltä komennolla mvn test ja testikattavuus raportin komennolla mvn jacoco:report

#### NFA

Nfasta testataan yksikkötestauksina nfafragmenttien luominen oikein. Eli jokaisella luodulla tilalla on tietyt seuraavat tilat mihin tulee olla pääsy
joko normaalilla siirtymällä tai epsilon siirtymällä.
nfaTest luokka testaa useamman fragmentin toimintaa kerralla samalla periaatteella kuin nfafragmenttien testaaminen.

Regextopostfix testeissä annetaan syöte ja muutetaan se ekvialenttiin postfix muotoon. Samaan tiedostoon on laitettu myös konkatenaatioden testaamiset
(erotettu toisistaan kommenteilla.)
#### DFA

Dfa testeissä tarkistetaan että dfatilat luodaan oikein eli jokainen dfatila sisältää oikeat nfatilat.
Dfan syötteiden tarkistamista testataan käyttäen apuna javan merkkijonojen matches metodia millä tarkistetaan säännöllisiä lausekkeita. 

#### Tietorakenteet

Kaikista tietorakenteista on testattu sovelluksessa käytetyt toiminnot. Testit on toteutettu niin että on käytetty jotakin haluttua operaatiota
ja tarkistettu tämän jälkeen tietorakenteen tila. Esim jos lisätään taulukkoon alkio ja taulukko tulee täyteen niin kasvaako listan koko automaattisesti
vai ei. 

#### Käyttöliittymä

Käyttöliittymä on testattu manuaalisesti


