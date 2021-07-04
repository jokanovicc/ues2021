
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Petar','petarp','Petrovic','KUPAC','$2y$12$NBbapzmuJ51yZfY7YDcjc.EVGgiSa8QQROlbhn8jSrSp57BqNXvmq','Багљаш, Зрењанин');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Jovan','jovanj','Jovanovic','KUPAC','$2y$12$4/Un/5KB8khlSkeBQ3M8WulnbpleV2dqmRBRMyfhWHSdEj89zjnqe','Zarka Zrenjanina 42a Zrenjanin');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Milan','milan','Milanic','PRODAVAC','$2y$12$p.LEypYqiJw2TOnB3fHQH.LlbYxykTaMD/9cfxjPJ50//eUo8z5am','Сечањ, Вожда Карађорђа');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Janko','janko','Jovanovic','PRODAVAC','$2y$12$Gzr4ylnTWwZ2enY.AbVTbO78wS6bf8OIdC5VA7qTSCcw.vjXvaHwO','Mileticeva 21 Zrenjanin');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Ado','adminat','Adminic','ADMIN','$2y$12$gTVzo.W/QhBe6Hn4mXeVwuuXdHSjvQsoU5fQGOZ8w9gZ1xLHeBiLK','Beograd');

INSERT INTO KUPAC(korisnik_id) VALUES (1);
INSERT INTO KUPAC(korisnik_id) VALUES (2);

INSERT INTO PRODAVAC(korisnik_id,naziv, posluje_od,imejl) VALUES (3,'КЛОПА КОД МИЛАНА','2020-10-10','milan@gmail.com');
INSERT INTO PRODAVAC(korisnik_id,naziv, posluje_od,imejl) VALUES (4,'Ресторан Галија','2014-05-10','jovanovic@gmail.com');


insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Пуњена вешалица','Donec feugiat tellus at diam dignissim, sagittis vestibulum ligula semper. Nullam sagittis elit et mauris mattis, id imperdiet justo blandit. Nullam tempus auctor consectetur. Nam non ante libero.',3,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (750,'Карађорђева шницла','Mauris vulputate risus risus. Phasellus at erat sollicitudin, sollicitudin sapien convallis, suscipit quam. Curabitur mollis a felis in scelerisque. Suspendisse potenti.',3,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Пуњене паприке','Suspendisse quis hendrerit odio. Integer eu euismod velit. Fusce nec mi vitae justo varius pellentesque. Maecenas et leo nunc. Suspendisse sagittis nisi a libero rutrum, quis semper nulla dictum. Fusce volutpat aliquet vehicula.',4,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (280,'Бечка шницла','Sed vitae vulputate orci. Sed a facilisis sapien. Nulla facilisi. Phasellus ut pellentesque ante. Nulla facilisi. Cras eget lacinia felis, in fermentum nulla.м',4,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Мусака','Sed vitae vulputate orci. Sed a facilisis sapien. Nulla facilisi. Phasellus ut pellentesque ante. Nulla facilisi.',3,false);



insert into porudzbina(id,dostavljeno,satnica,kupac_korisnik_id,anoniman_komentar,arhiviran_komentar,komentar,ocena) values (1,true,'2021-05-01',1,false,false,'У суштини јако добар ресторан, мала замерка јелте, мало више прилога следеће пут молим вас лепо!',4);
insert into porudzbina(id,dostavljeno,satnica,kupac_korisnik_id,anoniman_komentar,arhiviran_komentar,komentar,ocena) values (2,true,'2021-05-01',2,true,false,'Jako dobro odradjeno iskreno',5);
insert into porudzbina(id,dostavljeno,satnica,kupac_korisnik_id,anoniman_komentar,arhiviran_komentar) values (3,true,'2021-06-10',2,false,false);
insert into porudzbina(id,dostavljeno,satnica,kupac_korisnik_id,anoniman_komentar,arhiviran_komentar) values (4,false,'2021-06-10',2,false,false);



  insert into stavka(kolicina,artikal_id,porudzbina_id) values (10,1,1);
  insert into stavka(kolicina,artikal_id,porudzbina_id) values (5,2,1);
  insert into stavka(kolicina,artikal_id,porudzbina_id) values (5,2,2);
  insert into stavka(kolicina,artikal_id,porudzbina_id) values (5,2,3);
  insert into stavka(kolicina,artikal_id,porudzbina_id) values (3,1,3);
  insert into stavka(kolicina,artikal_id,porudzbina_id) values (3,1,4);



insert into akcija(do_kad,od_kad,procenat,tekst,prodavac_korisnik_id) values ('2021-08-20','2021-06-22',30,'Akcija',3);
insert into artikli_akcije(akcija_id,artikal_id) values (1,1);
insert into artikli_akcije(akcija_id,artikal_id) values (1,2);


insert into akcija(do_kad,od_kad,procenat,tekst,prodavac_korisnik_id) values ('2021-10-20','2021-06-25',30,'Akcija 2',3);
insert into artikli_akcije(akcija_id,artikal_id) values (2,1);
insert into artikli_akcije(akcija_id,artikal_id) values (2,2);
