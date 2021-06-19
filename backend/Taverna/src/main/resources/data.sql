
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


 insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Пуњена вешалица','Donec feugiat tellus at diam dignissim, sagittis vestibulum ligula semper. Nullam sagittis elit et mauris mattis, id imperdiet justo blandit. Nullam tempus auctor consectetur. Nam non ante libero.',3,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (750,'Карађорђева шницла','Mauris vulputate risus risus. Phasellus at erat sollicitudin, sollicitudin sapien convallis, suscipit quam. Curabitur mollis a felis in scelerisque. Suspendisse potenti.',3,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (350,'Мусака','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tincidunt faucibus justo facilisis fringilla. Etiam tellus purus, cursus in ligula et, lobortis accumsan orci.',4,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (250,'Лесковачки ћевапи','Phasellus varius finibus nisi sed semper. Donec quis facilisis metus. Suspendisse dictum metus a posuere fermentum. Suspendisse vel pellentesque nunc. Ut non justo sapien.',4,false);
 insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (500,'Пуњене паприке','Suspendisse quis hendrerit odio. Integer eu euismod velit. Fusce nec mi vitae justo varius pellentesque. Maecenas et leo nunc. Suspendisse sagittis nisi a libero rutrum, quis semper nulla dictum. Fusce volutpat aliquet vehicula.',5,false);
insert into artikal(cena,naziv,opis,prodavac_korisnik_id,obrisan) values (80,'Бурек','Sed vitae vulputate orci. Sed a facilisis sapien. Nulla facilisi. Phasellus ut pellentesque ante. Nulla facilisi. Cras eget lacinia felis, in fermentum nulla.м',5,false);

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