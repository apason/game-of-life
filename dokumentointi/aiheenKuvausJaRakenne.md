Aiheen kuvaus ja rakenne
========================

Aihe lyhyesti
-------------
 
Usean solutyypin soluautomaatti. Toteutetaan ohjelma, joka simuloi satunnaista tai itse valittua 
halutun kokoista maailmaa. Maailman säännöt (esim game of lifen säännöt) voidaan määrittää
ennen simulaation aloitusta. Simulaatiota voidaan suorittaa jatkuvasti tai eri pituisissa
ajanjaksoissa esim 5 askelta kerrallaan. Myös simulaatiomaaliman koko ja simulaation piirtonopeus
voidaan määritellä.

Käyttäjät ja toiminnot
----------------------

### Käyttäjät ###
Ohjelmalla on vain yksi käyttäjä.

###Toiminnot###
* solujen säännöt
 * käyttäjä määrittää säännöt joilla solusta tulee elävä tai kuollut ja jolloin solu pysyy elävänä tai kuolleena
* muut säännöt
 * käyttäjä määrittää minkä kokoisessa maailmassa simulaatio tapahtuu, simulaationopeuden yms.
* simulaation aloitus
* simulaation lopetus
* tietyn lopputuloksen tallentaminen ja lataaminen

Tekniset tiedot
---------------

### Rakenne ###

### Säännöt ###

Ohjelma käsittelee kerrallaan vain yhtä solumaailmaa. Solumaailma n*n kokoinen neliö,
jossa n on kokonaisluku väliltä [2, 99]. Maailmassa on siis vähintään 4 ja enintään 9 801 solua. 

Yhdellä solulla (reunasoluja lukuunottamatta) on määritelty olevan 8 naapurisolua. Koska 
simulaatio tapahtuu kahdessa ulottuvuudessa, on naapuoisolut kyseisestä solusta katsottuna
ylhäällä (pohjoinen), alhaalla (etelä), oikealla (itä), vasemmalla (länsi), ja kulmittain
jokasuunnassa (koillinen, kaakko, lounas, luode). Reunoissa olevilla soluilla on vain 5 
naapurisolua ja kulmasoluilla 3 naapurisolua.

Solu on joko elävä tai kuollut. Kuollet solut ovat kaikki samanlaisia, mutta elävällä solulla
on tyyppi (solun säännöt). Säännöt koostuvat kolmesta eri osasäännöstä:
 * Syntymäsäännöt: Tieto siitä, milloin kyseinen solutyyppi herää henkiin.
 * Kuolemasäännöt: Tieto siitä, milloin kyseinen solutyyppi kuolee.
 * prioriteetti: Solutyyppien kesken uniikki kokonaisluku [0, 12].

Syntymä- ja kuolemasäännöt ovat lista kokonaislukuja [0,99]. Sääntö edustaa sitä **naapurisolujen
prioriteettien summaa**, joka saa kyseisen solun eläväksi. Näinollen tunnetun John Conwayn
kehittämän game of life - soluautomaatin säännöt olisivat seuraavat:
 * Syntymäsäännöt: 3
 * Kuolemasäännöt: 0,1,4,5,6,7,8
 * Prioriteetti:   1
Tässä (poikkeus)tapauksessa (vain yksisolutyyppi maailmassa, prioriteetilla 1) syntymä- ja
kuolinsäännöt ovat siis naapurisolujen lukumäärä. Toisinsanoen jos pelissä on syntymäsäännöllä
10 varustettu solu, jonka prioriteetti on 3 ja jonkin kuolleen solun naapurisolujen
prioriteettien summa on 10 (esim. 4 kuollutta solua, 3 solua prioriteeteillä 2, ja yksi
prioriteetillä 4),  niin kyseisen kuolleen solun paikalle syntyy seuraavalle kierrokselle solu
prioriteetillä 3. Vastaavasti, jos kyseiseisellä solulla on kuolinsäännöissä luku 5 ja 
maailmasta löytyy kyseinen elävä solu, jonka naapurisolujen prioriteettien summa on 5, kuolee
kyseinen solu seuraavalle kierrokselle. ** Syntymä- ja kuolinsäännöt eivät voi olla
ristiriidassa. ** eivät siis voi sisältää samaa lukuarvoa.

Koska prioriteetti on solutyypille uniikki, ja prioriteetit ovat rajattu arvoihin [0, 12], on
yhdessä kerrallaan enintään 13 erilaista solua.

