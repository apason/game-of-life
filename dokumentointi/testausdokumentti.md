Vapaaehtoinen testausdokumentti
===============================

Testit ovat melko kattavat. Muutamia asioita en kuitenkaan saanut järkevästi testattua. Rivikattavuus jäi vajaaksi mm. 
metodilta, joka käsittelee satunnaislukuja (World luokan randomizeMap(). Myös Session luokan run metodi jäi yksikkötestien 
ulkopuolelle. Tämä johtuu siitä, että sen suorittamiseksi tarvitaan GUI luokan ilmentymä ja 2 säiettä. Näinollen en osannut
kyseistä metodia testata. Myöskään World luokan printWorld() metodia en osannut testata, sillä metodi ei muuta minkään olion 
tilaa eikä palauta mitään. Se vain tulostaa näytölle. Mutantteja pyrin myös tappamaan mahdollisimman paljon, mutta muutamissa 
asioissa tämä ei onnitunut. Esim. tiedoston käsittelyssä, mutantit jotka poistivat koodista close() funktion jäivät 
eloon. Tämä johtuu siitä, että sulkemisella tai sulkematta jättämisellä ei ole vaikutusta testattavien olioiden tilaan, eikä 
ne vaikuta metodien paluuarvoon. Samoin hashCode metodeista jäi mutantteja henkiin, sillä hashin voi laskea mielivaltaisella 
tavalla, kunhan se lasketaan kaikille luokan ilmentymille samoin. 

Käyttöliittymää ja Sessionin run() metodia testasin lähinnä kokeilemalla ohjelmaa, ja muutamilla välitulostuksilla. Näissä 
toiminnoissa ei kuitenkaan ollut merkittäviä ongelmia missään vaiheessa projektia. Viimeisin Sessionin run() metodiin 
liittyvä korjattu bugi, johtui siitä että käyttöliittymän randomize toiminto ei pysäyttänyt run() metodia ennen maailman 
satunnaitamista. Näinollen molemmat metodit saattoivat käsitellä solutaulukkoa samaan aikaan, aiheuttaen poikkeuksen run() 
metodissa. 

Ohjelmaan ei jäänyt yhtään tiedossa olevaa bugia. Jos käyttäjä tälläiseen törmää, otan tiedon mielelläni vastaan!
