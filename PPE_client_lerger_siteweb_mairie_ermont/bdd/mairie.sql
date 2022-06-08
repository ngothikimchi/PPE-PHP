drop database if exists mairieErmont;
create database mairieErmont; 
use mairieErmont;

--creattion les tables
create table role(
	idRole int(10) not null auto_increment,
	nomRole varchar(20) not null,
	primary key (idRole)
)engine=innodb, charset=utf8;

create table user(
	idUser int(10) not null auto_increment, 
	emailUser varchar(50) not null unique,
	mdpUser varchar(255) not null,
	idRoleUser int(10) default 1,
	primary key (idUser),
	foreign key (idRoleUser) references role(idRole)
)engine=innodb, charset=utf8;

create table citoyen(
	idCit int(10) not null auto_increment,
	nomCit varchar(30) not null, 
	prenomCit varchar(30) not null, 
	sexeCit enum("Masculin", "Feminin"),
	dateNaissCit date not null,
	lieuNaissCit varchar(20) not null,
	cpLieuNaissCit varchar(5) not null,
	adresseCit varchar(25) not null,
	villeCit varchar(50) not null,
	cpCit varchar(5) not null, 
	situationFamilialeCit enum("Celibataire", "Marie(e)", "Divorce(e)","Pacse(e)"),
	emailCit varchar(50) not null unique,
	question varchar(255),
	reponse varchar(255),
  	primary key (idCit)
)engine=innodb, charset=utf8;


create table service(
	idService int(3) not null auto_increment,
	nomService varchar(20) not null,
	primary key(idservice)
)engine=innodb, charset=utf8;


create table employe(
  	idEmploye int(10) not null auto_increment,
	nomEmploye varchar(25) not null,
	prenomEmploye varchar(25) not null, 
	emailEmploye varchar(50) not null unique,
	idServiceEmploye int(3) not null,
	primary key (idEmploye),
	foreign key (idServiceEmploye) references service(idService)
)engine=innodb, charset=utf8;


create table association(
	idAssoc int(10) not null auto_increment,
	nomAssoc varchar (50) not null,
	adresseAssoc varchar(120) not null,
	cpAssoc varchar (12) not null,
	villeAssoc varchar (20) not null,
	telAssoc varchar(12) not null,
	primary key (idAssoc)
)engine=innodb, charset=utf8;


create table type_evenement(
	codeTypeEve varchar(30) not null,
	nomtTypeEve varchar (255) not null,
	primary key (codetypeEve)
)engine=innodb, charset=utf8;


create table type_evenement_enfant(
	codeTypeEve varchar(30) not null,
	nomTypeEve varchar (255) not null,
	trancheAgeMin int(5),
	trancheAgeMax int(5),
	accompagnant boolean,
	primary key (codeTypeEve)
)engine=innodb, charset=utf8;


create table type_evenement_adulte(
	codeTypeEve varchar(30) not null,
	nomTypeEve varchar (255) not null,
	primary key (codeTypeEve)
)engine=innodb, charset=utf8;
    

create table evenement(
	idEve int(10) not null auto_increment, 
	nomEve varchar (100) not null,
	contenuEve varchar (255) not null,
	adresseEve varchar (50) not null,
	debutEve date not null, 
	finEve date not null,
	dateFinInscriptionEve date not null,
	nbParticipantMaxEve int(5),
	codeTypeEve varchar(30) not null,
	idAssocEve int(10) not null,
	primary key (idEve), 
	foreign key (codeTypeEve) references type_evenement(codeTypeEve),
	foreign key (idAssocEve) references association(idAssoc)
)engine=innodb, charset=utf8;


create table participer(
	idCit int(10) not null,
	idEve int(10) not null,
	dateDemande date not null,
	foreign key (idCit) references citoyen(idCit),
	foreign key (idEve) references evenement(idEve),
	primary key (idCit,idEve)
)engine=innodb, charset=utf8;


create table type_demande(
	idTypeDem int(2) not null auto_increment, 
	nomTypeDem varchar(100) not null, 
	etrePlurielDem boolean,
	primary key (idTypeDem)
)engine=innodb, charset=utf8;


create table demande_mono(
	idDemande int(6) not null auto_increment, 
	dateDemande date not null, 
	dateRep date, 
	etat enum("En cours de traitement","Demande acceptee","Demande refusee") default "En cours de traitement", 
	idCit int(10) not null,
	idTypeDem int(2) not null,
	idEmploye int(10),
	primary key (idDemande), 
	foreign key (idCit) references citoyen(idCit),
	foreign key (idTypeDem) references type_demande(idTypeDem),
	foreign key (idEmploye) references employe(idEmploye)
)engine=innodb, charset=utf8;


create table demande_pluriel(
	idDemande int(6) not null auto_increment, 
	dateDemande date not null,
	dateRep date,
	idCit1 int(10) not null,
	idCit2 int(10) not null,
	idEmploye int(10),
	idTypeDem int(2) not null,
	etat enum("En cours de traitement", "Demande acceptee", "Demande refusee") default "En cours de traitement", 
	primary key (idDemande,idCit1,idCit2,dateDemande),
	foreign key (idCit1) references citoyen(idCit),
	foreign key (idCit2) references citoyen(idCit),
	foreign key (idTypeDem) references type_demande(idTypeDem),
	foreign key (idEmploye) references employe(idEmploye)
)engine=innodb, charset=utf8;



--View demande_mono
create view gestion_demande_mono_view (idDemande,typeD,etat,idCit,nomCit,prenomCit,emailCit,dateDemande,
dateRep, traiteePar)
as
select d.idDemande, t.nomTypeDem, d.etat,c.idCit, c.nomCit, c.prenomCit,c.emailCit,d.dateDemande,d.dateRep,
CONCAT( e.nomEmploye,' ',e.prenomEmploye)
as traitee_par
from demande_mono d
inner join citoyen c
on c.idCit = d.idCit
inner join type_demande t
on t.idTypeDem = d.idTypeDem
left join employe e
on d.idEmploye = e.idEmploye ;


--View demande_pluriel
create view gestion_demande_pluriel_view (idDemande,typeDemande,etatDemande,idCit1,nomCit1,prenomCit1,emailCit1,idCit2,
nomCit2,prenomCit2,emailCit2,dateDemande,dateRep, traiteePar)
as
select d.idDemande, t.nomTypeDem, d.etat,d.idCit1,c1.nomCit,c1.prenomCit,c1.emailCit,
d.idCit2,c2.nomCit, c2.prenomCit,c2.emailCit,d.dateDemande,d.dateRep,
CONCAT( e.nomEmploye,' ',e.prenomEmploye) as traitee_par
from demande_pluriel d
inner join citoyen c1
on c1.idCit = d.idCit1
inner join citoyen c2
on c2.idCit = d.idCit2
inner join type_demande t
on t.idTypeDem = d.idTypeDem
left join employe e
on d.idEmploye = e.idEmploye ;


--view affichier les evenements avec assoiciation (jointure)
create view evenement_association_view (idEve,nomEve,contenu,adresse,debutEve,
finEve,dateFinInscription,nbParticipantMax,association)
as
select e.idEve,e.nomEve,e.contenuEve,e.adresseEve,e.debutEve,e.finEve,e.dateFinInscriptionEve,e.nbParticipantMaxEve,
a.nomAssoc
from evenement e inner join association a
on e.idAssocEve = a.idAssoc;


--view evenement_enfant
create view evenement_enfant_view as
select e.*,t.trancheAgeMin,t.trancheAgeMax,t.accompagnant from evenement e
inner join type_evenement_enfant t
on t.codeTypeEve = e.codeTypeEve;


--view participer_evenement_citoyen
create view participer_citoyen_evenement_view as
select p.idCit,c.nomCit,c.prenomCit,e.nomEve,p.dateDemande
from participer p inner join citoyen c
on c.idCit = p.idCit
inner join evenement e
on e.idEve = p.idEve;

--Trigger insert type_evenement_enfant
drop trigger if exists ajout_evenement_enfant_trigger ;
delimiter //
create trigger ajout_evenement_enfant_trigger
before insert on type_evenement_enfant
for each row
begin
declare s int;
declare a int ;
select count(*) into a
from type_evenement
where codeTypeEve=new.codeTypeEve ;
if a=0
 then
    insert into type_evenement values (new.codeTypeEve ,new.nomTypeEve);
end if ;
select count(*) into s
from type_evenement_adulte
where codeTypeEve=new.codeTypeEve ;
  if s > 0
    then
       signal sqlstate '45000'
       set message_text='Ce code a été déjà utilisé';
  end if ;
end //
delimiter ;

--Trigger insert type_evenementadult
drop trigger if exists ajout_evenement_adulte_trigger ;
delimiter //
create trigger ajout_evenement_adulte_trigger
before insert on type_evenement_adulte
for each row
begin
declare s int;
declare a int ;
select count(*) into a
from type_evenement
where codetypeEve=new.codetypeEve ;
if a=0
 then
    insert into type_evenement values (new.codetypeEve ,new.nomTypeEve);
end if ;
select count(*) into s
from type_evenement_enfant
where codetypeEve=new.codetypeEve ;
  if s > 0
    then
       signal sqlstate '45000'
       set message_text='Ce code a été déjà utilisé';
  end if ;
end //
delimiter ;


--trigger update Evenement type enfant
drop trigger if exists update_eve_enfant_trigger;
delimiter //
create trigger update_eve_enfant_trigger
 after update on type_evenement_enfant
 for each row
 begin
update type_evenement
	set nomtTypeEve = new.nomTypeEve
	    where codetypeEve =old.codetypeEve;
end //
delimiter ;


--trigger update Evenement type adulte
drop trigger if exists update_eve_adulte_trigger;
delimiter //
create trigger update_eve_adulte_trigger
after update on type_evenement_adulte
for each row
begin
	update type_evenement
	set nomtTypeEve = new.nomTypeEve
	    where codetypeEve =old.codetypeEve;
end //
delimiter ;

---trigger delete evenement type enfant
drop trigger if exists delete_evenement_enfant_trigger;
delimiter //
create trigger delete_evenement_enfant_trigger
 before delete on type_evenement_enfant
 for each row
 begin
 delete from type_evenement where codeTypeEve=old.codeTypeEve;
end //
delimiter ;

---trigger delete evenement type adulte
drop trigger if exists delete_evenement_adulte_trigger;
delimiter //
create trigger delete_evenement_adulte_trigger
 before delete on type_evenement_adulte
 for each row
 begin
 delete from type_evenement where codetypeEve=old.codetypeEve;
end //
delimiter ;


insert into role values (1,"user"),(2,"admin"),(3,"editor");

insert into user values (null,"a@gmail.com","123",1),(null,"b@gmail.com","123",1);
insert into user values (null,"a@ville-ermont.fr","123",2),(null,"b@ville-ermont.fr","123",2);
insert into user values (null,"c@gmail.com","123",1);
insert into user values (null,"d@gmail.com","123",1);


insert into service values (null,"administration"),(null,"communication");

insert into employe values (null,"Henry","Tom","a@ville-ermont.fr",1);
insert into employe values (null,"Alain","Nicolas","b@ville-ermont.fr",1);


insert into association values (null,"Art en Vie","30 all Chevaliers","95120","Ermont","0875963245"),
(null,"Ass des Fetes ","9 Place Monet","95120","Ermont","0687462736");

insert into type_demande values (null,"Acte de mariages",true),
(null,"Acte de naissance",false),(null,"Demande de passport",false),(null,"Pacs",true),(null,"Demande de divorce",true);
--test user participer evenement

insert into citoyen values
(null,"Helen","Jade","Feminin","2009-02-01","Paris","75004","19 place de la Grande Tour","Ermont","95120","Celibataire","a@gmail.com","profpref","Sarah");

insert into citoyen values(null,"Jack","Tom","Feminin","2008-02-01","Ermont","75008","2 allée des Hortensias","Ermont",
"95120","Celibataire","b@gmail.com","ecoleprimaire","Jeanmoulin");

insert into citoyen values
(null,"David","Gabirel","Masculin","1980-02-01","Paris","75006","12 rue des tulipes","Ermont","95120","Celibataire","c@gmail.com","nommere","Helen");

insert into citoyen values(null,"Louis","Gabirel","Masculin","2007-02-01","Ermont","95120","4 rue Marcel Pagnol", "Paris",
"78000","Celibataire","d@gmail.com","ecoleprimaire","Nacelle");

update user set mdpUser=sha1(mdpUser);


insert into type_evenement_enfant values ("en123","sport_ete",10,15,false);
insert into type_evenement_enfant values ("en124","musique_ete",10,18,false);
insert into type_evenement_enfant values ("en125","rencontre_sociale",9,18,false);


insert into type_evenement_adulte values ("ad123","concert de musique");
insert into type_evenement_adulte values ("ad125","theatre");


insert into evenement values (null,"Le plateau omnisports","Cet espace, ouvert a tout type de glisse, est en acces libre.
Chaque usager doit respecter le reglement affiche et etre equipe 
des protections corporelles individuelles minimum le protegeant en cas de chute ou de collision.",
"l’ecole primaire Guyonnerie","2022-07-15","2022-07-17","2022-07-15",200,"en123",1);
insert into evenement values (null,"Le terrain stabilise",
"Ce terrain peut accueillir un grand nombre d’entrainements et de rencontres amicales grace a la presence de deux vestiaires.","l’ecole primaire Guyonnerie",
"2022-07-15","2022-07-17","2022-07-15",200,"en123",1);

insert into evenement values (null,"Fete la musique",
"Rejoignez-nous Place Carree (rue de Stalingrad) pour une soiree pleine de rythme et l'ambiance dans laquelle des artistes vous invitent a decouvrir leurs univers.","l’ecole primaire Guyonnerie",
"2022-06-21","2022-06-21","2022-06-20",150,"ad123",1);

--table enregistrement Evenement
create table sentinelleEvenement as
select *,sysdate() dateEnregistrer,user() userEve,'__________' action
from evenement
where 2=0 ;
alter table sentinelleEvenement add primary key(idEve,dateEnregistrer);

--trigger supprimer table evenement
drop trigger if exists sentinelleEve_supp;
delimiter //
create trigger sentinelleEve_supp
before delete on evenement
for each row
begin
insert into sentinelleEvenement select *,sysdate(),user(),
'delete' from evenement where idEve=old.idEve;
end //
delimiter ;

--trigger modification table evenement
drop trigger if exists sentinellevol_modif;
delimiter //
create trigger sentinellevol_modif
before update on evenement
for each row
begin
insert into sentinelleEvenement select *,sysdate(),user(),
'update' from evenement where idEve=old.idEve;
end //
delimiter ;

--trigger insert table evenement
drop trigger if exists sentinelleEve_insert;
delimiter //
create trigger sentinelleEve_insert
after insert on evenement
for each row
begin
insert into sentinelleEvenement select *,sysdate(),user(),
'insert' from evenement where idEve=new.idEve;
end //
delimiter ;

--hachage question secrete et reponse
--update citoyen set question=sha1(question) where idCit in (1,2,3,4);
--update citoyen set reponse=sha1(reponse) where idCit in (1,2,3,4);

--trigger quand delete from citoyen
drop trigger if exists delete_citoyen;
delimiter //
create trigger delete_citoyen
after delete on citoyen
for each row
begin
delete from user where emailUser=old.emailCit;
end //
delimiter ;

create or replace view V_employe_user (iduser,idemploye,nom,prenom,email,service) as 
select u.iduser,e.idEmploye ,e.nomEmploye,e.prenomEmploye,u.emailuser,s.nomService
from user u, employe e, service s
where u.emailUser = e.emailEmploye 
and e.idServiceEmploye =s.idService 