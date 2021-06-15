
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Petar','petarp','Petrovic','KUPAC','$2y$12$8d0gEZmTpz6tIm4WU6slm.79IjDa7Z8QLk2VmXUQYp0i67S5NThV.','Багљаш, Зрењанин');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Jovan','jovanj','Jovanovic','KUPAC','$2y$12$99.CtVgYZce/73Rt0CkrrOVfnE/8TdfqDk19HmAbJk6jxOP5EbCOO','Zarka Zrenjanina 42a Zrenjanin');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Milan','milan','Milanic','PRODAVAC','$2y$12$t2Qfd5lK.lHW.J8IhYSOT.Zf5TknWB/6620JAcRksxvahHrTmC9Ae','Сечањ, Вожда Карађорђа');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Mitar','mitarm','Mitrovic','PRODAVAC','$2y$12$9E2MngEait79bsgpEolQhO/4JyGrB2CGOE1TBEz3Ibi6/d1iYslfK ','Леснина, Зрењанин');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Janko','janko','Jankovic','PRODAVAC','$2y$12$848XfTXhx5Z9U5.L3cZDEu/nyLVrKHXr5CNqiJblY6MUtwkd8az9a','Mileticeva 21 Zrenjanin');
INSERT INTO KORISNIK(blokiran,ime,korisnicko,prezime,roles,sifra,adresa) VALUES (0,'Ado','adminat','Adminic','ADMIN','$2y$12$Sn9YDcViRVs0X8izUlmOyeu/dwnc/5K74SbWhToLT8S3WwZoS.Yry','Beograd');

INSERT INTO KUPAC(korisnik_id) VALUES (1);
INSERT INTO KUPAC(korisnik_id) VALUES (2);

INSERT INTO PRODAVAC(korisnik_id,naziv, posluje_od,imejl) VALUES (3,'КЛОПА КОД МИЛАНА','1993-10-10','milan@gmail.com');
INSERT INTO PRODAVAC(korisnik_id,naziv, posluje_od,imejl) VALUES (4,'РОШТИЉ 24h','2012-05-10','mitrovic@gmail.com');
INSERT INTO PRODAVAC(korisnik_id,naziv, posluje_od,imejl) VALUES (5,'Ресторан Галија','2014-05-10','jankovic@gmail.com');


 insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Пуњена вешалица','Сочна и пуна сира',3,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (750,'Карађорђева шницла','600г најукуснија и најбоља',3,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (350,'Мусака','Најбоље из Србија',4,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (250,'Лесковачки ћевапи','Печени на јаком жару',4,false);
 insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Пуњене паприке','Од паприка са наших њива',5,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (80,'Бурек','Бурек са домаћим сиром и јогуртом',5,false);

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



  insert into akcija(do_kad,od_kad,procenat,tekst,prodavac_korisnik_id) values ('2021-05-10','2021-07-10',20,'Majski popust',1);
  insert into artikli_akcije(akcija_id,artikal_id) values (1,1);