### Käyttöohje

Sovelluksesta on ladattavissa github release ja ohjelman saa käynnistettyä komennolla

java -jar regex-tulkki.jar

Sovelluksessa on kaksi tekstikenttää. Ylempänä olevaan tekstikenttään sijoitetaan haluttu säännöllinen lauseke ja alempaan
kenttään sijoitetaan syöte mitä halutaan testata ja painetaan "tarkista syöte" painiketta.
Jos syöte on ok tulee vihreällä teksti: "Hyväksytty syöte" ja mikäli syöte ei ole ok tulee punaisella teksti "Hylätty syöte"

Ohjelma hyväksyy vain seuraavia merkkejä:

Sallitut merkit: a-z , A-Z, 0-9
Sallitut erikoismerit: * , + , | ja ()

Jos lausekkeessa on käytetty muita merkkejä tulee käyttäjälle virheilmoitus: "Sallitut merkit ovat: A-Z , a-z, 0-9, * , + , ( , ) ja |"

