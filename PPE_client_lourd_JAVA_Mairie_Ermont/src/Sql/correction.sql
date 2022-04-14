1. select nomlieumonument, count(nrosite)
from site
group by nomlieumonument;

2. create view vlistMon(lieu, nomroi)
as select nomlieumoment, group_concet(nomroi,'') as 'liste de roi'
from roi r, concerner c
where r.nroordre= c.nroordre 
group by nomlieumoment

create view vlist (lieu,nomroi)
as select nomlieumoment, nomroi
from roi r , concerner c 
where r.nroorde = c.nroordre;

3. drop function if exists nbRoi;
delimiter //
create function nbRoi (nd int)
returns int
begin dclare x, y int;
select count (*) into x from roi where nrodysnate=nd;

if x=0
then 
select 'existe pas'
else 
select count(*) into nbRoifrom roi where nrodynastie = nd
end if;
return(nb)
end//
delimiter ;


select *,nbroi(nrodynastie) from dynastie;


4)
select * from roi
where year() =xxxx or year ()=xxxx;


drop procedure if exists afficheANNEENAISSSANCE;
delimiter //
create procedure afficheANNEENAISSSANCE (anneM int, anneN int)
begin
declare x int;
select count(*) into x from roi 
where year(datenaissance)=anneE or year(datemort)=anneM;
if x=0
then select 'annee de naissance ou mort','anneN',' ','anneM','nexist pas';
else 
select * from roi
where year(datenaissance)=anneN or year(datemort)=anneM;
end if;
end //


4)
alter table roi add age int default 0;

drop trigger if exists calculage;
delimiter //
create trigger calculage
before insert on roi
for each row 
begin
set new.age=year(new.dateMORT) - year(new.dateNaissance);
ou 
datediff(ne.datemort, new.dateNaissance)/356.25;
end//
delimiter ;



5)
drop procedure if exists insdynasti;
delimiter //
create procedure insdynasti(nrod int,nomd varchar(50),datedeb int, fin int)
begin
insert into dysnastie values (nrod,nomd,datedeb,fin);
end// 
delimiter ;


drop procedure if exists updynasti;
delimiter //
create procedure updynasti(nrod int,nomd varchar(50),datedeb int, fin int)
begin
update dysnastie set .....=nrod, where nrodysnatie = nrod;
end// 
delimiter ;

drop procedure if exists deldynasti;
delimiter //
create procedure deldynasti(nrod int)
begin
delete dysnastie from nrodysnatie = nrod;
end// 
delimiter ;

