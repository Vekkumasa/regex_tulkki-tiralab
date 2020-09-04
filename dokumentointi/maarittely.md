Tietojenkäsittelytieteen kandidaatti (TKT)
Projekti toteutetaan Suomen kielellä. Ohjelmointikieli: Java

Kyseessä on säännöllisten lausekkeiden tulkki. Käyttäjä sijoittaa kenttään halutun säännöllisen lausekkeen,
tämän jälkeen käyttäjä antaa ohjelmalle halutun syötteen ja tulkki tarkistaa täsmääkö annettu syöte annettuun
säännölliseen lausekkeeseen.

Tarkoituksena on luoda annetusta säännöllisestä lausekkeesta NFA (epädeterministinen äärellinen automaatti, 
nondeterministic finite automaton) minkä avulla tarkistetaan vastaako annettu syöte käyttäjältä saatua
säännöllistä lauseketta. NFA käyttää apuna pino-tietorakennetta.

Ohjelma käy läpi ensin annetun m pituisen säännöllisen lausekkeen ja muodostaa automaatin, tämän jälkeen
käydään läpi n pituinen syöte automaatilla ja annetaan vastaus onko syöte hyväksytty vai hylätty joten
aikavaativuus on O(nm)

Lähteet:
* https://cs.stackexchange.com/questions/40819/how-to-create-dfa-from-regular-expression-without-using-nfa
* https://cstheory.stackexchange.com/questions/14939/what-algorithms-exist-for-construction-a-dfa-that-recognizes-the-language-descri/14946#14946
* https://www.researchgate.net/post/Is_there_any_algorithm_exist_to_convert_a_regular_expression_directly_into_DFA
* http://www.compsci.hunter.cuny.edu/~sweiss/course_materials/csci265/KleenesTheorem.pdf
* http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.304.2807&rep=rep1&type=pdf
* http://www.cs.man.ac.uk/~pjj/cs211/ho/node6.html
* https://moodle.helsinki.fi/pluginfile.php/2620710/mod_resource/content/21/lama001-259.pdf
* https://swtch.com/~rsc/regexp/regexp1.html
