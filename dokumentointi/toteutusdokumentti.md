# Toteutusdokumentti

### Käyttöliittymä

Käyttöliittymänä toimii hyvin pelkistetty JavaFX:llä toteutettu käyttöliittymä, missä on kaksi tekstikenttää ja yksi painike.
Toiseen tekstikenttään laitetaan haluttu syöte ja toiseen syötetään haluttu säännöllinen lauseke.

### Toteutus

Ensin annettu säännöllinen lauseke muutetaan Postfix (Reverse polish notation) muotoon regexToPostfix luokan metodeilla kahdessa vaiheessa.
Ensiksi lisätään konkatenaatiopisteet ja tämän jälkeen annettu tulos muutetaan postfixiksi käyttämällä Shunting-Yard algoritmiä.

Kun annettu lauseke on muutettu postfix muotoon luetaan se nfa luokassa kerran ja muodostetaan jokaisesta merkistä 
oma nfaFragmentti. Kirjainfragmentteja luodessa lisätään kyseinen kirjain myös hashsettiin, jotta saadaan dfa luokalle listattua
kaikki mahdolliset kirjaimet ilman duplikaatteja.

Nfa:n lopputuloksena on yksi "kaari" minkä alkutilasta pääsee lopputilaan, joko käyttämällä kirjaimia siirtymään tilasta toiseen tai
käyttämällä "epsilon" siirtymää eli siirtyy tilasta toiseen käyttämättä merkkiä siihen.

Dfa luokassa muutetaan Nfan tilat dfatiloiksi. Dfatila sisältää yhden tai useamman nfatilan ja yhden tai useamman siirtymän seuraavan
tilaan, mutta siellä ei ole mahdollisuutta käyttää epsilon siirtymiä. Dfatilat luodaan rekursiivisesti etsiJaLiitaEpsilonit metodilla, mikä
etsii nfatiloista mahdolliset seuraavat tilat mihin pääsee käyttämättä siirtymiä.
Dfatilat talletetaan hashsettiin ja hashmappiin jotta olisi nopeaa tarkistaa onko tämmöinen dfatila jo olemassa ja siirtymien lisääminen
tilasta toiseen tapahtuisi nopeasti.

Viimeisenä vaiheena käydään annettu syöte läpi tarkistamalla onko kyseisestä dfatilasta tietyllä merkillä olemassa siirtymää seuraavan tilaan,
mikäli on muutetaan muuttujan currentState arvo vastaamaan dfatilaa mihin siirtymällä päästään ja mikäli ei ole olemassa siirtymää
muutetaan currentState arvo -1 ja palautetaan false. Jos syöte luetaan loppuun asti ja dfatilojen joukossa on Nfa luokasta saatu lopputila
palautetaan true.

Dfatila luokassa kaikki nfatilat mitkä kuuluvat kyseiseen dfatilaan talletetaan arraylistiin ja siirtymät dfatilasta toiseen dfatilaan 
talletetaan hashmappiin.

### Aikavaativuus

Nfa:n luomisessa (nfaFragmentit luokassa) luodaan uusia tiloja "*" , "+" ja "|" erikoismerkeille, 
mikäli saisi tämän vaiheen toimimaan ilman uusien tilojen luomista niin sovelluksella pystyisi tarkistamaan pidempiä lausekkeita tehokkaammin.

#### Lähteet

Eniten käytetyt lähteet (Tarkemmin löytyy määrittelydokumentista, mutta suurinosa on ensimmäisenä iltana vilkaistuja lähteitä)

* https://moodle.helsinki.fi/pluginfile.php/2620710/mod_resource/content/21/lama001-259.pdf
* https://swtch.com/~rsc/regexp/regexp1.html
* https://medium.com/@gregorycernera/converting-regular-expressions-to-postfix-notation-with-the-shunting-yard-algorithm-63d22ea1cf88




 
