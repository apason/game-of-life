Käyttöohjeet
============

Ohjelman käyttöliittymässä on seuraavat ikkunat:
 * Pääikkuna: Itse simulaatio ja siihen liittyvät kontrollit löytyvät tästä ikkunasta
 * Options ikkuna: Täällä määritellään ohjelman asetukset
 * Filechooser ikkunat: Valitaan tiedosto tallennettavaksi tai kirjoitettavaksi. Intuitiivinen, ei edellytä ohjeistusta.
 * Colorchooser ikkuna: Täältä voi valita soluille värit. Intuitiivinen, ei edellytä ohjeistusta.
 * Erinäisiä viesti-ikkunoita: Korkeintaan kahden painikkeen ikkunoita (ok, cancel). Intuitiivisia, eivät edellytä ohjeistusta.

Pääikkuna
---------
Sääntöjen määrittämistä (ja tallennusta/lataamista) lukuunottamatta ohjelman suorituksessa on näkyvissä vain tämä ikkuna.
Ikkunalla on valikkopaneeli josta löytyvät kohdat file, edit, controls ja view.
file valikosta voidaan aloittaa uusi sessio valitsemalla new. Tämä painike tuhoaa edellisen session (kysyy varmistuksen käyttäjältä) ja aloittaa uuden.
Session lataus/tallennus tiedostosta/tiedostoon tapahtuu file alikon load- ja save as-painikkeista. 
Nämä painikkeet antavat graafisen tiedostoselaimen jolla tiedosto voidaan valita. Annettavan tiedostonimen perään 
lisätään pääte .dat, jos sitä ei siinä jo ole (katso tarkemmin javadoc).
Painike save tallentaa session samaan tiedostoon josta se aiemmin ladattiin. Jos sessiota ei ole ladattu, aiemmin,
toimii save, kuten save as. 
exit painike sulkee ohjelman.
edit valikon options kohdasta aukeaa uusi ikkuna, josta voi tehdä haluamansa asetukset. (Asetuksista lisää alempana.)
controls valikossa on kaikki simulaation kontrollointiin liittyvät toiminnot.
Painike next step siirtyy simulaation **seuraavaan** askeleeseen. (Vaikka asetuksista olisi määritelty iterations per step != 1)
Painike start aloittaa simulaation asetuksissa määrätyllä tavalla. Siis laskee asetusten määrittämän määrän simulaatioaskeleita,
odottaa asetuksien määräämän ajan ja piirtää uuden kuvan maailmasta.
Paikine stop pysäyttää simulaation
Painike randomize satunnaistaa maailman, niillä soluilla jotka kyseisessä sessiossa on määriteltynä.
clear painike tappaa kaikki solut maailmasta.
Valikossa view on vain toiminto view priorities. Jos tämä toiminto on valittuna (ruudussa näkyy tyhjä boksi tai ruksi sen päällä),
niin pääikkunan soluissa näkyy minkä prioriteetin soluja ne ovat. Kuolleissa soluissa lukee ".".
Solumaailmassa jokaiseen soluun liittyy myös toiminto. Solua painamalla esiin aukeaa valikko, josta voi määritellä solun tyypin.
Vaihtoehtoina on kaikki sessiossa sillähetkellä olevat solut ja kuollut solu. Tämä toiminto toimii vain jos simulaatio ei ole päällä.
Simulaation ollessa päällä se tulee ensin pysäyttää (controls -> stop) ennenkuin soluja voidaan manipuloida yksitellen. Hieman hämäävästi
valikko tulee kuitenkin näkyviin vaikka simulaatio olisikin päällä. Tällöin sillä ei kuitenkaan ole vaikutusta solun tilaan.


Options ikkuna
--------------
Optionsikkunassa on kaksi välilehteä: rules ja general. välilehdestä rules lisätään, muokataan ja poistetaan maailmaan liittyviä sääntöjä.
Rules välilehdessä näkyy myös kaikki sessiossa sillä hetkellä olevat säännöt. Ne näkyvät oikealla, edit ja remove painikkeiden alapuolella.
Jos sääntöjä ei ole, ei myöskään editin ja removen yläpuolella niitä näy.
Uusi sääntö lisätään täyttämällä sääntökentät asianmukaisesti. Birth contidions kenttään lisätään solun syntymäsäännöt pilkulla eroteltuina.
Death conditions kenttään lisätään kuolinsäännöt pilkulla erotettuna, ja priority kenttään laitetaan kyseisen solun prioriteetti.
Näiden lisäksi säännöille voi lisätä halutessaan värin. Väri valitaan color nappulasta aukeavasta värinvalitsinikkunasta.
Lisätietoa pelin säännöistä löytyy tiedostosta AiheenKuvausJaRakenne.md kohdasta säännöt. Kun sääntökentät on täytetty asianmukaisesti,
voidaan sääntö lisätä ohjelmaan painamalla kenttien alapuolella olevaa add painiketta. Tällöin uusi sääntö ilmestyy oikealle edit ja 
remove nappuloiden yläpuolelle. Säännön nimenä lukee "rule x", jossa x on kyseiseen sääntöön liittyvä prioriteetti. edit ja remove 
nappulat ovat käytössä vain silloin kun sessiolle on asetettu solu(ja) ja jokin solu on valittu. remove nappula poistaa valitun solun.
edit nappula poistaa myös valitun solun, mutta lisää solun sisältämät tiedot tekstikenttiin, jolloin niitä on helppo muuttaa. Muuttamisen
jälkeen pitää painaa add nappulaa, jotta sääntö lisätään takaisin ohjelmaan. Jos simuloitavassa maailmassa on soluja, joiden sääntöjä on 
muutettu, tuhoutuvat kyseiset solut. Poikkeuksena on kuitenkin solun väri. Sitä voi vaihtaa ilman että solut maailmassa tuhoutuvat.
Tämä johtuu siitä, että värejä ei määritellä teknisellä tasolla sääntöihin, vaan se on käyttöliittymän oma lisä sovelluslogiikalle.
Rakenteesta lisää tiedostossa AiheenKuvausJaRakenne.md. Painike ok sulkee options ikkunan.
Options ikkunan generals välilehdestä löytyy muut asetukset. Kentässä edge size määritellään solumaailman koko. Koska solumaailma on neliö,
on solujen lukumäärä edge sizen neliö. Maailman koon muuttaminen tuhoaa senhetkisen maailma (tappaa kaikki solut). Tästä ohjelma kysyy 
vielä varmennuksen asetuksia tallennettaessa. Kenttä time per step sisältää tiedon siitä, kauanko ohjelma odottaa jokaisen simulaatio-
askeleen välillä, ennenkuin laskee ja piirtää seuraavan. Arvo iterations per step määrittelee sen, montako askelta maailmaa pelataan
eteenpäin jokaisen piirtämisen välissä, pelin ollessa päällä (pääikkunan controls valikosta painettu start nappulaa). iterations per step
ja time per step arvojen muuttaminen ei vaikuta maailman soluihin kuten koon muuttaminen. Save nappula tallentaa muutetut säännöt.
ok nappula sulkee ikkunan. 

Katso myös ohjelman demovideo! Linkki löytyy tiedostosta demo.md
