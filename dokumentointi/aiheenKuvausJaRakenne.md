Aiheen kuvaus ja rakenne
========================

Aihe lyhyesti
-------------
 
Usean solutyypin soluautomaatti. Toteutetaan ohjelma, joka simuloi satunnaista tai itse valittua 
halutun kokoista maailmaa. Maailman säännöt (esim game of lifen säännöt) voidaan määrittää
ennen simulaation aloitusta. Simulaatiota voidaan suorittaa jatkuvasti tai eri pituisissa
ajanjaksoissa esim 5 askelta kerrallaan. Myös simulaatiomaaliman koko ja simulaation piirtonopeus
voidaan määritellä (Kuitenkin tiettyjen rajojen sisällä). 


Käyttäjät ja toiminnot
----------------------

### Käyttäjät ###
Ohjelmalla on vain yksi käyttäjä.

###Toiminnot###
* solujen säännöt
 * käyttäjä määrittää säännöt joilla soluista tulee eläviä tai kuolleita sekä solujen prioriteetit.
* muut säännöt
 * käyttäjä määrittää minkä kokoisessa maailmassa simulaatio tapahtuu, simulaationopeuden ja montako askelta simuloidaan kerallaan.
* simulaation aloitus
* simulaation lopetus
* seuraavan simulaatioaskelen tekeminen
* maailman satunnaistaminen
* maailman tyhjennys
* tietyn lopputuloksen tallentaminen ja lataaminen
* Prioriteettien piilottaminen solumaailmasta

Tekniset tiedot
---------------

### Rakenne ###

Ohjelmakoodi on jaettu seuraaviin pakkauksiin ja luokkiin:
 * main
  * Main
 * interfaces
  * Session
  * GUI
 * logic
  * Utilities
  * World
  * Cell
  * Rules
 * filehandling
  * Loader
  * Saver

Main luokalla ei ole muuta funktiota kuin käynnistää ohjelma GUI luokkaa käyttämällä.
GUI luokka on graafinen käyttöliittymä joka toimii rajapintana käyttäjän ja ohjelman välillä.
Session luokka taas toimii käyttöliittymänä GUIn ja muun ohjelman välillä. Vaikka GUI ja Session
ovat javan normaaleita luokkia, ne ovat käyttöliittymiä käyttäjän ja Sessionin ja Sessionin
ja sovelluslogiikan välillä. Tästä syystä pakkauksen nimi on interfaces. Kyse ei siis ole 
javan interface tyypistä. GUI tuntee Sessionin ja Session tuntee GUIn. GUI ei
kuitenkaan itse käytä sovelluslogiikan tai tiedostonkäsittelyn metodeita. Itse simulaatio
tapahtuu World luokassa ja sen tuntemissa ja käyttämissä luokissa Cell ja Rules. World
omistaa taulukollisen Cell olioita ja listan Rules olioita. Kyseinen Cell taulukko on
simuloitava ja piirrettävä maailma. Rules olioita sisältävässä listassa puolestaan on tieto
kaikista maailman Cell olioilla olevista Rules olioista. Vaikka tieto olisikin saatavissa
käymällä kaikki Cell oliot läpi, säästää listan pitäminen kuitenkin merkittävästi laskentatehoa.
Luokat Saver ja Loader hoitavat tiedostonkäsittelyn Sessionin pyynnöstä. Tarkemmat määrittelyt
rakenteesta löytyy javadocsista ja ohjelmakoodista.

### Säännöt ###

Ohjelma käsittelee kerrallaan vain yhtä solumaailmaa. Solumaailma n*n kokoinen neliö,
jossa n on kokonaisluku väliltä [2, 99]. Maailmassa on siis vähintään 4 ja enintään 9 801 solua. 

Yhdellä solulla (reunasoluja lukuunottamatta) on määritelty olevan 8 naapurisolua. Koska 
simulaatio tapahtuu kahdessa ulottuvuudessa, on naapuoisolut kyseisestä solusta katsottuna
ylhäällä (pohjoinen), alhaalla (etelä), oikealla (itä), vasemmalla (länsi), ja kulmittain
jokasuunnassa (koillinen, kaakko, lounas, luode). Reunoissa olevilla soluilla on vain 5 
naapurisolua ja kulmasoluilla 3 naapurisolua.

Solu on joko elävä tai kuollut. Kuolleet solut ovat kaikki samanlaisia, mutta elävällä solulla
on tyyppi (solun säännöt). Säännöt koostuvat kolmesta eri osasäännöstä:
 * Syntymäsäännöt: Tieto siitä, milloin kyseinen solutyyppi herää henkiin.
 * Kuolemasäännöt: Tieto siitä, milloin kyseinen solutyyppi kuolee.
 * prioriteetti: Solutyyppien kesken uniikki kokonaisluku [0, 12].

Syntymä- ja kuolemasäännöt ovat lista kokonaislukuja [0,96]. Sääntö edustaa sitä **naapurisolujen
prioriteettien summaa**, joka saa kyseisen solun eläväksi.

**Syntymä- ja kuolinsäännöt eivät voi olla ristiriidassa** , eivät siis voi sisältää samaa lukuarvoa.

Solun syntymisessä on myös seuraavat edellytykset:
 * **Solu ei voi syntyä, jos sillä ei ole vastaavaa solua viereisessä ruudussa.** Toisinsanoen,
vaikka simulaatiossa olisi solutyyppi, jonka prioriteetti on n, ja syntymäsääntö x ja
solumaailmasta löytyisi kuollut solu, jonka naapurien prioriteettien summa olisi x, mutta naapurina
ei ole yhtään solua prioriteetillä n, ei kyseiseen paikkaan synny prioriteetin n omaavaa solua.
 * Tilanne jossa tiettyyn paikkaan olisi sääntöjen mukaan mahdollista syntyä useampikin solu
(eli useammalla solulla on sama syntymäsääntö ja tälläiset solut ovat saman solun naapureita),
**syntyy prioriteetiltään suurin** solu.
 * **Syntyminen on säännöistä ensisijainen.** Toisinsanoen jos paikalla x,y oleva solu kuolee omien
sääntöjensä mukaan seuraavalla kierroksella, mutta jon(u/ki)n muun säännön perusteella siihen 
syntyy solu, niin solu **ensin kuolee, ja sitten syntyy uusi solu**. Näinollen seuraavalla 
kierroksella paikassa on elävä, ei kuollut solu.
 * **Suuremman prioriteetin omaava solu voi syntyä pienemmän prioriteetin omaavan solun päälle.**
Solun ei siis tarvitse olla kuollut, jotta siihen voisi syntyä uusi solu.

Koska prioriteetti on solutyypille uniikki, ja prioriteetit ovat rajattu arvoihin [0, 12], on
yhdessä simulaatiossa kerrallaan enintään 13 erilaista solua. (Prioriteetin 0 omaava solutyyppi
on hieman erikoinen, sillä se ei vaikuta muiden solujen syntymään/kuolemaan, mutta muut solut
vaikuttavat siihen.) Prioriteetin maksimiarvosta seuraa, että syntymä- ja kuolemasäännöt
rajoittuvat 97 vaihtoehtoon. 8*12=96

#### Esimerkkejä ####

##### Esim 1 #####
Tunnetun John Conwayn kehittämän game of life - soluautomaatin säännöt olisivat seuraavat:

Maailma, jossa **vain** seuraava solutyyppi:

 * Syntymäsäännöt: 3
 * Kuolemasäännöt: 0,1,4,5,6,7,8
 * Prioriteetti:   1

Tässä (poikkeus)tapauksessa (vain yksi solutyyppi maailmassa, prioriteetilla 1) syntymä- ja
kuolinsäännöt ovat siis naapurisolujen lukumäärä.

##### Esim 2 #####
Jos pelissä on syntymäsäännöllä
10 varustettu solu, jonka prioriteetti on 3 ja jonkin kuolleen solun naapurisolujen
prioriteettien summa on 10 (esim. 4 kuollutta solua, 3 solua prioriteeteillä 2, ja yksi
prioriteetillä 4),  niin kyseisen kuolleen solun paikalle syntyy seuraavalle kierrokselle solu
prioriteetillä 3. Vastaavasti, jos kyseiseisellä solulla on kuolinsäännöissä luku 5 ja 
maailmasta löytyy kyseinen elävä solu, jonka naapurisolujen prioriteettien summa on 5, kuolee
kyseinen solu seuraavalle kierrokselle. 

##### Esim 3 #####
Simuloidaan muutama askel seuraavaa 3*3 kokoista maailmaa:

Solutyyppi 1:
 * Syntyy: 1,2
 * Kuolee: 4,5,6,7,8,9,10,12,14,16,18,20,22,24
 * Prioriteetti: 1

Solutyyppi 2:
 * Syntyy: 2,5
 * Kuolee: 0,1,3,4,6,7,8,9,10,11,12,13,14,15,16,20,21,22,23,24
 * prioriteetti: 2

Solutyyppi 3:
 * Syntyy: 8
 * Kuolee: 0,1,3,4,5,6,9,10,11,12,13,15,17,18,19,20,21,22,23,24
 * prioriteetti: 3


   |   | **1** |   |   |   | **2** |   |   |   | **3** |   |   |   | **4**   |   |   |   |
 --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---
 3 | 2 | 0 |   | 2 | 0 | 0 |   | 2 | 0 | 2 |   | 0 | 2 | 1 |   
 0 | 0 | 1 | **->** | 2 | 0 | 2 | **->** | 0 | 0 | 1 | **->** | 0 | 0 | 2 | **->**
 0 | 0 | 0 |   | 0 | 1 | 1 |   | 0 | 2 | 1 |   | 2 | 1 | 1 |     

   | **5** |   |   |   | **6** |   |   |   | **7** |   |   |   | **8** |   |   |
 --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---
 2 | 0 | 0 |   | 2 | 0 | 2 |   | 2 | 0 | 0 |   | 2 | 0 | 2 |   
 2 | 0 | 2 | **->** | 2 | 0 | 0 | **->** | 2 | 0 | 2 | **->** | 0 | 0 | 2 | **->**   
 1 | 2 | 1 |   | 0 | 0 | 0 |   | 2 | 2 | 0 |   | 0 | 0 | 0 |         

   |  **9** |   |   |   | **10** |   |   |   | **11** |   |   
 --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- 
 0 | 0 | 2 |   | 2 | 0 | 2 |   | 2 | 0 | 0 |   | 
 2 | 0 | 2 | **->** | 2 | 0 | 0 | **->** | 2 | 0 | 2 |  
 0 | 2 | 2 |   | 0 | 0 | 0 |   | 2 | 2 | 0 |   | 

Solutyypit 3 ja 1 siis kuolevat maailmasta ja solutyyppi 2 jää oskilloimaan vaiheisiin 7-10.
