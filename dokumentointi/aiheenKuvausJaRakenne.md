Aihe: Game of life. Toteutetaan ohjelma, joka simuloi satunnaista tai itse valittua halutun kokoista maailmaa.
Maailman säännöt (esim game of lifen säännöt) voidaan määrittää ennen simulaation aloitusta. Simulaatiota
voidaan suorittaa jatkuvasti tai eri pituisissa ajanjaksoissa esim 5 askelta kerrallaan. Myös simulaatiomaaliman
koko ja simulaation piirtonopeus voidaan määritellä. Mahdollisesti voidaan ottaa vielä ottaa asetus, joka
määrittää viimeisen simulaatioaskelen. (esim. peliä lasketaan eteenpäni vain 10,000 askelta)

Peli toimii joko niin, että maailma on yhtenäinen tai niin että se on rajattu. Yhtenäisellä maailmalla tarkoittaen
sitä, että jokaisella ruudulla on 8 naapuria. (jos ruutu on viimeisellä rivillä niin sillä on 3 naapuria
ensimmäisellä rivillä. Tällöin game of lifen "kulkija" hyppäisi toiselle puolelle ruutua, törmättyään seinään)
Rajattu maailma olisi vastaavasti sellainen jolla "reunimmaisilla" soluilla olisi vähemmän naapureita.
(esim game of lifen "kulkija" tuhoutuu törmätessään seinään). Tämän ominaisuuden toteutusta ei ole vielä
päätetty. Mahdollisesti molemmat vaihtoehdot tulevat mukaan lopulliseen ohjelmaan, niin että käyttäjä saa
valita kummalla tavalla maailma toimii.

Käyttäjät: Ohjelmalla on vain yksi käyttäjä.

Käyttäjän toiminnot:

* sääntöjen määrittely
 * käyttäjä määrittää säännöt joilla solusta tulee elävä tai kuollut ja joillo solu pysyy elävänä tai kuolleena
* maailman koko
 * käyttäjä määrittää minkä kokoisessa maailmassa simulaatio tapahtuu
* simulaation nopeus
 * käyttäjä määrittää jokaisen simulaatioaskeleen ajallisen pituuden (esim 0.5 sekuntia tai 1 sekunti)
* simulaation aloitus
* simulaation lopetus
* mahdollisesti tietyn lopputuloksen tallentaminen ja lataaminen
